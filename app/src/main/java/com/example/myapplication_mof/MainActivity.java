package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<Product> originalProducts;
    private List<Product> displayProducts;
    private HomeProductAdapter productAdapter;
    private TextView txtSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // 홈 상품 더미 데이터
        originalProducts = new ArrayList<>(Arrays.asList(
                new Product("[국내도서] 스프링 부트 4 개발자 되기 + AI: 자바 편", "신선영 지저(글)", "골든래빗(주)", "15,000원", 15000, R.drawable.img_1,
                        "스프링 부트 입문서입니다.\n필기 거의 없고 상태 깨끗합니다.\n성결관 1층에서 직거래 가능합니다.\n\n구매 후 환불은 어렵습니다."),
                new Product("[국내도서] 클린 코드", "로버트 마틴(글)", "인사이트", "18,000원", 18000, R.drawable.cleancode,
                        "클린 코드 원서 번역본입니다.\n형광펜 밑줄 약간 있습니다.\n중간고사 이후 사용하지 않아 판매합니다.\n\n택배 거래도 가능합니다. (배송비 별도)"),
                new Product("[국내도서] 자료구조와 알고리즘 입문", "황정규(글)", "한빛미디어", "12,000원", 12000, R.drawable.structure,
                        "자료구조 수업 교재입니다.\n연필 필기 조금 있으나 지울 수 있습니다.\n\n학교 앞 카페에서 직거래 희망합니다."),
                new Product("[국내도서] 운영체제: 공룡책", "Silberschatz(글)", "퍼스트북", "20,000원", 20000, R.drawable.os,
                        "운영체제 공룡책 10판입니다.\n새 책과 동일한 상태입니다. 한 학기 사용했습니다.\n\n성결대 정문 앞에서 직거래 가능합니다."),
                new Product("[국내도서] 데이터베이스 개론", "이한호(글)", "한빛아카데미", "10,000원", 10000, R.drawable.database,
                        "데이터베이스 개론 3판입니다.\n필기 없고 깨끗한 상태입니다.\n급하게 팔아야 해서 가격 네고 가능합니다.\n\n채팅 주세요!")
        ));

        displayProducts = new ArrayList<>(originalProducts);
        productAdapter = new HomeProductAdapter(displayProducts);

        RecyclerView recyclerHomeProduct = findViewById(R.id.recyclerHomeProduct);
        recyclerHomeProduct.setLayoutManager(new LinearLayoutManager(this));
        recyclerHomeProduct.setAdapter(productAdapter);

        // 카테고리 RecyclerView + 필터링
        List<String> categories = Arrays.asList(
                "전체", "신학대학", "인문대학", "사회과학대학",
                "글로벌 경영기술 대학", "사범대학", "IT공과대학", "예술대학", "파이데이아칼리지"
        );
        RecyclerView recyclerCategory = findViewById(R.id.recyclerCategory);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerCategory.setAdapter(new CategoryAdapter(categories, category -> {
            displayProducts.clear();
            if (category.equals("전체") || category.equals("IT공과대학")) {
                // 전체, IT공과대학: 모든 책 표시
                displayProducts.addAll(originalProducts);
            }
            // 나머지 카테고리: 해당 카테고리에 책이 없으므로 빈 리스트
            productAdapter.notifyDataSetChanged();
        }));

        // 정렬 버튼
        txtSort = findViewById(R.id.txtSort);
        txtSort.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, txtSort);
            popupMenu.getMenu().add(0, 0, 0, "최신순");
            popupMenu.getMenu().add(0, 1, 1, "가격순");
            popupMenu.setOnMenuItemClickListener(item -> {
                List<Product> sorted = new ArrayList<>(originalProducts);
                if (item.getItemId() == 1) {
                    Collections.sort(sorted, (a, b) -> a.getPriceValue() - b.getPriceValue());
                    txtSort.setText("가격순 ▼");
                } else {
                    txtSort.setText("최신순 ▼");
                }
                displayProducts.clear();
                displayProducts.addAll(sorted);
                productAdapter.notifyDataSetChanged();
                return true;
            });
            popupMenu.show();
        });

        // 검색 아이콘 클릭 → 검색 화면
        findViewById(R.id.imgSearch).setOnClickListener(v ->
                startActivity(new Intent(this, SearchActivity.class)));

        // 알림 아이콘 클릭
        findViewById(R.id.imgNotification).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
            startActivity(intent);
        });

        // 하단 네비게이션
        setupBottomNavigation(R.id.menu_home);
    }
}
