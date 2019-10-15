package com.school.foot_patroling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeGridViewAdapter extends BaseAdapter {

    private Context mContext;
 //   private final HomeItem[] items;
    private final String[] iconNames = {"Checklist", "Observation", "Data Sync", "Compliance", "Reports"};
    private final int[] iconResources = {R.drawable.check_list, R.drawable.observation, R.drawable.data_sync, R.drawable.compliance, R.drawable.reports2};

    // 1
    public HomeGridViewAdapter(Context context) {
        this.mContext = context;
    }

    // 2
    @Override
    public int getCount() {
        return iconNames.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final String iconName = iconNames[position];
        final int iconRes = iconResources[position];
        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.layout_griditem_home, null);
        }

        // 3
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.item_image);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.item_name);



        // 4
        imageView.setImageResource(iconRes);
        nameTextView.setText(iconName);


        return convertView;
    }

}
