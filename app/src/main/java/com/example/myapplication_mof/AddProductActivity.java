package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        findViewById(R.id.imgBack).setOnClickListener(v -> NavigationUtils.goBack(this));
        findViewById(R.id.btnCamera).setOnClickListener(v ->
                startActivity(new Intent(this, AddBarcodeActivity.class)));
        findViewById(R.id.btnSearch).setOnClickListener(v ->
                startActivity(new Intent(this, AddIsbnActivity.class)));
        findViewById(R.id.btnManual).setOnClickListener(v ->
                startActivity(new Intent(this, AddSelfActivity.class)));
    }
}
