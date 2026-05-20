package com.example.myapplication_mof;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewHolder> {

    private List<Product> products;

    public HomeProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.txtTitle.setText(product.getTitle());
        holder.txtSeller.setText(product.getSeller());
        holder.txtPrice.setText(product.getPrice());
        holder.imgBook.setImageResource(product.getImageResId());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBook;
        TextView txtTitle;
        TextView txtSeller;
        TextView txtPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtSeller = itemView.findViewById(R.id.txtSeller);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
