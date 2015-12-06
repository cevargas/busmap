package com.vargas.carlos.busmap;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vargas.carlos.busmap.utils.ConnectionCheckUtils;

public class AbstractActivity extends AppCompatActivity {

    //definicao da identificacao da classe nos logs
    final String TAG = this.getClass().getSimpleName();

    //mensagem de alert
    void showMessage(String msg, int duraction) {
        Toast.makeText(this, msg, duraction).show();
    }

    void alert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertdialog_custom_view, null);

        builder.setCancelable(false);
        builder.setTitle("Alerta!");
        builder.setMessage("Não foi possível sincronizar os dados. Você não está conectado a Internet.");
        builder.setView(dialogView);
        Button btn_positive = (Button) dialogView.findViewById(R.id.dialog_ok_btn);

        final AlertDialog dialog = builder.create();

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    boolean isConnected() {
        ConnectionCheckUtils connectionCheckUtils = new ConnectionCheckUtils(this);
        return connectionCheckUtils.hasInternetConnection();
    }
}