package com.example.esteban.brlife.Clases;

import android.content.DialogInterface;
import android.view.KeyEvent;


/**
 * @author BrotherWare
 *
 */
public class DialogKeyListener implements DialogInterface.OnKeyListener {


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK){
          dialog.dismiss();
            return false;
        }

        return true;
    }
}
