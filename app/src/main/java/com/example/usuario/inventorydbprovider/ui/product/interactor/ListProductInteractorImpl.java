package com.example.usuario.inventorydbprovider.ui.product.interactor;

import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.repository.ProductRepository;

import java.util.ArrayList;

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
    public void deleteProducts(ArrayList<Product> products) {
        ProductRepository.getInstance().deleteProduct(products, this);
    }

    @Override
    public void onSuccess() {
        listener.onProductViewsDeleted();
    }

    @Override
    public void onError(Throwable throwable) {
        listener.onProductDeletedError(throwable);
    }

}
