package com.example.leitnersystem;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.leitnersystem.Activities.MainActivity;


import org.junit.After;
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
public class CategoryActivityTest {
    private String testCat = "1111Cat";

    // Provide functional testing of a single activity
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    // Press the FAB
    @Test
    public void clickFAB_OpensCategoryNewTitleFragment() {
        // Find View to preform action
        onView(withId(R.id.fab_button)).perform(click());
        // Enter new Category
        onView(withId(R.id.new_category_tv_category)).perform(typeText(testCat));
        // Save new category
        onView(withId(R.id.btn_save)).perform(click());

        // Check if category fragment loads after save
        onView(withId(R.id.fragment_category)).check(matches(isDisplayed()));

    }

    @Test
    public void recyclerView_ClickTestCat() {
        // Check to see if test cat shows up in the recycler view.
        onView(withText(testCat)).check(matches(isDisplayed()));
        // Preform Click on testCat card
        onView(withText(testCat)).perform(click());
        // Does question fragment load
        onView(withId(R.id.fragment_question)).check(matches(isDisplayed()));

    }

    @After
    public void delete_TestCat() {
        // Remove
        onView(withText(testCat)).perform(swipeLeft());
    }
}
