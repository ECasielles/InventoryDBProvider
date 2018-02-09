package com.example.usuario.inventorydbprovider.ui.product.interactor;


import com.example.usuario.inventorydbprovider.data.db.model.Product;

public interface ViewProductInteractor extends ProductCallback {

    void saveProduct(Product product);
    void updateProduct(Product product);

    interface OnProducViewLoadedListener {
        void onProductLoaded();
        void onProductLoadError(Throwable throwable);
    }

}
