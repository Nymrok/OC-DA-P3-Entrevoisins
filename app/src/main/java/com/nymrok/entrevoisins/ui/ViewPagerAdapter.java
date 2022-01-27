package com.nymrok.entrevoisins.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.nymrok.entrevoisins.ui.fragments.FavoritesFragment;
import com.nymrok.entrevoisins.ui.fragments.NeighboursFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private static final Integer NUM_TABS = 2;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 1) {
            return new FavoritesFragment();
        }
        return new NeighboursFragment();
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
