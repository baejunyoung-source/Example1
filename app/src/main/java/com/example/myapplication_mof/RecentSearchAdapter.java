package com.example.myapplication_mof;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.ViewHolder> {

    private List<String> searchTerms;
    private OnDeleteClickListener deleteListener;
    private OnItemClickListener itemClickListener;

    public interface OnDeleteClickListener {
        void onDelete(int position);
    }

    public interface OnItemClickListener {
        void onItemClick(String term);
    }

    public RecentSearchAdapter(List<String> searchTerms, OnDeleteClickListener deleteListener, OnItemClickListener itemClickListener) {
        this.searchTerms = searchTerms;
        this.deleteListener = deleteListener;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recent_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String term = searchTerms.get(position);
        holder.txtSearch.setText(term);

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(term);
            }
        });

        holder.imgDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchTerms.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSearch;
        ImageView imgDelete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSearch = itemView.findViewById(R.id.txtSearch);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
