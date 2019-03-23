package com.example.leitnersystem.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leitnersystem.R;

import butterknife.ButterKnife;

public class StudyFragment extends Fragment {


    public StudyFragment() {
    }

    /**
     * Inflates the fragment_category_layout file
     *
     * @param inflater To return a layout from onCreateView, you can inflate if from a layout resource
     *                 defined in XML.
     * @param container Is the parent ViewGroup in which the fragment is Layout is inserted.
     * @param savedInstanceState is a bundle that provides data about the previous instance of the
     *                          fragment.
     * @return a View that is the root of the fragments layout
     */
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_study_layout, container, false);

        ButterKnife.bind(this, view);





        return view;
    }
}
