package com.example.myapplication_mof;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class KeywordActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ListView listView;
    private KeywordAdapter adapter;
    private ArrayList<KeywordItem> keywordDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);

        btnBack = findViewById(R.id.btnBack);
        listView = findViewById(R.id.listViewKeyword);

        keywordDataList = new ArrayList<>();
        listView = findViewById(R.id.listViewKeyword);

        keywordDataList = new ArrayList<>();
        keywordDataList.add(new KeywordItem("스프링 부트", true));
        keywordDataList.add(new KeywordItem("안드로이드", true));
        keywordDataList.add(new KeywordItem("자바스크립트", false));
        keywordDataList.add(new KeywordItem("데이터베이스", true));

        adapter = new KeywordAdapter(this, keywordDataList);
        listView.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
