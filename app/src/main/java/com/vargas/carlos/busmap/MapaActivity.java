package com.vargas.carlos.busmap;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.vargas.carlos.busmap.client.MyWebViewClient;
import com.vargas.carlos.busmap.dao.LinhasOnibusDAO;
import com.vargas.carlos.busmap.model.LinhasOnibus;

public class MapaActivity extends AbstractActivity {

    static final int defaultValue = 0;
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //recupera parametro passado pela outra activity
        String id = String.valueOf(getIntent().getIntExtra("idMapa", defaultValue));
        //showMessage("ID mapa " + id, Toast.LENGTH_LONG);

        LinhasOnibus linhaOnibus = new LinhasOnibusDAO(this).pesquisaLinhaOnibus(LinhasOnibusDAO.ID, id);

        myWebView = new WebView(this);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl(linhaOnibus.getMapa());
        setContentView(myWebView);

        Log.i(TAG, linhaOnibus.getMapa());
    }
}
