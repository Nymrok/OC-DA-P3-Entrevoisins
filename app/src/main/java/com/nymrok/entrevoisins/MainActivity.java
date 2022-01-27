package com.nymrok.entrevoisins;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.nymrok.entrevoisins.ui.ViewPagerAdapter;
import com.nymrok.entrevoisins.ui.activities.NeighbourAddActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.Tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ViewPager2)
    ViewPager2 mViewPager;

    ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        List<String> tabs = new ArrayList<String>();
        tabs.add("My neighbours");
        tabs.add("Favorites");

        TabLayout tabLayout = findViewById(R.id.Tabs);
        ViewPager2 viewPager2 = findViewById(R.id.ViewPager2);

        mPagerAdapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                tab.setText(tabs.get(position))
        ).attach();
    }

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        NeighbourAddActivity.navigate(this);
    }
}