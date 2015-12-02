package com.vargas.carlos.busmap.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vargas.carlos.busmap.R;
import com.vargas.carlos.busmap.model.HorarioTrajetoReferenciaLinhasOnibus;

import java.util.List;

public class HorariosAdapter extends ArrayAdapter<HorarioTrajetoReferenciaLinhasOnibus> {

    public HorariosAdapter(Context context, List<HorarioTrajetoReferenciaLinhasOnibus> listaHorarios) {
        super(context, R.layout.item_list_horarios, listaHorarios);
    }

    public View getView(int position, View row, ViewGroup parent) {

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_list_horarios, parent, false);
        }

        final HorarioTrajetoReferenciaLinhasOnibus horarioTrajetoReferenciaLinhasOnibus = getItem(position);

        Log.i("HorariosAdapter", "Caiu em setar os Horarios " + horarioTrajetoReferenciaLinhasOnibus.getInicializador());

        ((TextView) row.findViewById(R.id.textInicializador)).setText(horarioTrajetoReferenciaLinhasOnibus.getInicializador());
        ((TextView) row.findViewById(R.id.textCentralizador)).setText(horarioTrajetoReferenciaLinhasOnibus.getCentralizado());
        ((TextView) row.findViewById(R.id.textFinalizador)).setText(horarioTrajetoReferenciaLinhasOnibus.getFinalizacao());

        return row;

    }

}
