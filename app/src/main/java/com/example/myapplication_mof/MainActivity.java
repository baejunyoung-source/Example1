package com.example.myapplication_mof;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        List<String> categories = Arrays.asList("전체", "컴퓨터", "경영", "의학", "법학", "공학", "인문", "사회");

        RecyclerView recyclerCategory = findViewById(R.id.recyclerCategory);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerCategory.setAdapter(new CategoryAdapter(categories));

        // 홈 상품 RecyclerView
        List<Product> products = Arrays.asList(
                new Product("[국내도서] 스프링 부트 4", "신설영 저자", "15,000원", R.drawable.ic_launcher_background),
                new Product("[국내도서] 클린 코드", "로버트 마틴 저자", "18,000원", R.drawable.ic_launcher_background),
                new Product("[국내도서] 자료구조 입문", "황정규 저자", "12,000원", R.drawable.ic_launcher_background),
                new Product("[국내도서] 운영체제", "공룡책", "20,000원", R.drawable.ic_launcher_background),
                new Product("[국내도서] 데이터베이스", "이한호 저자", "10,000원", R.drawable.ic_launcher_background)
        );

        RecyclerView recyclerHomeProduct = findViewById(R.id.recyclerHomeProduct);
        recyclerHomeProduct.setLayoutManager(new LinearLayoutManager(this));
        recyclerHomeProduct.setAdapter(new HomeProductAdapter(products));
    }
}
