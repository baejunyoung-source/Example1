package com.example.myapplication_mof;

import android.os.Bundle;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class FavoriteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_page);

        // 찜 목록 더미 데이터 (홈 화면 상품과 동일한 데이터)
        List<Product> favorites = Arrays.asList(
                new Product("[국내도서] 클린 코드", "로버트 마틴(글)", "인사이트", "18,000원", 18000, R.drawable.cleancode,
                        "클린 코드 원서 번역본입니다.\n형광펜 밑줄 약간 있습니다.\n중간고사 이후 사용하지 않아 판매합니다.\n\n택배 거래도 가능합니다. (배송비 별도)"),
                new Product("[국내도서] 자료구조와 알고리즘 입문", "황정규(글)", "한빛미디어", "12,000원", 12000, R.drawable.structure,
                        "자료구조 수업 교재입니다.\n연필 필기 조금 있으나 지울 수 있습니다.\n\n학교 앞 카페에서 직거래 희망합니다."),
                new Product("[국내도서] 데이터베이스 개론", "이한호(글)", "한빛아카데미", "10,000원", 10000, R.drawable.database,
                        "데이터베이스 개론 3판입니다.\n필기 없고 깨끗한 상태입니다.\n급하게 팔아야 해서 가격 네고 가능합니다.\n\n채팅 주세요!")
        );

        RecyclerView recyclerFavorite = findViewById(R.id.recyclerFavorite);
        recyclerFavorite.setLayoutManager(new LinearLayoutManager(this));
        recyclerFavorite.setAdapter(new FavoriteAdapter(favorites));

        // 하단 네비게이션
        setupBottomNavigation(R.id.menu_favorite);
    }
}
