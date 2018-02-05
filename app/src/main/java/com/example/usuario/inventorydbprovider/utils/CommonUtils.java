package com.example.usuario.inventorydbprovider.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by usuario on 13/11/17.
 */
public final class CommonUtils {

    /**
     * Comprueba que la contraseña tenga los siguientes requisitos:
     *  Debe contener al menos un dígito
     *  Debe contener al menos un carácter en mayúsculas
     *  Debe contener al menos un carácter en minúsculas
     *  Debe contener una longitud mínima de 6 caracteres
     * @param password contraseña
     * @return True si la contraseña cumple el patrón.
     */
    public static boolean isPasswordValid(String password){
        Pattern pattern;
        Matcher matcher;
        /**
         * ^               # Inicio de la cadena
         (?=.*[0-9])       # Al menos un dígito
         (?=.*[a-z])       # Al menos una minúscula
         (?=.*[A-Z])       # Al menos una mayúscula
         .{6,20}           # Longitud mínima 6 y máxima 20
         $                 # Fin de la cadena
         */
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static ProgressDialog showLoadDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        if(progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    /*
    //Claro caso de extraer una clase, con función e identidad propias
    interface ConfirmationDialog {
        static final String MESSAGE = "Message";
        static final String TITLE = "Title";
    }

    *//**
     * Cuadro de diálogo genérico que sirve para cualquier Fragment o Presenter.
     * @param bundle
     * @param context
     * @return
     *//*
    public static Dialog showConfirmDialog(Bundle bundle, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(bundle.getString(CommonDialog.ConfirmationDialog.MESSAGE))
                .setTitle(bundle.getString(CommonDialog.ConfirmationDialog.TITLE))
                .setPositiveButton(R.string.btnOk, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Usamos cancel en lugar de dismiss, porque dismiss no elimina
                        dialogInterface.cancel();
                    }
                });
        return builder.create();
    }*/


}
