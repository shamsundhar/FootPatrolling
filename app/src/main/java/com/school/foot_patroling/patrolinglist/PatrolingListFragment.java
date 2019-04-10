package com.school.foot_patroling.patrolinglist;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.R;
import com.school.foot_patroling.database.DatabaseHelper;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class PatrolingListFragment extends BaseFragment {
    @BindView(R.id.checklistRecyclerview)
    RecyclerView checklistRecyclerView;
    @BindView(R.id.empty_view)
    TextView empty_view;
    @BindView(R.id.tv1)
    TextView tv1;
    private ChecklistAdapter checklistAdapter;
    SQLiteDatabase database;
    DatabaseHelper dbhelper = null;

  //  @Inject
  //  TodayApi todayApi;

  //  public static EdsenseDatabase mEdsenseDatabase;
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
   //     mEdsenseDatabase = Room.databaseBuilder(getActivity(), EdsenseDatabase.class, EDSENSE_DATABASE)
    //            .allowMainThreadQueries()
   //             .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patrolinglist, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);

        try {
            dbhelper = DatabaseHelper.getInstance(getActivity());
            dbhelper.createDataBase();
            database = dbhelper.getReadableDatabase("Wf@trd841$ams327");

        } catch (Exception e){

            Log.e(TAG, "creating database - "+ e.getMessage());
        }

        empty_view.setText(R.string.empty_check_list_message);
        checklistAdapter = new ChecklistAdapter();
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

                Cursor cursor = database.rawQuery(sql, null);
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        ObservationsCheckListDto dto = new ObservationsCheckListDto();
                        dto.setPriority( cursor.getString(0) );
                        dto.setDescription( cursor.getString(1) );
                        cursor.moveToNext();
                        checkList.add(dto);
                    }
                }
                cursor.close();
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

}
