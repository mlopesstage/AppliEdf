package com.mlopesla_joliverie.appliedf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Zemica on 14/11/2017.
 */

class BdReleves {
    static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "edf.db";
    static final String TABLE_RELEVES = "table_releves";
    static final String COL_ID = "_id";
    static final int NUM_COL_ID = 0;
    static final String COL_DATE_RELEVE = "DATE_RELEVE";
    static final int NUM_COL_DATE_RELEVE = 1;
    static final String COL_CPTHC = "cptHC";
    static final int NUM_COL_CPTHC = 2;
    static final String COL_CPTHP = "cptHP";
    static final int NUM_COL_CPTHP = 3;
    static final String COL_CODECLI = "codeCli";
    static final int NUM_COL_CODECLI = 4;
    static final String COL_TYPERELEVE = "typeReleve";
    static final int NUM_COL_TYPERELEVE = 5;

    private CreateBdReleves bdReleves;
    private Context context;
    private SQLiteDatabase db;

    //Constructeur
    public BdReleves(Context context) {
        this.context = context;
        bdReleves = new CreateBdReleves(context, NOM_BDD, null, VERSION_BDD);
    }

    //Ouverture de la BBD
    public BdReleves open() {
        db = bdReleves.getWritableDatabase();
        return this;
    }

    //Fermeture de la BDD
    public BdReleves close() {
        db.close();
        return this;
    }

    //Insertion d'un releve
    public long insererReleve(Releves unReleve) {
        ContentValues values = new ContentValues();
        values.put(COL_DATE_RELEVE, unReleve.getDateReleve());
        values.put(COL_CPTHC, unReleve.getCptHC());
        values.put(COL_CPTHP, unReleve.getCptHP());
        values.put(COL_CODECLI, unReleve.getCodeCli());
        values.put(COL_TYPERELEVE, unReleve.getTypeReleve());
        return db.insert(TABLE_RELEVES, null, values);
    }

    public Cursor getData() {
        return db.rawQuery("SELECT * FROM " + TABLE_RELEVES + ";", null);
    }
}