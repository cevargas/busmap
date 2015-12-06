package com.vargas.carlos.busmap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vargas.carlos.busmap.R;
import com.vargas.carlos.busmap.model.LinhasOnibus;

import java.util.List;

public class ListaOnibusAdapter extends ArrayAdapter<LinhasOnibus> {

    //static final int REQ_ACTIVITY_HORARIOS = 1;
    //static final int REQ_ACTIVITY_TRAJETOS = 1;

    public ListaOnibusAdapter(Context context, List<LinhasOnibus> listaOnibus) {
        super(context, R.layout.item_list, listaOnibus);
    }

    public View getView(int position, View row, ViewGroup parent) {

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_list, parent, false);
        }

        final LinhasOnibus listaOnibus = getItem(position);
        ((TextView) row.findViewById(R.id.textLinha)).setText(listaOnibus.getNome());

        //ImageView imageView = (ImageView) row.findViewById(R.id.icon);
        //imageView.setImageResource(R.drawable.ic_action_overflow);
        //((TextView) row.findViewById(R.id.textLinhaDesc)).setText(listaOnibus.getTrajeto());

        /*
        final Context activity = this.getContext();
        //Botao Lista Horarios
        ((Button) row.findViewById(R.id.btnHorarios)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, HorariosActivity.class);
                intent.putExtra("idHorario", listaOnibus.getId());
                ((Activity) activity).startActivityForResult(intent, REQ_ACTIVITY_HORARIOS);
            }
        });

        //Botao Mapa
        ((Button) row.findViewById(R.id.btnMapa)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MapaActivity.class);
                intent.putExtra("idMapa", listaOnibus.getId());
                ((Activity) activity).startActivityForResult(intent, REQ_ACTIVITY_TRAJETOS);
            }
        });
        */

        return row;

    }
}

