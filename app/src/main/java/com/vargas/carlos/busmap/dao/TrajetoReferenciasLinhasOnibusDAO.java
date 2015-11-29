package com.vargas.carlos.busmap.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vargas.carlos.busmap.dto.ReferenciaLinhaOnibusDTO;
import com.vargas.carlos.busmap.dto.TrajetoReferenciaLinhaOnibusDTO;

public class TrajetoReferenciasLinhasOnibusDAO {

    public static final String TABELA = "trajetos_ref_linhas_onibus";
    public static final String ID = "_id";
    public static final String ID_REF_LINHAS_ONIBUS = "id_ref_linhas_onibus";
    public static final String BAIRRO_INICIAL = "bairro_inicial";
    public static final String CENTRO = "centro";
    public static final String BAIRRO_FINAL = "bairro_final";

    public static final String FK_ID_REF_LINHAS_ONIBUS = "fk__id_ref_linhas_onibus";

    private Context context;

    public TrajetoReferenciasLinhasOnibusDAO(Context context) {
        this.context = context;
    }

    public void salvar(ReferenciaLinhaOnibusDTO referenciaLinhaOnibusDTO) {

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();

        delete();

        TrajetoReferenciaLinhaOnibusDTO[] r = referenciaLinhaOnibusDTO.trajetos;

        ContentValues valores = new ContentValues();
        valores.put(TrajetoReferenciasLinhasOnibusDAO.BAIRRO_INICIAL, r[0].bairro_inicial);
        valores.put(TrajetoReferenciasLinhasOnibusDAO.CENTRO, r[0].centro);
        valores.put(TrajetoReferenciasLinhasOnibusDAO.BAIRRO_FINAL, r[0].bairro_final);
        valores.put(TrajetoReferenciasLinhasOnibusDAO.ID_REF_LINHAS_ONIBUS, r[0].id_ref_linhas_onibus);

        db.insert(TrajetoReferenciasLinhasOnibusDAO.TABELA, null, valores);

        Log.i("MAIN", "Salvando trajetos de referencias de linhas de onibus: " + r[0].id);

        db.close();
    }

    public void delete() {

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();

        //DELETA TODOS OS DADOS, PARA INSERIR DE NOVO
        db.execSQL("DELETE FROM " + TrajetoReferenciasLinhasOnibusDAO.TABELA);

        Log.i("MAIN", "Deletando dados TrajetoReferenciasLinhasOnibusDAO!");

        db.close();

    }
}
