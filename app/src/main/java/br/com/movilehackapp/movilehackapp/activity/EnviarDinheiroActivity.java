package br.com.movilehackapp.movilehackapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.movilehackapp.movilehackapp.R;

public class EnviarDinheiroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_dinheiro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
