package com.nymrok.entrevoisins.ui.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nymrok.entrevoisins.R;
import com.nymrok.entrevoisins.events.EventRemoveFavorite;
import com.nymrok.entrevoisins.events.EventViewProfileNeighbour;
import com.nymrok.entrevoisins.models.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter<FavoritesRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mFavoritesDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout mFavoriteLine;
        public ImageView mFavoriteAvatar;
        public TextView mFavoriteName;
        public ImageButton mFavoriteDeleteButton;

        public ViewHolder(View view) {
            super(view);
            mFavoriteLine = (ConstraintLayout) view.findViewById(R.id.fragment_favorite);
            mFavoriteAvatar = (ImageView) view.findViewById(R.id.fragment_favorite_picture);
            mFavoriteName = (TextView) view.findViewById(R.id.fragment_favorite_name);
            mFavoriteDeleteButton = (ImageButton) view.findViewById(R.id.fragment_favorite_delete_button);
        }
    }

    public FavoritesRecyclerViewAdapter(List<Neighbour> NeighboursDataSet) {
        mFavoritesDataSet = NeighboursDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_favorites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Neighbour neighbour = mFavoritesDataSet.get(position);
        viewHolder.mFavoriteName.setText(neighbour.getName());
        Glide.with(viewHolder.mFavoriteAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.mFavoriteAvatar);

        viewHolder.mFavoriteDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventRemoveFavorite(neighbour));
            }
        });

        viewHolder.mFavoriteLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventViewProfileNeighbour(neighbour));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFavoritesDataSet.size();
    }
}