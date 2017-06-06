package com.maxgfr.traxivityd4j;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.indexing.NDArrayIndex;

import java.io.IOException;
import java.io.InputStream;

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

        INDArray networkOutput = network.output(iterator,true);

        System.out.println("p(positive): " + networkOutput.getFloat(0));

    }

}