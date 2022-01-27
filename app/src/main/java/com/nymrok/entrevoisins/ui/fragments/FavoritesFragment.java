package com.nymrok.entrevoisins.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nymrok.entrevoisins.R;
import com.nymrok.entrevoisins.di.DI;
import com.nymrok.entrevoisins.events.EventRemoveFavorite;
import com.nymrok.entrevoisins.models.Neighbour;
import com.nymrok.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private NeighbourApiService API;
    private RecyclerView recyclerView;

    private static final String ARG_FAVORITE = "favoriteStatus";

    public FavoritesFragment() {}

    public static FavoritesFragment newInstance(String favoriteStatus) {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FAVORITE, favoriteStatus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        API = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites_recyclerview, container, false);
        Context c = v.getContext();
        recyclerView = (RecyclerView) v;
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return v;
    }

    private void initList() {
        List<Neighbour> Favorites = API.getFavorites();
        recyclerView.setAdapter(new FavoritesRecyclerViewAdapter(Favorites));
    }

    @Subscribe
    public void onRemoveFavorite(EventRemoveFavorite event) {
        API.switchFav(event.neighbour);
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}