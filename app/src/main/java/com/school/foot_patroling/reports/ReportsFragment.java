package com.school.foot_patroling.reports;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.GenericFileProvider;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.com.school.foot_patroling.compliance.ClickListener;
import com.school.foot_patroling.database.DatabaseHelper;
import com.school.foot_patroling.register.RegisterActivity;
import com.school.foot_patroling.register.RegisterApi;
import com.school.foot_patroling.register.model.MasterDto;
import com.school.foot_patroling.utils.Common;
import com.school.foot_patroling.utils.Constants;
import com.school.foot_patroling.utils.PreferenceHelper;

import net.sqlcipher.database.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_AUTH;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_LAST_SYNC_DATE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_URL;

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



    @BindView(R.id.reportsRecyclerview)
    RecyclerView reportsRecyclerView;
    @BindView(R.id.empty_view)
    TextView empty_view;

    private List<Object> itemList;
    private ReportsListAdapter reportsListAdapter;


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

        reportsListAdapter = new ReportsListAdapter();
        reportsListAdapter.setClickListener(new ClickListener() {
            @Override
            public void onItemClick(Object model, int position) {
                downloadReport(model.toString());
            }
        });
        reportsRecyclerView.setAdapter(reportsListAdapter);
        reportsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        displayReports();
       // String samplePdf = "JVBERi0xLjQKJeLjz9MKMyAwIG9iago8PC9MZW5ndGggMTIxL0ZpbHRlci9GbGF0ZURlY29kZT4+c3RyZWFtCnicK+RyCuEyNlOwMDBRCEnhcg3hCuQyUvACiRoqGAAhiLQwMVAIyeXSdzNUMDRSCEnj0tAMyQKpRSgxUEjORdZkbGKsZ2muYG5poWdsDtdsAdIMUVKUzqXhl6/gkliSqOCWX5qXAjLSQCEdi7HRsUA6BewsABdnJDkKZW5kc3RyZWFtCmVuZG9iagoxIDAgb2JqCjw8L0dyb3VwPDwvVHlwZS9Hcm91cC9DUy9EZXZpY2VSR0IvUy9UcmFuc3BhcmVuY3k+Pi9QYXJlbnQgNCAwIFIvQ29udGVudHMgMyAwIFIvVHlwZS9QYWdlL1Jlc291cmNlczw8L1Byb2NTZXQgWy9QREYgL1RleHQgL0ltYWdlQiAvSW1hZ2VDIC9JbWFnZUldL0NvbG9yU3BhY2U8PC9DUy9EZXZpY2VSR0I+Pi9Gb250PDwvRjEgMiAwIFI+Pj4+L01lZGlhQm94WzAgMCA4MTAgODQwXT4+CmVuZG9iago1IDAgb2JqClsxIDAgUi9YWVogMCA4NTIgMF0KZW5kb2JqCjIgMCBvYmoKPDwvQmFzZUZvbnQvSGVsdmV0aWNhL1R5cGUvRm9udC9FbmNvZGluZy9XaW5BbnNpRW5jb2RpbmcvU3VidHlwZS9UeXBlMT4+CmVuZG9iago0IDAgb2JqCjw8L0lUWFQoMi4xLjcpL1R5cGUvUGFnZXMvQ291bnQgMS9LaWRzWzEgMCBSXT4+CmVuZG9iago2IDAgb2JqCjw8L05hbWVzWyhKUl9QQUdFX0FOQ0hPUl8wXzEpIDUgMCBSXT4+CmVuZG9iago3IDAgb2JqCjw8L0Rlc3RzIDYgMCBSPj4KZW5kb2JqCjggMCBvYmoKPDwvTmFtZXMgNyAwIFIvVHlwZS9DYXRhbG9nL1BhZ2VzIDQgMCBSPj4KZW5kb2JqCjkgMCBvYmoKPDwvQ3JlYXRvcihKYXNwZXJSZXBvcnRzIFwoMTMyS1ZDVEhZXCkpL1Byb2R1Y2VyKGlUZXh0IDIuMS43IGJ5IDFUM1hUKS9Nb2REYXRlKEQ6MjAxOTA2MTQxMDUyNTYrMDUnMzAnKS9DcmVhdGlvbkRhdGUoRDoyMDE5MDYxNDEwNTI1NiswNSczMCcpPj4KZW5kb2JqCnhyZWYKMCAxMAowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDAyMDMgMDAwMDAgbiAKMDAwMDAwMDQ3MiAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDA1NjAgMDAwMDAgbiAKMDAwMDAwMDQzNyAwMDAwMCBuIAowMDAwMDAwNjIzIDAwMDAwIG4gCjAwMDAwMDA2NzcgMDAwMDAgbiAKMDAwMDAwMDcwOSAwMDAwMCBuIAowMDAwMDAwNzY2IDAwMDAwIG4gCnRyYWlsZXIKPDwvUm9vdCA4IDAgUi9JRCBbPDZlN2UyNzc1MGRmMGQzY2I0YmJlMTUwMWVmMGNjYWU4Pjw1YTAzZDcyNDNlMjU2ODNlY2IwODE1ZmRlMTVkYWRlMD5dL0luZm8gOSAwIFIvU2l6ZSAxMD4+CnN0YXJ0eHJlZgo5MjUKJSVFT0YK";
       // displayPDF(samplePdf);


        return view;
    }

    private void displayPDF(ReportModel reportModel, String reportData){
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
                if(reportData != null && reportData.length()>0){
                    byte[] pdfAsBytes = Base64.decode(reportData, 0);
                    FileOutputStream os;

                    os = getContext().openFileOutput(reportModel.getReportId() + ".pdf", Context.MODE_PRIVATE);

                    os.write(pdfAsBytes);
                    os.flush();
                    os.close();
                }
            }
            Uri photoURI = GenericFileProvider.getUriForFile(getActivity().getApplicationContext(), getActivity().getApplicationContext().getPackageName() + ".fileprovider", dwldsPath);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(photoURI, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(intent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void displayReports(){
        itemList = new ArrayList<Object>();
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
                                            itemList = new ArrayList<Object>();
                                            for(int i=0;i<reportNamesArray.length();i++){
                                                itemList.add(reportNamesArray.getString(i));
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    updateUI();
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

    private void updateUI(){
        if(itemList != null && !itemList.isEmpty()){
            empty_view.setVisibility(View.GONE);
            reportsRecyclerView.setVisibility(View.VISIBLE);

        }else{
            empty_view.setText("Reports list is empty");
            empty_view.setVisibility(View.VISIBLE);
            reportsRecyclerView.setVisibility(View.GONE);
        }
        reportsListAdapter.setItems(itemList);
        reportsListAdapter.notifyDataSetChanged();
    }

    private void downloadReport(final String reportName){

        if(Common.isNetworkAvailable(getActivity())){
            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage(getString(R.string.text_please_wait));
            progressDialog.show();

            final ReportModel reportModel = new ReportModel();
            reportModel.setReportId(reportName);
            reportModel.setFacilityId("300000");
            reportModel.setSubDivision("");
            reportModel.setFromDate("");
            reportModel.setThruDate("");

            //TODO call reports request API
            String url = preferenceHelper.getString(getActivity(), BUNDLE_KEY_URL, "");
            url = url + Constants.REST_REPORT_EXECUTION;
            registerApi.executeReport(url,reportModel)
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
                                    String reportData = reportResult.getReportResult();//jsonObject.getString("reportResult");
                                    if(reportData != null && reportData.length()>0){

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
