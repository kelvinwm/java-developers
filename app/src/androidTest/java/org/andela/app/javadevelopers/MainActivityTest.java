package org.andela.app.javadevelopers;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.andela.app.javadevelopers.view.MainActivity;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import org.andela.app.javadevelopers.util.EspressoIdlingResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;

import android.support.test.espresso.contrib.RecyclerViewActions;

import static org.junit.Assert.*;

import android.view.View;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void registerIdlingResource() {
        // Register your Idling Resource before any tests regarding this component
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());

        mainActivity = activityTestRule.getActivity();
    }

    @Test
    public void recyclerView_isRendered() {
        // quick check if the recyclerView is visible
        onView(withId(R.id.developer_list)).check(matches(isDisplayed()));

    }

    @Test
    public void recyclerView_items_can_be_clicked() {
        //click item in recyclerView
        onView(ViewMatchers.withId(R.id.developer_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
    }

    @After
    public void unregisterIdlingResource() {
        // Unregister your Idling Resource so it can be garbage collected and does not leak any memory
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}
