package com.example.myapplication_mof;

import android.os.Bundle;



public class MyPageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        // 하단 네비게이션
        setupBottomNavigation(R.id.menu_mypage);
    }
}
