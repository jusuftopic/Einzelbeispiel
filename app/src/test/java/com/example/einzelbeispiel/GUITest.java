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

    //CASE 4 TESTS
    @Test
    public void testSortedMartikelNrWihoutPrimazahlen(){

        String martikelnummer = "11918546";
        String sortedWithoutPrimezahlMartikelNr = "1114689";

        Assert.assertEquals(sortedWithoutPrimezahlMartikelNr,mainActivity.getSortedNumber(martikelnummer));
    }

    @Test
    public void testSortedArrayList()
    {
        String martikeNr= "11918546";

        ArrayList<Integer> sortedArray = new ArrayList<>();

        sortedArray.add(1);
        sortedArray.add(1);
        sortedArray.add(1);
        sortedArray.add(4);
        sortedArray.add(5);
        sortedArray.add(6);
        sortedArray.add(8);
        sortedArray.add(9);

        Assert.assertEquals(sortedArray,mainActivity.getSortedList(martikeNr));
    }

    @Test
    public void SortedListWithoutPrimeNumbers(){
        ArrayList<Integer> listWihtPrimzahlen = new ArrayList<>();
        ArrayList<Integer> listWihtoutPrimzahlen = new ArrayList<>();

        for(int i = 1; i < 10; i++){
            listWihtPrimzahlen.add(i);
        }

        listWihtoutPrimzahlen.add(1);
        listWihtoutPrimzahlen.add(4);
        listWihtoutPrimzahlen.add(6);
        listWihtoutPrimzahlen.add(8);
        listWihtoutPrimzahlen.add(9);

        Assert.assertEquals(listWihtoutPrimzahlen,mainActivity.getSortedListWithoutPrimeNumbers(listWihtPrimzahlen));

    }

    //CASE 5 TESTS
    @Test
    public void testPrimzahlMartikelnummer(){
       String martikelnummer = "11918546";
       String primzahlMartikelnummer = "5";

       Assert.assertEquals(primzahlMartikelnummer,mainActivity.getPrimzahlMartikelnummer(martikelnummer));
    }

    @Test
    public void testMartikelnummerToIntegerList(){

        String martikelNr = "123456789";
        ArrayList<Integer> martikelNrZiffern = new ArrayList<>();
        int i = 1;

        while (i < 10){
            martikelNrZiffern.add(i);
            i++;
        }

        Assert.assertEquals(martikelNrZiffern,mainActivity.getMartikelNrZiffern(martikelNr));
    }

    @Test
    public void testPrimZahlZiffern(){
        ArrayList<Integer> integerMartikelNr = new ArrayList<>();
        ArrayList<Integer> primzahlMartikelNr = new ArrayList<>();

        integerMartikelNr.add(1);
        integerMartikelNr.add(2);
        integerMartikelNr.add(3);
        integerMartikelNr.add(6);

        primzahlMartikelNr.add(2);
        primzahlMartikelNr.add(3);

        Assert.assertEquals(primzahlMartikelNr,mainActivity.getPrimzahlZiffern(integerMartikelNr));
    }

    //CASE 6 TESTS
    @Test
    public void testAlternierndeQuerSummeInfo(){

        String martikelNr = "11918549";
        String info = "Summe ist gerade Zahl";

        Assert.assertEquals(info,mainActivity.getAlternierndeQuerSummeInfo(martikelNr));

    }

    @Test
    public void testAlternierendeQuersumme(){
        ArrayList<Integer> martikelNr = new ArrayList<>();

        martikelNr.add(1);
        martikelNr.add(1);
        martikelNr.add(9);
        martikelNr.add(1);
        martikelNr.add(8);
        martikelNr.add(5);
        martikelNr.add(4);
        martikelNr.add(9);

        int result = 6;

       Assert.assertEquals(result,mainActivity.getAlternierendQuersumme(martikelNr));


    }

}
