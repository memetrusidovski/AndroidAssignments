package com.example.androidassignments;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

public class ListItemsActivityTest{

    public static final String TEST_STRING = "This is a string";
    public static final long TEST_LONG = 12345678L;


    @Before
    public void createLogHistory() {

    }


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void onCreate() {
        ArrayList myArrayList = Mockito.spy(new ArrayList());
        myArrayList.add("Hello class");
        Mockito.verify(myArrayList).add("Hello class");
        assertEquals(1, myArrayList.size());
    }

    @Test
    public void setOnCheckedChanged() {
    }
}
