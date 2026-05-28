package com.example.myapplication_mof;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private Button btnDeleteAll;
    private ListView listView;

    private AlarmAdapter adapter;
    private ArrayList<AlarmItem> alarmDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        btnBack = findViewById(R.id.btnBack);
        btnDeleteAll = findViewById(R.id.button);
        listView = findViewById(R.id.listViewAlarm);

        alarmDataList = new ArrayList<>();

        alarmDataList.add(new AlarmItem(R.drawable.database, "'데이터베이스 개론' 새 게시글이 등록되었습니다", "1일전"));
        alarmDataList.add(new AlarmItem(R.drawable.os, "'운영체제' 새 게시글이 등록되었습니다", "2일전"));
        alarmDataList.add(new AlarmItem(R.drawable.cleancode, "'clean code' 새 게시글이 등록되었습니다", "3일전"));

        adapter = new AlarmAdapter(this, alarmDataList);
        listView.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarmDataList.isEmpty()) {
                    Toast.makeText(AlarmActivity.this, "삭제할 알림이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    alarmDataList.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(AlarmActivity.this, "모든 알림이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addNewAlarm(String bookTitle, String timeAgo) {
        if (alarmDataList != null && adapter != null) {
            alarmDataList.add(0, new AlarmItem(R.drawable.database, "'" + bookTitle + "' 새 게시글이 등록되었습니다", timeAgo));
            adapter.notifyDataSetChanged();
        }
    }
}
