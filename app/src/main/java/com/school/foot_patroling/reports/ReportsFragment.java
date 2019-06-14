package com.school.foot_patroling.reports;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.R;
import com.school.foot_patroling.com.school.foot_patroling.compliance.ClickListener;
import com.school.foot_patroling.register.RegisterApi;
import com.school.foot_patroling.utils.Common;
import com.school.foot_patroling.utils.Constants;
import com.school.foot_patroling.utils.PreferenceHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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


        return view;
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
                                    //TODO convert object o to itemList
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
                                    String reportResult = jsonObject.getString("reportResult");
                                    if(reportResult != null && reportResult.length()>0){
                                        //TODO show PDF report
                                        //TODO convert base64 to PDF
                                        final File dwldsPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + reportModel.getReportId() + ".pdf");
                                        byte[] pdfAsBytes = Base64.decode(reportResult, 0);
                                        FileOutputStream os;
                                        os = new FileOutputStream(dwldsPath, false);
                                        os.write(pdfAsBytes);
                                        os.flush();
                                        os.close();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (FileNotFoundException e) {

                                    e.printStackTrace();
                                } catch (IOException e) {
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

}
