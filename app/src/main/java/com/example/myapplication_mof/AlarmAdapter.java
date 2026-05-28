package com.example.myapplication_mof;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlarmAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<AlarmItem> alarmList;

    public AlarmAdapter(Context context, ArrayList<AlarmItem> alarmList) {
        this.context = context;
        this.alarmList = alarmList;
    }

    @Override
    public int getCount() { return alarmList.size(); }

    @Override
    public Object getItem(int position) { return alarmList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_alarm, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView tvDate = convertView.findViewById(R.id.textView5);
        TextView tvTitle = convertView.findViewById(R.id.textView4);
        ImageButton btnDeleteEach = convertView.findViewById(R.id.imageButton);

        AlarmItem item = alarmList.get(position);
        tvTitle.setText(item.getTitle());
        tvDate.setText(item.getDate());
        imageView.setImageResource(item.getImageResId());

        btnDeleteEach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}