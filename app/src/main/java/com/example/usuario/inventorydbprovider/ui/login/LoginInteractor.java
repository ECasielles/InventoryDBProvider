package com.example.usuario.inventorydbprovider.ui.login;

/**
 * Interfaz del interactor de la vista Login. Contiene
 * la interfaz OnLoginFinishedListener
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 *
 */

public interface LoginInteractor {

    void validateCredentials(String user, String password, OnLoginFinishedListener onLoginFinishedListener);

    interface OnLoginFinishedListener {
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
        void onSuccess();
    }
}
