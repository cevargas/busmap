package com.vargas.carlos.busmap.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static final int VERSION = 2;
    private static final String DB_NAME = "busmap";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+LinhasOnibusDAO.TABELA+" (" +
                LinhasOnibusDAO.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LinhasOnibusDAO.NOME + " VARCHAR(100), " +
                LinhasOnibusDAO.TRAJETO + " TEXT, " +
                LinhasOnibusDAO.MAPA + " VARCHAR(200)" +
                ")");

        db.execSQL("CREATE TABLE "+ReferenciasLinhasOnibusDAO.TABELA+" (" +
                ReferenciasLinhasOnibusDAO.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ReferenciasLinhasOnibusDAO.ID_LINHAS_ONIBUS + " INTEGER, " +
                ReferenciasLinhasOnibusDAO.HORARIOS + " VARCHAR(100), " +
                ReferenciasLinhasOnibusDAO.SENTIDO + " VARCHAR(100), " +
                ReferenciasLinhasOnibusDAO.LEGENDAS + " TEXT, " +
                ReferenciasLinhasOnibusDAO.ENCERRAMENTO + " TEXT, " +
                "CONSTRAINT "+ReferenciasLinhasOnibusDAO.FK_ID_LINHAS_ONIBUS+" FOREIGN KEY("+ReferenciasLinhasOnibusDAO.ID_LINHAS_ONIBUS+") REFERENCES "+LinhasOnibusDAO.TABELA+"("+LinhasOnibusDAO.ID+") ON DELETE CASCADE" +
                ")");

        db.execSQL("CREATE TABLE " + TrajetoReferenciasLinhasOnibusDAO.TABELA + " (" +
                TrajetoReferenciasLinhasOnibusDAO.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TrajetoReferenciasLinhasOnibusDAO.ID_REF_LINHAS_ONIBUS + " INTEGER, " +
                TrajetoReferenciasLinhasOnibusDAO.BAIRRO_INICIAL + " VARCHAR(100), " +
                TrajetoReferenciasLinhasOnibusDAO.CENTRO + " VARCHAR(100), " +
                TrajetoReferenciasLinhasOnibusDAO.BAIRRO_FINAL + " VARCHAR(100), " +
                "CONSTRAINT "+TrajetoReferenciasLinhasOnibusDAO.FK_ID_REF_LINHAS_ONIBUS+" FOREIGN KEY("+TrajetoReferenciasLinhasOnibusDAO.ID_REF_LINHAS_ONIBUS+") REFERENCES " + ReferenciasLinhasOnibusDAO.TABELA + "(" + ReferenciasLinhasOnibusDAO.ID + ") ON DELETE CASCADE" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+LinhasOnibusDAO.TABELA+"");
        db.execSQL("DROP TABLE IF EXISTS "+ReferenciasLinhasOnibusDAO.TABELA+"");
        db.execSQL("DROP TABLE IF EXISTS "+TrajetoReferenciasLinhasOnibusDAO.TABELA+"");
        onCreate(db);
    }
}
