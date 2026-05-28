package com.example.myapplication_mof;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class KeywordAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<KeywordItem> keywordList;

    public KeywordAdapter(Context context, ArrayList<KeywordItem> keywordList) {
        this.context = context;
        this.keywordList = keywordList;
    }

    @Override
    public int getCount() { return keywordList.size(); }

    @Override
    public Object getItem(int position) { return keywordList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_keyword, parent, false);
        }

        ImageButton btnAlarmToggle = convertView.findViewById(R.id.imageButton3);
        ImageButton btnDelete = convertView.findViewById(R.id.imageButton4);
        TextView tvKeywordName = convertView.findViewById(R.id.textView6);

        final KeywordItem item = keywordList.get(position);

        tvKeywordName.setText(item.getKeywordName());

        if (item.isAlarmOn()) {
            btnAlarmToggle.setImageResource(R.drawable.alarm);
            btnAlarmToggle.setAlpha(1.0f);
        } else {
            btnAlarmToggle.setImageResource(R.drawable.alarm_off);
            btnAlarmToggle.setAlpha(0.3f);
        }

        btnAlarmToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean currentStatus = item.isAlarmOn();
                item.setAlarmOn(!currentStatus);

                if (item.isAlarmOn()) {
                    Toast.makeText(context, item.getKeywordName() + "알림이 켜졌습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, item.getKeywordName() + "알림이 꺼졌습니다.", Toast.LENGTH_SHORT).show();
                }

                notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String removedName = item.getKeywordName();
                keywordList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, removedName + "키워드가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
