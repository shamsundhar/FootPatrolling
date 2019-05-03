package com.school.foot_patroling.patrolinglist;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.school.foot_patroling.R;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;
import com.school.foot_patroling.utils.Constants;
import com.school.foot_patroling.utils.DateTimeUtils;

import java.util.List;

public class ChecklistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ObservationsCheckListDto> items;
    private ClickListener clickListener;
    private final int NEWS_LIST_ITEM = 0;

    public ChecklistAdapter(){}
    public void setClickListener(ClickListener listener){
        this.clickListener = listener;
    }
    public void setItems(List<ObservationsCheckListDto> items) {
        this.items = items;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if(viewType == NEWS_LIST_ITEM){
            View v1 = inflater.inflate(R.layout.layout_checklistitem, viewGroup, false);
            viewHolder = new ChecklistAdapter.ViewHolder1(v1);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case NEWS_LIST_ITEM:
                ChecklistAdapter.ViewHolder1 vh1 = (ChecklistAdapter.ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            default:
                //  RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
                //  configureDefaultViewHolder(vh, position);
                break;
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
        if (items.get(position) instanceof ObservationsCheckListDto) {
            return NEWS_LIST_ITEM;
        }
        return -1;
    }
    private void configureViewHolder1(ChecklistAdapter.ViewHolder1 vh1, final int position) {
        final ObservationsCheckListDto model = (ObservationsCheckListDto) items.get(position);
        if (model != null) {
            vh1.getTitle().setText(model.getObservationItem());
            vh1.getSubtitle().setText(model.getPriority());
            vh1.getaSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    clickListener.onCheckListSwitchSelected(model, position);
                }
            });

        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView subtitle;
        private Switch aSwitch;

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(TextView subtitle) {
            this.subtitle = subtitle;
        }
        public Switch getaSwitch() {
            return aSwitch;
        }

        public void setaSwitch(Switch aSwitch) {
            this.aSwitch = aSwitch;
        }
        public ViewHolder1(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            subtitle = (TextView) v.findViewById(R.id.subtitle);
            aSwitch = (Switch)v.findViewById(R.id.switch1);
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
            subtitle.setTypeface(tf);
        }
    }

}
