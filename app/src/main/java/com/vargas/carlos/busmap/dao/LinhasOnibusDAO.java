package com.vargas.carlos.busmap.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vargas.carlos.busmap.dto.LinhaOnibusDTO;
import com.vargas.carlos.busmap.dto.RetornoDTO;
import com.vargas.carlos.busmap.model.LinhasOnibus;

import java.util.ArrayList;
import java.util.List;

public class LinhasOnibusDAO {

    public static final String TABELA = "linhas_onibus";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String TRAJETO = "trajeto";
    public static final String MAPA = "mapa";

    private Context context;

    public LinhasOnibusDAO(Context context) {
        this.context = context;
    }

    public List<LinhasOnibus> listar(){

        List<LinhasOnibus> lista = new ArrayList<>();

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();
        String[] colunas = new String[] { ID, NOME, TRAJETO, MAPA };

        Cursor cursor = db.query(TABELA, colunas, null, null, null, null, null);

        while(cursor.moveToNext()) {

            LinhasOnibus linhasOnibus = new LinhasOnibus();
            linhasOnibus.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            linhasOnibus.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
            linhasOnibus.setTrajeto(cursor.getString(cursor.getColumnIndex(TRAJETO)));
            linhasOnibus.setMapa(cursor.getString(cursor.getColumnIndex(MAPA)));

            lista.add(linhasOnibus);
        }
        db.close();
        cursor.close();

        return lista;
    }

    public LinhasOnibus pesquisaLinhaOnibus(String field, String value) {

        LinhasOnibus linhaOnibus = new LinhasOnibus();

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+TABELA+" WHERE "+field+" = '"+value+"'", null);
        if(c.moveToFirst()) {

            linhaOnibus.setId(c.getColumnIndex(LinhasOnibusDAO.ID));
            linhaOnibus.setNome(c.getString(c.getColumnIndex(LinhasOnibusDAO.NOME)));
            linhaOnibus.setTrajeto(c.getString(c.getColumnIndex(LinhasOnibusDAO.TRAJETO)));
            linhaOnibus.setMapa(c.getString(c.getColumnIndex(LinhasOnibusDAO.MAPA)));
        }

        c.close();
        db.close();

        return linhaOnibus;

    }

    public void salvar(RetornoDTO retornoDTO) {

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();

        delete();

        for(LinhaOnibusDTO l: retornoDTO.linhas_onibus) {

            ContentValues valores = new ContentValues();
            valores.put(LinhasOnibusDAO.NOME, l.nome);
            valores.put(LinhasOnibusDAO.TRAJETO, l.trajeto);
            valores.put(LinhasOnibusDAO.MAPA, l.mapa);

            db.insert(LinhasOnibusDAO.TABELA, null, valores);

            if(l.referencias != null) {
                //salva as referencias
                new ReferenciasLinhasOnibusDAO(context).salvar(l);
            }

            Log.i("MAIN", "Salvando linhas de onibus: " + l.id);
        }

        db.close();
    }

    public void delete() {

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();

        //DELETA TODOS OS DADOS, PARA INSERIR DE NOVO
        db.execSQL("DELETE FROM " + LinhasOnibusDAO.TABELA);

        Log.i("MAIN", "Deletando dados LinhasOnibusDAO!");

        db.close();
    }
}
