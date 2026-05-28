package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
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

        // 뒤로가기 / 공유
        findViewById(R.id.btnBack).setOnClickListener(v -> NavigationUtils.goBack(this));
        findViewById(R.id.btnShare).setOnClickListener(v ->
                Toast.makeText(this, "공유 기능 버튼입니다.", Toast.LENGTH_SHORT).show());

        // 채팅하기
        findViewById(R.id.btnChat).setOnClickListener(v ->
                startActivity(new Intent(this, ChatRoomActivity.class)));

        // 찜하기 토글
        ImageButton heartButton = findViewById(R.id.btnHeart);
        heartButton.setOnClickListener(v -> {
            liked = !liked;
            heartButton.setImageResource(liked ? R.drawable.img_5 : R.drawable.img_9);
        });

        // 판매 상태 변경
        TextView statusButton = findViewById(R.id.btnStatus);
        statusButton.setOnClickListener(v -> showStatusMenu(statusButton));

        // Intent에서 상품 데이터 받기
        Product product = (Product) getIntent().getSerializableExtra("product");
        if (product != null) {
            populateProductData(product);
        }
    }

    private void populateProductData(Product product) {
        // 상품 이미지
        ImageView imgProduct = findViewById(R.id.imgProduct);
        imgProduct.setImageResource(product.getImageResId());

        // 상품 제목
        TextView txtTitle = findViewById(R.id.txtProductTitle);
        txtTitle.setText(product.getTitle());

        // 상품 가격
        TextView txtPrice = findViewById(R.id.txtProductPrice);
        txtPrice.setText(product.getPrice());

        // 상품 설명
        TextView txtDescription = findViewById(R.id.txtProductDescription);
        String desc = product.getDescription();
        if (desc != null && !desc.isEmpty()) {
            txtDescription.setText(desc);
        } else {
            txtDescription.setText(product.getAuthor() + " / " + product.getPublisher()
                    + "\n\n상태 좋습니다. 직거래 가능합니다.");
        }
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
