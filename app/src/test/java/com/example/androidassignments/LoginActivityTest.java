package com.example.androidassignments;

import junit.framework.TestCase;

import org.junit.Test;
import static org.junit.Assert.*;
import android.text.TextUtils;

public class LoginActivityTest extends TestCase {

    @Test
    public void testIsValidEmail() {
        assertEquals(true, LoginActivity.isEmpty("") );
    }
}
