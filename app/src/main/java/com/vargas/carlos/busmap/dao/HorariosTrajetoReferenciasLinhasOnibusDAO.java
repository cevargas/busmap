package com.vargas.carlos.busmap.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vargas.carlos.busmap.dto.HorariosTrajetoReferenciaLinhaOnibusDTO;
import com.vargas.carlos.busmap.dto.TrajetoReferenciaLinhaOnibusDTO;
import com.vargas.carlos.busmap.model.HorarioTrajetoReferenciaLinhasOnibus;

import java.util.ArrayList;
import java.util.List;

public class HorariosTrajetoReferenciasLinhasOnibusDAO {

    public static final String TABELA = "horarios_trajetos_ref_linhas_onibus";
    public static final String ID = "_id";
    public static final String ID_TRAJ_REF_LINHAS_ONIBUS = "id_trajetos_ref_linhas_onibus";
    public static final String INICIALIZADOR = "inicializador";
    public static final String CENTRALIZADOR = "centralizador";
    public static final String FINALIZADOR = "finalizador";

    public static final String FK_ID_REF_LINHAS_ONIBUS = "fk_id_traj_ref_linhas_onibus";

    private Context context;

    public HorariosTrajetoReferenciasLinhasOnibusDAO(Context context) {
        this.context = context;
    }

    public void salvar(TrajetoReferenciaLinhaOnibusDTO trajetoReferenciaLinhaOnibusDTO) {

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();

        delete();

        for(HorariosTrajetoReferenciaLinhaOnibusDTO l: trajetoReferenciaLinhaOnibusDTO.horarios) {

            ContentValues valores = new ContentValues();
            valores.put(HorariosTrajetoReferenciasLinhasOnibusDAO.INICIALIZADOR, l.inicializador);
            valores.put(HorariosTrajetoReferenciasLinhasOnibusDAO.CENTRALIZADOR, l.centralizador);
            valores.put(HorariosTrajetoReferenciasLinhasOnibusDAO.FINALIZADOR, l.finalizador);
            valores.put(HorariosTrajetoReferenciasLinhasOnibusDAO.ID_TRAJ_REF_LINHAS_ONIBUS, l.id_trajetos_ref_linhas_onibus);

            db.insert(HorariosTrajetoReferenciasLinhasOnibusDAO.TABELA, null, valores);

        }

        Log.i("MAIN", "Salvando horarios de trajetos de referencias de linhas de onibus: " + trajetoReferenciaLinhaOnibusDTO.id);

        db.close();
    }

    public void delete() {

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();

        //DELETA TODOS OS DADOS, PARA INSERIR DE NOVO
        db.execSQL("DELETE FROM " + HorariosTrajetoReferenciasLinhasOnibusDAO.TABELA);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '"+ HorariosTrajetoReferenciasLinhasOnibusDAO.TABELA +"'");

        Log.i("MAIN", "Deletando dados HorariosTrajetoReferenciasLinhasOnibusDAO!");

        db.close();
    }

    public List<HorarioTrajetoReferenciaLinhasOnibus> listaHorarios (int linhaOnibus){

        List<HorarioTrajetoReferenciaLinhasOnibus> lista = new ArrayList<>();

        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();
        String query = "SELECT lo."+LinhasOnibusDAO.NOME+", hor.* FROM "+LinhasOnibusDAO.TABELA+" lo " +
                            "INNER JOIN "+ReferenciasLinhasOnibusDAO.TABELA+" ref on ref."+ReferenciasLinhasOnibusDAO.ID_LINHAS_ONIBUS+" = lo."+LinhasOnibusDAO.ID+" "+
                            "INNER JOIN "+TrajetoReferenciasLinhasOnibusDAO.TABELA+" tra on tra."+TrajetoReferenciasLinhasOnibusDAO.ID_REF_LINHAS_ONIBUS+" = ref."+ReferenciasLinhasOnibusDAO.ID+" " +
                            "INNER JOIN "+HorariosTrajetoReferenciasLinhasOnibusDAO.TABELA+" hor on hor."+HorariosTrajetoReferenciasLinhasOnibusDAO.ID_TRAJ_REF_LINHAS_ONIBUS+" = tra."+TrajetoReferenciasLinhasOnibusDAO.ID+" " +
                            "WHERE lo."+LinhasOnibusDAO.ID+" = "+linhaOnibus;

        //String query = "SELECT * FROM "+HorariosTrajetoReferenciasLinhasOnibusDAO.TABELA;

        Log.d("query", query);
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {

            HorarioTrajetoReferenciaLinhasOnibus horarioTrajetoReferenciaLinhasOnibus = new HorarioTrajetoReferenciaLinhasOnibus();

            horarioTrajetoReferenciaLinhasOnibus.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            horarioTrajetoReferenciaLinhasOnibus.setInicializador(cursor.getString(cursor.getColumnIndex(INICIALIZADOR)));
            horarioTrajetoReferenciaLinhasOnibus.setCentralizado(cursor.getString(cursor.getColumnIndex(CENTRALIZADOR)));
            horarioTrajetoReferenciaLinhasOnibus.setFinalizacao(cursor.getString(cursor.getColumnIndex(FINALIZADOR)));

            lista.add(horarioTrajetoReferenciaLinhasOnibus);
        }
        db.close();
        cursor.close();

        return lista;
    }
}
