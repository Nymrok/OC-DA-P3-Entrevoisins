package com.nymrok.entrevoisins.ui.activities;

import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.nymrok.entrevoisins.R;
import com.nymrok.entrevoisins.di.DI;
import com.nymrok.entrevoisins.models.Neighbour;
import com.nymrok.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NeighbourAddActivity extends AppCompatActivity {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.nameLyt)
    TextInputLayout nameInput;
    @BindView(R.id.phoneNumberLyt)
    TextInputLayout phoneInput;
    @BindView(R.id.addressLyt)
    TextInputLayout addressInput;
    @BindView(R.id.aboutMeLyt)
    TextInputLayout aboutMeInput;
    @BindView(R.id.create)
    MaterialButton addButton;

    private NeighbourApiService mApiService;
    private String mNeighbourImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_add);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getNeighbourApiService();
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mNeighbourImage = randomImage();
        Glide.with(this).load(mNeighbourImage).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform()).into(avatar);
        nameInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                addButton.setEnabled(s.length() > 0);
            }
        });

    }

    @OnClick(R.id.create)
    void addNeighbour() {
        Neighbour neighbour = new Neighbour(
                System.currentTimeMillis(),
                nameInput.getEditText().getText().toString(),
                mNeighbourImage,
                addressInput.getEditText().getText().toString(),
                phoneInput.getEditText().getText().toString(),
                aboutMeInput.getEditText().getText().toString(),
                false
        );
        mApiService.createNeighbour(neighbour);
        finish();
    }

    /**
     * Generate a random image. Useful to mock image picker
     * @return String
     */
    String randomImage() {
        return "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, NeighbourAddActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}
