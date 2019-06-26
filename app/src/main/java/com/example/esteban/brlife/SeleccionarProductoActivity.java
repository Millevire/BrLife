package com.example.esteban.brlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SeleccionarProductoActivity extends AppCompatActivity {
   private Button btnMostrarAlimento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_producto);
        btnMostrarAlimento=(Button)findViewById(R.id.btnBuscarSelAlimAct);
      //  btnMostrarAlimento=(Button)findViewById(R.id.btnMostrarAliemento);


    }
}
