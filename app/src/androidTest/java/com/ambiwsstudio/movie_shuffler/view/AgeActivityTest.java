package com.ambiwsstudio.movie_shuffler.view;

import com.ambiwsstudio.movie_shuffler.R;
import com.ambiwsstudio.movie_shuffler.test.AgeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AgeActivityTest {

    @Rule
    public ActivityScenarioRule<AgeActivity> activityRule = new ActivityScenarioRule<>(AgeActivity.class);

    @Test
    public void testOnCreate() {

        Espresso.onView(ViewMatchers.withId(R.id.editTextAge)).perform(ViewActions.typeText("20"));
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click());

    }

}
