package com.example.usuario.inventorydbprovider.ui.product.interactor;

import com.example.usuario.inventorydbprovider.data.db.repository.ProductRepository;

/**
 * Created by usuario on 1/02/18.
 */

public class ProductListInteractorImpl implements ProductListInteractor {

    private ProductListInteractor.OnProductLoadedListener listener;

    public ProductListInteractorImpl(OnProductLoadedListener listener) {
        this.listener = listener;
    }

    @Override
    public void loadProductViews() {
        listener.onProductViewsLoaded(ProductRepository.getInstance().getProductViews());
    }

}
