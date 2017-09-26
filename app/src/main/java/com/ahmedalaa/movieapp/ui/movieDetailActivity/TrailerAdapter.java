package com.ahmedalaa.movieapp.ui.movieDetailActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ahmedalaa.movieapp.R;
import com.ahmedalaa.movieapp.data.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerAdapterHolder> {
    private final ClickListener clickListener;
    private Context context;
    private List<Trailer> trailers;

    public TrailerAdapter(ClickListener clickListener) {
        trailers = new ArrayList<>();
        this.clickListener = clickListener;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @Override
    public TrailerAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrailerAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TrailerAdapterHolder holder, int position) {
        Picasso.with(context).load("https://img.youtube.com/vi/" + trailers.get(position).getKey() + "/mqdefault.jpg").placeholder(R.drawable.loading)
                .into(holder.trailerImg);
//        holder.trailerName.setText(reviews.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    class TrailerAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trailer_img)
        ImageView trailerImg;
//        @BindView(R.id.trailer_name)
//        TextView trailerName;

        public TrailerAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> clickListener.onTrailerItemClick(trailers.get(getAdapterPosition())));
        }
    }
}
