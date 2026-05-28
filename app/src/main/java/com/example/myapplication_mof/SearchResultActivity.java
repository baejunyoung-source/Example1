package com.example.myapplication_mof;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    public static final String EXTRA_QUERY = "extra_query";

    private List<ProductData> allProductData;
    private String currentQuery;
    private String currentFilter = "전체"; // 전체, 도서명, 강의명, 저자
    private HomeProductAdapter adapter;
    private List<Product> displayResults;

    private TextView chipAll, chipTitle, chipLecture, chipAuthor;

    // 강의명 정보를 포함한 확장 데이터
    static class ProductData {
        Product product;
        String lectureName;

        ProductData(Product product, String lectureName) {
            this.product = product;
            this.lectureName = lectureName;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        initProductData();

        currentQuery = getIntent().getStringExtra(EXTRA_QUERY);
        if (currentQuery == null || currentQuery.trim().isEmpty()) {
            currentQuery = "";
        }

        TextView queryText = findViewById(R.id.txtSearchResultQuery);
        TextView alertText = findViewById(R.id.txtAlert);
        queryText.setText(currentQuery);
        alertText.setText(currentQuery + " 알림 받기");

        // 버튼들
        findViewById(R.id.btnBack).setOnClickListener(v -> NavigationUtils.goBack(this));
        findViewById(R.id.btnClose).setOnClickListener(v -> NavigationUtils.goBack(this));
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            queryText.setText("");
            alertText.setText("알림 받기");
        });
        findViewById(R.id.btnAlert).setOnClickListener(v ->
                Toast.makeText(this, "알림이 설정되었습니다.", Toast.LENGTH_SHORT).show());

        // 상단 검색바 클릭 → 검색 화면으로 이동
        findViewById(R.id.searchBox).setOnClickListener(v ->
                startActivity(new Intent(this, SearchActivity.class)));

        // 검색 결과 RecyclerView
        displayResults = new ArrayList<>();
        adapter = new HomeProductAdapter(displayResults);
        RecyclerView recyclerSearchResults = findViewById(R.id.recyclerSearchResults);
        recyclerSearchResults.setLayoutManager(new LinearLayoutManager(this));
        recyclerSearchResults.setAdapter(adapter);

        // 칩 필터 설정
        chipAll = findViewById(R.id.chip_all);
        chipTitle = findViewById(R.id.chip_title);
        chipLecture = findViewById(R.id.chip_lecture);
        chipAuthor = findViewById(R.id.chip_author);

        chipAll.setOnClickListener(v -> selectChip("전체"));
        chipTitle.setOnClickListener(v -> selectChip("도서명"));
        chipLecture.setOnClickListener(v -> selectChip("강의명"));
        chipAuthor.setOnClickListener(v -> selectChip("저자"));

        // 초기 검색 실행
        selectChip("전체");
    }

    private void selectChip(String filter) {
        currentFilter = filter;

        // 칩 스타일 업데이트
        setChipSelected(chipAll, filter.equals("전체"));
        setChipSelected(chipTitle, filter.equals("도서명"));
        setChipSelected(chipLecture, filter.equals("강의명"));
        setChipSelected(chipAuthor, filter.equals("저자"));

        // 필터링 실행
        performSearch();
    }

    private void setChipSelected(TextView chip, boolean selected) {
        if (selected) {
            chip.setBackgroundResource(R.drawable.bg_chip_selected);
            chip.setTextColor(0xFFFFFFFF);
        } else {
            chip.setBackgroundResource(R.drawable.bg_chip_unselected);
            chip.setTextColor(0xFF000000);
        }
    }

    private void performSearch() {
        displayResults.clear();

        if (currentQuery.isEmpty()) {
            for (ProductData data : allProductData) {
                displayResults.add(data.product);
            }
        } else {
            String lowerQuery = currentQuery.toLowerCase();
            for (ProductData data : allProductData) {
                boolean match = false;
                switch (currentFilter) {
                    case "도서명":
                        match = data.product.getTitle().toLowerCase().contains(lowerQuery);
                        break;
                    case "강의명":
                        match = data.lectureName.toLowerCase().contains(lowerQuery);
                        break;
                    case "저자":
                        match = data.product.getAuthor().toLowerCase().contains(lowerQuery);
                        break;
                    default: // 전체
                        match = data.product.getTitle().toLowerCase().contains(lowerQuery) ||
                                data.product.getAuthor().toLowerCase().contains(lowerQuery) ||
                                data.product.getPublisher().toLowerCase().contains(lowerQuery) ||
                                data.lectureName.toLowerCase().contains(lowerQuery) ||
                                data.product.getDescription().toLowerCase().contains(lowerQuery);
                        break;
                }
                if (match) {
                    displayResults.add(data.product);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    private void initProductData() {
        allProductData = new ArrayList<>();

        // 스프링 부트 관련 4권 (동일 도서, 다른 판매자)
        allProductData.add(new ProductData(
                new Product("[국내도서] 스프링 부트 4 개발자 되기 + AI: 자바 편", "신선영 지저(글)", "골든래빗(주)", "15,000원", 15000, R.drawable.img_1,
                        "스프링 부트 입문서입니다.\n필기 거의 없고 상태 깨끗합니다.\n성결관 1층에서 직거래 가능합니다.\n\n구매 후 환불은 어렵습니다."),
                "웹 프로그래밍"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 스프링 부트 4 개발자 되기 + AI: 자바 편", "신선영 지저(글)", "골든래빗(주)", "13,000원", 13000, R.drawable.img_1,
                        "스프링 부트 입문서입니다.\n밑줄 약간 있습니다.\n한 학기 수업용으로 구매했습니다.\n\n직거래 희망합니다."),
                "웹 프로그래밍"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 스프링 부트 4 개발자 되기 + AI: 자바 편", "신선영 지저(글)", "골든래빗(주)", "12,000원", 12000, R.drawable.img_1,
                        "스프링 부트 입문서입니다.\n새 책 상태 그대로입니다.\n\n택배 가능합니다."),
                "소프트웨어공학"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 스프링 부트 4 개발자 되기 + AI: 자바 편", "신선영 지저(글)", "골든래빗(주)", "10,000원", 10000, R.drawable.img_1,
                        "스프링 부트 입문서입니다.\n독학용으로 사용했습니다.\n\n가격 네고 가능합니다."),
                "자바 프로그래밍"));

        // 클린 코드 관련 4권 (동일 도서, 다른 판매자)
        allProductData.add(new ProductData(
                new Product("[국내도서] 클린 코드", "로버트 마틴(글)", "인사이트", "18,000원", 18000, R.drawable.cleancode,
                        "클린 코드 원서 번역본입니다.\n형광펜 밑줄 약간 있습니다.\n중간고사 이후 사용하지 않아 판매합니다.\n\n택배 거래도 가능합니다. (배송비 별도)"),
                "소프트웨어공학"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 클린 코드", "로버트 마틴(글)", "인사이트", "15,000원", 15000, R.drawable.cleancode,
                        "클린 코드 서적입니다.\n상태 매우 좋습니다.\n영어 원서 수업에서 사용했습니다.\n\n직거래만 가능합니다."),
                "소프트웨어공학"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 클린 코드", "로버트 마틴(글)", "인사이트", "14,000원", 14000, R.drawable.cleancode,
                        "클린 코드 서적입니다.\n소프트웨어 설계 수업 교재였습니다.\n필기 없습니다.\n\n택배 가능합니다."),
                "소프트웨어 설계"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 클린 코드", "로버트 마틴(글)", "인사이트", "12,000원", 12000, R.drawable.cleancode,
                        "클린 코드 서적입니다.\n프로페셔널 프로그래머의 길을 제시합니다.\n상태 양호합니다."),
                "캡스톤디자인"));

        // 자료구조 관련 4권 (동일 도서, 다른 판매자)
        allProductData.add(new ProductData(
                new Product("[국내도서] 자료구조와 알고리즘 입문", "황정규(글)", "한빛미디어", "12,000원", 12000, R.drawable.structure,
                        "자료구조 수업 교재입니다.\n연필 필기 조금 있으나 지울 수 있습니다.\n\n학교 앞 카페에서 직거래 희망합니다."),
                "자료구조"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 자료구조와 알고리즘 입문", "황정규(글)", "한빛미디어", "10,000원", 10000, R.drawable.structure,
                        "자료구조 수업 교재입니다.\n1학기 수업 교재로 사용했습니다.\n깨끗한 상태입니다."),
                "자료구조"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 자료구조와 알고리즘 입문", "황정규(글)", "한빛미디어", "9,000원", 9000, R.drawable.structure,
                        "자료구조 수업 교재입니다.\n코딩 테스트 준비용으로 좋습니다.\n\n직거래 가능합니다."),
                "알고리즘"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 자료구조와 알고리즘 입문", "황정규(글)", "한빛미디어", "8,000원", 8000, R.drawable.structure,
                        "자료구조 수업 교재입니다.\n교양 수업 교재로 사용했습니다.\n필기 없습니다."),
                "자료구조"));

        // 운영체제 관련 4권 (동일 도서, 다른 판매자)
        allProductData.add(new ProductData(
                new Product("[국내도서] 운영체제: 공룡책", "Silberschatz(글)", "퍼스트북", "20,000원", 20000, R.drawable.os,
                        "운영체제 공룡책 10판입니다.\n새 책과 동일한 상태입니다. 한 학기 사용했습니다.\n\n성결대 정문 앞에서 직거래 가능합니다."),
                "운영체제"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 운영체제: 공룡책", "Silberschatz(글)", "퍼스트북", "18,000원", 18000, R.drawable.os,
                        "운영체제 공룡책 10판입니다.\n연필 밑줄 있습니다.\n\n가격 협의 가능합니다."),
                "운영체제"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 운영체제: 공룡책", "Silberschatz(글)", "퍼스트북", "15,000원", 15000, R.drawable.os,
                        "운영체제 공룡책 10판입니다.\n입문용으로 적합합니다.\n상태 깨끗합니다.\n\n직거래 희망합니다."),
                "운영체제"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 운영체제: 공룡책", "Silberschatz(글)", "퍼스트북", "13,000원", 13000, R.drawable.os,
                        "운영체제 공룡책 10판입니다.\n운영체제 심화 수업에서 사용했습니다.\n\n택배 거래 가능합니다."),
                "운영체제 심화"));

        // 데이터베이스 관련 4권 (동일 도서, 다른 판매자)
        allProductData.add(new ProductData(
                new Product("[국내도서] 데이터베이스 개론", "이한호(글)", "한빛아카데미", "10,000원", 10000, R.drawable.database,
                        "데이터베이스 개론 3판입니다.\n필기 없고 깨끗한 상태입니다.\n급하게 팔아야 해서 가격 네고 가능합니다.\n\n채팅 주세요!"),
                "데이터베이스"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 데이터베이스 개론", "이한호(글)", "한빛아카데미", "9,000원", 9000, R.drawable.database,
                        "데이터베이스 개론 3판입니다.\n데이터베이스 수업 보조 교재로 사용했습니다.\n상태 양호합니다."),
                "데이터베이스"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 데이터베이스 개론", "이한호(글)", "한빛아카데미", "8,000원", 8000, R.drawable.database,
                        "데이터베이스 개론 3판입니다.\n대학원 수업에서 사용했습니다.\n형광펜 밑줄 있습니다.\n\n직거래 희망합니다."),
                "데이터베이스 설계"));
        allProductData.add(new ProductData(
                new Product("[국내도서] 데이터베이스 개론", "이한호(글)", "한빛아카데미", "7,000원", 7000, R.drawable.database,
                        "데이터베이스 개론 3판입니다.\n빅데이터 수업 참고 교재였습니다.\n새 책 상태입니다.\n\n택배 가능합니다."),
                "빅데이터"));
    }
}
