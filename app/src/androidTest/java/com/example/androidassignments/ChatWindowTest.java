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
import androidx.test.rule.ActivityTestRule;


public class ChatWindowTest {

    @Rule
    public ActivityTestRule<ChatWindow> mActivityRule =
        new ActivityTestRule<>(ChatWindow.class, true,true);

    /*DEBUG WINDOW TEST
    * 2022-11-06 12:48:33.475 21283-21283/com.example.androidassignments I/ChatDatabaseHelper: Calling onUpgrade, oldVersion=3 newVersion=4
2022-11-06 12:48:33.475 21283-21283/com.example.androidassignments D/Database: Upgrade The Table
2022-11-06 12:48:33.502 21283-21283/com.example.androidassignments I/Database: Cursor’s  column count =2
2022-11-06 12:49:03.923 21410-21410/com.example.androidassignments D/Database: TextView
2022-11-06 12:49:03.924 21410-21410/com.example.androidassignments D/Database: qwerqwe
2022-11-06 12:49:03.924 21410-21410/com.example.androidassignments D/Database: werqwer
2022-11-06 12:49:03.924 21410-21410/com.example.androidassignments I/Database: Cursor’s  column count =2
2022-11-06 12:49:30.099 21481-21481/com.example.androidassignments D/Database: 2
2022-11-06 12:49:30.099 21481-21481/com.example.androidassignments D/Database: 3
2022-11-06 12:49:30.099 21481-21481/com.example.androidassignments D/Database: 4
2022-11-06 12:49:30.100 21481-21481/com.example.androidassignments I/Database: Cursor’s  column count =2
    * */

    @Test
    public void TestSendFunctionality() {
        Intent intent = new Intent();
        intent.putExtra("your_key", "your_value");

        mActivityRule.launchActivity(intent);

        onView(withId(R.id.send)).perform(click());
        onView(withId(R.id.messageBox)).perform(typeText("This is a test message"));
        onView(withId(R.id.send)).perform(click());
        onView(withText("This is a test message")).check(matches(isDisplayed()));

        onView(withId(R.id.messageBox)).perform(typeText("Message goes to database"));
        onView(withId(R.id.send)).perform(click());
    }

    @Test
    public void ChatWindowActivitybyPressing() {
        Intent intent = new Intent();
        intent.putExtra("your_key", "your_value");
        mActivityRule.launchActivity(intent);

        Espresso.pressBack();

    }

    @Test
    public void onCreate() {
    }

    @Test
    public void send() {
    }
}
