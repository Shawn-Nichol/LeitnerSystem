package com.example.leitnersystem.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.leitnersystem.Activities.DetailsActivity;
import com.example.leitnersystem.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryFragment extends Fragment {

    @BindView(R.id.button) Button testButton;

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
        if(savedInstanceState != null) {
            // TODO: add saved information here.
        }


        //TODO TEST Button remove after recycler view is setup.
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getActivity(), DetailsActivity.class);



                Toast.makeText(getActivity(), "Button Pressed", Toast.LENGTH_SHORT).show();

                startActivity(myIntent);

            }

        });


        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle currentState) {
        // TODO: fill on saved instance state with data.
    }
}
