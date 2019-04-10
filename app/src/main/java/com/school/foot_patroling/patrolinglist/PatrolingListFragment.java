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
import com.school.foot_patroling.login.LoginFragment;
import com.school.foot_patroling.utils.Common;
import com.school.foot_patroling.utils.Constants;
import com.school.foot_patroling.utils.PreferenceHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.internal.http2.StreamResetException;

import static android.content.ContentValues.TAG;

public class PatrolingListFragment extends BaseFragment {
    @BindView(R.id.newsRecyclerview)
    RecyclerView newsRecyclerView;
    @BindView(R.id.empty_view)
    TextView empty_view;
    @BindView(R.id.tv1)
    TextView tv1;
   // private ChecklistAdapter checklistAdapter;

  //  @Inject
  //  TodayApi todayApi;

  //  public static EdsenseDatabase mEdsenseDatabase;
 //   private ArrayList<News> newsList;

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


        empty_view.setText(R.string.empty_check_list_message);
//        checklistAdapter = new ChecklistAdapter();
//        newsRecyclerView.setAdapter(checklistAdapter);
//        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.text_please_wait));
        progressDialog.show();
            progressDialog.dismiss();
          //  displayNewsFromDB();


        return view;
    }

//    private void displayNewsFromDB(){
//        newsList = (ArrayList<News>)mEdsenseDatabase.getNewsDao().getAllNews();
//        if(newsList != null && !newsList.isEmpty()){
//            empty_view.setVisibility(View.GONE);
////            newsRecyclerView.setVisibility(View.VISIBLE);
//
//        }else{
//            empty_view.setVisibility(View.VISIBLE);
////            newsRecyclerView.setVisibility(View.GONE);
//        }
//        newsRecyclerViewAdapter.setItems(newsList);
//        newsRecyclerViewAdapter.notifyDataSetChanged();
//    }
//
}
