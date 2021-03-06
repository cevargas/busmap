package com.vargas.carlos.busmap;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.vargas.carlos.busmap.adapter.ListaOnibusAdapter;
import com.vargas.carlos.busmap.dao.LinhasOnibusDAO;
import com.vargas.carlos.busmap.model.LinhasOnibus;
import com.vargas.carlos.busmap.task.SincronizaTask;

import java.util.List;

public class MainActivity extends AbstractActivity {

    static String URL = "http://busmap.moblin.com.br/api";

    static final int REQ_ACTIVITY_HORARIOS = 1;
    static final int REQ_ACTIVITY_TRAJETOS = 1;

    private LinhasOnibus linhaOnibus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //busca lista de linhas de onibus
        List<LinhasOnibus> list = new LinhasOnibusDAO(this).listar();

        //se a lista nao estiver vazia..carrega os dados do banco
        if(!list.isEmpty()) {

            final ListaOnibusAdapter adapter = new ListaOnibusAdapter(this, list);
            final ListView listOnibus = (ListView) findViewById(R.id.listOnibus);
            listOnibus.setAdapter(adapter);

            listOnibus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    linhaOnibus = (LinhasOnibus) parent.getItemAtPosition(position);
                    parent.showContextMenuForChild(view);
                }
            });

            registerForContextMenu(listOnibus);

        }
        else {
            //se nao, verifica se a conexao de internet esta disponivel
            if (isConnected()) {
                //se tiver, busca os dados da api
                new SincronizaTask(URL, this).execute();
            }
            else {
                alert();

                TextView text = (TextView) findViewById(R.id.noresult);
                text.setText("Nenhuma informação para listar.");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.options_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem register = menu.findItem(R.id.voltar);
        register.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.sincronizar:
                if (isConnected()) {
                    new SincronizaTask(URL, this).execute();
                }
                else {
                    alert();
                }

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {

        MenuItem horarios = menu.add("Horários");
        horarios.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, HorariosActivity.class);
                intent.putExtra("idHorario", linhaOnibus.getId());
                startActivityForResult(intent, REQ_ACTIVITY_HORARIOS);

                return false;
            }
        });

        MenuItem trajetos = menu.add("Trajeto");
        trajetos.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, MapaActivity.class);
                intent.putExtra("idMapa", linhaOnibus.getId());
                startActivityForResult(intent, REQ_ACTIVITY_TRAJETOS);

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, info);
    }
}
