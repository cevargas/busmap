package com.vargas.carlos.busmap;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.vargas.carlos.busmap.client.MyWebViewClient;
import com.vargas.carlos.busmap.dao.LinhasOnibusDAO;
import com.vargas.carlos.busmap.model.LinhasOnibus;

public class MapaActivity extends AbstractActivity {

    static final int defaultValue = 0;
    private WebView myWebView;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //recupera parametro passado pela outra activity
        String id = String.valueOf(getIntent().getIntExtra("idMapa", defaultValue));

        LinhasOnibus linhaOnibus = new LinhasOnibusDAO(this).pesquisaLinhaOnibus(LinhasOnibusDAO.ID, id);

        progressDialog = new ProgressDialog(MapaActivity.this);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();

        myWebView = new WebView(this);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });

        myWebView.loadUrl(linhaOnibus.getMapa());
        setContentView(myWebView);

        Log.i(TAG, linhaOnibus.getMapa());
    }
}
