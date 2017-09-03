package com.example.vitaliy.saxparser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vitaliy on 9/3/2017.
 */

public class PlaceMarkBaseAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<PlaceMarkValue> placeMarkValueArrayList;

    public PlaceMarkBaseAdapter(Context context, ArrayList<PlaceMarkValue> placeMarkValueArrayList){
        this.layoutInflater = LayoutInflater.from(context);
        this.placeMarkValueArrayList = placeMarkValueArrayList;
    }

    @Override
    public int getCount() {
        return placeMarkValueArrayList.size();
    }

    @Override
    public PlaceMarkValue getItem(int i) {
        return placeMarkValueArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_placmark, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PlaceMarkValue placeMarkValue = getItem(position);

        viewHolder.tvName.setText(placeMarkValue.getName());
        viewHolder.tvDescription.setText(placeMarkValue.getDescriptrion());
        viewHolder.tvPoint.setText(placeMarkValue.getPoint());

        return convertView;
    }
    private class ViewHolder{
        TextView tvName, tvDescription, tvPoint;
        public ViewHolder(View view){
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            tvPoint = (TextView) view.findViewById(R.id.tvPoint);
        }
    }
}
