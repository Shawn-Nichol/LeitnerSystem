//package com.example.leitnersystem.Fragments;
//
//import android.arch.lifecycle.Observer;
//import android.arch.lifecycle.ViewModelProviders;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.leitnersystem.R;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class StudyFragment extends Fragment {
//
//
//    @BindView(R.id.btn_study_correct) Button btnCorrect;
//    @BindView(R.id.btn_study_wrong) Button btnWrong;
//    @BindView(R.id.tv_study_answer) TextView tvAnswer;
//    @BindView(R.id.tv_study_question) TextView tvQuestion;
//
//
//    private QuestionsViewModel questionsViewModel;
//    private List<String> listItem;
//
//    private int questionNumber;
//
//    public StudyFragment() {
//    }
//
//    /**
//     * Inflates the fragment_category_layout file
//     *
//     * @param inflater To return a layout from onCreateView, you can inflate if from a layout resource
//     *                 defined in XML.
//     * @param container Is the parent ViewGroup in which the fragment is Layout is inserted.
//     * @param savedInstanceState is a bundle that provides data about the previous instance of the
//     *                          fragment.
//     * @return a View that is the root of the fragments layout
//     */
//    @Nullable
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_study_layout, container, false);
//
//        ButterKnife.bind(this, view);
//
//        questionNumber = 0;
//
//        questionsViewModel = ViewModelProviders.of(getActivity()).get(QuestionsViewModel.class);
//        questionsViewModel.findCategory("Cat 3").observe(this, new Observer<List<Questions>>() {
//            @Override
//            public void onChanged(@Nullable final List<Questions> questions) {
//                btnCorrect.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v) {
//                        Log.d("Questions", questions.get(questionNumber).getQuestions() +
//                                " Answer " + questions.get(questionNumber).getAnswer() +
//                                " Category " + questions.get(questionNumber).getCategory());
//                        questionNumber++;
//                    }
//                });
//            }
//        });
//
//
//        return view;
//    }
//}
