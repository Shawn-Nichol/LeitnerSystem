package com.example.leitnersystem.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.leitnersystem.R;


import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {

    public DetailsFragment() {
    }

    @BindView(R.id.button_details_fragment) Button btnNextFragment;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_layout, container, false);

        // ButterKnife requires view to bind in fragments.
        ButterKnife.bind(this, view);










        btnNextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Toast.makeText(getActivity(), "Button Pressed 2", Toast.LENGTH_SHORT).show();

                StudyFragment studyFragment = new StudyFragment();

                FragmentManager fragmentManager = getFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction
                        .replace(R.id.details_activity_container, studyFragment)
                        .commit();

            }
        });

        return view;
    }
}
