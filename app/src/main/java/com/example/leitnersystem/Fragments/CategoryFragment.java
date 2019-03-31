package com.example.leitnersystem.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leitnersystem.Adapters.CategoryAdapter;
import com.example.leitnersystem.RoomCategory.Category;
import com.example.leitnersystem.RoomCategory.CategoryViewModel;
import com.example.leitnersystem.R;

import java.util.List;

import butterknife.ButterKnife;


public class CategoryFragment extends Fragment {

    public static final int NEW_TITLE_ACTIVITY_REQUEST_CODE = 1;

    private CategoryViewModel mCategoryViewModel;

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

        // RecyclerView handler, specify the layout
        RecyclerView recyclerView = view.findViewById(R.id.rv_category);

        // Adapter handler, specify the handler
        final CategoryAdapter adapter = new CategoryAdapter(getActivity());

        // set adapter to RecyclerView
        recyclerView.setAdapter(adapter);

        // set the LayoutManager type for the recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get new or existing ViewModel from the ViewModel provider.
        mCategoryViewModel = ViewModelProviders.of(getActivity()).get(CategoryViewModel.class);

        // Observer the LiveData, return by get AlphabetizedWords.
        // The onChanged() fires when the observed data changes and the activity is in the foreground.
        mCategoryViewModel.getAllCategories().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable final List<Category> categories) {
                adapter.setTitles(categories);
            }
        });

        // FAB Handler
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_button);

        /**
         * setOnClickListener, will launch new window to enter title for a new Category.
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create detailsFragment object
            CategoryNewTitleFragment newTitleFragment = new CategoryNewTitleFragment();

            // FragmentManager handle
            FragmentManager fragmentManager = getFragmentManager();

            // FragmentTransaction handle
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

            // Start fragment transaction.
            fragmentTransaction
                    .replace(R.id.activity_main_container, newTitleFragment)
                    .addToBackStack(null)
                    .commit();

            }
        });

        return view;
    }
}
