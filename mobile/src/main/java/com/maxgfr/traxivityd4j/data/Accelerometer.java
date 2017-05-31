package com.maxgfr.traxivityd4j.data;

import com.maxgfr.traxivityd4j.deeplearning.LearnableModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maxime on 29-May-17.
 */

public class Accelerometer implements LearnableModel {
    private int type;
    private int choice;
    private int probability;

    public Accelerometer(int type, int salary, int choice) {
        this.type = type;
        this.choice = choice;
    }

    @Override
    public void setProbability(int probability) {
        this.probability = probability;
    }

    @Override
    public int[] getFeatures() {
        return new int[]{type};
    }

    @Override
    public void setFeatures(int[] features) {
        type = features[0];
        choice = features[1];
    }

    @Override
    public int[] getLabels() {
        return new int[]{choice};
    }

    @Override
    public void setLabels(int[] labels) {
        this.choice = labels[0];
    }

    @Override
    public Map<String, Integer> getValues() {
        Map<String, Integer> values = new HashMap<>();
        values.put("type", type);
        values.put("choice", choice);
        values.put("probability", probability);
        return values;
    }

}
