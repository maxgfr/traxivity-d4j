package com.maxgfr.traxivityd4j.data;

import com.maxgfr.traxivityd4j.deeplearning.LearnableModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by maxime on 29-May-17.
 */

public class MockData {
    public static List<LearnableModel> mockSet() {
        List<LearnableModel> trainSet = new LinkedList<LearnableModel>();

        //Mechanic likes from 6 up
        trainSet.add(new Activity(0,5,0));
        trainSet.add(new Activity(0,6,1));
        trainSet.add(new Activity(0,7,1));
        trainSet.add(new Activity(0,8,1));
        trainSet.add(new Activity(0,9,1));
        trainSet.add(new Activity(0,10,1));
        trainSet.add(new Activity(0,11,1));
        trainSet.add(new Activity(0,12,1));
        trainSet.add(new Activity(0,13,1));
        trainSet.add(new Activity(0,14,1));
        trainSet.add(new Activity(0,15,1));
        trainSet.add(new Activity(0,16,1));
        trainSet.add(new Activity(0,17,1));
        trainSet.add(new Activity(0,18,1));
        trainSet.add(new Activity(0,19,1));
        trainSet.add(new Activity(0,20,1));

        //Programmer likes from 8 up
        trainSet.add(new Activity(1,5,0));
        trainSet.add(new Activity(1,6,0));
        trainSet.add(new Activity(1,7,0));
        trainSet.add(new Activity(1,8,1));
        trainSet.add(new Activity(1,9,1));
        trainSet.add(new Activity(1,10,1));
        trainSet.add(new Activity(1,11,1));
        trainSet.add(new Activity(1,12,1));
        trainSet.add(new Activity(1,13,1));
        trainSet.add(new Activity(1,14,1));
        trainSet.add(new Activity(1,15,1));
        trainSet.add(new Activity(1,16,1));
        trainSet.add(new Activity(0,17,1));
        trainSet.add(new Activity(0,18,1));
        trainSet.add(new Activity(0,19,1));
        trainSet.add(new Activity(0,20,1));

        //Teacher likes from 10 up
        trainSet.add(new Activity(2,5,0));
        trainSet.add(new Activity(2,6,0));
        trainSet.add(new Activity(2,7,0));
        trainSet.add(new Activity(2,8,0));
        trainSet.add(new Activity(2,9,0));
        trainSet.add(new Activity(2,10,1));
        trainSet.add(new Activity(2,11,1));
        trainSet.add(new Activity(2,12,1));
        trainSet.add(new Activity(2,13,1));
        trainSet.add(new Activity(2,14,1));
        trainSet.add(new Activity(2,15,1));
        trainSet.add(new Activity(2,16,1));
        trainSet.add(new Activity(0,17,1));
        trainSet.add(new Activity(0,18,1));
        trainSet.add(new Activity(0,19,1));
        trainSet.add(new Activity(0,20,1));

        //Taxi driver likes from 12 up
        trainSet.add(new Activity(3,5,0));
        trainSet.add(new Activity(3,6,0));
        trainSet.add(new Activity(3,7,0));
        trainSet.add(new Activity(3,8,0));
        trainSet.add(new Activity(3,9,0));
        trainSet.add(new Activity(3,10,0));
        trainSet.add(new Activity(3,11,0));
        trainSet.add(new Activity(3,12,1));
        trainSet.add(new Activity(3,13,1));
        trainSet.add(new Activity(3,14,1));
        trainSet.add(new Activity(3,15,1));
        trainSet.add(new Activity(3,16,1));
        trainSet.add(new Activity(0,17,1));
        trainSet.add(new Activity(0,18,1));
        trainSet.add(new Activity(0,19,1));
        trainSet.add(new Activity(0,20,1));

        //Manager likes likes from 14 up
        trainSet.add(new Activity(4,5,0));
        trainSet.add(new Activity(4,6,0));
        trainSet.add(new Activity(4,7,0));
        trainSet.add(new Activity(4,8,0));
        trainSet.add(new Activity(4,9,0));
        trainSet.add(new Activity(4,10,0));
        trainSet.add(new Activity(4,11,0));
        trainSet.add(new Activity(4,12,0));
        trainSet.add(new Activity(4,13,0));
        trainSet.add(new Activity(4,14,1));
        trainSet.add(new Activity(4,15,1));
        trainSet.add(new Activity(4,16,1));
        trainSet.add(new Activity(0,17,1));
        trainSet.add(new Activity(0,18,1));
        trainSet.add(new Activity(0,19,1));
        trainSet.add(new Activity(0,20,1));

        //Carpenter likes from 16 up
        trainSet.add(new Activity(5,5,0));
        trainSet.add(new Activity(5,6,0));
        trainSet.add(new Activity(5,7,0));
        trainSet.add(new Activity(5,8,0));
        trainSet.add(new Activity(5,9,0));
        trainSet.add(new Activity(5,10,0));
        trainSet.add(new Activity(5,11,0));
        trainSet.add(new Activity(5,12,0));
        trainSet.add(new Activity(5,13,0));
        trainSet.add(new Activity(5,14,0));
        trainSet.add(new Activity(5,15,0));
        trainSet.add(new Activity(5,16,1));
        trainSet.add(new Activity(0,17,1));
        trainSet.add(new Activity(0,18,1));
        trainSet.add(new Activity(0,19,1));
        trainSet.add(new Activity(0,20,1));

        return trainSet;
    }
}
