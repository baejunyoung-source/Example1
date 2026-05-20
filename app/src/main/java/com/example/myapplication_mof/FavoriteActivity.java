package com.example.myapplication_mof;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class FavoriteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.favorite_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 찜 목록 더미 데이터
        List<Product> favorites = Arrays.asList(
                new Product("클린 코드", "로버트 마틴(글)", "인사이트", "18,000원", 18000, R.drawable.cleancode),
                new Product("자료구조 입문", "황정규(글)", "한빛미디어", "12,000원", 12000, R.drawable.structure),
                new Product("데이터베이스 개론", "이한호(글)", "한빛아카데미", "10,000원", 10000, R.drawable.database)
        );

        RecyclerView recyclerFavorite = findViewById(R.id.recyclerFavorite);
        recyclerFavorite.setLayoutManager(new LinearLayoutManager(this));
        recyclerFavorite.setAdapter(new FavoriteAdapter(favorites));

        // 하단 네비게이션
        setupBottomNavigation(R.id.menu_favorite);
    }
}
