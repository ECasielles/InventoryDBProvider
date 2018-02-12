package com.example.usuario.inventorydbprovider.ui.product.interactor;

import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;

import java.util.ArrayList;

/**
 * Created by usuario on 1/02/18.
 */

public interface ListProductInteractor extends ProductCallback {

    void loadProductViews();

    void deleteProducts(ArrayList<Product> product);

    interface OnProductLoadedListener {
        void onProductViewsLoaded(ArrayList<ProductView> productViews);
        void onProductViewsDeleted();
        void onProductDeletedError(Throwable throwable);
    }

}
