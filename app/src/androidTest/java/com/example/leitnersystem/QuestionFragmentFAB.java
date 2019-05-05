package com.example.leitnersystem;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.leitnersystem.Activities.QuestionActivity;
import com.example.leitnersystem.RoomQuestion.QuestionDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class QuestionFragmentFAB {


    private QuestionDatabase db;

    @Rule
    public ActivityTestRule<QuestionActivity> questionActivityActivityTestRule =
            new ActivityTestRule<QuestionActivity>(QuestionActivity.class) {
                final String mTestCategory = "TestCategory";

                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent();
                    intent.putExtra("Category", mTestCategory);
                    return intent;
                }
            };

    @Before
    public void LoadQuestion() {
        db = QuestionDatabase.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());



    }
    @Test
    public void clickFAB_() {
        String mInsertTestQuestion = "InsertTestQuestion";
        String mInsertTestAnswer = "InsertTestAnswer";

        // Find View to preform action
        onView(withId(R.id.fab_button)).perform(click());
        // Enter new question and Category
        onView(withId(R.id.tv_new_question)).perform(typeText(mInsertTestQuestion));
        onView(withId(R.id.tv_new_answer)).perform(typeText(mInsertTestAnswer));
        // Press Save Button
        onView(withId(R.id.question_btn_save)).perform(click());
        // Check if question fragment loads after save.
        onView(withId(R.id.fragment_question)).check(matches(isDisplayed()));
        // Swipe to delete
        onView(withText(mInsertTestQuestion)).perform(swipeLeft());
    }

    @After
    public void deleteTestQuestion() {
        // For some reason swipe doesn't allows delete question. This will ensure all entries are deleted
        db.questionDao().deleteAllQuestions();

    }
}
