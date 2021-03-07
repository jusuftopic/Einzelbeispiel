package com.example.einzelbeispiel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Servermessagetest {

    private MainActivity mainActivity;
    private  MainActivity.MessageReciver reciver;

    @Before
    public void setUp(){
        mainActivity = new MainActivity();
        reciver = mainActivity.new MessageReciver();
    }

    @After
    public void tearDown(){
        mainActivity = null;
        reciver = null;
    }

    @Test
    public void testOutputMessage(){


    }
}