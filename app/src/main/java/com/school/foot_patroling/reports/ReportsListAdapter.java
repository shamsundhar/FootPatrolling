package com.school.foot_patroling.reports;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.foot_patroling.R;
import com.school.foot_patroling.com.school.foot_patroling.compliance.ClickListener;

import java.util.List;

public class ReportsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private ClickListener clickListener;


    public ReportsListAdapter(){

    }
    public void setClickListener(ClickListener listener){
        this.clickListener = listener;
    }
    public void setItems(List<Object> items) {
        this.items = items;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v1 = inflater.inflate(R.layout.layout_reports_item, viewGroup, false);
        viewHolder = new ReportsListAdapter.ViewHolder1(v1);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ReportsListAdapter.ViewHolder1 vh1 = (ReportsListAdapter.ViewHolder1) holder;
        configureViewHolder1(vh1, position);
    }
    private void configureViewHolder1(final ReportsListAdapter.ViewHolder1 vh1, final int position) {
        final Object model = (Object) items.get(position);
        if (model != null) {
            vh1.getTitle().setText(model.toString());


            vh1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(model.toString(), position);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        if(items != null) {
            return this.items.size();
        }
        else{
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView title;



        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }



        public ViewHolder1(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);

            applyFonts(v);
        }
        private void applyFonts(View v){
            // Font path
            String fontPath = "fonts/bariol_bold-webfont.ttf";
            String fontPath2 = "fonts/framd.ttf";
            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), fontPath);
            Typeface tf2 = Typeface.createFromAsset(v.getContext().getAssets(), fontPath2);
            title.setTypeface(tf);

        }
    }
}
