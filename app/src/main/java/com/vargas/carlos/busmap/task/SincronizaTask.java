package com.vargas.carlos.busmap.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.vargas.carlos.busmap.MainActivity;
import com.vargas.carlos.busmap.dao.LinhasOnibusDAO;
import com.vargas.carlos.busmap.dto.RetornoDTO;
import com.vargas.carlos.busmap.utils.HttpUtils;

public class SincronizaTask extends AsyncTask<Integer, Void, RetornoDTO> {

    private String URL;
    private Context context;
    private ProgressDialog progress;

    public SincronizaTask(String URL, Context context) {
        this.URL = URL;
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progress = ProgressDialog.show(context, "Sincronizando, aguarde...", "Sicronização de Dados", true, true);
    }

    @Override
    protected RetornoDTO doInBackground(Integer... params) {
        return HttpUtils.get(URL, RetornoDTO.class);
    }

    @Override
    protected void onPostExecute(RetornoDTO rs) {
        //chama metodo para salvar em banco o retorno json da api
        new LinhasOnibusDAO(context).salvar(rs);
        progress.dismiss();
        Toast.makeText(context, "Sincronização concluída!", Toast.LENGTH_LONG).show();

        //volta para activity main
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
