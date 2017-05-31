package com.maxgfr.traxivityd4j.deeplearning;

import android.util.Log;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;

import java.util.List;

/**
 * Created by maxime on 30-May-17.
 */

public class UseMultiLayerNetwork {

    private final String TAG = "UseMultiLayerNetwork";

    private MultiLayerNetwork multiLayerNetwork;
    private DataNormalization normalizer;
    private int featuresCount;

    public UseMultiLayerNetwork(int featuresCount, MultiLayerNetwork multiLayerNetwork,DataNormalization normalizer) {
        this.featuresCount = featuresCount;
        this.multiLayerNetwork = multiLayerNetwork;
        this.normalizer = normalizer;
    }

    public List<LearnableModel> predictFromLearnableList (List<LearnableModel> predictSet){
        if (multiLayerNetwork == null) {
            Log.e(TAG, "Train a model first!");
            return predictSet;
        }
        INDArray predictionData = createPredictionData(predictSet);
        INDArray output = multiLayerNetwork.output(predictionData);
        return returnPredictions(predictSet, output);
    }

    private INDArray createPredictionData(List<LearnableModel> list) {
        int size = list.size();
        double[][] data = new double[size][featuresCount];
        for (int i = 0;i<list.size();i++) {
            int[] features = list.get(i).getFeatures();
            for (int j = 0; j < features.length ; j++) {
                data[i][j] = features[j];
            }
        }
        INDArray predictionData = new NDArray(data);
        normalizer.transform(predictionData);
        return predictionData;
    }

    private List<LearnableModel> returnPredictions(List<LearnableModel> list, INDArray output){
        for (int i = 0; i < list.size() ; i++) {
            int probability = (int) Math.round(output.slice(i).getDouble(0)*100);
            list.get(i).setProbability(probability);
        }
        return list;
    }

}
