package com.maxgfr.traxivityd4j.data;

import com.maxgfr.traxivityd4j.deeplearning.LearnableModel;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.iter.NdIndexIterator;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.io.ClassPathResource;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by maxime on 29-May-17.
 */

public class MockData {

    private int labelIndex;
    private int numClasses;
    private int batchSize;

    public MockData (int labelIndex, int numClasses, int batchSize) {
        this.labelIndex = labelIndex;
        this.numClasses = numClasses;
        this.batchSize = batchSize;
    }

    public List<LearnableModel> createData (MultiLayerNetwork network, String path) throws IOException, InterruptedException {

        List<LearnableModel> trainSet = new LinkedList<LearnableModel>();
        int numLinesToSkip = 0;
        String delimiter = ",";
        int nRows = 2;
        int nCols = 2;

        // Read the txt file as a collection of records
        RecordReader recordReader = new CSVRecordReader(numLinesToSkip,delimiter);
        recordReader.initialize(new FileSplit(new ClassPathResource(path).getFile()));

        //Take the data
        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader,batchSize,labelIndex,numClasses);
        DataSet allData = iterator.next();
        allData.shuffle();

        INDArray output = network.output(allData.getFeatureMatrix());


        NdIndexIterator iter = new NdIndexIterator(nRows, nCols);
        while (iter.hasNext()) {
            int[] nextIndex = iter.next();
            double nextVal = output.getDouble(nextIndex);
            trainSet.add(new Accelerometer((int)nextVal,0,0));
        }

        return trainSet;
    }

}
