package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText titulo, color;
    private TextView presentacion;
    private Button ingresar;
    private LinearLayout fondo;
    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;

    private void pintaFondo(String prm1, String prm2){
        presentacion.setText(prm1);
        fondo.setBackgroundColor(Color.parseColor(prm2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titulo = findViewById(R.id.tx_titulo);
        color = findViewById(R.id.tx_color);
        ingresar = findViewById(R.id.bt_ingresar);
        presentacion = findViewById(R.id.lb_titulo);
        ingresar.setOnClickListener(this);
        fondo = findViewById(R.id.ll_fondo);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        edit = preferences.edit();

        if (preferences.getString("titulo","Titulo").equals("Titulo")){
            pintaFondo("Titulo", "#FFFFFF");
        }
        else{
            pintaFondo(preferences.getString("titulo", "Titulo").toString(), preferences.getString("color", "#FFFFFF").toString());
        }
    }

    @Override
    public void onClick(View v) {
        edit.putString("titulo",titulo.getText().toString());
        edit.putString("color", color.getText().toString());
        edit.commit();
        pintaFondo(preferences.getString("titulo", "Titulo").toString(), preferences.getString("color", "#FFFFFF").toString());

    }
}
