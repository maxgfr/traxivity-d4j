package com.maxgfr.traxivityd4j;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    public File fileGenerate (InputStream inputStream) {

        File locationNetwork = new File("src/res/");
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(locationNetwork);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return locationNetwork;
    }


}