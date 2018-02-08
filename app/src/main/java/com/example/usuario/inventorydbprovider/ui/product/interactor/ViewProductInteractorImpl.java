package com.example.usuario.inventorydbprovider.ui.product.interactor;

/**
 * Created by usuario on 2/02/18.
 */

public class ViewProductInteractorImpl implements ViewProductInteractor {

    private final OnProducViewLoadedListener listener;

    public ViewProductInteractorImpl(OnProducViewLoadedListener listener) {
        this.listener = listener;
    }


}
