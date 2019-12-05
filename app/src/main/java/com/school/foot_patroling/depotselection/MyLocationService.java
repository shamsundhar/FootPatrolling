//package com.school.foot_patroling.depotselection;
//
//import android.Manifest;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.os.IBinder;
//import androidx.core.app.ActivityCompat;
//import android.util.Log;
//
//import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
//import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
//import com.school.foot_patroling.NavigationDrawerActivity;
//import com.school.foot_patroling.R;
//import com.school.foot_patroling.register.model.FpMovementDto;
//import com.school.foot_patroling.utils.DateTimeUtils;
//import com.school.foot_patroling.utils.PreferenceHelper;
//
//import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_IMEI;
//import static com.school.foot_patroling.utils.Constants.PREF_KEY_FP_STARTED_TIME;
//
//public class MyLocationService extends Service {
//    private static final String TAG = "MyLocationService";
//    private LocationManager mLocationManager = null;
//    private static final int LOCATION_INTERVAL = 100;
//    private static final float LOCATION_DISTANCE = 1f;
//    PreferenceHelper preferenceHelper;
//
//    private class LocationListener implements android.location.LocationListener {
//        Location mLastLocation;
//
//        public LocationListener(String provider) {
//            Log.e(TAG, "LocationListener " + provider);
//            mLastLocation = new Location(provider);
//        }
//
//        @Override
//        public void onLocationChanged(Location location) {
//            Log.i(TAG, "onLocationChanged: " + location);
//            mLastLocation.set(location);
//            String currentTimeStamp = DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S");
//            String selectedImei = preferenceHelper.getString(getApplication(), BUNDLE_KEY_SELECTED_IMEI, "");
//            String FPStartedTime = preferenceHelper.getString(getApplication(), PREF_KEY_FP_STARTED_TIME, "");
//
//            FpMovementDto movementDto = new FpMovementDto();
//            movementDto.setLatitude(location.getLatitude()+"");
//            movementDto.setLongitude(location.getLongitude()+"");
//            movementDto.setCreatedStamp(currentTimeStamp);
//            movementDto.setDeviceId(selectedImei);
//            movementDto.setDeviceSeqId(FPStartedTime);
//            movementDto.setSeqId(currentTimeStamp);
//
//            NavigationDrawerActivity.mFPDatabase.movementDao().insert(movementDto);
//            Log.i("movement count::", NavigationDrawerActivity.mFPDatabase.movementDao().getCount()+"");
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//            Log.e(TAG, "onProviderDisabled: " + provider);
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//            Log.e(TAG, "onProviderEnabled: " + provider);
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            Log.e(TAG, "onStatusChanged: " + provider);
//        }
//    }
//
//    /*
//    LocationListener[] mLocationListeners = new LocationListener[]{
//            new LocationListener(LocationManager.GPS_PROVIDER),
//            new LocationListener(LocationManager.NETWORK_PROVIDER)
//    };
//    */
//
//    LocationListener[] mLocationListeners = new LocationListener[]{
//            new LocationListener(LocationManager.PASSIVE_PROVIDER)
//    };
//
//    @Override
//    public IBinder onBind(Intent arg0) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.e(TAG, "service onStartCommand");
//        super.onStartCommand(intent, flags, startId);
//        return START_STICKY;
//    }
//
//    @Override
//    public void onCreate() {
//
//        Log.e(TAG, "service onCreate");
//        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
//        initializeLocationManager();
//
//        try {
//            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                    ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//
//
//                mLocationManager.requestLocationUpdates(
//                        LocationManager.PASSIVE_PROVIDER,
//                        LOCATION_INTERVAL,
//                        LOCATION_DISTANCE,
//                        mLocationListeners[0]
//                );
//            }
//            else{
////requestPermission();
//            }
//        } catch (java.lang.SecurityException ex) {
//            Log.i(TAG, "fail to request location update, ignore", ex);
//        } catch (IllegalArgumentException ex) {
//            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
//        }
//
//        /*try {
//            mLocationManager.requestLocationUpdates(
//                    LocationManager.GPS_PROVIDER,
//                    LOCATION_INTERVAL,
//                    LOCATION_DISTANCE,
//                    mLocationListeners[1]
//            );
//        } catch (java.lang.SecurityException ex) {
//            Log.i(TAG, "fail to request location update, ignore", ex);
//        } catch (IllegalArgumentException ex) {
//            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
//        }*/
//    }
//    private void requestPermission(){
//        MultiplePermissionsListener dialogMultiplePermissionsListener =
//                DialogOnAnyDeniedMultiplePermissionsListener.Builder
//                        .withContext(getApplicationContext())
//                        .withTitle("Camera & Storage permission")
//                        .withMessage("Both camera and storage permission are needed to store pictures")
//                        .withButtonText(android.R.string.ok)
//                        .withIcon(R.drawable.ic_launcher)
//                        .build();
//
//    }
//    @Override
//    public void onDestroy() {
//        Log.e(TAG, "onDestroy");
//        super.onDestroy();
//        if (mLocationManager != null) {
//            for (int i = 0; i < mLocationListeners.length; i++) {
//                try {
//                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        return;
//                    }
//                    mLocationManager.removeUpdates(mLocationListeners[i]);
//                } catch (Exception ex) {
//                    Log.i(TAG, "fail to remove location listener, ignore", ex);
//                }
//            }
//        }
//    }
//
//    private void initializeLocationManager() {
//        Log.e(TAG, "initializeLocationManager - LOCATION_INTERVAL: "+ LOCATION_INTERVAL + " LOCATION_DISTANCE: " + LOCATION_DISTANCE);
//        if (mLocationManager == null) {
//            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
//        }
//    }
//}
