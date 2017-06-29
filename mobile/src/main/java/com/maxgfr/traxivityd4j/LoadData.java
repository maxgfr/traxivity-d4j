package com.maxgfr.traxivityd4j;

import org.deeplearning4j.berkeley.Pair;
import org.deeplearning4j.datasets.iterator.INDArrayDataSetIterator;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maxime on 05-Jun-17.
 */

public class LoadData {

    private static LoadData INSTANCE = null;

    private int nHeight;
    private int nDepth;
    private int nWidth;

    private LoadData(int nHeight, int nDepth, int nWidth) {
        this.nHeight = nHeight;
        this.nDepth = nDepth;
        this.nWidth = nWidth;
    }

    public static synchronized LoadData getInstance(int nHeight, int nDepth, int nWidth) {
        if (INSTANCE == null)
        { 	INSTANCE = new LoadData(nHeight, nDepth, nWidth);
        }
        return INSTANCE;
    }

    public DataSetIterator createSetDataFromInputStream (InputStream is) throws IOException, InterruptedException {

        List<INDArray> datas = getData(is);

        List<INDArray> fakeLabel = createFalseRandLabels(6,datas.size());

        ArrayList<Pair> featuresAndLabels = mergeFeaturesWithLabels(datas,fakeLabel);

        Iterable featLab = featuresAndLabels;

        INDArrayDataSetIterator ds = new INDArrayDataSetIterator(featLab, 1);

        return ds;

    }

    private List<INDArray> createFalseRandLabels(int numOutcomes,int numSamples){

        List<INDArray> falseTarget= new ArrayList<INDArray>();

        for(int i=0;i<numSamples;i++){
            INDArray y = Nd4j.zeros(1,numOutcomes);
            if (Math.random()<0.15){
                y.putScalar(0,0,1);
            } else if (Math.random()>0.15 && Math.random()<0.3){
                y.putScalar(0,1,1);
            } else if (Math.random()>0.3 && Math.random()<0.45){
                y.putScalar(0,2,1);
            } else if (Math.random()>0.45 && Math.random()<0.65){
                y.putScalar(0,3,1);
            }else if (Math.random()>0.65 && Math.random()<0.80){
                y.putScalar(0,4,1);
            } else{
                y.putScalar(0,5,1);
            }
            falseTarget.add(y);
        }

        return falseTarget;
    }

    private ArrayList<Pair> mergeFeaturesWithLabels(List<INDArray> features,List<INDArray> labels){

        ArrayList<Pair> featuresAndLabels = new ArrayList<Pair>();

        for(int i=0;i<features.size();i++){
            featuresAndLabels.add(new Pair(features.get(i),labels.get(i)));
        }

        System.out.println("Size dataset: " + featuresAndLabels.size());

        return featuresAndLabels;
    }

    private List<INDArray> getData (InputStream input) throws IOException {

        InputStreamReader is = new InputStreamReader(input);

        BufferedReader reader = new BufferedReader(is);

        List<INDArray> datas = new ArrayList<INDArray>();
        String nextLine;
        int k = 0;
        int j = 0;
        INDArray myArray = Nd4j.zeros(1,nDepth, nHeight,nWidth);

        nextLine = reader.readLine(); //skip the first line
        while ((nextLine = reader.readLine()) != null) {
            if (nextLine != null) {
                String base = nextLine.substring(1,nextLine.length()-1); // remove [ and ]
                for (int i = 0; i < 3; i++) {
                    if (j==nWidth) {//500
                        j=0;
                        k++;
                        if (k==nDepth) { //3
                            k=0;
                            datas.add(myArray);
                            //System.out.println(myArray);
                            myArray = Nd4j.zeros(1,nDepth, nHeight,nWidth);
                        }
                    }
                    double value = Double.parseDouble(base.split(",")[i]);
                    myArray.putScalar(0,k,0,j,value);
                    j++;
                }
            }
        }
        return datas;
    }

}