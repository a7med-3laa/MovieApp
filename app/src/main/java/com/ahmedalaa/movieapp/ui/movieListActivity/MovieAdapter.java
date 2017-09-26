package com.ahmedalaa.movieapp.ui.movieListActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ahmedalaa.movieapp.R;
import com.ahmedalaa.movieapp.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 20/09/2017.
 */


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final ClickListener clickListener;
    private final Context context;
    private List<Movie> mValues;

    public MovieAdapter(ClickListener clickListener, Context context) {
        this.clickListener = clickListener;
        this.context = context;
        mValues = new ArrayList<>();
    }

    public void setData(List<Movie> movies) {
        this.mValues = movies;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Picasso.with(context).load(mValues.get(position).getPosterPath()).into(holder.movieImg);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.movie_img)
        public ImageView movieImg;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            view.setOnClickListener(v -> clickListener.onItemClick(mValues.get(getAdapterPosition())));
        }

    }
}
