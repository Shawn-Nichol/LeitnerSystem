package com.example.leitnersystem.Fragments;

import android.app.Application;
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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.leitnersystem.Adapters.CategoryAdapter;
import com.example.leitnersystem.RoomCategory.Category;
import com.example.leitnersystem.RoomCategory.CategoryRepository;
import com.example.leitnersystem.RoomCategory.CategoryViewModel;
import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomQuestion.QuestionViewModel;

import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;


public class CategoryFragment extends Fragment {

    private final String LOGTAG = "CategoryFragment";

    private CategoryViewModel mCategoryViewModel;
    private QuestionViewModel mQuestionViewModel;

    public CategoryRepository categoryRepository;

    // Fragment requires empty constructor.
    public CategoryFragment() {
    }


    /**
     * Inflates the fragment_category_layout file
     *
     * @param inflater           To return a layout from onCreateView, you can inflate if from a layout resource
     *                           defined in XML.
     * @param container          Is the parent ViewGroup in which the fragment is Layout is inserted.
     * @param savedInstanceState is a bundle that provides data about the previous instance of the
     *                           fragment.
     * @return a View that is the root of the fragments layout
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOGTAG, "onCreateVew");

        // Inflate the fragment_category_layout
        View view = inflater.inflate(R.layout.fragment_category_layout, container, false);

        // Fragments need to supply a view root in order to bind ButterKnife.
        ButterKnife.bind(this, view);

        // Adapter handler, specify the handler
        final CategoryAdapter adapter = new CategoryAdapter(getActivity());

        // RecyclerView handler
        RecyclerView recyclerView = view.findViewById(R.id.rv_category);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get new or existing ViewModel from the ViewModel provider.
        mCategoryViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(CategoryViewModel.class);
        mQuestionViewModel = ViewModelProviders.of(getActivity()).get(QuestionViewModel.class);

        // Observer the LiveData, return by get AlphabetizedCategories.
        // The onChanged() fires when the observed data changes and the activity is in the foreground.
        mCategoryViewModel.getAllCategories().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable final List<Category> categories) {
                Log.d(LOGTAG, "" + categories);
                adapter.setTitles(categories);
            }
        });


        // Used to delete Categories and all the questions in them.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mCategoryViewModel.delete(adapter.getCategoryAt(viewHolder.getAdapterPosition()));
                mQuestionViewModel.deleteAllQuestions();
                Toast.makeText(getActivity(), "Category and all questions in the category deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        // FAB Handler
        FloatingActionButton fab = view.findViewById(R.id.fab_button);


        // setOnClickListener, will launch new window to enter title for a new Category.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOGTAG, "FAB pressed");

                // Create detailsFragment object
                CategoryNewTitleFragment newTitleFragment = new CategoryNewTitleFragment();

                // FragmentManager/FragmentTransaction
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = Objects.requireNonNull(fragmentManager).beginTransaction();
                fragmentTransaction
                        .replace(R.id.activity_main_container, newTitleFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

        return view;
    }
}




















































