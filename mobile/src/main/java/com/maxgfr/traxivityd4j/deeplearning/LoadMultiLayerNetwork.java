package com.maxgfr.traxivityd4j.deeplearning;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * Created by maxime on 29-May-17.
 */

public class LoadMultiLayerNetwork {

    /** Instance unique non préinitialisée */
    private static LoadMultiLayerNetwork INSTANCE = null;

    /** Constructeur privé */
    private LoadMultiLayerNetwork() {
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized LoadMultiLayerNetwork getInstance() {
        if (INSTANCE == null)
        { 	INSTANCE = new LoadMultiLayerNetwork();
        }
        return INSTANCE;
    }

    public MultiLayerNetwork loadModelFromFile (File locationToSave) throws IOException {
        MultiLayerNetwork restored = ModelSerializer.restoreMultiLayerNetwork(locationToSave);
        return restored;
    }

    public MultiLayerNetwork loadModelFromKeras (String path) throws UnsupportedKerasConfigurationException, IOException, InvalidKerasConfigurationException {
        MultiLayerNetwork network = KerasModelImport.importKerasSequentialModelAndWeights(path);
        return network;
    }

}