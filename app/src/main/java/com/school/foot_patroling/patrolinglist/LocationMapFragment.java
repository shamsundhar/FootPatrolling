package com.school.foot_patroling.patrolinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.ui.IconGenerator;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.register.model.OheLocationDto;
import com.school.foot_patroling.utils.GeoLocation;
import com.school.foot_patroling.utils.MapWrapperLayout;
import com.school.foot_patroling.utils.OnInfoWindowElemTouchListener;

import java.util.List;

public class LocationMapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    private ViewGroup infoWindow;
    private TextView infoTitle;
    private TextView infoAddress;
    private Button infoButton;
    private OnInfoWindowElemTouchListener infoButtonListener;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    public static String TAG = "LocationMapFragment";
    public static LocationMapFragment newInstance() {
        LocationMapFragment fragment = new LocationMapFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_locationmap, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        final MapWrapperLayout mapWrapperLayout = (MapWrapperLayout)rootView.findViewById(R.id.map_relative_layout);

        mMapView.onCreate(savedInstanceState);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);
                // MapWrapperLayout initialization
                // 39 - default marker height
                // 20 - offset between the default InfoWindow bottom edge and it's content bottom edge
                mapWrapperLayout.init(mMap, getPixelsFromDp(getActivity(), 39 + 20));

                LocationMapFragment.this.infoWindow = (ViewGroup) getActivity().getLayoutInflater()
                        .inflate(R.layout.map_info_window, null);
                LocationMapFragment.this.infoTitle = (TextView) infoWindow.findViewById(R.id.title);
                LocationMapFragment.this.infoAddress = (TextView) infoWindow.findViewById(R.id.address);
                LocationMapFragment.this.infoButton = (Button)infoWindow.findViewById(R.id.button);

                LocationMapFragment.this.infoButtonListener = new OnInfoWindowElemTouchListener(infoButton,
                        getResources().getDrawable(R.drawable.round_but_green_sel), //btn_default_normal_holo_light
                        getResources().getDrawable(R.drawable.round_but_red_sel)) //btn_default_pressed_holo_light
                {
                    @Override
                    protected void onClickConfirmed(View v, Marker marker) {
                        // Here we can perform some action triggered after clicking the button
                       // Toast.makeText(getActivity(), marker.getTitle() + "'s button clicked!", Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("location", marker.getTitle());
                        getActivity().setResult(Activity.RESULT_OK, resultIntent);
                        getActivity().finish();
                    }
                };
                LocationMapFragment.this.infoButton.setOnTouchListener(infoButtonListener);

                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        try {
                            Log.i(TAG, "getInfoContents");
                            // reference for current selected marker
                          //  markerShowingInfoWindow = marker;
                            // Setting up the infoWindow with current's marker info
                            infoTitle.setText(marker.getTitle());
                            infoAddress.setText(marker.getSnippet());
                            infoButtonListener.setMarker(marker);

                            // We must call this to set the current marker and infoWindow references
                            // to the MapWrapperLayout
                            mapWrapperLayout.setMarkerWithInfoWindow(marker, infoWindow);

                        } catch (Exception e) {
                            Log.e(TAG, "exception in getInfoContents" + e.toString());
                        }

                        return infoWindow;
                    }

                });

                getDeviceLocation();

                // For zooming automatically to the location of the marker
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private void getDeviceLocation() {
        try {
           // if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            Location location = task.getResult();
                            LatLng currentLatLng = new LatLng(location.getLatitude(),
                                    location.getLongitude());
                            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLatLng,
                                    16F);
                            googleMap.animateCamera(update);

                            //get nearby markers list

                            PointF center = new PointF((float)currentLatLng.latitude, (float)currentLatLng.longitude);
                          //  PointF center = new PointF(13.006827F, 79.439063F);
                            final double mult = 1; // mult = 1.1; is more reliable
                            final double radius = 100;
                            PointF p1 = calculateDerivedPosition(center, mult * radius, 0);
                            PointF p2 = calculateDerivedPosition(center, mult * radius, 90);
                            PointF p3 = calculateDerivedPosition(center, mult * radius, 180);
                            PointF p4 = calculateDerivedPosition(center, mult * radius, 270);

                            List<OheLocationDto> markerLocations =  NavigationDrawerActivity.mFPDatabase.oheLocationDtoDao()
                                    .getOheLocationsInRadius(
                                            String.valueOf(p3.x),
                                            String.valueOf(p1.x),
                                            String.valueOf(p2.y),
                                            String.valueOf(p4.y)
                                    );

                            Log.i("near by locations::", "near by locations::"+markerLocations.size());

                            IconGenerator iconGen = new IconGenerator(getActivity());

                            for(OheLocationDto locationDto: markerLocations){
                                MarkerOptions markerOptions = new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromBitmap(iconGen.makeIcon(locationDto.getOheMast()+"")))
                                        .title(locationDto.getOheMast()+"")
                                        .snippet(locationDto.getOheMast()+"")
                                        .position(new LatLng(Double.parseDouble(locationDto.latitude),
                                                Double.parseDouble(locationDto.longitude)))
                                        .anchor(iconGen.getAnchorU(), iconGen.getAnchorV());
                                googleMap.addMarker(markerOptions);
                            }

                          //  googleMap.setOnMarkerClickListener(LocationMapFragment.this);
                        }
                    }
                });
          //  }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    /* Calculates the end-point from a given source at a given range (meters)
* and bearing (degrees). This methods uses simple geometry equations to
* calculate the end-point.
*
        * @param point
*           Point of origin
* @param range
*           Range in meters
* @param bearing
*           Bearing in degrees
* @return End-point from the source given the desired range and bearing.
            */
    public static PointF calculateDerivedPosition(PointF point,
                                                  double range, double bearing)
    {
        double EarthRadius = 6371000; // m

        double latA = Math.toRadians(point.x);
        double lonA = Math.toRadians(point.y);
        double angularDistance = range / EarthRadius;
        double trueCourse = Math.toRadians(bearing);

        double lat = Math.asin(
                Math.sin(latA) * Math.cos(angularDistance) +
                        Math.cos(latA) * Math.sin(angularDistance)
                                * Math.cos(trueCourse));

        double dlon = Math.atan2(
                Math.sin(trueCourse) * Math.sin(angularDistance)
                        * Math.cos(latA),
                Math.cos(angularDistance) - Math.sin(latA) * Math.sin(lat));

        double lon = ((lonA + dlon + Math.PI) % (Math.PI * 2)) - Math.PI;

        lat = Math.toDegrees(lat);
        lon = Math.toDegrees(lon);

        PointF newPoint = new PointF((float) lat, (float) lon);

        return newPoint;

    }

    public static int getPixelsFromDp(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }
}