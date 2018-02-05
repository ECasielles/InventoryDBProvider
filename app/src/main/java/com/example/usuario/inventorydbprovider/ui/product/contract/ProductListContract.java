package com.example.usuario.inventorydbprovider.ui.product.contract;

import com.example.usuario.inventorydbprovider.data.db.model.ProductView;

import java.util.ArrayList;

/**
 * Created by usuario on 1/02/18.
 */

public interface ProductListContract {

    interface View {
        void setPresenter(ProductListContract.Presenter presenter);

        void showProductViews(ArrayList<ProductView> productViews);
    }

    interface Presenter {
        void loadProductViews();
    }

}
