package com.school.foot_patroling.reports;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.pdf.PdfWriter;
import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.GenericFileProvider;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.com.school.foot_patroling.compliance.AddComplianceFragment;
import com.school.foot_patroling.com.school.foot_patroling.compliance.ClickListener;
import com.school.foot_patroling.database.DatabaseHelper;
import com.school.foot_patroling.depotselection.DepotsListAdapter;
import com.school.foot_patroling.depotselection.SectionsListAdapter;
import com.school.foot_patroling.register.RegisterActivity;
import com.school.foot_patroling.register.RegisterApi;
import com.school.foot_patroling.register.model.FacilityDto;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.MasterDto;
import com.school.foot_patroling.utils.Common;
import com.school.foot_patroling.utils.Constants;
import com.school.foot_patroling.utils.CustomAlertDialog;
import com.school.foot_patroling.utils.DatePickerFragment;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;

import net.sqlcipher.database.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_AUTH;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_LAST_SYNC_DATE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_URL;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT1;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT2;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT4;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT5;

public class ReportsFragment extends BaseFragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */

    @Inject
    RegisterApi registerApi;
    PreferenceHelper preferenceHelper;
    @BindView(R.id.reportTV)
    TextView reportTV;
    @BindView(R.id.subdivisionTV)
    TextView subDivisionTV;
    @BindView(R.id.toDateTV)
    TextView toDateTV;
    @BindView(R.id.fromDateTV)
    TextView fromDateTV;
    @BindView(R.id.depotTV)
    TextView depotTV;
    @OnClick(R.id.fromCalendar)
    public void clickOnFromCalendar(){
        displayFromDateDialog();
    }
    @OnClick(R.id.toCalendar)
    public void clickOnToCalendar(){
        displayToDateDialog();
    }
    @OnClick(R.id.reportlistLayout)
    public void clickOnReportListLayout(){
        displayReportListPopup();
    }
    @OnClick(R.id.subdivisionLayout)
    public void clickOnSubDivisionListLayout(){
        displaySubDivisionListPopup();
    }
    @OnClick(R.id.depotLayout)
    public void clickOnDepotListLayout(){
        displayDepotListPopup();
    }
    @OnClick(R.id.btn_submit)
    public void clickOnSubmit(){
        if(!selectedReportID.isEmpty()) {
            ReportModel reportModel = new ReportModel();
            reportModel.setReportId(selectedReportID);
            reportModel.setFacilityId(selectedDepotId);

            reportModel.setSubDivision(selectedSubDivisionID);
            reportModel.setFromDate(selectedFromDate);
            reportModel.setThruDate(selectedToDate);
            downloadReport(reportModel);
        }
        else{
            CustomAlertDialog dialog = new CustomAlertDialog();
            dialog.showAlert1(getActivity(), R.string.text_alert, "Select Report");
        }
    }

    private List<Object> reportList;
    private List<String> subdivisionsList;
    private List<FacilityDto> depotList;
    private String selectedReportID="";
    private String selectedSubDivisionID="";
    private String selectedDepotId="";
    private String selectedFromDate="";
    private String selectedToDate="";
    ReportsListAdapter reportsListAdapter;
    SubDivisionsListAdapter subDivisionsListAdapter;
    DepotsListAdapter depotListAdapter;

    // TODO: Rename and change types and number of parameters
    public static ReportsFragment newInstance() {
        ReportsFragment fragment = new ReportsFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();


        getReportNames();
        getSubDivisions();
       // String samplePdf = "JVBERi0xLjQKJeLjz9MKMyAwIG9iago8PC9MZW5ndGggMTIxL0ZpbHRlci9GbGF0ZURlY29kZT4+c3RyZWFtCnicK+RyCuEyNlOwMDBRCEnhcg3hCuQyUvACiRoqGAAhiLQwMVAIyeXSdzNUMDRSCEnj0tAMyQKpRSgxUEjORdZkbGKsZ2muYG5poWdsDtdsAdIMUVKUzqXhl6/gkliSqOCWX5qXAjLSQCEdi7HRsUA6BewsABdnJDkKZW5kc3RyZWFtCmVuZG9iagoxIDAgb2JqCjw8L0dyb3VwPDwvVHlwZS9Hcm91cC9DUy9EZXZpY2VSR0IvUy9UcmFuc3BhcmVuY3k+Pi9QYXJlbnQgNCAwIFIvQ29udGVudHMgMyAwIFIvVHlwZS9QYWdlL1Jlc291cmNlczw8L1Byb2NTZXQgWy9QREYgL1RleHQgL0ltYWdlQiAvSW1hZ2VDIC9JbWFnZUldL0NvbG9yU3BhY2U8PC9DUy9EZXZpY2VSR0I+Pi9Gb250PDwvRjEgMiAwIFI+Pj4+L01lZGlhQm94WzAgMCA4MTAgODQwXT4+CmVuZG9iago1IDAgb2JqClsxIDAgUi9YWVogMCA4NTIgMF0KZW5kb2JqCjIgMCBvYmoKPDwvQmFzZUZvbnQvSGVsdmV0aWNhL1R5cGUvRm9udC9FbmNvZGluZy9XaW5BbnNpRW5jb2RpbmcvU3VidHlwZS9UeXBlMT4+CmVuZG9iago0IDAgb2JqCjw8L0lUWFQoMi4xLjcpL1R5cGUvUGFnZXMvQ291bnQgMS9LaWRzWzEgMCBSXT4+CmVuZG9iago2IDAgb2JqCjw8L05hbWVzWyhKUl9QQUdFX0FOQ0hPUl8wXzEpIDUgMCBSXT4+CmVuZG9iago3IDAgb2JqCjw8L0Rlc3RzIDYgMCBSPj4KZW5kb2JqCjggMCBvYmoKPDwvTmFtZXMgNyAwIFIvVHlwZS9DYXRhbG9nL1BhZ2VzIDQgMCBSPj4KZW5kb2JqCjkgMCBvYmoKPDwvQ3JlYXRvcihKYXNwZXJSZXBvcnRzIFwoMTMyS1ZDVEhZXCkpL1Byb2R1Y2VyKGlUZXh0IDIuMS43IGJ5IDFUM1hUKS9Nb2REYXRlKEQ6MjAxOTA2MTQxMDUyNTYrMDUnMzAnKS9DcmVhdGlvbkRhdGUoRDoyMDE5MDYxNDEwNTI1NiswNSczMCcpPj4KZW5kb2JqCnhyZWYKMCAxMAowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDAyMDMgMDAwMDAgbiAKMDAwMDAwMDQ3MiAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDA1NjAgMDAwMDAgbiAKMDAwMDAwMDQzNyAwMDAwMCBuIAowMDAwMDAwNjIzIDAwMDAwIG4gCjAwMDAwMDA2NzcgMDAwMDAgbiAKMDAwMDAwMDcwOSAwMDAwMCBuIAowMDAwMDAwNzY2IDAwMDAwIG4gCnRyYWlsZXIKPDwvUm9vdCA4IDAgUi9JRCBbPDZlN2UyNzc1MGRmMGQzY2I0YmJlMTUwMWVmMGNjYWU4Pjw1YTAzZDcyNDNlMjU2ODNlY2IwODE1ZmRlMTVkYWRlMD5dL0luZm8gOSAwIFIvU2l6ZSAxMD4+CnN0YXJ0eHJlZgo5MjUKJSVFT0YK";
       // displayPDF(samplePdf);

        return view;
    }
    String padding(int value)
    {
        String str = value+"";
        if(value < 10)
            str = "0"+str;
        return str;
    }
    private void displayFromDateDialog(){
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        args.putLong("maxdate", calender.getTimeInMillis());
        date.setListener(new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                String strDate = padding(i1+1)+"-"+padding(i2)+"-"+padding(i);
                selectedFromDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT4);
                strDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT5);

                fromDateTV.setText(strDate);
            }
        });
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }
    private void displayToDateDialog(){
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        args.putLong("maxdate", calender.getTimeInMillis());
        date.setListener(new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                String strDate = padding(i1+1)+"-"+padding(i2)+"-"+padding(i);
                selectedToDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT4);
                strDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT5);

                toDateTV.setText(strDate);
            }
        });
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }
    private void getSubDivisions(){
        subdivisionsList = NavigationDrawerActivity.mFPDatabase.facilityDtoDao().getSubDivisions();
        if(subdivisionsList.size() == 1){
            if(subdivisionsList.get(0) == null || subdivisionsList.get(0).trim().length()==0){
                subdivisionsList.clear();
            }
        }
    }
    private void getDepots(){
        if(selectedSubDivisionID != null && !selectedSubDivisionID.isEmpty()) {
            depotList = NavigationDrawerActivity.mFPDatabase.facilityDtoDao().getDepotsFromSubDivision(selectedSubDivisionID);
        }else{
            depotList = NavigationDrawerActivity.mFPDatabase.facilityDtoDao().getOHEFacilityDtos();
        }
    }

    private void displayPDF(ReportModel reportModel, Object reportData){
//        final DialogPDFViewer dialogPDFViewer = new DialogPDFViewer(getContext(),reportData, new DialogPDFViewer.OnDialogPdfViewerListener() {
//            @Override
//            public void onAgreeClick(DialogPDFViewer dialogFullEula) {
//
//            }
//
//            @Override
//            public void onCloseClick(DialogPDFViewer dialogFullEula) {
//                dialogFullEula.dismiss();
//            }
//        });
//        dialogPDFViewer.show();

        try {
            final File dwldsPath = new File(Environment.getExternalStorageDirectory()+"/"+reportModel.getReportId() + ".pdf");
            if(!dwldsPath.exists()){
                if(reportData != null){

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject((Object) reportData);
                    oos.flush();
                    byte [] data = bos.toByteArray();

                    byte[] pdfAsBytes = data;//Base64.decode(reportData, 0);
                    FileOutputStream os;

                    os = getContext().openFileOutput(reportModel.getReportId() + ".pdf", Context.MODE_PRIVATE);

                    os.write(pdfAsBytes);
//                    os.flush();
//                    os.close();



                    com.itextpdf.text.Document document = new com.itextpdf.text.Document();

                    PdfWriter.getInstance(document,os);

                    os.flush();
                    os.close();
                   // Object obj = dto.getReportProgress();
                  //  Log.d(TAG,"acquired--" +obj);



//                    FileOutputStream outValue = new FileOutputStream(FILE);
//                    outValue.write(data);
//                    outValue.close();
//                    Log.d(TAG,"written--"+data);




                }
            }
            Uri photoURI = GenericFileProvider.getUriForFile(getActivity().getApplicationContext(), getActivity().getApplicationContext().getPackageName() + ".fileprovider", dwldsPath);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(photoURI, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

//            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//            pdfIntent.setDataAndType(path, "application/pdf");
//            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            startActivity(intent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void getReportNames(){
        reportList = new ArrayList<Object>();
        if(Common.isNetworkAvailable(getActivity())) {
                final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage(getString(R.string.text_please_wait));
                progressDialog.show();

                //TODO call reports request API
                String url = preferenceHelper.getString(getActivity(), BUNDLE_KEY_URL, "");
                url = url + Constants.REST_GET_REPORT_NAMES;

                registerApi.getReportNames(url)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println("onSubscribe called");
                            }

                            @Override
                            public void onNext(Object o) {
                                progressDialog.dismiss();
                                System.out.println("onNext called" + o);
                                if (o != null) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(o.toString());
                                        JSONArray reportNamesArray = jsonObject.getJSONArray("reportNames");
                                        if(reportNamesArray != null && reportNamesArray.length()>0){
                                            reportList = new ArrayList<Object>();
                                            for(int i=0;i<reportNamesArray.length();i++){
                                                reportList.add(reportNamesArray.getString(i));
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println("error called::" + e.fillInStackTrace());
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onComplete() {
                                progressDialog.dismiss();
                                System.out.println("complete called");
                            }
                        });

            }else{
                    Toast.makeText(getContext(),"Network not available",Toast.LENGTH_LONG).show();
            }

    }

    private void displayReportListPopup()
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

            if (reportList != null && reportList.size() > 0) {
                reportsListAdapter = new ReportsListAdapter(reportList, getActivity().getBaseContext());
                listView.setAdapter(reportsListAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        builder.dismiss();
                        String dataModel = (String)reportList.get(position);
                        reportTV.setText(dataModel);
                        selectedReportID = dataModel;


                    }
                });
                builder.setCanceledOnTouchOutside(true);
                builder.show();
            }
            else{
                CustomAlertDialog dialog = new CustomAlertDialog();
                dialog.showAlert1(getActivity(), R.string.text_alert, "No reports available.");
            }
    }
    private void displaySubDivisionListPopup()
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

        if (subdivisionsList != null && subdivisionsList.size() > 0) {
            subDivisionsListAdapter = new SubDivisionsListAdapter(subdivisionsList, getActivity().getBaseContext());
            listView.setAdapter(subDivisionsListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    builder.dismiss();
                    String dataModel = (String)subdivisionsList.get(position);
                    subDivisionTV.setText(dataModel);
                    selectedSubDivisionID = dataModel;


                }
            });
            builder.setCanceledOnTouchOutside(true);
            builder.show();
        }
        else{
            CustomAlertDialog dialog = new CustomAlertDialog();
            dialog.showAlert1(getActivity(), R.string.text_alert, "No sub divisions available");
            getDepots();
        }
    }
    private void displayDepotListPopup(){
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

        if (depotList != null && depotList.size() > 0) {
            depotListAdapter = new DepotsListAdapter(depotList, getActivity().getBaseContext());
            listView.setAdapter(depotListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    builder.dismiss();
                    FacilityDto dataModel = (FacilityDto)depotList.get(position);
                    depotTV.setText(dataModel.getFacilityName());
                    selectedDepotId = dataModel.getFacilityId();

                }
            });
            builder.setCanceledOnTouchOutside(true);
            builder.show();
        }
        else{
            CustomAlertDialog dialog = new CustomAlertDialog();
            dialog.showAlert1(getActivity(), R.string.text_alert, "No depots available.");
        }
    }
    private void downloadReport(final ReportModel reportModel){

        if(Common.isNetworkAvailable(getActivity())){
            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage(getString(R.string.text_please_wait));
            progressDialog.show();

           // final ReportModel reportModel = new ReportModel();


            //TODO call reports request API
            String url = preferenceHelper.getString(getActivity(), BUNDLE_KEY_URL, "");
            url = url + Constants.REST_REPORT_EXECUTION;
            registerApi.executeReport(url, reportModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ReportResult>() {
                        @Override
                        public void onError(Throwable e) {
                            System.out.println("error called::" + e.fillInStackTrace());
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                            System.out.println("complete called");
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onSubscribe(Disposable d) {
                            System.out.println("onsubscribe called");
                        }

                        @Override
                        public void onNext(ReportResult reportResult) {
                            progressDialog.dismiss();
                            System.out.println("onNext called" + reportResult);
                            if (reportResult != null) {
//                                    String s = o.toString().replace("/","//");
//                                    JSONObject jsonObject = new JSONObject(s);
                                    Object reportData = reportResult.getReportResult();//jsonObject.getString("reportResult");
                                    if(reportData != null){

                                        displayPDF(reportModel, reportData);
//                                        decodedPDFString = Base64.decode(reportData.toString(), Base64.DEFAULT);
//
//                                        //pdfView = ((PDFView) findViewById(R.id.pdfView));
//                                        reportsRecyclerView.setVisibility(View.GONE);
//                                        pdfView.fromBytes(decodedPDFString).load();
//                                        pdfView.setVisibility(View.VISIBLE);

//                                        final File dwldsPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + reportModel.getReportId() + ".pdf");
//                                        byte[] pdfAsBytes = Base64.decode(reportData, 0);
//                                        FileOutputStream os;
//                                        os = new FileOutputStream(dwldsPath, false);
//                                        os.write(pdfAsBytes);
//                                        os.flush();
//                                        os.close();
                                    }


                            }

                        }
                    });


        }else{
            Toast.makeText(getContext(),"Network not available",Toast.LENGTH_LONG).show();
        }
    }

}
