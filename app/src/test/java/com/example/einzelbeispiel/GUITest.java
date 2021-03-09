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

    //CASE 0 TESTS
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

    //CASE 1 TESTS

    @Test
    public void testGemeinsamTeiler(){
        ArrayList<Integer> indexes = new ArrayList<>();

        indexes.add(1);
        indexes.add(2);

        String martikelNr = "1223456";

        Assert.assertEquals(indexes,mainActivity.getGemeinsamTeiler(martikelNr));
    }

    @Test
    public void testIndexes(){
        ArrayList<Integer> indexes = new ArrayList<>();

        indexes.add(1);
        indexes.add(0);

        Assert.assertEquals(indexes,mainActivity.test("11918546"));
    }

    //CASE 2 TESTS
    @Test
    public void testAsciiCharacters(){
        String martikelNr = "119185455";
        String asciiString = "aihde";

        Assert.assertEquals(asciiString,mainActivity.getASCIICharacters(martikelNr));
    }
    @Test
    public void testCharLength1(){

        String martikelNr = "11918546";
        int length = 4;

        Assert.assertEquals(length,mainActivity.getCharLength(martikelNr));
    }

    @Test
    public void testCharLength2(){

        String martikelNr = "119185461";
        int length = 5;

        Assert.assertEquals(length,mainActivity.getCharLength(martikelNr));
    }

    //CASE 3 TESTS
    @Test
    public void testBinarySumme(){

        String martikelNr = "11918546";
        String binearResult = "100011";

        Assert.assertEquals(binearResult,mainActivity.getBinearQuersumme(martikelNr));
    }


}
