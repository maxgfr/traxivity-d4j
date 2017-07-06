package com.maxgfr.traxivityd4j;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    private Button buttonLoadD4J;
    private TextView textViewLoadD4J;
    private TextView textViewDownstairs;
    private TextView textViewUpstairs;
    private TextView textViewWalking;
    private TextView textViewSitting;
    private TextView textViewJogging;
    private TextView textViewStanding;

    private static final int N_SAMPLES = 500;
    private MultiLayerNetwork D4JNetwork;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private static List<Float> x;
    private static List<Float> y;
    private static List<Float> z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();

        buttonLoadD4J.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                boolean b = loadNetworkFromD4J();

                if (b) {
                    textViewLoadD4J.setText("Network loaded");

                } else {
                    textViewLoadD4J.setText("Error Network null...");
                }
                buttonLoadD4J.setEnabled(false);

            }
        });

        x = new ArrayList<Float>();
        y = new ArrayList<Float>();
        z = new ArrayList<Float>();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void initializeView () {
        buttonLoadD4J = (Button) findViewById(R.id.buttonLoadD4J);
        textViewLoadD4J = (TextView) findViewById(R.id.textViewLoadD4J);
        textViewDownstairs = (TextView) findViewById(R.id.textViewDownstairs);
        textViewJogging = (TextView) findViewById(R.id.textViewJogging);
        textViewSitting = (TextView) findViewById(R.id.textViewSitting);
        textViewStanding = (TextView) findViewById(R.id.textViewStanding);
        textViewUpstairs = (TextView) findViewById(R.id.textViewUpstairs);
        textViewWalking = (TextView) findViewById(R.id.textViewWalking);
    }

    private boolean loadNetworkFromD4J () {
        NetworkManagement networkManagement = NetworkManagement.getInstance();
        InputStream inputStream = getResources().openRawResource(R.raw.network_d4j);
        try {
            D4JNetwork = networkManagement.loadModelFromD4J(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (D4JNetwork == null) {
            return false;
        } else { return true;}

    }

    private void toastRes (int x) {
        switch (x) {
            case 0:  Toast.makeText(this, "Downstairs",
                    Toast.LENGTH_LONG).show();
                break;
            case 1:  Toast.makeText(this, "Jogging",
                    Toast.LENGTH_LONG).show();
                break;
            case 2:  Toast.makeText(this, "Sitting",
                    Toast.LENGTH_LONG).show();
                break;
            case 3:  Toast.makeText(this, "Standing",
                    Toast.LENGTH_LONG).show();
                break;
            case 4:  Toast.makeText(this, "Upstairs",
                    Toast.LENGTH_LONG).show();
                break;
            case 5: Toast.makeText(this, "Walking",
                    Toast.LENGTH_LONG).show();;
                break;
        }
    }

    private void dispRes (INDArray array) {
        textViewDownstairs.setText(array.getRow(0).getColumn(0).toString());
        textViewJogging.setText(array.getRow(0).getColumn(1).toString());
        textViewSitting.setText(array.getRow(0).getColumn(2).toString());
        textViewStanding.setText(array.getRow(0).getColumn(3).toString());
        textViewUpstairs.setText(array.getRow(0).getColumn(4).toString());
        textViewWalking.setText(array.getRow(0).getColumn(5).toString());
    }

    private void activityPrediction() {
        if(x.size() == N_SAMPLES && y.size() == N_SAMPLES && z.size() == N_SAMPLES) {
            LoadData loadData = LoadData.getInstance(1,3,N_SAMPLES);
            NetworkManagement networkManagement = NetworkManagement.getInstance();
            DataSetIterator iterator = loadData.createSetDataFromList(x,y,z);
            dispRes(networkManagement.useNetworkMatrix(D4JNetwork,iterator));
            x.clear();
            y.clear();
            z.clear();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (D4JNetwork != null) {
            activityPrediction();
            x.add(event.values[0]);
            y.add(event.values[1]);
            z.add(event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

