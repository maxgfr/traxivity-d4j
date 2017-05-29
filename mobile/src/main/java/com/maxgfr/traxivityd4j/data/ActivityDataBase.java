package com.maxgfr.traxivityd4j.data;

import com.maxgfr.traxivityd4j.deeplearning.LearnableModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by maxime on 29-May-17.
 */

public class ActivityDataBase {
    private static List<LearnableModel> savedModels = new LinkedList<>();

    public static void writeActivity(List<LearnableModel> models) {
        savedModels.addAll(models);
    }

    public static List<LearnableModel> readActivity() {
        return savedModels;
    }
}
