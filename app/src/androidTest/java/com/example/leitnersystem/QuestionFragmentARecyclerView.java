package com.example.leitnersystem;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.example.leitnersystem.Activities.QuestionActivity;
import com.example.leitnersystem.RoomQuestion.Question;
import com.example.leitnersystem.RoomQuestion.QuestionDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class QuestionFragmentARecyclerView {

    private String mTestQuestion = "TestQuestion";
    private String mTestAnswer = "TestAnswer";
    private String mTestCategory = "TestCategory";
    private int mTestBox = 1;
    private int mTestCounter = 0;
    private QuestionDatabase db;
    private Question question;

    @Rule
    public ActivityTestRule<QuestionActivity> questionActivityActivityTestRule =
            new ActivityTestRule<QuestionActivity>(QuestionActivity.class) {
                String mTestCategory = "TestCategory";

                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent();
                    intent.putExtra("Category", mTestCategory);
                    return intent;
                }
            };

    @Before
    public void LoadQuestion(){
        db = (QuestionDatabase) QuestionDatabase.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
        question = new Question(mTestQuestion, mTestAnswer, mTestCategory, mTestBox, mTestCounter);
        db.questionDao().insert(question);

    }

    @Test
    public void recyclerView_Click() {
        // Check if questions loads
        onView(withText(mTestQuestion)).check(matches(isDisplayed()));
        // Preform click on question card
        onView(withText(mTestQuestion)).perform(click());
         //onView(withId(R.id.rv_category)).perform(RecyclerViewActions.<VH>actionOnItemAtPosition(0, click()));
        // Does Question Details Fragment load
        onView(withId(R.id.fragment_question_details)).check(matches(isDisplayed()));
        // Go back to Question Fragment
        onView(isRoot()).perform(pressBack());
        // Does Question Fragment load
        onView(withId(R.id.fragment_question)).check(matches(isDisplayed()));
        // Swipe left to delete card
        //onView(withText(mTestQuestion)).perform(swipeLeft());
    }

    @After
    public void delete_questions() {
        db.questionDao().deleteAllQuestions();
    }

}
