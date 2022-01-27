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
import com.nymrok.entrevoisins.events.EventDeleteNeighbour;
import com.nymrok.entrevoisins.events.EventViewProfileNeighbour;
import com.nymrok.entrevoisins.models.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class NeighboursRecyclerViewAdapter extends RecyclerView.Adapter<NeighboursRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighboursDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout mNeighbourLine;
        public ImageView mNeighbourAvatar;
        public TextView mNeighbourName;
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            mNeighbourLine = (ConstraintLayout) view.findViewById(R.id.fragment_neighbour);
            mNeighbourAvatar = (ImageView) view.findViewById(R.id.fragment_neighbour_picture);
            mNeighbourName = (TextView) view.findViewById(R.id.fragment_neighbour_name);
            mDeleteButton = (ImageButton) view.findViewById(R.id.fragment_neighbour_delete_button);
        }
    }

    public NeighboursRecyclerViewAdapter(List<Neighbour> NeighboursDataSet) {
        mNeighboursDataSet = NeighboursDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_neighbours, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Neighbour neighbour = mNeighboursDataSet.get(position);
        viewHolder.mNeighbourName.setText(neighbour.getName());
        Glide.with(viewHolder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.mNeighbourAvatar);

        viewHolder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventDeleteNeighbour(neighbour));
            }
        });

        viewHolder.mNeighbourLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventViewProfileNeighbour(neighbour));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNeighboursDataSet.size();
    }
}