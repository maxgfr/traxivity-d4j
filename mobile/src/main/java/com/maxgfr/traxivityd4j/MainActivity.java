package com.maxgfr.traxivityd4j;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Button buttonLoadNetwork;

    private TextView textViewLoadNetwork;

    private Button buttonLoadData;

    private TextView textViewLoadData;

    private Button buttonTestNetwork;

    private MultiLayerNetwork network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLoadNetwork = (Button) findViewById(R.id.buttonLoadNetwork);

        textViewLoadNetwork = (TextView) findViewById(R.id.textViewLoadNetwork);

        buttonLoadData = (Button) findViewById(R.id.buttonLoadData);

        textViewLoadData = (TextView) findViewById(R.id.textViewLoadData);

        buttonTestNetwork = (Button) findViewById(R.id.buttonTestNetwork);

        initializeButton();;

    }

    private void initializeButton () {
        buttonLoadNetwork.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                boolean b = loadNetwork();

                if (b) {
                    textViewLoadNetwork.setText("Network loaded");
                } else {
                    textViewLoadNetwork.setText("Error Network null...");
                }

            }
        });

        buttonLoadData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                textViewLoadData.setText("Data loaded");
            }
        });

        buttonTestNetwork.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {


            }
        });
    }

    private boolean loadNetwork () {

        LoadMultiLayerNetwork loadMultiLayerNetwork = LoadMultiLayerNetwork.getInstance();

        InputStream inputStream = getResources().openRawResource(R.raw.network_d4j);

        File file = loadMultiLayerNetwork.fileGenerate(inputStream);

        System.out.println(file.getAbsolutePath() + file.getName());

        try {
            network = loadMultiLayerNetwork.loadModelFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (network == null) {
            return false;
        }

        return true;

    }

}

