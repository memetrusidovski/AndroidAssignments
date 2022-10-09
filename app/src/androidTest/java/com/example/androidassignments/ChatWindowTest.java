package com.example.androidassignments;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import junit.framework.TestCase;

import android.content.Intent;
import android.os.Parcel;
import android.util.Pair;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import androidx.test.espresso.*;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.runner.RunWith;
import org.junit.Test;

public class ChatWindowTest extends TestCase {

    @Rule
    public IntentsTestRule<ChatWindow> mActivityRule =
        new IntentsTestRule<>(ChatWindow.class, true,true);


    @Test
    public void TestSendFunctionality() {
        Intent intent = new Intent();
        intent.putExtra("your_key", "your_value");

        mActivityRule.launchActivity(intent);

        onView(withId(R.id.send)).perform(click());
        onView(withId(R.id.messageBox)).perform(typeText("This is a test message"));
        onView(withId(R.id.send)).perform(click());
        onView(withText("This is a test message")).check(matches(isDisplayed()));
    }


    @Test
    public void onCreate() {
    }

    @Test
    public void send() {
    }
}
