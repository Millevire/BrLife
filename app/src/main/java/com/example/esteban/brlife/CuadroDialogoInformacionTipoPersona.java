package com.example.esteban.brlife;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class CuadroDialogoInformacionTipoPersona {

  public CuadroDialogoInformacionTipoPersona(final Context context){
      final Dialog dialogo=new Dialog(context);
      dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
      dialogo.setCancelable(false);
      dialogo.setContentView(R.layout.alert_informacion_tipo_persona);
      dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      dialogo.show();

      Button btnAceptarAlertInformacionTipoPersona=dialogo.findViewById(R.id.btnAceptarAlertInformacionTipoPersona);

      btnAceptarAlertInformacionTipoPersona.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              dialogo.dismiss();
          }
      });
  }
}
