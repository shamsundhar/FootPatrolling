package com.school.foot_patroling.depotselection;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.database.DatabaseHelper;
import com.school.foot_patroling.login.LoginFragment;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_FP_STARTED;

public class DepotSelectionFragment extends BaseFragment {
    PreferenceHelper preferenceHelper;
    @BindView(R.id.depotLayout)
    RelativeLayout depotLayout;
    @BindView(R.id.sectionLayout)
    RelativeLayout sectionLayout;
    @BindView(R.id.depotTV)
    TextView depotTV;
    @BindView(R.id.sectionTV)
    TextView sectionTV;
    @BindView(R.id.scheduleSwitch)
    Switch scheduleSwitch;
    @OnClick(R.id.depotLayout)
    public void depotClick(){
        displayDepotPopup();
    }
    @OnClick(R.id.sectionLayout)
    public void sectionClick(){
        displaySectionsPopup();
    }
    @OnClick(R.id.btn_startFP)
    public void startFPClick(){
//get current timestamp, and save this info into inspection table. and navigate to checklist fragment
       String fpStartTime =  DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S");
       String isScheduled;
       Boolean scheduleStatus = scheduleSwitch.isChecked();
       if(scheduleStatus){
           isScheduled = "Yes";
       }
       else{
           isScheduled = "No";
       }
preferenceHelper.setBoolean(getActivity(), PREF_KEY_FP_STARTED,Boolean.TRUE);

        ((NavigationDrawerActivity)getActivity()).displayCheckedListFragment();
    }
    public static DepotSelectionFragment newInstance() {
        DepotSelectionFragment fragment = new DepotSelectionFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_depot_selection, container, false);
        ButterKnife.bind(this, view);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        return view;
    }
    private void displayDepotPopup()
    {
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        builder.setContentView(R.layout.popup_listview);

//        final ListView listView = (ListView) builder.findViewById(R.id.popupListView);
//        listView.setTextFilterEnabled(true);
//        if(sectionResponseList != null) {
//            sectionsListAdapter = new DepotsListAdapter(sectionResponseList, getActivity().getBaseContext());
//            listView.setAdapter(sectionsListAdapter);
//        }
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // TODO Auto-generated method stub
//                builder.dismiss();
//                SectionResponseModel dataModel = sectionResponseList.get(position);
//                selectedSection = dataModel.getCompositeTagName();
//                sectionTV.setText(selectedSection);
//                selectedSectionId = dataModel.getCompositeTagId();
//                //  Snackbar.make(view, " " +dataModel.getCompositeTagName()+" "+dataModel.getCompositeTagId(), Snackbar.LENGTH_LONG)
//                //          .setAction("No action", null).show();
//                getUsersBasedOnSection();
//            }
//        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
    private void displaySectionsPopup()
    {
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        builder.setContentView(R.layout.popup_listview);

//        final ListView listView = (ListView) builder.findViewById(R.id.popupListView);
//        listView.setTextFilterEnabled(true);
//        if(sectionResponseList != null) {
//            sectionsListAdapter = new DepotsListAdapter(sectionResponseList, getActivity().getBaseContext());
//            listView.setAdapter(sectionsListAdapter);
//        }
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // TODO Auto-generated method stub
//                builder.dismiss();
//                SectionResponseModel dataModel = sectionResponseList.get(position);
//                selectedSection = dataModel.getCompositeTagName();
//                sectionTV.setText(selectedSection);
//                selectedSectionId = dataModel.getCompositeTagId();
//                //  Snackbar.make(view, " " +dataModel.getCompositeTagName()+" "+dataModel.getCompositeTagId(), Snackbar.LENGTH_LONG)
//                //          .setAction("No action", null).show();
//                getUsersBasedOnSection();
//            }
//        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }

}
