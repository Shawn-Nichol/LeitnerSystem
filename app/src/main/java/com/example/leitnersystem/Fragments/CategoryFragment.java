package com.example.leitnersystem.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leitnersystem.Adapters.CategoryAdapter;
import com.example.leitnersystem.R;

import java.util.ArrayList;

import butterknife.ButterKnife;


public class CategoryFragment extends Fragment {

    // Used to story data for recyclerView
    public ArrayList<String> categoryNames;

    // Fragment requires empty constructor.
        public CategoryFragment() {
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

        // Inflate the fragment_category_layout
        View view = inflater.inflate(R.layout.fragment_category_layout, container, false);

        // Fragments need to supply a view root in order to bind ButterKnife.
        ButterKnife.bind(this, view);

        // Loads the saved state, if there is one
        //noinspection StatementWithEmptyBody
//        if(savedInstanceState != null) {
//            // TODO: add saved information here.
//        }


        // TODO: Dummy Data remove after use
        categoryNames = new ArrayList<>();
        for(int i = 0; i  < 40; i++) {
            categoryNames.add(String.valueOf(i));
        }
        Log.d("Shawn", " " + categoryNames);

        // RecyclerView handle.
        RecyclerView recyclerView = view.findViewById(R.id.rv_category);
        // Connect RecyclerView handle to layoutManager, select LayoutManager.
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Specify the RecyclerView adapter.
        CategoryAdapter recyclerViewAdapter = new CategoryAdapter(getActivity(), categoryNames);
        // Set the recyclerView to the adapter.
        recyclerView.setAdapter(recyclerViewAdapter);



        return view;
    }


//    @Override
//    public void onSaveInstanceState(@NonNull Bundle currentState) {
//        // TODO: fill on saved instance state with data.
//    }


}
