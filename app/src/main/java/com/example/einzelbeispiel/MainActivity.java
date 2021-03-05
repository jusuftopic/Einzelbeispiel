package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView martikelnummer;
    private TextView antwortFromServer;
    private Button abschicken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        martikelnummer = (TextView) findViewById(R.id.martikelnummer);
        antwortFromServer = (TextView) findViewById(R.id.antwortFromServer);
        abschicken = (Button) findViewById(R.id.abschciken);
    }
}