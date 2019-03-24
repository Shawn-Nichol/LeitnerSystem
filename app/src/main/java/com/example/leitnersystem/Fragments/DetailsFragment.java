package com.example.leitnersystem.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.leitnersystem.Adapters.CategoryAdapter;
import com.example.leitnersystem.Adapters.DetailsAdapter;
import com.example.leitnersystem.R;


import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment {

    // Used to store data for RecyclerView
    private ArrayList<String> detailsInfo;

    // Buttons
    @BindView(R.id.btn_study) Button btnStudy;
    @BindView(R.id.btn_review) Button btnReview;
    @BindView(R.id.fab_button) FloatingActionButton fab;


    // Fragment empty constructor.
    public DetailsFragment() {
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
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_layout, container, false);

        // ButterKnife requires view to bind in fragments.
        ButterKnife.bind(this, view);

        // TODO Dummy Data remove after creating data
        detailsInfo = new ArrayList<>();
        for(int i = 1; i <= 5; i++) {
            detailsInfo.add(String.valueOf(i));
        }

        Log.d("Shawn", " " + detailsInfo);

        // RecyclerView handle
        RecyclerView recyclerView = view.findViewById(R.id.rv_details);
        // Connect recyclerView to layoutManager, select layoutManager.
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Specify the Recycler View adapter.
        DetailsAdapter recyclerViewAdapter = new DetailsAdapter(getActivity(), detailsInfo);
        // Set the recyclerView to the adapter.
        recyclerView.setAdapter(recyclerViewAdapter);



        /**
         * OnClickListener, when FAB is pressed a template will put to enter a new question.
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "FAB Button2 Pressed", Toast.LENGTH_SHORT).show();
            }
        });


        /**
         * onClickListener, Launch study fragment.
         */
        btnStudy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Create studyFragment objec
                StudyFragment studyFragment = new StudyFragment();

                // FragmentManager handle
                FragmentManager fragmentManager = getFragmentManager();

                // FragmentTransaction handle
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

                // Start fragment transaction.
                fragmentTransaction
                        .replace(R.id.details_activity_container, studyFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

        /**
         * OnClickListener, Launch Review study session.
         */
        btnReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Review Button", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
