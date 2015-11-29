package com.vargas.carlos.busmap;

import android.os.Bundle;
import android.widget.Toast;

public class HorariosActivity extends AbstractActivity {

    static final int defaultValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        //recupera parametro passado pela outra activity, id row linhas de onibus
        final int id = getIntent().getIntExtra("idHorario", defaultValue);

        showMessage("ID da Linha Onibus " + id, Toast.LENGTH_LONG);

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
