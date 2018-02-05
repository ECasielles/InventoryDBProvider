package com.example.usuario.inventorydbprovider.ui.login;

import android.text.TextUtils;

import com.example.usuario.inventorydbprovider.data.db.repository.UserRepository;
import com.example.usuario.inventorydbprovider.utils.CommonUtils;

/**
 * Clase interactor del presentador de la vista Login.
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 * @see LoginInteractor
 * @see LoginInteractor.OnLoginFinishedListener
 *
 */
public class LoginInteractorImpl implements LoginInteractor {

    public void validateCredentials(String user, String password,
                                    LoginInteractor.OnLoginFinishedListener onLoginFinishedListener) {
        //Realiza todas las comprobaciones
        if (TextUtils.isEmpty(password))
            onLoginFinishedListener.onPasswordEmptyError();
        else if (TextUtils.isEmpty(user))
            onLoginFinishedListener.onUserEmptyError();
        else if (!CommonUtils.isPasswordValid(password))
            onLoginFinishedListener.onPasswordError();
        else if (UserRepository.getInstance().validateCredentials(user, password))
            onLoginFinishedListener.onSuccess();
    }

}
