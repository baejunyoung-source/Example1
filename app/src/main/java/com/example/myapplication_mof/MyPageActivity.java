package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MyPageActivity extends BaseActivity {

    // 1. 사용할 버튼 변수 선언
    private Button profileButton;
    private Button keywordButton;
    private ImageButton imageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        // 하단 네비게이션 (기존 코드 유지)
        setupBottomNavigation(R.id.menu_mypage);

        // 2. XML 레이아웃의 ID와 자바 변수 연결 (findViewById)
        // ※ 만약 mypage.xml에 설정한 ID가 다르면 R.id.뒤의 이름을 수정해 주세요!
        profileButton = findViewById(R.id.profileButton);
        keywordButton = findViewById(R.id.keywordButton);
        imageButton2 = findViewById(R.id.imageButton2);

        // 3. 프로필 수정 화면으로 이동 (profileButton 클릭 시)
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditProfileActivity 로 이동
                Intent intent = new Intent(MyPageActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        // 4. 키워드 알림 관리 화면으로 이동 (keywordButton 클릭 시)
        keywordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // KeywordActivity 로 이동
                Intent intent = new Intent(MyPageActivity.this, KeywordActivity.class);
                startActivity(intent);
            }
        });

        // 5. 상품 관리 화면으로 이동 (imageButton2 클릭 시)
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ProductManagementActivity 로 이동
                Intent intent = new Intent(MyPageActivity.this, ProductManagementActivity.class);
                startActivity(intent);
            }
        });
    }
}