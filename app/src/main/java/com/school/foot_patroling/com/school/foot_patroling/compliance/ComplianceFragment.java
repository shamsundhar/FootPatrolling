package com.school.foot_patroling.com.school.foot_patroling.compliance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.school.foot_patroling.AWFActivity;
import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.datasync.DataSyncFragment;
import com.school.foot_patroling.patrolinglist.ChecklistAdapter;
import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;
import com.school.foot_patroling.utils.DatePickerFragment;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_COMPLIANCE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_VALUE_COMPLIANCE;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT1;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT2;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT3;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT5;

public class ComplianceFragment extends BaseFragment {
    private ComplianceListAdapter complianceListAdapter;
    @BindView(R.id.observationsRecyclerview)
    RecyclerView observationsRecyclerview;
    List<Observation> observationsList;
    @BindView(R.id.empty_view)
    TextView empty_view;

    @BindView(R.id.toDateTV)
    TextView to_dateTV;

    @BindView(R.id.fromDateTV)
    TextView from_dateTv;

    @BindView(R.id.et_loc1)
    EditText loc1;
    @OnClick(R.id.btn_apply)
    public void clickOnApply(){
       // display list based on only location or only dates or both
        validateDateFilters(from_dateTv.getText().toString(), to_dateTV.getText().toString());
    }
    @OnClick(R.id.fromCalendar)
    public void clickOnCalendarFromDate(){
        displayFromDateDialog();
    }

    @OnClick(R.id.toCalendar)
    public void clickOnCalendarToDate(){
        displayToDateDialog();
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
        date.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String strDate = padding(month+1)+"-"+padding(dayOfMonth)+"-"+padding(year);
                strDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT5);
                from_dateTv.setText(strDate);
                //validateDateFilters(from_dateTv.getText().toString(), to_dateTV.getText().toString());
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
        date.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String strDate = padding(month+1)+"-"+padding(dayOfMonth)+"-"+padding(year);
                strDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT5);
                to_dateTV.setText(strDate);
                //validateDateFilters(from_dateTv.getText().toString(), to_dateTV.getText().toString());
            }
        });
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    String padding(int value)
    {
        String str = value+"";
        if(value < 10)
            str = "0"+str;
        return str;
    }

    private void validateDateFilters(String fromDate, String toDate){
//        if(fromDate == null || fromDate.isEmpty()){
//            Toast.makeText(this.getContext(), "Please select valid From Date ",Toast.LENGTH_LONG).show();
//            return;
//        }else if(toDate == null || toDate.isEmpty()){
//            Toast.makeText(this.getContext(), "Please select valid To Date ",Toast.LENGTH_LONG).show();
//            return;
//        }else if(fromDate.equalsIgnoreCase(toDate)){
//            Toast.makeText(this.getContext(), "Dates should not be equal ",Toast.LENGTH_LONG).show();
//            return;
//        }else{
            //observationsList = Collections.sort(observationsList,new DateFilterComparator());
        //displayObservationsFromDB();

        observationsList = new ArrayList<>();
        observationsList.addAll(NavigationDrawerActivity.mFPDatabase.observationDao().getAllObservationDtos());

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Observation> filterdList = new ArrayList<Observation>();
                if(loc1.getText() != null && !loc1.getText().toString().isEmpty()){

                    for(Observation o : observationsList){
                        if(o.getLocation() != null && o.getLocation().contains(loc1.getText().toString())){
                            filterdList.add(o);
                        }
                    }
                    observationsList = new ArrayList<Observation>();
                    observationsList.addAll(filterdList);
                }
                if(to_dateTV.getText() != null && !to_dateTV.getText().toString().isEmpty() && !to_dateTV.getText().toString().equalsIgnoreCase("To Date")){
                    List<Observation> filteredObservationsList = new ArrayList<Observation>();
                    for(Observation obs : observationsList){
                        Calendar date1=Calendar.getInstance();

                        //Calendar fromDateObj = Calendar.getInstance();
                        Calendar toDateObj = Calendar.getInstance();
                        try {
                            SimpleDateFormat s1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S",Locale.US);
                            SimpleDateFormat s2 = new SimpleDateFormat(DATE_FORMAT3,Locale.US);
                            SimpleDateFormat s3 = new SimpleDateFormat(DATE_FORMAT3,Locale.US);
                            date1.setTime(s1.parse(obs.getCreatedDateTime()));
                            //fromDateObj.setTime(s2.parse(from_dateTv.getText().toString()));

                            toDateObj.setTime(s3.parse(to_dateTV.getText().toString()));
                            if(date1.before(toDateObj) || (date1.get(Calendar.YEAR)==toDateObj.get(Calendar.YEAR) && date1.get(Calendar.DAY_OF_MONTH)==toDateObj.get(Calendar.DAY_OF_MONTH) && date1.get(Calendar.MONTH)==toDateObj.get(Calendar.MONTH))) {
                                filteredObservationsList.add(obs);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }

                    if(filteredObservationsList != null){
                        observationsList =  new ArrayList<Observation>();
                        for(Observation observation : filteredObservationsList){
                            observationsList.add(observation);
                        }
                    }
                }

                if(from_dateTv.getText() != null && !from_dateTv.getText().toString().isEmpty() && !from_dateTv.getText().toString().equalsIgnoreCase("From Date")){
                    List<Observation> filteredObservationsList2 = new ArrayList<Observation>();
                    for(Observation obs : observationsList){
                        Calendar date1=Calendar.getInstance();

                        Calendar fromDateObj = Calendar.getInstance();
                        try {
                            SimpleDateFormat s1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S",Locale.US);
                            SimpleDateFormat s2 = new SimpleDateFormat(DATE_FORMAT3,Locale.US);
                            SimpleDateFormat s3 = new SimpleDateFormat(DATE_FORMAT3,Locale.US);
                            date1.setTime(s1.parse(obs.getCreatedDateTime()));
                            fromDateObj.setTime(s2.parse(from_dateTv.getText().toString()));

                            if(date1.before(fromDateObj) || (date1.get(Calendar.YEAR)==fromDateObj.get(Calendar.YEAR) && date1.get(Calendar.DAY_OF_MONTH)==fromDateObj.get(Calendar.DAY_OF_MONTH) && date1.get(Calendar.MONTH)==fromDateObj.get(Calendar.MONTH))) {

                                    filteredObservationsList2.add(obs);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }

                    if(filteredObservationsList2 != null){
                        observationsList =  new ArrayList<Observation>();
                        for(Observation observation : filteredObservationsList2){
                            observationsList.add(observation);
                        }

                    }
                }


            }
        complianceListAdapter.setItems(observationsList);
        complianceListAdapter.notifyDataSetChanged();


//        }
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ComplianceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComplianceFragment newInstance() {
        ComplianceFragment fragment = new ComplianceFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compliance, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        // preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        complianceListAdapter = new ComplianceListAdapter();
        complianceListAdapter.setClickListener(new ClickListener() {

            @Override
            public void onItemClick(Object model, int position) {
                Intent in = new Intent(getActivity(), AWFActivity.class);
                in.putExtra(BUNDLE_KEY_DISPLAY_FRAGMENT, BUNDLE_VALUE_COMPLIANCE);
                in.putExtra(BUNDLE_KEY_SELECTED_COMPLIANCE, ((Observation)model).getDeviceSeqId());
                getActivity().startActivity(in);
            }
        });
        observationsRecyclerview.setAdapter(complianceListAdapter);
        observationsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        displayObservationsFromDB();
        return view;
    }

    private void displayObservationsFromDB(){

        try {
            //          if (database != null) {
            Log.d(TAG, "fetching user id");
            String sql = "select priority, description from observations_check_list";
            observationsList = new ArrayList<>();
            observationsList.addAll(NavigationDrawerActivity.mFPDatabase.observationDao().getAllObservationDtos());
            //        }
        }catch(Exception e){

        }

        if(observationsList != null && !observationsList.isEmpty()){
            empty_view.setVisibility(View.GONE);
            observationsRecyclerview.setVisibility(View.VISIBLE);

        }else{
            empty_view.setText("Observation list is empty");
            empty_view.setVisibility(View.VISIBLE);
            observationsRecyclerview.setVisibility(View.GONE);
        }
        complianceListAdapter.setItems(observationsList);
        complianceListAdapter.notifyDataSetChanged();
    }
}
