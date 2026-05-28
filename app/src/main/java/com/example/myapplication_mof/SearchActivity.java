package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends BaseActivity {

    private List<String> recentSearches;
    private RecentSearchAdapter recentSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 카테고리 RecyclerView
        List<String> categories = Arrays.asList(
                "전체", "신학대학", "인문대학", "사회과학대학",
                "글로벌 경영기술 대학", "사범대학", "IT공과대학", "예술대학", "파이데이아칼리지"
        );
        RecyclerView recyclerCategory = findViewById(R.id.recyclerCategory);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerCategory.setAdapter(new CategoryAdapter(categories));

        // 최근 검색어
        recentSearches = new ArrayList<>(Arrays.asList(
                "스프링 부트", "자료구조", "운영체제", "클린 코드", "데이터베이스"
        ));
        RecyclerView recyclerRecentSearch = findViewById(R.id.recyclerRecentSearch);
        recyclerRecentSearch.setLayoutManager(new LinearLayoutManager(this));
        recentSearchAdapter = new RecentSearchAdapter(recentSearches,
                // 삭제 콜백
                position -> {
                    recentSearches.remove(position);
                    recentSearchAdapter.notifyItemRemoved(position);
                    recentSearchAdapter.notifyItemRangeChanged(position, recentSearches.size());
                },
                // 클릭 콜백 → 검색 결과 화면으로 이동
                term -> navigateToSearchResult(term)
        );
        recyclerRecentSearch.setAdapter(recentSearchAdapter);

        // 최근 본 책
        RecyclerView recyclerRecentBook = findViewById(R.id.recyclerRecentBook);
        recyclerRecentBook.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // 전체 삭제
        findViewById(R.id.txtDeleteAll).setOnClickListener(v -> {
            recentSearches.clear();
            recentSearchAdapter.notifyDataSetChanged();
        });

        // 검색 기능
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                navigateToSearchResult(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // 하단 네비게이션
        setupBottomNavigation(R.id.menu_home);
    }

    private void navigateToSearchResult(String query) {
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra(SearchResultActivity.EXTRA_QUERY, query);
        startActivity(intent);
    }
}
