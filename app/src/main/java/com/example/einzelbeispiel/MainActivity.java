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

    public void getBerechnung (View view){
        if (isValidMartikelnummer()) {
            int matNr = Integer.parseInt(String.valueOf(martikelnummer.getText()));
            int withModulo = matNr%7;

            resultFromDivision(withModulo);
        }
        else{
            Toast.makeText(MainActivity.this,"Martikelnummer is not valid", Toast.LENGTH_LONG).show();
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

    public boolean isValidMartikelnummer(){
        if(martikelnummer.getText().toString() != null && martikelnummer.getText().toString() != ""){
            return true;
        }
        else{
            return false;
        }
    }

    public void resultFromDivision(int result){
        switch (result){
            case 0:
                antwortFromServer.setText(sortMartikelnummer(martikelnummer.getText().toString()));
                break;
        }
    }

    //CASE 0
    public String sortMartikelnummer(String martikelnummer){
        ArrayList<Integer> unsortiredMartikelNr= new ArrayList<>();
        ArrayList<Integer> geradeZahlen = new ArrayList<>();
        ArrayList<Integer> ungeradeZahlen = new ArrayList<>();
        String sortedMartikelNr = "";

        for(int i = 0; i < martikelnummer.length(); i++){
            unsortiredMartikelNr.add(Integer.parseInt(Character.toString(martikelnummer.charAt(i))));
        }

        for (Integer zahl : unsortiredMartikelNr){
            if(zahl % 2 == 0){
              geradeZahlen.add(zahl);
            }
            else{
                ungeradeZahlen.add(zahl);
            }
        }

        Collections.sort(geradeZahlen);
        Collections.sort(ungeradeZahlen);

        sortedMartikelNr = getConcatList(geradeZahlen,ungeradeZahlen);

        return sortedMartikelNr.toString();
    }

    public String getConcatList(ArrayList<Integer> geradezahlen, ArrayList<Integer> ungeradezahlen){
        ArrayList<Integer> finalList = new ArrayList<>();
        String sortedMartikelNr = "";

        for(int geradezahl : geradezahlen){
            finalList.add(geradezahl);
        }

        for (int ungereadezahl : ungeradezahlen){
            finalList.add(ungereadezahl);
        }

        for(int sortedelement : finalList){
            sortedMartikelNr+= Integer.toString(sortedelement);
        }

        return sortedMartikelNr;
    }

    //CASE 1




    public void setAntwortFromServer(String serverantwort) {
        antwortFromServer.setText(serverantwort);
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