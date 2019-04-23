package com.example.leitnersystem;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.leitnersystem.Activities.QuestionActivity;

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
public class QuestionActivityFragment {

    private String testQuestion = "TestQuestion";
    private String testAnswer = "TestAnswer";

    @Rule
    public ActivityTestRule<QuestionActivity> questionActivityActivityTestRule =
            new ActivityTestRule<QuestionActivity>(QuestionActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent();
                    intent.putExtra("Category", "TestTitle");
                    return intent;
                }
            };

//    @Before
//    public void StubAllIntents(){
////        assertThat(intent).extras().string("Category").isEqualTo("testTitle");
//
//    }

    @Test
    public void clickFAB_OpensQuestionsNewTitleFragment(){
        // Find View to preform action
        onView(withId(R.id.fab_button)).perform(click());
        // Enter new question and Category
        onView(withId(R.id.tv_new_question)).perform(typeText(testQuestion));
        onView(withId(R.id.tv_new_answer)).perform(typeText(testAnswer));
        // Press Save Button
        onView(withId(R.id.question_btn_save)).perform(click());

        // Check if question fragment loads after save.
        onView(withId(R.id.fragment_question)).check(matches(isDisplayed()));
    }

    @After
    public void deleteTestQuestion() {
        onView(withText(testQuestion)).perform(swipeLeft());
    }
}
