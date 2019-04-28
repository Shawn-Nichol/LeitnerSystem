package com.example.leitnersystem;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.leitnersystem.Activities.QuestionActivity;
import com.example.leitnersystem.RoomQuestion.Question;
import com.example.leitnersystem.RoomQuestion.QuestionDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class QuestionFragmentStudy {

    private String mTestQuestion = "TestQuestion";
    private String mTestAnswer = "TestAnswer";
    private String mTestCategory = "TestCategory";
    private int mTestBox = 1;
    private int mTestCounter = 0;
    QuestionDatabase db;

    @Rule
    public ActivityTestRule<QuestionActivity> questionActivityActivityTestRule =
            new ActivityTestRule<QuestionActivity>(QuestionActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent();
                    intent.putExtra("Category", mTestCategory);
                    return intent;
                }
            };

    @Before
    public void LoadQuestion() {
        db = (QuestionDatabase) QuestionDatabase.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        Question question = new Question(mTestQuestion, mTestAnswer, mTestCategory, mTestBox, mTestCounter);
        db.questionDao().insert(question);

    }


    @Test
    public void StudyButton_Pressed() {
        // Press Study Button
        onView(withId(R.id.btn_study)).perform(click());
        // Does Study Fragment Load
        onView(withId(R.id.fragment_question_study)).check(matches(isDisplayed()));
        // Was the correct question loaded
        onView(withId(R.id.tv_study_question)).check(matches(withText(mTestQuestion)));
        // Click the answer card
        onView(withId(R.id.tv_study_answer)).perform(click());
        // Was the correct answer loaded
        onView(withId(R.id.tv_study_answer)).check(matches(withText(mTestAnswer)));
        // Press correct button
        onView(withId(R.id.btn_study_correct)).perform(click());
        // No more questions
        onView(withId(R.id.fragment_question_study_done)).check(matches(isDisplayed()));
        // Go back to Question Fragment
        onView(isRoot()).perform(pressBack());
        // Question Fragment
        onView(withId(R.id.fragment_question)).check(matches(isDisplayed()));
    }

    @After
    public void deleteTestQuestion() {

        // For some reason swipe doesn't allows delete question. This will ensure all entries are deleted
        db.questionDao().deleteAllQuestions();



    }
}
