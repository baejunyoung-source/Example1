package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private boolean liked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        findViewById(R.id.btnBack).setOnClickListener(v -> NavigationUtils.goBack(this));
        findViewById(R.id.btnShare).setOnClickListener(v ->
                Toast.makeText(this, "공유 기능 버튼입니다.", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btnChat).setOnClickListener(v ->
                startActivity(new Intent(this, ChatRoomActivity.class)));

        ImageButton heartButton = findViewById(R.id.btnHeart);
        heartButton.setOnClickListener(v -> {
            liked = !liked;
            heartButton.setImageResource(liked ? R.drawable.img_5 : R.drawable.img_9);
        });

        TextView statusButton = findViewById(R.id.btnStatus);
        statusButton.setOnClickListener(v -> showStatusMenu(statusButton));
    }

    private void showStatusMenu(TextView anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenu().add("판매중");
        popupMenu.getMenu().add("예약중");
        popupMenu.getMenu().add("판매완료");
        popupMenu.setOnMenuItemClickListener(item -> {
            anchor.setText(item.getTitle() + "⌄");
            return true;
        });
        popupMenu.show();
    }
}
