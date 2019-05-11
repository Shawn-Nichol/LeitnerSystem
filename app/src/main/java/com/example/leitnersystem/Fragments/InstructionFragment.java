package com.example.leitnersystem.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leitnersystem.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionFragment extends Fragment {

    @BindView(R.id.ad_view)
    AdView adView;

    /**
     * Inflates the fragment_instructions file
     *
     * @param inflater The LayoutInflater object can be used to inflate any views in the fragment.
     * @param container this is the parent view that the fragment's UI is attached to.
     * @param savedInstanceState if non-null this fragment is being re-constructed from a previous saved state.
     * @return return the view of the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instructions, container, false);

        //Butter Knife
        ButterKnife.bind(this, view);

        // AdMob
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        return view;
    }
}
