package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<Product> originalProducts;
    private HomeProductAdapter productAdapter;
    private TextView txtSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 카테고리 RecyclerView
        List<String> categories = Arrays.asList(
                "전체", "신학대학", "인문대학", "사회과학대학",
                "글로벌 경영기술 대학", "사범대학", "IT공과대학", "예술대학", "파이데이아칼리지"
        );
        RecyclerView recyclerCategory = findViewById(R.id.recyclerCategory);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerCategory.setAdapter(new CategoryAdapter(categories));

        // 홈 상품 더미 데이터
        originalProducts = new ArrayList<>(Arrays.asList(
                new Product("[국내도서] 스프링 부트 4 개발자 되기 + AI: 자바 편", "신선영 지저(글)", "골든래빗(주)", "15,000원", 15000, R.drawable.img_1),
                new Product("[국내도서] 클린 코드", "로버트 마틴(글)", "인사이트", "18,000원", 18000, R.drawable.cleancode),
                new Product("[국내도서] 자료구조와 알고리즘 입문", "황정규(글)", "한빛미디어", "12,000원", 12000, R.drawable.structure),
                new Product("[국내도서] 운영체제: 공룡책", "Silberschatz(글)", "퍼스트북", "20,000원", 20000, R.drawable.os),
                new Product("[국내도서] 데이터베이스 개론", "이한호(글)", "한빛아카데미", "10,000원", 10000, R.drawable.database)
        ));

        List<Product> displayProducts = new ArrayList<>(originalProducts);
        productAdapter = new HomeProductAdapter(displayProducts);

        RecyclerView recyclerHomeProduct = findViewById(R.id.recyclerHomeProduct);
        recyclerHomeProduct.setLayoutManager(new LinearLayoutManager(this));
        recyclerHomeProduct.setAdapter(productAdapter);

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

        // 하단 네비게이션
        setupBottomNavigation(R.id.menu_home);
    }
}
