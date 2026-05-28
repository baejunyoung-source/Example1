package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChatRoomActivity extends AppCompatActivity {

    private LinearLayout messageContainer;
    private ScrollView messageScroll;
    private EditText editMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        messageContainer = findViewById(R.id.messageContainer);
        messageScroll = findViewById(R.id.messageScroll);
        editMessage = findViewById(R.id.editMessage);

        findViewById(R.id.btnBack).setOnClickListener(v -> NavigationUtils.goBack(this));
        findViewById(R.id.btnAdd).setOnClickListener(v ->
                Toast.makeText(this, "첨부 버튼입니다.", Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnReview).setOnClickListener(v -> {
            Intent intent = new Intent(ChatRoomActivity.this, TemperatureActivity.class);
            startActivity(intent);
        });

        ImageButton sendButton = findViewById(R.id.btnSend);
        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String message = editMessage.getText().toString().trim();
        if (message.isEmpty()) {
            return;
        }

        TextView bubble = new TextView(this);
        bubble.setText(message);
        bubble.setTextColor(0xFFFFFFFF);
        bubble.setTextSize(13);
        bubble.setTypeface(bubble.getTypeface(), android.graphics.Typeface.BOLD);
        bubble.setBackgroundResource(R.drawable.bg_chat_sent);
        bubble.setMaxWidth((int) (getResources().getDisplayMetrics().widthPixels * 0.72f));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.END;
        params.setMargins(dp(56), dp(8), dp(34), 0);
        messageContainer.addView(bubble, params);

        editMessage.setText("");
        messageScroll.post(() -> messageScroll.fullScroll(ScrollView.FOCUS_DOWN));
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }
}