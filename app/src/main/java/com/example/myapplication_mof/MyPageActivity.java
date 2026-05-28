package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MyPageActivity extends BaseActivity {

    private Button profileButton;
    private Button keywordButton;
    private ImageButton imageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        setupBottomNavigation(R.id.menu_mypage);

        profileButton = findViewById(R.id.profileButton);
        keywordButton = findViewById(R.id.keywordButton);
        imageButton2 = findViewById(R.id.imageButton2);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        keywordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // KeywordActivity 로 이동
                Intent intent = new Intent(MyPageActivity.this, KeywordActivity.class);
                startActivity(intent);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, ProductManagementActivity.class);
                startActivity(intent);
            }
        });
    }
}