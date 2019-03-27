package com.mlopesla_joliverie.appliedf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Zemica on 10/10/2017.
 */

class BdClients {
    static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "edf.db";
    static final String TABLE_CLIENTS = "table_clients";
    static final String COL_ID = "_id";
    static final int NUM_COL_ID = 0;
    static final String COL_NOM = "NOM";
    static final int NUM_COL_NOM = 1;
    static final String COL_PREN = "PRENOM";
    static final int NUM_COL_PREN = 2;
    static final String COL_ADR = "ADRESSE";
    static final int NUM_COL_ADR = 3;
    /*static final String COL_EMAIL = "EMAIL";
    static final int NUM_COL_EMAIL = 4;*/
    static final String COL_TEL = "TEL";
    static final int NUM_COL_TEL = 4;

    private CreateBdClients bdClients;
    private Context context;
    private SQLiteDatabase db;

    //Constructeur
    public BdClients(Context context) {
        this.context = context;
        bdClients = new CreateBdClients(context, NOM_BDD, null, VERSION_BDD);
    }

    //Ouverture de la BBD
    public BdClients open() {
        db = bdClients.getWritableDatabase();
        return this;
    }

    //Fermeture de la BDD
    public BdClients close() {
        db.close();
        return this;
    }

    //Insertion d'un client
    public long insererClient(Client unClient) {
        ContentValues values = new ContentValues();
        values.put(COL_NOM, unClient.getNom());
        values.put(COL_PREN, unClient.getPrenom());
        values.put(COL_ADR, unClient.getAdresse());
        //values.put(COL_EMAIL, unClient.getEmail());
        values.put(COL_TEL, unClient.getTelephone());
        return db.insert(TABLE_CLIENTS, null, values);
    }

    public Cursor getData() {
        return db.rawQuery("SELECT * FROM " + TABLE_CLIENTS + ";", null);
    }
}