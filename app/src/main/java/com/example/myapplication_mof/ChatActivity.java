package com.example.myapplication_mof;

import android.os.Bundle;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class ChatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        List<ChatItem> chatItems = Arrays.asList(
                new ChatItem("주먹밥공이", "2시에 성결관에서 봬요", "1분전", R.drawable.cleancode),
                new ChatItem("책방지기", "감사합니다 잘 받았어요!", "10분전", R.drawable.structure),
                new ChatItem("도서왕", "혹시 직거래 가능하신가요?", "1시간전", R.drawable.os),
                new ChatItem("성결이", "네 괜찮습니다", "3시간전", R.drawable.database),
                new ChatItem("책읽는곰", "상태가 어떤가요?", "어제", R.drawable.cleancode)
        );

        RecyclerView recyclerChat = findViewById(R.id.recyclerChat);
        recyclerChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerChat.setAdapter(new ChatAdapter(chatItems));

        setupBottomNavigation(R.id.menu_chat);
    }
}
