package com.vargas.carlos.busmap;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.vargas.carlos.busmap.adapter.ListaOnibusAdapter;
import com.vargas.carlos.busmap.dao.LinhasOnibusDAO;
import com.vargas.carlos.busmap.model.LinhasOnibus;
import com.vargas.carlos.busmap.task.SincronizaTask;
import com.vargas.carlos.busmap.utils.ConnectionCheckUtils;

import java.util.List;

public class MainActivity extends AbstractActivity {

    static String URL = "http://192.168.0.102/busmap/api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //busca lista de linhas de onibus
        List<LinhasOnibus> list = new LinhasOnibusDAO(this).listar();

        //se a lista nao estiver vazia..carrega os dados do banco
        if(!list.isEmpty()) {
            ListaOnibusAdapter adapter = new ListaOnibusAdapter(this, list);
            ListView listOnibus = (ListView) findViewById(R.id.listOnibus);
            listOnibus.setAdapter(adapter);
        }
        else {
            //se nao, verifica se a conexao de internet esta disponivel
            ConnectionCheckUtils connectionCheckUtils = new ConnectionCheckUtils(this);
            if (connectionCheckUtils.hasInternetConnection()) {
                //se tiver, busca os dados da api
                new SincronizaTask(URL, this).execute();
            }
            else {
                //alert
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        //TODO: implementar a pesquisa
        //SearchView mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.sincronizar:
                new SincronizaTask(URL, this).execute();

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
