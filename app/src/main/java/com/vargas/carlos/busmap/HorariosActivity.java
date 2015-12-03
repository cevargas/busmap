package com.vargas.carlos.busmap;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.vargas.carlos.busmap.adapter.HorariosAdapter;
import com.vargas.carlos.busmap.dao.HorariosTrajetoReferenciasLinhasOnibusDAO;
import com.vargas.carlos.busmap.dao.LinhasOnibusDAO;
import com.vargas.carlos.busmap.model.HorarioTrajetoReferenciaLinhasOnibus;
import com.vargas.carlos.busmap.model.LinhasOnibus;

import java.util.List;

public class HorariosActivity extends AbstractActivity {

    static final int defaultValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        //recupera parametro passado pela outra activity, id row linhas de onibus
        final int id = getIntent().getIntExtra("idHorario", defaultValue);
        String convertId = String.valueOf(id);

        LinhasOnibus linhaOnibus = new LinhasOnibusDAO(this).pesquisaLinhaOnibus(LinhasOnibusDAO.ID, convertId);

        final TextView viewById = (TextView) findViewById(R.id.textLinhaOnibus);
        viewById.setText(linhaOnibus.getNome());

        List<HorarioTrajetoReferenciaLinhasOnibus> list = new HorariosTrajetoReferenciasLinhasOnibusDAO(this).listaHorarios(id);

        HorariosAdapter adapter = new HorariosAdapter(this, list);
        ListView listHorarios = (ListView) findViewById(R.id.listHorarios);
        listHorarios.setAdapter(adapter);

        //seta o valor
        /*TextView getText = (TextView) findViewById(R.id.textPlaneta);
        getText.setText(planeta);

        Button btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("result", planeta);
                setResult(RESULT_OK, i);
                finish();
            }
        });*/
    }
}
