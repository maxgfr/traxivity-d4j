package com.maxgfr.traxivityd4j;

import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

import com.maxgfr.traxivityd4j.deeplearning.LoadMultiLayerNetwork;
=======
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.maxgfr.traxivityd4j.deeplearning.BinaryClassifier;
import com.maxgfr.traxivityd4j.deeplearning.LearnableModel;
>>>>>>> parent of ce760d7... :shark:

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
    private Button buttonLoadNetwork;

    private TextView textViewLoadNetwork;

    private Button buttonLoadData;

    private TextView textViewLoadData;

    private Button buttonTestNetwork;

    private MultiLayerNetwork network;
=======
    private BinaryClassifier binaryClassifier;
    private int inputs = 2;
    private int outputs = 1;
>>>>>>> parent of ce760d7... :shark:

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

                textViewLoadNetwork.setText("Network loaded");
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

    private void loadNetwork () {

        File locationNetwork = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        LoadMultiLayerNetwork l = LoadMultiLayerNetwork.getInstance();

        try {
            inputStream = getResources().openRawResource(R.raw.network_d4j);
            outputStream = new FileOutputStream(locationNetwork);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            network = l.loadModelFromFile(locationNetwork);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}

