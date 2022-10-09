package com.example.androidassignments;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.android.material.internal.ContextUtils.getActivity;

import static org.junit.Assert.*;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class LoginActivityInstrumentTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
        new ActivityTestRule<>(LoginActivity.class);


    @Test
    public void onLogin() {
    }

    @Test
    public void TestLoginningIntoApp() {
        onView(withId(R.id.passwordBox)).perform(typeText("Steve"));
        onView(withId(R.id.loginButton)).perform(click());
        //onView(withText("Hello Steve!")).check(matches(isDisplayed()));
    }

}
