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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class CategoryFragmentFAB {
    private String mInsertTestCategory = "InsertTestCategory";
    private CategoryRoomDatabase db;
    private Category category;

    // Provide functional testing of a single activity
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void ConnectDatabase() {
            db = CategoryRoomDatabase.getDatabase(InstrumentationRegistry.getInstrumentation().getTargetContext());
            category = new Category(mInsertTestCategory);
    }

    @Test
    public void clickFAB() {
        // Find View to preform action
        onView(withId(R.id.fab_button)).perform(click());
        // Enter new Category
        onView(withId(R.id.new_category_tv_category)).perform(typeText(mInsertTestCategory));
        // Save new category
        onView(withId(R.id.btn_save)).perform(click());
        // Check if category fragment loads after save
        onView(withId(R.id.fragment_category)).check(matches(isDisplayed()));
        // Check if category was inserted
        onView(withText(mInsertTestCategory)).check(matches(isDisplayed()));
        // Delete
        onView(withText(mInsertTestCategory)).perform(swipeLeft());
    }

    @After
    public void delete_TestCat() {

        db.CategoryDao().delete(category);
    }
}
