package com.school.foot_patroling.patrolinglist;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.database.DatabaseHelper;
import com.school.foot_patroling.depotselection.DepotsListAdapter;
import com.school.foot_patroling.register.model.FacilityDto;
import com.school.foot_patroling.register.model.Inspection;
import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.register.model.ObservationCategoriesDto;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;
import com.school.foot_patroling.utils.Common;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_IMEI;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_FP_STARTED;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_FP_STARTED_TIME;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_DEPOT;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_SECTION;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_USER;

public class PatrolingListFragment extends BaseFragment {
    PreferenceHelper preferenceHelper;
    @BindView(R.id.checklistRecyclerview)
    RecyclerView checklistRecyclerView;
    @BindView(R.id.empty_view)
    TextView empty_view;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.radiogroup)
    RadioGroup radioGroup;
    @BindView(R.id.rbpriority)
    RadioButton radioButtonPriority;
    @BindView(R.id.categoryLayout)
    RelativeLayout categoryLayout;
    @BindView(R.id.categoryTV)
    TextView categoryTV;
    @BindView(R.id.et_loc1)
    EditText loc1;
    @BindView(R.id.et_loc2)
    EditText loc2;
    CategoryListAdapter categoryListAdapter;
    String selectedCategoryId;
    String selectedCategory;
    @OnClick(R.id.categoryLayout)
    public void categoryClick(){
        displayCategoryListPopup();
    }
    @OnClick(R.id.btn_stop)
    public void stopButtonClick(){
//close inspection
        String fpStopTime =  DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S");
        preferenceHelper.setBoolean(getActivity(), PREF_KEY_FP_STARTED, Boolean.FALSE);
        preferenceHelper.setString(getActivity(), PREF_KEY_SELECTED_DEPOT, "");
        preferenceHelper.setString(getActivity(), PREF_KEY_SELECTED_SECTION, "");

        String fpStartedTime = preferenceHelper.getString(getActivity(), PREF_KEY_FP_STARTED_TIME, "" );
        Inspection inspection = NavigationDrawerActivity.mFPDatabase.inspectionDao().getStartedInspection(fpStartedTime);
        //  String selectedImei = preferenceHelper.getString(getActivity(), BUNDLE_KEY_SELECTED_IMEI, "");
        inspection.setStopTime(fpStopTime);
        NavigationDrawerActivity.mFPDatabase.inspectionDao().insert(inspection);
        ((NavigationDrawerActivity)getActivity()).displayDepotSelectionFragment();

    }
    @OnClick(R.id.btn_submit)
    public void submitButtonClick(){
        if(validate()) {
            //insert observation data.
            if(checkList != null && checkList.size()>0) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    Stream<ObservationsCheckListDto> selectedCheckList = checkList.stream().filter(new Predicate<ObservationsCheckListDto>() {
                        @Override
                        public boolean test(ObservationsCheckListDto p) {
                            return p.isChecked();
                        }
                    });
                    final String fpStartedTime = preferenceHelper.getString(getActivity(), PREF_KEY_FP_STARTED_TIME, "" );
                    selectedCheckList.forEach(new Consumer<ObservationsCheckListDto>() {
                        @Override
                        public void accept(ObservationsCheckListDto p) {
                            if(p.isChecked()) {
                                String currentTimeStamp = DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S");
                                String selectedImei = preferenceHelper.getString(getActivity(), BUNDLE_KEY_SELECTED_IMEI, "");
                                String location = loc1.getText().toString() + "/" + loc2.getText().toString();
                                Observation observation = new Observation();
                                observation.setCreatedBy(preferenceHelper.getString(getActivity(), PREF_KEY_SELECTED_USER, ""));
                                observation.setCreatedDateTime(currentTimeStamp);
                                observation.setInspectionSeqId(fpStartedTime);
                                observation.setDeviceId(selectedImei);
                                observation.setDeviceSeqId(currentTimeStamp);
                                observation.setObservationCategory(p.getObservationCategory());
                                observation.setObservationItem(p.getObservationItem());
                                observation.setObservation(p.getComments());
                                observation.setLocation(location);
                                observation.setSeqId("null");
                                NavigationDrawerActivity.mFPDatabase.observationDao().insert(observation);

                            }
                        }
                    });
                    categoryTV.setText("Category");
                    radioButtonPriority.setChecked(true);
                    modifyCheckList_Priority();
                    checklistAdapter.notifyDataSetChanged();
                    loc1.setText("");
                    loc2.setText("");
                    View view = getView().getRootView();
                    Common.hideKeyboardFrom(getActivity(), view);
                    Toast.makeText(getActivity(), "Details saved successfully", Toast.LENGTH_SHORT).show();
                }


            }
        }
    }
    private ChecklistAdapter checklistAdapter;
    SQLiteDatabase database;
    DatabaseHelper dbhelper = null;

    private ArrayList<ObservationsCheckListDto> checkList;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatrolingListFragment newInstance() {
        PatrolingListFragment fragment = new PatrolingListFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patrolinglist, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        try {
            dbhelper = DatabaseHelper.getInstance(getActivity());
            dbhelper.createDataBase();
            database = dbhelper.getReadableDatabase("Wf@trd841$ams327");

        } catch (Exception e){

            Log.e(TAG, "creating database - "+ e.getMessage());
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.rbcategory) {
                    categoryTV.setText("Category");
                    categoryLayout.setVisibility(View.VISIBLE);
                } else if(checkedId == R.id.rbpriority) {
                    modifyCheckList_Priority();
                    categoryLayout.setVisibility(View.GONE);
                }
            }

        });
        empty_view.setText(R.string.empty_check_list_message);
        checklistAdapter = new ChecklistAdapter();
        checklistAdapter.setClickListener(new ClickListener() {
            @Override
            public void onCheckListSwitchSelected(ObservationsCheckListDto model, int position) {
                //display dialog with comments section
                //   Toast.makeText(getActivity(), position+" clicked", Toast.LENGTH_LONG).show();
                // displayCommentsPopup(model, position);
            }
        });
        checklistRecyclerView.setAdapter(checklistAdapter);
        checklistRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
        progressDialog.dismiss();
        displayNewsFromDB();


        return view;
    }

    private void displayNewsFromDB(){

        try {
            if (database != null) {
                Log.d(TAG, "fetching user id");
                String sql = "select priority, description from observations_check_list";
                checkList = new ArrayList<>();
                List<ObservationsCheckListDto> observationsCheckListDtoList = NavigationDrawerActivity.mFPDatabase.observationsCheckListDtoDao().getAllObservationsCheckListDtos();
                Collections.sort(observationsCheckListDtoList, new Comparator()
                        {

                            public int compare(Object o1, Object o2)
                            {
                                ObservationsCheckListDto sa = (ObservationsCheckListDto)o1;
                                ObservationsCheckListDto sb = (ObservationsCheckListDto)o2;
                                int a = sa.getPriorityValue();
                                int b = sb.getPriorityValue();

                                int v = b-a;

                                return v;

                                // it can also return 0, and 1
                            }
                        }
                );
                checkList.addAll(observationsCheckListDtoList);
            }
        }catch(Exception e){

        }

        if(checkList != null && !checkList.isEmpty()){
            empty_view.setVisibility(View.GONE);
            checklistRecyclerView.setVisibility(View.VISIBLE);

        }else{
            empty_view.setVisibility(View.VISIBLE);
            checklistRecyclerView.setVisibility(View.GONE);
        }
        checklistAdapter.setItems(checkList);
        checklistAdapter.notifyDataSetChanged();
    }
    private void displayCommentsPopup(final ObservationsCheckListDto model, final int position){
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        builder.setCanceledOnTouchOutside(false);
        builder.setContentView(R.layout.popup_enter_observations);

        String fontPath = "fonts/bariol_bold-webfont.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        final EditText reasonEditText = (EditText)builder.findViewById(R.id.input_reason);

        final ImageView close = (ImageView)builder.findViewById(R.id.close);
        Button save = (Button)builder.findViewById(R.id.btn_save);

        reasonEditText.setTypeface(tf);save.setTypeface(tf);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = reasonEditText.getText().toString().trim();
                //  Toast.makeText(getActivity(), ""+reason, Toast.LENGTH_LONG).show();
                final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage(getString(R.string.text_please_wait));
                // progressDialog.show();
                builder.dismiss();
                Toast.makeText(getActivity(), "Comments saved successfully", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
    private void displayCategoryListPopup()
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
        final List<ObservationCategoriesDto> categoriesDtoList = NavigationDrawerActivity.mFPDatabase.observationCategoriesDtoDao().getAllCategoryDtos();
        if(categoriesDtoList != null) {
            categoryListAdapter = new CategoryListAdapter(categoriesDtoList, getActivity().getBaseContext());
            listView.setAdapter(categoryListAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                ObservationCategoriesDto dataModel = categoriesDtoList.get(position);
                categoryTV.setText(dataModel.getObservationCategory());
                selectedCategory = dataModel.getObservationCategory();
                selectedCategoryId = dataModel.getSeqId();
                Snackbar.make(view, " " +dataModel.getSeqId()+" "+dataModel.getDescription(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                modifyCheckListData_Category();

            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
    private void modifyCheckListData_Category(){
        checkList.clear();
        List<ObservationsCheckListDto> observationsCheckListDtoList = NavigationDrawerActivity.mFPDatabase.observationsCheckListDtoDao().getAllObservationsCheckListDtosFromCategory(selectedCategory);
        Collections.sort(observationsCheckListDtoList, new Comparator()
                {

                    public int compare(Object o1, Object o2)
                    {
                        ObservationsCheckListDto sa = (ObservationsCheckListDto)o1;
                        ObservationsCheckListDto sb = (ObservationsCheckListDto)o2;
                        int a = sa.getPriorityValue();
                        int b = sb.getPriorityValue();

                        int v = b-a;

                        return v;

                        // it can also return 0, and 1
                    }
                }
        );
        checkList.addAll(observationsCheckListDtoList);
        checklistAdapter.setItems(checkList);
        checklistAdapter.notifyDataSetChanged();
    }
    private void modifyCheckList_Priority(){
        checkList.clear();
        List<ObservationsCheckListDto> observationsCheckListDtoList = NavigationDrawerActivity.mFPDatabase.observationsCheckListDtoDao().getAllObservationsCheckListDtos();
        Collections.sort(observationsCheckListDtoList, new Comparator()
                {

                    public int compare(Object o1, Object o2)
                    {
                        ObservationsCheckListDto sa = (ObservationsCheckListDto)o1;
                        ObservationsCheckListDto sb = (ObservationsCheckListDto)o2;
                        int a = sa.getPriorityValue();
                        int b = sb.getPriorityValue();

                        int v = b-a;

                        return v;

                        // it can also return 0, and 1
                    }
                }
        );
        checkList.addAll(observationsCheckListDtoList);
        checklistAdapter.setItems(checkList);
        checklistAdapter.notifyDataSetChanged();

    }
    public boolean validate() {
        boolean valid = true;

        String locvalue1 = loc1.getText().toString().trim();
        String locvalue2 = loc2.getText().toString().trim();
        if (locvalue1.isEmpty()) {
            loc1.setError("Enter a valid location");
            valid = false;
        }
        if(locvalue2.isEmpty()){
            loc2.setError("Enter a valid location");
            valid = false;
        }
        return valid;
    }

}
