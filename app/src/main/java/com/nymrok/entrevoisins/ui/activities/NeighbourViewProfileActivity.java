package com.nymrok.entrevoisins.ui.activities;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nymrok.entrevoisins.R;
import com.nymrok.entrevoisins.di.DI;
import com.nymrok.entrevoisins.models.Neighbour;
import com.nymrok.entrevoisins.service.NeighbourApiService;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourViewProfileActivity extends AppCompatActivity {

    Neighbour neighbour;
    private NeighbourApiService API;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_page_button_backToList)
    ImageButton backToListBtn;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_page_avatar_banner)
    ImageView avatarBanner;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_page_title_banner)
    TextView titleBanner;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_page_button_addToFav)
    FloatingActionButton addToFavBtn;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_page_links_title)
    TextView linksTitle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_page_links_postalAddress)
    TextView linksPostalAddress;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_page_links_phoneNumber)
    TextView linksPhoneNumber;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_page_links_socialWebsite)
    TextView linksSocialWebsite;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_page_description_content)
    TextView descriptionContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_view_profile);
        ButterKnife.bind(this);
        API = DI.getNeighbourApiService();

        // Recovery of the aimed Neighbour instance
        neighbour = Objects.requireNonNull(getIntent().getExtras()).getParcelable("neighbour");

        // Filling the layout with recovered data
        Glide.with(this).load(neighbour.getAvatarUrl()).into(avatarBanner);
        titleBanner.setText(neighbour.getName());
        linksTitle.setText(neighbour.getName());
        linksPostalAddress.setText(neighbour.getAddress());
        linksPhoneNumber.setText(neighbour.getPhoneNumber());
        linksSocialWebsite.setText(String.format("www.facebook.com/%s", neighbour.getName().toLowerCase()));
        descriptionContent.setText(neighbour.getAboutMe());
        updateFavBtn();

        // Toggle favorite state function
        addToFavBtn.setOnClickListener(v -> {
            API.switchFav(neighbour);
            updateFavBtn();
        });

        // End of this activity
        backToListBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void updateFavBtn() {
        if (neighbour.isFavorite()) {
            addToFavBtn.setImageTintList(ColorStateList.valueOf(Color.rgb(255, 215, 0)));
        } else {
            addToFavBtn.setImageTintList(ColorStateList.valueOf(Color.rgb(128, 128, 128)));
        }
    }
}