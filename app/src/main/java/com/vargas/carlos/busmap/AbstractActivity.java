package com.vargas.carlos.busmap;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AbstractActivity  extends AppCompatActivity {
    //definicao da identificacao da classe nos logs
    final String TAG = this.getClass().getSimpleName();

    //mensagem de alert
    void showMessage(String msg, int duraction) {
        Toast.makeText(this, msg, duraction).show();
    }
}
