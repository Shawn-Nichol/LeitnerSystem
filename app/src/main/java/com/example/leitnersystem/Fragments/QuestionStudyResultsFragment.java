package com.example.leitnersystem.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leitnersystem.R;

public class QuestionStudyResultsFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle onSavedInstanceState) {
       return inflater.inflate(R.layout.fragment_question_study_results_layout, viewGroup, false);
    }
}
