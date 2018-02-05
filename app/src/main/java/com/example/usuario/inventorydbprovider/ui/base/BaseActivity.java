package com.example.usuario.inventorydbprovider.ui.base;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;


/**
 * Clase base de las Activity donde se encuentran los métodos
 * comunes a todas las clases
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 * @see android.app.Activity
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Crea un Snackbar con mensaje o id de string
     * @param message
     */
    public void onError(String message){
        Snackbar.make(findViewById(android.R.id.content), message,
                Snackbar.LENGTH_SHORT).show();
    }
    /**
     * Crea un Snackbar con mensaje o id de string
     * @param resourceId
     */
    public void onError(int resourceId){
        Snackbar.make(findViewById(android.R.id.content), getResources().getString(resourceId),
                Snackbar.LENGTH_SHORT).show();
    }

}
