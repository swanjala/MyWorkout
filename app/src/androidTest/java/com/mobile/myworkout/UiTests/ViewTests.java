package com.mobile.myworkout.UiTests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mobile.myworkout.MainActivity;
import com.mobile.myworkout.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ViewTests {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test_that_image_view_loads_correctly() {
        onView(withId(R.id.iv_myworkoutlog)).check(matches(isDisplayed()));
    }
    @Test
    public void test_that_text_view_loads_correctly() {
        onView(withId(R.id.tv_app_name)).check(matches(isDisplayed()));
    }
    @Test
    public void test_that_guiding_test_loads_correctly() {
        onView(withId(R.id.tv_guiding_tests)).check(matches(isDisplayed()));
    }
    @Test
    public void test_that_email_entry_edit_texts_loads_correctly() {
        onView(withId(R.id.tv_email_address)).check(matches(isDisplayed()));

    }

}
