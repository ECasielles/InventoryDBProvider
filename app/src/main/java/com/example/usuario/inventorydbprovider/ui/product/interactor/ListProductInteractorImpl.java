package com.example.usuario.inventorydbprovider.ui.product.interactor;

import com.example.usuario.inventorydbprovider.data.db.repository.ProductRepository;

/**
 * Created by usuario on 1/02/18.
 */

public class ListProductInteractorImpl implements ListProductInteractor {

    private ListProductInteractor.OnProductLoadedListener listener;

    public ListProductInteractorImpl(OnProductLoadedListener listener) {
        this.listener = listener;
    }

    @Override
    public void loadProductViews() {
        listener.onProductViewsLoaded(ProductRepository.getInstance().getProducts());
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}