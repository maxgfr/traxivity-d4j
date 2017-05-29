package com.maxgfr.traxivityd4j.data;

import com.maxgfr.traxivityd4j.deeplearning.LearnableModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by maxime on 29-May-17.
 */

public class RandomizeData {

    public static Activity randomizeActivity() {
        return new Activity(randomNumber(0,5),randomNumber(5,20),0);
    }

    public static List<LearnableModel> randomizeActivities(int number) {
        List<LearnableModel> jobs = new LinkedList<>();
        for (int i=0;i<number;i++) {
            jobs.add(randomizeActivity());
        }
        return jobs;
    }

    private static int randomNumber(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
