package com.maxgfr.traxivityd4j;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.SequenceRecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.split.InputStreamInputSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by maxime on 05-Jun-17.
 */

public class LoadData {

    private static LoadData INSTANCE = null;

    private DataSetIterator dataSetIterator;

    private LoadData() {
    }

    public static synchronized LoadData getInstance() {
        if (INSTANCE == null)
        { 	INSTANCE = new LoadData();
        }
        return INSTANCE;
    }

    public DataSetIterator getData () { return  dataSetIterator; }

    public void createSetDataFromInputStream (InputStream is) throws IOException, InterruptedException {

        int batchSize = 500;

        RecordReader rr = new CSVRecordReader(1,",");
        rr.initialize(new InputStreamInputSplit(is));

        dataSetIterator = new RecordReaderDataSetIterator(rr, batchSize);

    }


}