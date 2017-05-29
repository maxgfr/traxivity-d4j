package com.maxgfr.traxivityd4j.data;

import com.maxgfr.traxivityd4j.deeplearning.LearnableModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maxime on 29-May-17.
 */

public class Activity implements LearnableModel {
    private int type;
    private int salary;
    private int choice;
    private int probability;

    public Activity(int type, int salary, int choice) {
        this.type = type;
        this.salary = salary;
        this.choice = choice;
    }

    @Override
    public void setProbability(int probability) {
        this.probability = probability;
    }

    @Override
    public int[] getFeatures() {
        return new int[]{type, salary};
    }

    @Override
    public void setFeatures(int[] features) {
        type = features[0];
        salary = features[1];
        choice = features[2];
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
        values.put("salary", salary);
        values.put("choice", choice);
        values.put("probability", probability);
        return values;
    }

}
