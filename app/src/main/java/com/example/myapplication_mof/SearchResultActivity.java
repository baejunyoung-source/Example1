package com.example.myapplication_mof;

import android.os.Bundle;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    public static final String EXTRA_QUERY = "extra_query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(0xFFFFFFFF);
        getWindow().setNavigationBarColor(0xFFF7FAFF);
        int systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
        setContentView(R.layout.activity_search_result);

        String query = getIntent().getStringExtra(EXTRA_QUERY);
        if (query == null || query.trim().isEmpty()) {
            query = "13 미니";
        }

        TextView queryText = findViewById(R.id.txtSearchResultQuery);
        TextView alertText = findViewById(R.id.txtAlert);
        queryText.setText(query);
        alertText.setText(query + " 알림 받기");

        findViewById(R.id.btnBack).setOnClickListener(v -> NavigationUtils.goBack(this));
        findViewById(R.id.btnClose).setOnClickListener(v -> NavigationUtils.goBack(this));
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            queryText.setText("");
            alertText.setText("알림 받기");
        });
        findViewById(R.id.btnAlert).setOnClickListener(v ->
                Toast.makeText(this, "알림이 설정되었습니다.", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnMore).setOnClickListener(v ->
                Toast.makeText(this, "중고거래 결과를 더 불러옵니다.", Toast.LENGTH_SHORT).show());

        RecyclerView recyclerSearchResults = findViewById(R.id.recyclerSearchResults);
        recyclerSearchResults.setLayoutManager(new LinearLayoutManager(this));
        recyclerSearchResults.setAdapter(new SearchResultAdapter(createSampleResults(query)));
    }

    private List<SearchResultItem> createSampleResults(String query) {
        return Arrays.asList(
                new SearchResultItem(query + " 128GB 화이트", "연수구 송도2동 · 35분 전", "230,000원", 13, 22, R.drawable.img_2),
                new SearchResultItem(query + " 128GB 미드나이트", "1.8km · 정왕동 · 1일 전", "220,000원", 6, 4, R.drawable.img_3),
                new SearchResultItem(query + " 그린", "2.2km · 정왕2동 · 14일 전", "180,000원", 3, 10, R.drawable.img_4),
                new SearchResultItem(query, "정왕동 · 1일 전", "200,000원", 2, 6, R.drawable.img_5)
        );
    }
}
