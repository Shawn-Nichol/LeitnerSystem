package com.example.leitnersystem.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leitnersystem.R;
import com.example.leitnersystem.RoomCategory.Category;
import com.example.leitnersystem.RoomCategory.CategoryViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryNewTitleFragment extends Fragment {

    private String LOGTAG = "CategoryNewTitleFragment";

    private CategoryViewModel mCategoryViewModel;

    @BindView(R.id.btn_save) Button btnSave;
    @BindView(R.id.edit_title) EditText mEditTitleView;

    // Empty Constructor
    public CategoryNewTitleFragment() {

    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOGTAG, "onCreateView");

        View view = inflater.inflate(R.layout.fragment_category_new_title_layout, container, false);

        // ButterKnife
        ButterKnife.bind(this, view);

        // Get new or existing ViewModel from the ViewModel provider.
        mCategoryViewModel = ViewModelProviders.of(getActivity()).get(CategoryViewModel.class);

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG, "saveButton pressed");
                String newEntery = String.valueOf(mEditTitleView.getText());

                Log.d(LOGTAG, "New db category = " + newEntery);
                if(TextUtils.isEmpty(mEditTitleView.getText())) {
                    Toast.makeText(getActivity(), "No New Entry saved", Toast.LENGTH_SHORT).show();
                } else {
                    Category category = new Category(String.valueOf(mEditTitleView.getText()));
                    Log.d(LOGTAG,  "Category  = " + category.toString());

                    mCategoryViewModel.insert(category);
                }

                /**
                 * ReLaunch CategoryFragment.
                 */
                // Create detailsFragment object
                CategoryFragment categoryFragment = new CategoryFragment();
                // FragmentManager handle
                FragmentManager fragmentManager = getFragmentManager();

                // FragmentTransaction handle
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

                // Start fragment transaction.
                fragmentTransaction
                        .replace(R.id.activity_main_container, categoryFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
