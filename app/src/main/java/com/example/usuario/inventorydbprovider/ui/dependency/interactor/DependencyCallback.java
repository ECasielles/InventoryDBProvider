package com.example.usuario.inventorydbprovider.ui.dependency.interactor;

import com.example.usuario.inventorydbprovider.utils.Error;

/**
 * Created by usuario on 25/01/18.
 */

public interface DependencyCallback {

    void onSuccess();
    void onError(Error error);

}
