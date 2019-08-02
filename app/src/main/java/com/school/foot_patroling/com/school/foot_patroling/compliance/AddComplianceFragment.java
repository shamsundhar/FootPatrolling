package com.school.foot_patroling.com.school.foot_patroling.compliance;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.depotselection.ScheduleListAdapter;
import com.school.foot_patroling.register.model.Compliance;
import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.utils.DatePickerFragment;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_COMPLIANCE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_OBSERVATION;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT1;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT2;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT5;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT6;
import static com.school.foot_patroling.utils.Constants.FP_PICS_FOLDER;

public class AddComplianceFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener{
    ScheduleListAdapter scheduleListAdapter;
    private String picname;
    private String selectedDate;
    private int currentCounter;
    private Uri outputImgUri;
    private File pictureSaveFolderPath;
    Observation observationModel;
    Compliance complianceModel;
    private int CAMERA_PIC_REQUEST = 100;
    PreferenceHelper preferenceHelper;
    String selectedStatusType;
    @BindView(R.id.tvComplianceProvided)
    TextView complianceProvidedStatus;
    @BindView(R.id.et_actionBy)
    EditText actionBy;
    @BindView(R.id.et_actionDone)
    EditText actionDone;
    @BindView(R.id.statusTV)
    TextView statusTV;
    @OnClick(R.id.statusLayout)
    public void clickOnStatus(){
        displayStatusPopup();
    }
    @BindView(R.id.cameraLayout)
    LinearLayout cameraLayout;
    @BindView(R.id.dateTV)
    TextView dateTv;
    @BindView(R.id.tvChecklistItem)
    TextView tvCheckListItem;
    @BindView(R.id.tvObservation)
    TextView tvObservation;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @OnClick(R.id.launchCamera)
    public void clickOnCamera(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            picname = "C_"+observationModel.getDeviceSeqId()+"_"+observationModel.getDeviceId()+"_"+getImageCounter()+".jpg";

            pictureSaveFolderPath = new File(Environment.getExternalStorageDirectory(), FP_PICS_FOLDER);
            if(!pictureSaveFolderPath.exists()){
                pictureSaveFolderPath.mkdirs();
            }

            File picToBeSaved = new File(pictureSaveFolderPath, picname);
            if(picToBeSaved.exists()){
                picToBeSaved.delete();
            }
            outputImgUri = getImageFileUriByOsVersion(picToBeSaved);
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputImgUri);
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
        }
        else{
            requestPermission();
        }
    }
    @OnClick(R.id.calendar)
    public void clickOnCalendar(){
        displayDateDialog();
    }
    @OnClick(R.id.btn_submit)
    public void clickOnSubmit(){
       // observationModel.setActionBy("");
      //  observationModel.setAction("");
        Compliance compliance = new Compliance();
        compliance.setAction(actionDone.getText().toString().trim());
        compliance.setComplianceBy(actionBy.getText().toString().trim());
        compliance.setCreatedStamp(observationModel.getCreatedStamp());
        compliance.setDescription(observationModel.getDescription());
        compliance.setDeviceId(observationModel.getDeviceId());
        compliance.setDeviceSeqId(observationModel.getDeviceSeqId());
        compliance.setObeservationSeqId(observationModel.getDeviceSeqId());
        compliance.setStatus(selectedStatusType);
        compliance.setSeqId("null");
        compliance.setCompliedDateTime(selectedDate);
        NavigationDrawerActivity.mFPDatabase.complianceDao().insert(compliance);
        Toast.makeText(getActivity(), "Compliance saved successfully", Toast.LENGTH_SHORT).show();
        //List<Compliance> list = NavigationDrawerActivity.mFPDatabase.complianceDao().getAllCompliancesDtos();
        complianceModel = NavigationDrawerActivity.mFPDatabase.complianceDao().getStartedCompliance(observationModel.getDeviceSeqId());
        if(complianceModel != null){
            cameraLayout.setVisibility(View.VISIBLE);
            complianceProvidedStatus.setVisibility(View.VISIBLE);
            complianceProvidedStatus.setText("Compliance already provided");
            initCounter();
        }else{
            cameraLayout.setVisibility(View.GONE);
            complianceProvidedStatus.setVisibility(View.GONE);
        }
    }
    private void initCounter(){
        String deviceSequenceId = observationModel.getDeviceSeqId();
        currentCounter = preferenceHelper.getInteger(getActivity(), "Ccounter_"+deviceSequenceId,0);
    }
    private String getImageCounter(){
        currentCounter++;
        return "_"+currentCounter;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ComplianceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddComplianceFragment newInstance() {
        AddComplianceFragment fragment = new AddComplianceFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_compliance, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
         preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        String deviceSequenceId = getArguments().getString(BUNDLE_KEY_SELECTED_COMPLIANCE);
        observationModel = NavigationDrawerActivity.mFPDatabase.observationDao().getStartedObservation(deviceSequenceId);
        tvCheckListItem.setText(observationModel.getObservationItem());
        tvObservation.setText(observationModel.getObservation());
        tvLocation.setText(observationModel.getLocation());

        complianceModel = NavigationDrawerActivity.mFPDatabase.complianceDao().getStartedCompliance(deviceSequenceId);
        if(complianceModel != null){
            cameraLayout.setVisibility(View.VISIBLE);
            complianceProvidedStatus.setVisibility(View.VISIBLE);
            complianceProvidedStatus.setText("Compliance already provided");
            initCounter();
        }else{
            cameraLayout.setVisibility(View.GONE);
            complianceProvidedStatus.setVisibility(View.GONE);
        }

        return view;
    }
    private void displayDateDialog(){
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        String observationCreatedTime = observationModel.getCreatedDateTime();
        try {
            Calendar minDateCalendar = DateTimeUtils.getCalendarObject(observationCreatedTime, DATE_FORMAT6);
            //minDateCalendar.add(Calendar.DATE, 1);
            args.putLong("mindate", minDateCalendar.getTimeInMillis());
        }catch (Exception e){
            e.printStackTrace();
        }
        args.putLong("maxdate", calender.getTimeInMillis());
        date.setListener(AddComplianceFragment.this);
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void onDateSet(DatePicker view, int i, int i1, int i2) {
        String strDate = padding(i1+1)+"-"+padding(i2)+"-"+padding(i);
        selectedDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT6);
        strDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT5);

        dateTv.setText(strDate);

    }
    String padding(int value)
    {
        String str = value+"";
        if(value < 10)
            str = "0"+str;
        return str;
    }
    private void displayStatusPopup()
    {
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        builder.setContentView(R.layout.popup_listview);

        final ListView listView = (ListView) builder.findViewById(R.id.popupListView);
        listView.setTextFilterEnabled(true);
        //  final List<FootPatrollingSectionsDto> sectionList = NavigationDrawerActivity.mFPDatabase.footPatrollingSectionsDao().getAllFootPatrollingSectionDtosByDepot(selectedDepotId);
        final List<String> statusList = new ArrayList<String>();
        statusList.add("Open");
        statusList.add("Pending");
        statusList.add("InProgress");
        statusList.add("Completed");
        if(statusList != null) {
            scheduleListAdapter = new ScheduleListAdapter(statusList, getActivity().getBaseContext());
            listView.setAdapter(scheduleListAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                String dataModel = statusList.get(position);
                statusTV.setText(dataModel);
                selectedStatusType = dataModel;
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
    private void requestPermission(){
        MultiplePermissionsListener dialogMultiplePermissionsListener =
                DialogOnAnyDeniedMultiplePermissionsListener.Builder
                        .withContext(getActivity())
                        .withTitle("Camera & Storage permission")
                        .withMessage("Both camera and storage permission are needed to store pictures")
                        .withButtonText(android.R.string.ok)
                        .withIcon(R.drawable.ic_launcher)
                        .build();

        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(dialogMultiplePermissionsListener).check();

    }
    private Uri getImageFileUriByOsVersion(File file)
    {
        Uri ret = null;

        // Get output image unique resource identifier. This uri is used by camera app to save taken picture temporary.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            // /sdcard/ folder link to /storage/41B7-12F1 folder
            // so below code return /storage/41B7-12F1
            File externalStorageRootDir = Environment.getExternalStorageDirectory();

            // contextRootDir = /data/user/0/com.dev2qa.example/files in my Huawei mate 8.
            File contextRootDir = getActivity().getFilesDir();

            // contextCacheDir = /data/user/0/com.dev2qa.example/cache in my Huawei mate 8.
            File contextCacheDir = getActivity().getCacheDir();

            // For android os version bigger than or equal to 7.0 use FileProvider class.
            // Otherwise android os will throw FileUriExposedException.
            // Because the system considers it is unsafe to use local real path uri directly.
            Context ctx = getActivity().getApplicationContext();
            ret = FileProvider.getUriForFile(ctx, "com.win.focus.footpatroling.fileprovider", file);

        }else
        {
            // For android os version less than 7.0 there are no safety issue,
            // So we can get the output image uri by file real local path directly.
            ret = Uri.fromFile(file);
        }

        return ret;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Process result for camera activity.
        if (requestCode == CAMERA_PIC_REQUEST) {

            // If camera take picture success.
            if (resultCode == RESULT_OK) {
                Toast.makeText(getActivity(), "Image saved successfully", Toast.LENGTH_SHORT).show();
                preferenceHelper.setInteger(getActivity(), "Ccounter_"+observationModel.getDeviceSeqId(), currentCounter);
                Log.i("Camera pic location:", ""+outputImgUri);
            }
        }
    }
}
