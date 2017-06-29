package com.maxgfr.traxivityd4j;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int DOWNSTAIRS_DATA = 0;
    private static final int JOGGING_DATA = 1;
    private static final int SITTING_DATA = 2;
    private static final int STANDING_DATA = 3;
    private static final int UPSTAIRS_DATA = 4;
    private static final int WALKING_DATA = 5;

    private Button buttonLoadD4J;
    private TextView textViewLoadD4J;
    private Button buttonLoadKeras;
    private TextView textViewLoadKeras;
    private Button buttonLoadDownstairs;
    private Button buttonLoadJogging;
    private Button buttonLoadSitting;
    private Button buttonLoadStanding;
    private Button buttonLoadUpstairs;
    private Button buttonLoadWalking;
    private TextView textViewLoadData;
    private Button buttonTestD4J;
    private TextView textViewTestD4J;
    private Button buttonTestKeras;
    private TextView textViewTestKeras;

    private MultiLayerNetwork D4JNetwork;
    private MultiLayerNetwork KerasNetwork;
    private DataSetIterator iterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();

        setButton();

        disableAllDataButton();
    }

    private void initializeView () {
        buttonLoadD4J = (Button) findViewById(R.id.buttonLoadD4J);
        textViewLoadD4J = (TextView) findViewById(R.id.textViewLoadD4J);
        buttonLoadKeras = (Button) findViewById(R.id.buttonLoadKeras);
        textViewLoadKeras = (TextView) findViewById(R.id.textViewLoadKeras);
        buttonLoadDownstairs = (Button) findViewById(R.id.buttonLoadDownstairs);
        buttonLoadJogging = (Button) findViewById(R.id.buttonLoadJogging);
        buttonLoadSitting = (Button) findViewById(R.id.buttonLoadSitting);
        buttonLoadStanding = (Button) findViewById(R.id.buttonLoadStanding);
        buttonLoadUpstairs = (Button) findViewById(R.id.buttonLoadUpstairs);
        buttonLoadWalking = (Button) findViewById(R.id.buttonLoadWalking);
        textViewLoadData = (TextView) findViewById(R.id.textViewLoadData);
        buttonTestD4J = (Button) findViewById(R.id.buttonTestD4J);
        textViewTestD4J = (TextView) findViewById(R.id.textViewTestD4J);
        buttonTestKeras = (Button) findViewById(R.id.buttonTestKeras);
        textViewTestKeras = (TextView) findViewById(R.id.textViewTestKeras);
        buttonTestD4J.setEnabled(false);
        buttonTestKeras.setEnabled(false);
    }

    private void setButton () {
        buttonLoadD4J.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                boolean b = loadNetworkFromD4J();

                if (b) {
                    textViewLoadD4J.setText("Network loaded");
                    buttonTestD4J.setEnabled(true);

                } else {
                    textViewLoadD4J.setText("Error Network null...");
                }
                buttonLoadD4J.setEnabled(false);
                enableAllDataButton();

            }
        });

        buttonLoadKeras.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                boolean b = loadNetworkFromKeras();
                if (b) {
                    textViewLoadKeras.setText("Network loaded");
                    buttonTestKeras.setEnabled(true);

                } else {
                    textViewLoadKeras.setText("Error Network null...");
                }
                buttonLoadKeras.setEnabled(false);
                enableAllDataButton();
            }
        });

        buttonLoadDownstairs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textViewLoadData.setText("Downstairs data loaded");
                loadData(DOWNSTAIRS_DATA);
            }
        });


        buttonLoadJogging.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textViewLoadData.setText("Jogging data loaded");
                loadData(JOGGING_DATA);
            }
        });

        buttonLoadSitting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textViewLoadData.setText("Sitting data loaded");
                loadData(SITTING_DATA);
            }
        });

        buttonLoadStanding.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textViewLoadData.setText("Standing data loaded");
                loadData(STANDING_DATA);
            }
        });

        buttonLoadUpstairs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textViewLoadData.setText("Upstairs data loaded");
                loadData(UPSTAIRS_DATA);
            }
        });

        buttonLoadWalking.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                textViewLoadData.setText("Walking data loaded");
                loadData(WALKING_DATA);
            }
        });

        buttonTestD4J.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                testD4JNetwork();
                textViewTestD4J.setText("Test D4J model");
                buttonTestD4J.setEnabled(false);
            }
        });

        buttonTestKeras.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                testKerasNetwork();
                textViewTestKeras.setText("Test Keras model");
                buttonTestKeras.setEnabled(false);
            }
        });

    }

    private void disableAllDataButton () {
        buttonLoadDownstairs.setEnabled(false);
        buttonLoadJogging.setEnabled(false);
        buttonLoadSitting.setEnabled(false);
        buttonLoadStanding.setEnabled(false);
        buttonLoadUpstairs.setEnabled(false);
        buttonLoadWalking.setEnabled(false);
    }

    private void enableAllDataButton () {
        buttonLoadDownstairs.setEnabled(true);
        buttonLoadJogging.setEnabled(true);
        buttonLoadSitting.setEnabled(true);
        buttonLoadStanding.setEnabled(true);
        buttonLoadUpstairs.setEnabled(true);
        buttonLoadWalking.setEnabled(true);
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

    private boolean loadNetworkFromKeras () {
        NetworkManagement networkManagement = NetworkManagement.getInstance();
        /*InputStream inputStream = getResources().openRawResource(0);
        try {
            KerasNetwork = networkManagement.loadModelFromD4J(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        if (KerasNetwork == null) {
            return false;
        } else { return true;}

    }

    private void loadData (int value) {
        LoadData loadData = LoadData.getInstance(1,3,500);
        InputStream inputStream = null;
        switch (value) {
            case DOWNSTAIRS_DATA :
                inputStream = getResources().openRawResource(R.raw.downstairs);
                break;
            case JOGGING_DATA :
                inputStream = getResources().openRawResource(R.raw.jogging);
                break;
            case SITTING_DATA :
                inputStream = getResources().openRawResource(R.raw.sitting);
                break;
            case STANDING_DATA :
                inputStream = getResources().openRawResource(R.raw.standing);
                break;
            case UPSTAIRS_DATA :
                inputStream = getResources().openRawResource(R.raw.upstairs);
                break;
            case WALKING_DATA :
                inputStream = getResources().openRawResource(R.raw.walking);
                break;
            default:
                Toast.makeText(this, "Problem to load Data!", Toast.LENGTH_LONG).show();
                break;
        }
        try {
            iterator = loadData.createSetDataFromInputStream(inputStream);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void testD4JNetwork () {
        NetworkManagement networkManagement = NetworkManagement.getInstance();
        int res = networkManagement.useNetwork(D4JNetwork,iterator);
        toastRes(res);
    }

    private void testKerasNetwork () {
        NetworkManagement networkManagement = NetworkManagement.getInstance();
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
}

