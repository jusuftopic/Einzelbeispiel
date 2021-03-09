package com.example.einzelbeispiel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GUITest {

    private MainActivity mainActivity;


    @Before
    public void setUp(){
        mainActivity = new MainActivity();



    }
    @After
    public void tearUp(){
        mainActivity = null;
    }
    @Test
    public void testSortedMartikelNr(){

        String unsortedMartikelNr = "11918546";
        String sortedMartikelNr = "46811159";

        Assert.assertEquals(sortedMartikelNr,mainActivity.sortMartikelnummer(unsortedMartikelNr));
    }
    @Test
    public void testListConcat(){
        ArrayList<Integer> geradezahlen= new ArrayList<>();
        ArrayList<Integer> ungeradezahlen = new ArrayList<>();
        ArrayList<Integer> finalList = new ArrayList<>();

        geradezahlen.add(2);
        geradezahlen.add(4);

        ungeradezahlen.add(1);
        ungeradezahlen.add(3);

        finalList.add(2);
        finalList.add(4);
        finalList.add(1);
        finalList.add(3);

        Assert.assertEquals(finalList,mainActivity.getConcatList(geradezahlen,ungeradezahlen));
    }
}
