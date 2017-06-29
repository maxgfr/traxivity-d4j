package com.maxgfr.traxivityd4j;

import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.indexing.NDArrayIndex;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by maxime on 29-May-17.
 */

public class NetworkManagement {

    private static NetworkManagement INSTANCE = null;

    private NetworkManagement() {
    }

    public static synchronized NetworkManagement getInstance() {
        if (INSTANCE == null)
        { 	INSTANCE = new NetworkManagement();
        }
        return INSTANCE;
    }

    public MultiLayerNetwork loadModelFromD4J (InputStream is) throws IOException {
        MultiLayerNetwork restored = ModelSerializer.restoreMultiLayerNetwork(is);
        return restored;
    }

    /*//0.8.0 of D4J : throw new UnsupportedOperationException("Reading HDF5 files from InputStreams currently unsupported.");
    public MultiLayerNetwork loadModelFromKeras (InputStream is) throws InvalidKerasConfigurationException, IOException, UnsupportedKerasConfigurationException {
        MultiLayerNetwork restored = KerasModelImport.importKerasSequentialModelAndWeights(is);
        return restored;
    }*/

    public int useNetwork (MultiLayerNetwork network, DataSetIterator it) {
        int i = 0;
        System.out.println("Prediction is starting");
        List<Integer> list = new LinkedList<Integer>();

        while (it.hasNext()) {
            DataSet ds = it.next();
            int[] ls = network.predict(ds.getFeatureMatrix());
            for(int s : ls) {
                list.add(s);
            }
            i++;
        }

        dispOccurence(list);

        System.out.println("Analysis finished");

        int res = mostCommon(list);

        return res;
    }

    private <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();
    }

    private void dispOccurence (List<Integer> myList) {
        int downstairs,jogging,sitting,standing,upstairs,walking;
        downstairs=jogging=sitting=standing=upstairs=walking = 0;
        for (Integer x : myList) {
            switch (x) {
                case 0:  downstairs++;
                    break;
                case 1:  jogging++;
                    break;
                case 2:  sitting++;
                    break;
                case 3:  standing++;
                    break;
                case 4:  upstairs++;
                    break;
                case 5: walking++;
                    break;
            }
        }
        System.out.println("Number of occurrence :" +
                "\nfor downstairs is "+downstairs+
                "\nfor jogging is "+jogging+
                "\nfor sitting is "+sitting+
                "\nfor standing is "+ standing+
                "\nfor upstairs is " +upstairs+
                "\nfor walking is " +walking);
    }


}