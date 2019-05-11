package com.example.leitnersystem.Fragments;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
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
import com.example.leitnersystem.RoomCategory.CategoryViewModel;
import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomQuestion.QuestionViewModel;
import com.example.leitnersystem.Widget.AppWidget;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryFragment extends Fragment {

    private final String LOGTAG = "CategoryFragment";

    private CategoryViewModel mCategoryViewModel;
    private QuestionViewModel mQuestionViewModel;

    @BindView(R.id.ad_view)
    AdView adView;

    // Fragment requires empty constructor.
    public CategoryFragment() {
    }

    /**
     * Inflates the fragment_category_layout file
     *
     * @param inflater The LayoutInflater object can be used to inflate any views in the fragment.
     * @param container this is the parent view that the fragment's UI is attached to.
     * @param savedInstanceState if non-null this fragment is being re-constructed from a previous saved state.
     * @return return the view of the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment_category_layout
        View view = inflater.inflate(R.layout.fragment_category_layout, container, false);

        Log.d(LOGTAG, "onCreateVew");

        // Fragments need to supply a view root in order to bind ButterKnife.
        ButterKnife.bind(this, view);

        // AdMob
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        // Adapter handler, specify the handler
        final CategoryAdapter adapter = new CategoryAdapter(getActivity());

        // RecyclerView handler
        RecyclerView recyclerView = view.findViewById(R.id.rv_category);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get new or existing ViewModel from the ViewModel provider.
        mQuestionViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(QuestionViewModel.class);
        mCategoryViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(CategoryViewModel.class);
        // Observer the LiveData, return by get AlphabetizedCategories.
        // The onChanged() fires when the observed data changes and the activity is in the foreground.
        mCategoryViewModel.getAllCategories().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable final List<Category> categories) {
                Log.d(LOGTAG, "" + categories);
                adapter.setTitles(categories);

                // Updates Widget
                Context context = Objects.requireNonNull(getActivity()).getApplication();
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName thisWidget = new ComponentName(context, AppWidget.class);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_stack_view);
            }
        });


        // Used to delete Categories and all the questions in them.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            /**
             * onMove call when Item TouchHelper wants to move the dragged item from its old position
             * to the new position.
             *
             * @param recyclerView The RecyclerView to which the ItemTouchHelper is attached.
             * @param viewHolder The ViewHolder is being dragged by the user.
             * @param viewHolder1 The ViewHolder over which the current active item is being dragged.
             * @return the view of the fragment's UI, or null.
             */
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            /**
             * onSwiped Called when the ViewHolder is swiped by the user.
             *
             * @param viewHolder The ViewHolder which has been swiped by the user.
             * @param direction The direction to which the ViewHolder is swiped(Left or Right).
             */
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mCategoryViewModel.delete(adapter.getCategoryAt(viewHolder.getAdapterPosition()));
                mQuestionViewModel.deleteAllQuestions();
                Toast.makeText(getActivity(), R.string.category_swipe,
                        Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        // FAB Handler
        FloatingActionButton fab = view.findViewById(R.id.fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "FAB pressed");

                // Create detailsFragment object
                CategoryNewTitleFragment newTitleFragment = new CategoryNewTitleFragment();

                // FragmentManager/FragmentTransaction
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = Objects.requireNonNull(fragmentManager).beginTransaction();
                fragmentTransaction
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_up, R.anim.slide_in_right, R.anim.slide_up)
                        .replace(R.id.activity_main_container, newTitleFragment)

                        .addToBackStack(null)
                        .commit();

            }
        });

        return view;
    }
}