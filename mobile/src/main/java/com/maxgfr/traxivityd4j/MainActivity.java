package com.maxgfr.traxivityd4j;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class MainActivity extends AppCompatActivity {

    private BinaryClassifier binaryClassifier;
    private int inputs = 2;
    private int outputs = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binaryClassifier = new BinaryClassifier(inputs, outputs);
    }

}

