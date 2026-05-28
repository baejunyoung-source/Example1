package com.example.myapplication_mof;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddSelfActivity extends AppCompatActivity {

    private static final String[] COLLEGES = {
            "단과대", "신학대학", "인문대학", "사회과학대학", "글로벌경영기술대학", "사범대학", "IT공과대학", "예술대학"
    };

    private static final String[] MAJORS = {
            "학과", "컴퓨터공학과", "미디어소프트웨어학과", "경영학과", "국어국문학과", "사회복지학과", "유아교육과"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_self);

        findViewById(R.id.btnBack).setOnClickListener(v -> NavigationUtils.goBack(this));
        findViewById(R.id.btnAddPhoto).setOnClickListener(v ->
                Toast.makeText(this, "사진 등록 버튼입니다.", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnSubmit).setOnClickListener(v ->
                Toast.makeText(this, "상품 정보 입력이 완료되었습니다.", Toast.LENGTH_SHORT).show());

        setupSpinner(findViewById(R.id.spinnerCollege), COLLEGES);
        setupSpinner(findViewById(R.id.spinnerMajor), MAJORS);
    }

    private void setupSpinner(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
