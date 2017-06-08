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
import java.util.List;

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

    public void useNetwork (MultiLayerNetwork network, DataSetIterator iterator) {
        //batch size = 500 for eachDataSet
        int i = 0;
        //while (iterator.hasNext()) {
            for (int j = 0 ; j<10 ; j++){
            DataSet dataSet = iterator.next();
            network.output(dataSet.getFeatureMatrix(),false);
            i++;
            System.out.println(i);
        }

        System.out.println(network.getIndex());



        /*allData.shuffle();
        DataNormalization normalizer = new NormalizerStandardize();
        normalizer.fit(allData);
        normalizer.transform(allData);
        INDArray array = allData.getFeatures();
        int[] res = network.predict(array);
        for (int i : res){
            System.out.println(i);
        }*/


    }

}