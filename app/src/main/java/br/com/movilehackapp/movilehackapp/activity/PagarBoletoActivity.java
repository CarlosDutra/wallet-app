package br.com.movilehackapp.movilehackapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import br.com.movilehackapp.movilehackapp.R;

public class PagarBoletoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar_boleto);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
