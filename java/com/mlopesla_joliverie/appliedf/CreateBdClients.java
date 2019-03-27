package com.mlopesla_joliverie.appliedf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zemica on 10/10/2017.
 */

//creation de la classe pour creer la bdd client
public class CreateBdClients extends SQLiteOpenHelper {
    private static final String TABLE_CLIENTS = "table_clients";
    static final String COL_ID = "_id";
    private static final String COL_NOM = "NOM";
    private static final String COL_PREN = "PRENOM";
    private static final String COL_ADR = "ADRESSE";
    //private static final String COL_EMAIL = "EMAIL";
    private static final String COL_TEL = "TEL";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_CLIENTS + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_NOM + " TEXT NOT NULL, " + COL_PREN + " TEXT NOT NULL," + COL_ADR + " TEXT NOT NULL," + /*COL_EMAIL + " TEXT NOT NULL," +*/ COL_TEL + " TEXT NOT NULL);";

    //Constructeur
    public CreateBdClients(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Appelée lorsqu’aucune base n’existe et que la classe utilitaire doit en créer
        //On créé la table à partir de requête écrite dans variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    //Appelée si la version de base a changé
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut supprimer la table et de recréer
        db.execSQL("DROP TABLE " + TABLE_CLIENTS + ";");
        onCreate(db);
    }
}