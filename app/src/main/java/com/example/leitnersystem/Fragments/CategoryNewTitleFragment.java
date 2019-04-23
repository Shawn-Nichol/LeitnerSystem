package com.example.leitnersystem.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomCategory.Category;
import com.example.leitnersystem.RoomCategory.CategoryViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryNewTitleFragment extends Fragment {

    private final String LOGTAG = "CategoryNewTitleFragment";

    private CategoryViewModel mCategoryViewModel;

    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.new_category_tv_category)
    EditText mEditTitleView;
    @BindView(R.id.ad_view)
    AdView adView;

    // Empty Constructor
    public CategoryNewTitleFragment() {

    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_new_title_layout, container, false);

        Log.d(LOGTAG, "onCreateView");

        // ButterKnife
        ButterKnife.bind(this, view);

        // AdMob
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        // Get new or existing ViewModel from the ViewModel provider.
        mCategoryViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(CategoryViewModel.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "saveButton pressed");
                String newEntry = String.valueOf(mEditTitleView.getText());

                Log.d(LOGTAG, "New db category = " + newEntry);
                if (TextUtils.isEmpty(mEditTitleView.getText())) {
                    Toast.makeText(getActivity(), "No New Entry saved", Toast.LENGTH_SHORT).show();
                } else {
                    Category category = new Category(String.valueOf(mEditTitleView.getText()));
                    Log.d(LOGTAG, "Category  = " + category.toString());

                    mCategoryViewModel.insert(category);
                }


                // ReLaunch CategoryFragment.
                // Create detailsFragment object
                CategoryFragment categoryFragment = new CategoryFragment();

                // FragmentManager handle
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = Objects.requireNonNull(fragmentManager).beginTransaction();
                fragmentTransaction
                        .replace(R.id.activity_main_container, categoryFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
