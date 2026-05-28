package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductManagementActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private Button btnAddProduct, btnSelling, btnSoldOut;
    private ListView listView;

    private ProductAdapter adapter;
    private ArrayList<ProductItem> allProductsList;
    private ArrayList<ProductItem> displayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);

        btnBack = findViewById(R.id.btnBack);
        btnAddProduct = findViewById(R.id.button4);
        btnSelling = findViewById(R.id.button2);
        btnSoldOut = findViewById(R.id.button3);
        listView = findViewById(R.id.listViewProducts);

        allProductsList = new ArrayList<>();
        displayList = new ArrayList<>();

        allProductsList.add(new ProductItem(R.drawable.img_1, false, "[국내도서] 스프링 부트 4 개발자 되기 + AI", "신성연 저자(글)", "골든래빗(주)", "15,000원"));
        allProductsList.add(new ProductItem(R.drawable.os, false, "운영체제", "Abraham Silberschatz , Peter Baer Galvin , Greg Gagne 저자(글) · 박민규 번역", "퍼스트북", "20,000원"));
        allProductsList.add(new ProductItem(R.drawable.cleancode, false, "Clean Code", "Robert Martin 저자(글)", "Addison-Wesley Professional", "80,000원"));

        adapter = new ProductAdapter(this, displayList);
        listView.setAdapter(adapter);
        filterProducts(false);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagementActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });

        btnSelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterProducts(false);
            }
        });

        btnSoldOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterProducts(true);
            }
        });
    }

    private void filterProducts(boolean isSoldOutTarget) {
        displayList.clear();

        for (ProductItem item : allProductsList) {
            if (item.isSoldOut() == isSoldOutTarget) {
                displayList.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
