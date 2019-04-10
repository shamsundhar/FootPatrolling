//package com.school.foot_patroling.patrolinglist;
//
//import android.graphics.Typeface;
//import android.os.Build;
//import android.support.v7.widget.RecyclerView;
//import android.text.Html;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.school.foot_patroling.R;
//import com.school.foot_patroling.utils.Constants;
//import com.school.foot_patroling.utils.DateTimeUtils;
//
//public class ChecklistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    private List<News> items;
//    private final int NEWS_LIST_ITEM = 0;
//
//    public ChecklistAdapter(){}
//    public void setItems(List<News> items) {
//        this.items = items;
//    }
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//
//        RecyclerView.ViewHolder viewHolder = null;
//        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
//        if(viewType == NEWS_LIST_ITEM){
//            View v1 = inflater.inflate(R.layout.layout_checklistitem, viewGroup, false);
//            viewHolder = new ChecklistAdapter.ViewHolder1(v1);
//        }
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
//        switch (viewHolder.getItemViewType()) {
//            case NEWS_LIST_ITEM:
//                ChecklistAdapter.ViewHolder1 vh1 = (ChecklistAdapter.ViewHolder1) viewHolder;
//                configureViewHolder1(vh1, position);
//                break;
//            default:
//                //  RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                //  configureDefaultViewHolder(vh, position);
//                break;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        if(items != null) {
//            return this.items.size();
//        }
//        else{
//            return 0;
//        }
//    }
//    @Override
//    public int getItemViewType(int position) {
//        if (items.get(position) instanceof News) {
//            return NEWS_LIST_ITEM;
//        }
//        return -1;
//    }
//    private void configureViewHolder1(ChecklistAdapter.ViewHolder1 vh1, int position) {
//        News newsModel = (News) items.get(position);
//        if (newsModel != null) {
//            vh1.getTitle().setText(newsModel.getNewsName());
//            if(newsModel.getDescription() != null && newsModel.getDescription().trim().length() > 0) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    vh1.getSubtitle().setText(Html.fromHtml(newsModel.getDescription(), Html.FROM_HTML_MODE_COMPACT));
//                } else {
//                    vh1.getSubtitle().setText(Html.fromHtml(newsModel.getDescription()));
//                }
//            }
//            String date = DateTimeUtils.parseDateTime(newsModel.getNewsDate(),Constants.DATE_FORMAT6, Constants.DATE_FORMAT7);
//            date = date.replace(',','\n');
//            vh1.getDate().setText(date);
//        }
//    }
//
//    class ViewHolder1 extends RecyclerView.ViewHolder {
//
//        private TextView title;
//        private TextView subtitle;
//        private TextView date;
//
//        public TextView getDate() {
//            return date;
//        }
//
//        public void setDate(TextView date) {
//            this.date = date;
//        }
//
//        public TextView getTitle() {
//            return title;
//        }
//
//        public void setTitle(TextView title) {
//            this.title = title;
//        }
//
//        public TextView getSubtitle() {
//            return subtitle;
//        }
//
//        public void setSubtitle(TextView subtitle) {
//            this.subtitle = subtitle;
//        }
//
//        public ViewHolder1(View v) {
//            super(v);
//            title = (TextView) v.findViewById(R.id.title);
//            subtitle = (TextView) v.findViewById(R.id.subtitle);
//            date = (TextView)v.findViewById(R.id.date);
//
//            applyFonts(v);
//        }
//        private void applyFonts(View v){
//            // Font path
//            String fontPath = "fonts/bariol_bold-webfont.ttf";
//            String fontPath2 = "fonts/framd.ttf";
//            // Loading Font Face
//            Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), fontPath);
//            Typeface tf2 = Typeface.createFromAsset(v.getContext().getAssets(), fontPath2);
//            title.setTypeface(tf);
//            subtitle.setTypeface(tf);
//            date.setTypeface(tf2);
//        }
//    }
//
//}
