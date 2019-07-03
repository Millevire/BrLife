package com.example.esteban.brlife.CuadroDialogo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.esteban.brlife.R;


/**
 * @author BrotherWare
 *
 */
public class CuadroDialogoAgregarProducto {

public CuadroDialogoAgregarProducto(final Context context){
    final Dialog dialogo=new Dialog(context);
    dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialogo.setCancelable(false);
    dialogo.setContentView(R.layout.alert_agregar_producto);
    dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialogo.show();

    Button btnAgregarAlimento=dialogo.findViewById(R.id.btnAgregarRegistro);

    btnAgregarAlimento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogo.dismiss();
        }
    });
}
}
