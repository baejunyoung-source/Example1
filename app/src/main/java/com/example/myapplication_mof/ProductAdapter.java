package com.example.myapplication_mof;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ProductItem> productList;

    public ProductAdapter(Context context, ArrayList<ProductItem> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() { return productList.size(); }

    @Override
    public Object getItem(int position) { return productList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_product_management, parent, false);
        }

        ImageView ivProduct = convertView.findViewById(R.id.imageView3);
        TextView tvStatus = convertView.findViewById(R.id.textView8);
        TextView tvTitle = convertView.findViewById(R.id.textView9);
        TextView tvAuthor = convertView.findViewById(R.id.textView10);
        TextView tvPublisher = convertView.findViewById(R.id.textView11);
        TextView tvPrice = convertView.findViewById(R.id.textView12);

        ProductItem item = productList.get(position);

        ivProduct.setImageResource(item.getImageResId());
        tvTitle.setText(item.getTitle());
        tvPrice.setText(item.getPrice());

        if (item.isSoldOut()) {
            tvStatus.setText("거래완료");
            tvStatus.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
        } else {
            tvStatus.setText("거래중");
            tvStatus.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
        }

        if (item.getAuthor() != null && !item.getAuthor().isEmpty()) {
            tvAuthor.setText(item.getAuthor());
        } else {
            tvAuthor.setText("");
        }

        if (item.getPublisher() != null && !item.getPublisher().isEmpty()) {
            tvPublisher.setText(item.getPublisher());
        } else {
            tvPublisher.setText("");
        }

        return convertView;
    }
}
