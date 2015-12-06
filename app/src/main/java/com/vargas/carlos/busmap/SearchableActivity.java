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

import java.util.List;

public class SearchableActivity extends AbstractActivity {

    private LinhasOnibus linhaOnibus;

    static final int REQ_ACTIVITY_HORARIOS = 2;
    static final int REQ_ACTIVITY_TRAJETOS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            List<LinhasOnibus> linhasOnibus = new LinhasOnibusDAO(this).pesquisaLinhasOnibus(LinhasOnibusDAO.NOME, query);

            if(!linhasOnibus.isEmpty()) {
                final ListaOnibusAdapter adapter = new ListaOnibusAdapter(this, linhasOnibus);
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

                TextView text = (TextView) findViewById(R.id.noresult);
                text.setText("Nenhuma informação para listar.");
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem register = menu.findItem(R.id.sincronizar);
        register.setVisible(false);

        MenuItem register2 = menu.findItem(R.id.voltar);
        register2.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.voltar:
                Intent intent = new Intent(SearchableActivity.this, MainActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(SearchableActivity.this, HorariosActivity.class);
                intent.putExtra("idHorario", linhaOnibus.getId());
                startActivityForResult(intent, REQ_ACTIVITY_HORARIOS);

                return false;
            }
        });

        MenuItem trajetos = menu.add("Trajeto");
        trajetos.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(SearchableActivity.this, MapaActivity.class);
                intent.putExtra("idMapa", linhaOnibus.getId());
                startActivityForResult(intent, REQ_ACTIVITY_TRAJETOS);

                return false;
            }
        });

        super.onCreateContextMenu(menu, v, info);
    }
}