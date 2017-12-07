package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.content.Loader;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;


public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureButtonDisableAfterOneClick() {
        onView(withId(R.id.fetch_joke_button)).perform(click());
        MainActivity activity = mActivityRule.getActivity();

        // get button
        // perform onclick

        Loader<?> loader =
                activity.getSupportLoaderManager().getLoader(MainActivity.LOADER_ID_FETCH_JOKE);

        if (loader != null)
            LoaderUtils.waitForLoader(loader);

        // this test passes only if onLoaderFinished method is called
        assertTrue(activity.loaderFinished);
    }


}