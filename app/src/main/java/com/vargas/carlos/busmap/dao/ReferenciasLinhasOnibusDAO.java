package com.vargas.carlos.busmap.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vargas.carlos.busmap.dto.LinhaOnibusDTO;
import com.vargas.carlos.busmap.dto.ReferenciaLinhaOnibusDTO;

public class ReferenciasLinhasOnibusDAO {

    public static final String TABELA = "ref_linhas_onibus";
    public static final String ID = "_id";
    public static final String ID_LINHAS_ONIBUS = "id_linhas_onibus";
    public static final String HORARIOS = "horarios";
    public static final String SENTIDO = "sentido";
    public static final String LEGENDAS = "legendas";
    public static final String ENCERRAMENTO = "encerramento";

    public static final String FK_ID_LINHAS_ONIBUS = "fk_id_linhas_onibus";

    private Context context;

    public ReferenciasLinhasOnibusDAO(Context context) {
        this.context = context;
    }

    public void salvar(LinhaOnibusDTO linhaOnibusDTO) {

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();

        delete();

        ReferenciaLinhaOnibusDTO[] r = linhaOnibusDTO.referencias;

        ContentValues valores = new ContentValues();
        valores.put(ReferenciasLinhasOnibusDAO.HORARIOS, r[0].horarios);
        valores.put(ReferenciasLinhasOnibusDAO.SENTIDO, r[0].sentido);
        valores.put(ReferenciasLinhasOnibusDAO.LEGENDAS, r[0].legendas);
        valores.put(ReferenciasLinhasOnibusDAO.ENCERRAMENTO, r[0].encerramento);
        valores.put(ReferenciasLinhasOnibusDAO.ID_LINHAS_ONIBUS, r[0].id_linhas_onibus);

        db.insert(ReferenciasLinhasOnibusDAO.TABELA, null, valores);

        if(r[0].trajetos != null) {
            //salva os trajetos
            new TrajetoReferenciasLinhasOnibusDAO(context).salvar(r[0]);
        }

        Log.i("MAIN", "Salvando referencias de linhas de onibus: " + r[0].id);

        db.close();
    }

    public void delete() {

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();

        //DELETA TODOS OS DADOS, PARA INSERIR DE NOVO
        db.execSQL("DELETE FROM " + ReferenciasLinhasOnibusDAO.TABELA);

        Log.i("MAIN", "Deletando dados ReferenciasLinhasOnibusDAO!");

        db.close();
    }
}
