package com.maxgfr.traxivityd4j.deeplearning;

import android.util.Log;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

/**
 * Created by maxime on 31-May-17.
 */

public class DataClassifier {

    private final String TAG = "DataClassifier";

    private int labelsCount;
    private int featuresCount;

    public DataClassifier (int labelsCount, int featuresCount) {
        this.labelsCount = labelsCount;
        this.featuresCount = featuresCount;
    }

    public DataNormalization createNormalizer (List<LearnableModel> list)  {
        DataSet trainingData = createTrainingData(list);
        DataNormalization normalizer = new NormalizerStandardize();
        normalizer.fit(trainingData);
        normalizer.transform(trainingData);
        return  normalizer;
    }

    private DataSet createTrainingData(List<LearnableModel> list) {
        int size = list.size();
        double[][] inputData = new double[size][featuresCount];
        double[][] outputData = new double[size][labelsCount];
        for (int i = 0;i<list.size();i++) {
            int[] features = list.get(i).getFeatures();
            if (features.length!= featuresCount) {
                Log.e(TAG, "Mismatched number of features! Got: "+features.length+", expected: "+ featuresCount);
            }
            for (int j = 0; j < features.length ; j++) {
                inputData[i][j] = features[j];
            }

            int[] labels = list.get(i).getLabels();
            if (labels.length!= labelsCount) {
                Log.e(TAG, "Mismatched number of labels! Got: "+labels.length+", expected: "+ labelsCount);
            }
            for (int j = 0; j < labels.length ; j++) {
                outputData[i][j] = labels[j];
            }
        }

        INDArray trainingFeatures = new NDArray(inputData);
        INDArray trainingLabels   = new NDArray(outputData);
        DataSet trainingData = new org.nd4j.linalg.dataset.DataSet(trainingFeatures, trainingLabels);
        return trainingData;
    }

}
