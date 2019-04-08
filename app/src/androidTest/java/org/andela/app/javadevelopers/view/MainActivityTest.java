package org.andela.app.javadevelopers.view;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.andela.app.javadevelopers.R;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {

        mainActivity = activityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){

        View view = mainActivity.findViewById(R.id.content_main);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}