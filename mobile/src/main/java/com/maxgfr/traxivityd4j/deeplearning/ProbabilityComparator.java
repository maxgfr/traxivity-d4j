package com.maxgfr.traxivityd4j.deeplearning;

import java.util.Comparator;

/**
 * Created by maxime on 29-May-17.
 */

public class ProbabilityComparator implements Comparator<LearnableModel> {
    @Override
    public int compare(LearnableModel o1, LearnableModel o2) {
        return o2.getValues().get("probability").compareTo(o1.getValues().get("probability"));
    }
}

