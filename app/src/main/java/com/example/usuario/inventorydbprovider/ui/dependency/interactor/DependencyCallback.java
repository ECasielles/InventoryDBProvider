package com.example.usuario.inventorydbprovider.ui.dependency.interactor;

/**
 * Created by usuario on 25/01/18.
 */

public interface DependencyCallback {

    void onSuccess();
    void onError(Throwable error);

}
