package com.example.usuario.inventorydbprovider.ui.product.interactor;

/**
 * Created by usuario on 2/02/18.
 */

public class ProductViewInteractorImpl implements ProductViewInteractor {

    private final OnProducViewLoadedListener listener;

    public ProductViewInteractorImpl(OnProducViewLoadedListener listener) {
        this.listener = listener;
    }


}
