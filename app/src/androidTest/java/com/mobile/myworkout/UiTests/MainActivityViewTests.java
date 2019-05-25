package com.mobile.myworkout.UiTests;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mobile.myworkout.activities.MainActivity;
import com.mobile.myworkout.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.startsWith;

@RunWith(AndroidJUnit4.class)
    public class MainActivityViewTests {

        @Rule
        public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
                new ActivityTestRule<>(MainActivity.class);

        public static final Intent MAIN_ACTIVITY_INTENT
                = new Intent(InstrumentationRegistry.getTargetContext(),MainActivity.class);

        @Before
        public void init() {

            mainActivityActivityTestRule.launchActivity(MAIN_ACTIVITY_INTENT);
        }

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
            onView(withId(R.id.tv_guiding_text)).check(matches(isDisplayed()));
        }
        @Test
        public void test_that_email_entry_edit_texts_loads_correctly() {
            onView(withId(R.id.tv_email_address)).check(matches(isDisplayed()));

        }

        @Test
        public void test_user_should_see_message_on_blank_email_entry() {

            onView(withId(R.id.img_login))
                    .perform(click());
            onView(withText(startsWith("Email cannot")))
                    .inRoot(withDecorView(
                            not(is(mainActivityActivityTestRule.getActivity()
                            .getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
        }


}
