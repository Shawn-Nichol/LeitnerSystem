package com.example.leitnersystem.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leitnersystem.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionStudyResultsFragment extends Fragment {

    @BindView(R.id.ad_view)
    AdView adView;

    // Constructor, empty
    public QuestionStudyResultsFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle onSavedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_study_results_layout, viewGroup, false);

        // ButterKnife
        ButterKnife.bind(this, view);

        // AdMob
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        return view;

    }
}
