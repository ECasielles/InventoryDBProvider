package com.example.usuario.inventorydbprovider.ui.product.interactor;

import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.repository.ProductRepository;

/**
 * Created by usuario on 2/02/18.
 */

public class ViewProductInteractorImpl implements ViewProductInteractor {

    private final OnProducViewLoadedListener listener;

    public ViewProductInteractorImpl(OnProducViewLoadedListener listener) {
        this.listener = listener;
    }

    @Override
    public void saveProduct(Product product) {
        ProductRepository.getInstance().addProduct(product, this);
    }

    @Override
    public void updateProduct(Product product) {
        ProductRepository.getInstance().updateProduct(product, this);
    }

    @Override
    public void onSuccess() {
        listener.onProductLoaded();
    }

    @Override
    public void onError(Throwable throwable) {
        listener.onProductLoadError(throwable);
    }

}
