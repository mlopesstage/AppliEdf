package com.mlopesla_joliverie.appliedf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zemica on 14/11/2017.
 */

//creation de la classe pour creer la bdd releves
public class CreateBdReleves extends SQLiteOpenHelper {
    private static final String TABLE_RELEVES = "table_releves";
    static final String COL_ID = "_id";
    private static final String COL_DATE_RELEVE = "DATE_RELEVE";
    private static final String COL_CPTHC = "CPTHC";
    private static final String COL_CPTHP = "CPTHP";
    private static final String COL_CODECLI = "CODE_CLI";
    private static final String COL_TYPERELEVE = "TYPE_RELEVE";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_RELEVES + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_DATE_RELEVE + " TEXT NOT NULL, " + COL_CPTHC + " NUMERIC NOT NULL," + COL_CPTHP + " NUMERIC NOT NULL," + COL_CODECLI + " NUMERIC NOT NULL," + COL_TYPERELEVE + " TEXT NOT NULL);";

    public CreateBdReleves(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
        db.execSQL("DROP TABLE " + TABLE_RELEVES + ";");
        onCreate(db);
    }
}
