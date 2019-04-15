package org.andela.app.javadevelopers;

import android.content.Intent;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.andela.app.javadevelopers.util.EspressoIdlingResource;
import org.andela.app.javadevelopers.view.DetailsActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DetailsActivityTest {

    @Rule
    public ActivityTestRule<DetailsActivity> activityTestRule =
            new ActivityTestRule<>(DetailsActivity.class);


    @Before
    public void registerIdlingResource() {
        // Register your Idling Resource before any tests regarding this component
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @Before
    public void setUp() {
        Intent intent = new Intent();
        intent.putExtra("Github_username", "jumaallan");
        intent.putExtra("Github_photo_link", "https://avatars3.githubusercontent.com/u/25085146?v=4");
        intent.putExtra("Github_link", "https://github.com/jumaallan");
        activityTestRule.launchActivity(intent);
    }

    @Test
    public void detailsActivityLayout_Rendered() {
        onView(withId(R.id.details_layout)).check(matches(isDisplayed()));

    }

    @Test
    public void imageTextViewLayout_Rendered() {
        onView(withId(R.id.user_profile_photo)).check(matches(isDisplayed()));
        onView(withId(R.id.share)).check(matches(isDisplayed()));
        onView(withId(R.id.user_profile_name)).check(matches(withText("jumaallan")));

    }


    @After
    public void unregisterIdlingResource() {
        // Unregister your Idling Resource so it can be garbage collected and does not leak any memory
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
}
