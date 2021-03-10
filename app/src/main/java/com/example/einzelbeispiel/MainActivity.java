package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView martikelnummer;
    private TextView antwortFromServer;
    private Button abschicken;
    private Button berechnen;

    private MessageReciver messageReciver;
    private Handler messagerecivehandler = new Handler();


    public void getAntwortFromServer(View view) {
        messageReciver = new MessageReciver();
        new Thread(messageReciver).start();

        try {
            messageReciver.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

    public void getBerechnung(View view) {
        if (isValidMartikelnummer()) {
            int matNr = Integer.parseInt(String.valueOf(martikelnummer.getText()));
            int withModulo = matNr % 7;

            resultFromDivision(withModulo);
        } else {
            Toast.makeText(MainActivity.this, "Martikelnummer is not valid", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        martikelnummer = (TextView) findViewById(R.id.martikelnummer);
        antwortFromServer = (TextView) findViewById(R.id.antwortFromServer);
        abschicken = (Button) findViewById(R.id.abschciken);
        berechnen = (Button) findViewById(R.id.berechnen);

    }

    public boolean isValidMartikelnummer() {
        if (martikelnummer.getText().toString() != null && martikelnummer.getText().toString() != "") {
            return true;
        } else {
            return false;
        }
    }

    public void resultFromDivision(int result) {
        switch (result) {
            case 0:
                antwortFromServer.setText(sortMartikelnummer(martikelnummer.getText().toString()));
                break;

            case 1:
                //   antwortFromServer.setText(getGemeinsamTeiler(martikelnummer.getText().toString()));
                break;

            case 2:
                antwortFromServer.setText(getASCIICharacters(String.valueOf(martikelnummer.getText())));
                break;

            case 3:
                antwortFromServer.setText(getBinearQuersumme(String.valueOf(martikelnummer.getText())));
                break;

            case 4:
                antwortFromServer.setText(getSortedNumber(String.valueOf(martikelnummer.getText())));
                break;

            case 5:
                antwortFromServer.setText(getPrimzahlMartikelnummer(String.valueOf(martikelnummer.getText())));
        }
    }

    //CASE 0
    public String sortMartikelnummer(String martikelnummer) {
        ArrayList<Integer> unsortiredMartikelNr = new ArrayList<>();
        ArrayList<Integer> geradeZahlen = new ArrayList<>();
        ArrayList<Integer> ungeradeZahlen = new ArrayList<>();
        String sortedMartikelNr = "";

        for (int i = 0; i < martikelnummer.length(); i++) {
            unsortiredMartikelNr.add(Integer.parseInt(Character.toString(martikelnummer.charAt(i))));
        }

        for (Integer zahl : unsortiredMartikelNr) {
            if (zahl % 2 == 0) {
                geradeZahlen.add(zahl);
            } else {
                ungeradeZahlen.add(zahl);
            }
        }

        Collections.sort(geradeZahlen);
        Collections.sort(ungeradeZahlen);

        sortedMartikelNr = getConcatList(geradeZahlen, ungeradeZahlen);

        return sortedMartikelNr.toString();
    }

    public String getConcatList(ArrayList<Integer> geradezahlen, ArrayList<Integer> ungeradezahlen) {
        ArrayList<Integer> finalList = new ArrayList<>();
        String sortedMartikelNr = "";

        for (int geradezahl : geradezahlen) {
            finalList.add(geradezahl);
        }

        for (int ungereadezahl : ungeradezahlen) {
            finalList.add(ungereadezahl);
        }

        for (int sortedelement : finalList) {
            sortedMartikelNr += Integer.toString(sortedelement);
        }

        return sortedMartikelNr;
    }

    //CASE 1
    public ArrayList<Integer> getGemeinsamTeiler(String martikelnummer) {

        ArrayList<Integer> indexes = new ArrayList<>();

        a:
        for (int i = 1; i < martikelnummer.length(); i++) {
            int ziffer1 = Integer.parseInt(Character.toString(martikelnummer.charAt(i - 1)));
            int ziffer2 = Integer.parseInt(Character.toString(martikelnummer.charAt(i)));

            int teiler = 2;

            while (teiler <= 9) {

                if (ziffer1 % teiler == 0 && ziffer2 % teiler == 0) {

                    indexes.add(i - 1);
                    indexes.add(i);

                    break a;
                }

                teiler++;

            }
        }

        return indexes;
    }

    public boolean isEmptyList(ArrayList<Integer> indexes) {
        if (indexes.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Integer> test(String martikelnr) {

        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 1; i < 2; i++) {
            int ziffer1 = Integer.parseInt(Character.toString(martikelnr.charAt(i - 1)));
            int ziffer2 = Integer.parseInt(Character.toString(martikelnr.charAt(i)));

            indexes.add(i);
            indexes.add(i - 1);
        }


        return indexes;
    }

    //CASE 2
    public String getASCIICharacters(String martikelnummer) {

        char[] asciiChars = new char[getCharLength(martikelnummer)];
        asciiChars[0] = (char) ((Integer.parseInt(Character.toString(martikelnummer.charAt(0)))) + 96);

        int j = 0;
        for (int i = 2; i < martikelnummer.length(); i = i + 2) {

            if (i - 1 == 1) {

                asciiChars[i - 1] = (char) ((Integer.parseInt(Character.toString(martikelnummer.charAt(i)))) + 96);
            } else {

                asciiChars[(i - 2) - j] = (char) ((Integer.parseInt(Character.toString(martikelnummer.charAt(i)))) + 96);
                j++;
            }

        }


        String asciiString = new String(asciiChars);

        return asciiString;
    }

    public int getCharLength(String martikelNr) {
        if (martikelNr.length() % 2 == 0) {
            return martikelNr.length() / 2;
        } else {
            return (martikelNr.length() / 2) + 1;
        }
    }

    //CASE 3
    public String getBinearQuersumme(String martikelNr) {
        int result = 0;

        for (int i = 0; i < martikelNr.length(); i++) {

            result += Integer.parseInt(Character.toString(martikelNr.charAt(i)));
        }

        String binearsumme = Integer.toBinaryString(result);

        return binearsumme;

    }

    //CASE 4
    public String getSortedNumber(String martikelNr) {

        ArrayList<Integer> sortedNumber = getSortedList(martikelNr);
        String sortedMartikelNr = "";

        sortedNumber = getSortedListWithoutPrimeNumbers(sortedNumber);

        sortedMartikelNr = getConvertedList(sortedNumber);

        return sortedMartikelNr;

    }

    public ArrayList<Integer> getSortedList(String martikelNr) {
        ArrayList<Integer> sortedNummer = new ArrayList<>();

        for (int i = 0; i < martikelNr.length(); i++) {
            sortedNummer.add(Integer.parseInt(Character.toString(martikelNr.charAt(i))));
        }

        Collections.sort(sortedNummer);

        return sortedNummer;
    }

    public ArrayList<Integer> getSortedListWithoutPrimeNumbers(ArrayList<Integer> sortedList) {
        ArrayList<Integer> sortedListWitoutPrimezahlen = new ArrayList<>();


        for (int element : sortedList) {
            int numberOfTeilers = 0;
            for (int i = 1; i < 10; i++) {
                if (element % i == 0 && element != 1) {
                    numberOfTeilers += 1;
                }
            }

            if (numberOfTeilers > 2 || element == 1) {
                sortedListWitoutPrimezahlen.add(element);
            }
        }

        return sortedListWitoutPrimezahlen;
    }

    public String getConvertedList(ArrayList<Integer> sortedNumber) {
        String sortedMartikelNr = "";

        for (int sortedelement : sortedNumber) {
            sortedMartikelNr += Integer.toString(sortedelement);
        }


        return sortedMartikelNr;
    }

    //CASE 5
    public String getPrimzahlMartikelnummer(String martikelnummer){

        String martikelnummerOnlyPrimzahl = "";
        ArrayList<Integer> integerMartikelNr = getMartikelNrZiffern(martikelnummer);

        integerMartikelNr = getPrimzahlZiffern(integerMartikelNr);

        martikelnummerOnlyPrimzahl = toPrimzahlString(integerMartikelNr);

        return martikelnummerOnlyPrimzahl;
    }

    public ArrayList<Integer> getMartikelNrZiffern(String martikelnummer){
        ArrayList<Integer> integerMartikelNr = new ArrayList<>();

        for(int i = 0; i < martikelnummer.length(); i++){
            integerMartikelNr.add(Integer.parseInt(Character.toString(martikelnummer.charAt(i))));
        }

        return integerMartikelNr;
    }

    public ArrayList<Integer> getPrimzahlZiffern(ArrayList<Integer> integerMartikelNr){

        ArrayList<Integer> primzahlMartikelNr = new ArrayList<>();

        for (int ziffer : integerMartikelNr){
            int numOfTeilers = 0;

            for(int i = 1; i < 10; i++){
                if(ziffer != 1 && ziffer % i == 0){
                    numOfTeilers += 1;
                }
            }

            if(numOfTeilers == 2){
                primzahlMartikelNr.add(ziffer);
            }
        }

        return primzahlMartikelNr;
    }

    public String toPrimzahlString(ArrayList<Integer> primzahlZiffern){
        String primzahlMartikelnummer = "";

        for (int primziffer : primzahlZiffern){
            primzahlMartikelnummer += Integer.toString(primziffer);
        }

        return primzahlMartikelnummer;
    }


    class MessageReciver extends Thread {

        private String inputMessage = martikelnummer.getText().toString();
        private String outputMessage = "";


        @Override
        public void run() {
            Socket socket;
            DataOutputStream dataOutputStream;
            BufferedReader reader;

            try {
                socket = new Socket("se2-isys.aau.at", 53212);

                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                if (inputMessage != "" || inputMessage != null) {
                    dataOutputStream.writeBytes(inputMessage + '\n');
                } else {
                    Toast.makeText(MainActivity.this, "Input message error", Toast.LENGTH_LONG).show();
                }

                outputMessage = reader.readLine();

                messagerecivehandler.post(new Runnable() {
                    @Override
                    public void run() {
                        antwortFromServer.setText(outputMessage);
                    }
                });

                socket.close();
            } catch (UnknownHostException uhe) {
                Log.i("Exception", "Unknown Host");
                uhe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        public String getOutputMessage() {
            return this.outputMessage;
        }
    }
}