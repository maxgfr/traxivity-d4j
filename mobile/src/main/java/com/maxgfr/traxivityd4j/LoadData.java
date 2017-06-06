package com.maxgfr.traxivityd4j;

/**
 * Created by maxime on 05-Jun-17.
 */

public class LoadData {

    /** Instance unique non préinitialisée */
    private static LoadData INSTANCE = null;

    /** Constructeur privé */
    private LoadData() {
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized LoadData getInstance() {
        if (INSTANCE == null)
        { 	INSTANCE = new LoadData();
        }
        return INSTANCE;
    }

    //public void

}
