package com.example.leitnersystem;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.leitnersystem.Activities.MainActivity;
import com.example.leitnersystem.RoomCategory.Category;
import com.example.leitnersystem.RoomCategory.CategoryRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CategoryFragmentARecyclerView {

    private String mTestCategory = "TestCategory";

    private CategoryRoomDatabase db;
    private Category category;
    // Provide functional testing of a single activity
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void LoadQuestion() {
        db = CategoryRoomDatabase.getDatabase(InstrumentationRegistry.getInstrumentation().getTargetContext());
        category = new Category(mTestCategory);
        db.CategoryDao().insert(category);
    }

    @Test
    public void recyclerView_ClickTestCat() {
        // Check to see if test cat shows up in the recycler view.
        onView(withText(mTestCategory)).check(matches(isDisplayed()));
        // Preform Click on testCat card
        onView(withText(mTestCategory)).perform(click());
        // Does question fragment load
        onView(withId(R.id.fragment_question)).check(matches(isDisplayed()));
        // Delete with swipe
        onView(withText(mTestCategory)).perform(swipeLeft());

    }

    @After
    public void delete_TestCat() {
        // Remove
        db.CategoryDao().delete(category);

    }
}

