package com.school.foot_patroling.com.school.foot_patroling.compliance;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.school.foot_patroling.R;

import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;

import java.util.List;

public class ComplianceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Observation> items;
    private ClickListener clickListener;
    private final int NEWS_LIST_ITEM = 0;

    public ComplianceListAdapter(){}
    public void setClickListener(ClickListener listener){
        this.clickListener = listener;
    }
    public void setItems(List<Observation> items) {
        this.items = items;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //if(viewType == NEWS_LIST_ITEM){
        View v1 = inflater.inflate(R.layout.layout_compliance_item, viewGroup, false);
        viewHolder = new ComplianceListAdapter.ViewHolder1(v1);
        //}
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ComplianceListAdapter.ViewHolder1 vh1 = (ComplianceListAdapter.ViewHolder1) viewHolder;
        configureViewHolder1(vh1, position);

        /*switch (viewHolder.getItemViewType()) {

            case NEWS_LIST_ITEM:
                ChecklistAdapter.ViewHolder1 vh1 = (ChecklistAdapter.ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            default:
                //  RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
                //  configureDefaultViewHolder(vh, position);
                break;
        }*/


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
        /*if (items.get(position) instanceof ObservationsCheckListDto) {
            return NEWS_LIST_ITEM;
        }*/
        return position;
    }
    private void configureViewHolder1(final ComplianceListAdapter.ViewHolder1 vh1, final int position) {
        final Observation model = (Observation) items.get(position);
        if (model != null) {
            vh1.getTitle().setText(model.getObservationItem());
            vh1.getSubtitle().setText(model.getObservation());
            vh1.getLocation().setText(model.getLocation());
            vh1.getDate().setText(model.getCreatedDateTime());

            vh1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   clickListener.onItemClick(model, position);
                }
            });
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView subtitle;
        private TextView location;
        private TextView date;

        public TextView getLocation() {
            return location;
        }

        public void setLocation(TextView location) {
            this.location = location;
        }

        public TextView getDate() {
            return date;
        }

        public void setDate(TextView date) {
            this.date = date;
        }

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
         public ViewHolder1(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            subtitle = (TextView) v.findViewById(R.id.subtitle);
            location = (TextView)v.findViewById(R.id.location);
            date = (TextView)v.findViewById(R.id.date);
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
