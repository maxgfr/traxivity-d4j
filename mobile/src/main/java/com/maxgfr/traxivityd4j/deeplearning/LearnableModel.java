package com.maxgfr.traxivityd4j.deeplearning;

import java.util.Map;

/**
 * Created by maxime on 29-May-17.
 */

public interface LearnableModel {

    int[] getFeatures();

    void setFeatures(int[] features);

    int[] getLabels();

    void setLabels(int[] labels);

    Map<String, Integer> getValues();

    void setProbability(int probability);

}
