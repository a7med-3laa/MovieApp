package com.ahmedalaa.movieapp.ui.movieDetailActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmedalaa.movieapp.R;
import com.ahmedalaa.movieapp.data.Review;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    Context context;
    private List<Review> reviews;

    public ReviewAdapter() {
        reviews = new ArrayList<>();
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @Override
    public ReviewAdapter.ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReviewAdapter.ReviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ReviewHolder holder, int position) {
        holder.reviewName.setText(reviews.get(position).getAuthor());
        holder.reviewDesc.setText(reviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.review_name)
        TextView reviewName;
        @BindView(R.id.review_desc)
        TextView reviewDesc;

        public ReviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
