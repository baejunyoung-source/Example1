package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 강제 라이트 모드 — 다크 모드에서 검은 화면 방지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
    }

    protected void setupBottomNavigation(int selectedItemId) {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        if (bottomNav == null) return;

        // 리스너를 먼저 설정한 뒤 선택 항목을 지정해야 한다
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == selectedItemId) {
                return true;
            }

            Intent intent = null;
            if (itemId == R.id.menu_home) {
                intent = new Intent(this, MainActivity.class);
            } else if (itemId == R.id.menu_chat) {
                intent = new Intent(this, ChatActivity.class);
            } else if (itemId == R.id.menu_add) {
                intent = new Intent(this, AddProductActivity.class);
            } else if (itemId == R.id.menu_favorite) {
                intent = new Intent(this, FavoriteActivity.class);
            } else if (itemId == R.id.menu_mypage) {
                intent = new Intent(this, MyPageActivity.class);
            }

            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
            return true;
        });

        bottomNav.setSelectedItemId(selectedItemId);
    }
}
