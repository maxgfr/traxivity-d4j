package com.maxgfr.traxivityd4j;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Button buttonLoadD4J;

    private TextView textViewLoadD4J;

    private Button buttonLoadKeras;

    private TextView textViewLoadKeras;

    private Button buttonLoadData;

    private TextView textViewLoadData;

    private Button buttonTestD4J;

    private TextView textViewTestD4J;

    private Button buttonTestKeras;

    private TextView textViewTestKeras;

    private MultiLayerNetwork D4JNetwork;

    private MultiLayerNetwork KerasNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLoadD4J = (Button) findViewById(R.id.buttonLoadD4J);
        textViewLoadD4J = (TextView) findViewById(R.id.textViewLoadD4J);
        buttonLoadKeras = (Button) findViewById(R.id.buttonLoadKeras);
        textViewLoadKeras = (TextView) findViewById(R.id.textViewLoadKeras);
        buttonLoadData = (Button) findViewById(R.id.buttonLoadData);
        textViewLoadData = (TextView) findViewById(R.id.textViewLoadData);
        buttonTestD4J = (Button) findViewById(R.id.buttonTestD4J);
        textViewTestD4J = (TextView) findViewById(R.id.textViewTestD4J);
        buttonTestKeras = (Button) findViewById(R.id.buttonTestKeras);
        textViewTestKeras = (TextView) findViewById(R.id.textViewTestKeras);

        initializeButton();
    }

    private void initializeButton () {
        buttonLoadD4J.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                boolean b = loadNetworkFromD4J();

                if (b) {
                    textViewLoadD4J.setText("Network loaded");
                } else {
                    textViewLoadD4J.setText("Error Network null...");
                }

            }
        });

        buttonLoadKeras.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                boolean b = loadNetworkFromKeras();
                if (b) {
                    textViewLoadKeras.setText("Network loaded");
                } else {
                    textViewLoadKeras.setText("Error Network null...");
                }
            }
        });

        buttonLoadData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textViewLoadData.setText("Data loaded");
            }
        });

        buttonTestD4J.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textViewTestD4J.setText("Test D4J model");
            }
        });

        buttonTestKeras.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textViewTestKeras.setText("Test Keras model");
            }
        });

    }

    private boolean loadNetworkFromD4J () {
        LoadMultiLayerNetwork loadMultiLayerNetwork = LoadMultiLayerNetwork.getInstance();
        InputStream inputStream = getResources().openRawResource(R.raw.network_d4j);
        try {
            D4JNetwork = loadMultiLayerNetwork.loadModelFromD4J(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (D4JNetwork == null) {
            return false;
        } else { return true;}

    }

    private boolean loadNetworkFromKeras () {
        LoadMultiLayerNetwork loadMultiLayerNetwork = LoadMultiLayerNetwork.getInstance();
        InputStream inputStream = getResources().openRawResource(R.raw.cnn_wrist_33);
        try {
            KerasNetwork = loadMultiLayerNetwork.loadModelFromKeras(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedKerasConfigurationException e) {
            e.printStackTrace();
        } catch (InvalidKerasConfigurationException e) {
            e.printStackTrace();
        }

        if (KerasNetwork == null) {
            return false;
        } else { return true;}

    }

}

