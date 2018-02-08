package com.example.usuario.inventorydbprovider.ui.product.contract;

import com.example.usuario.inventorydbprovider.data.db.model.Product;

/**
 * Created by usuario on 1/02/18.
 */

public interface ViewProductContract {

    interface View {
        void setPresenter(ViewProductContract.Presenter presenter);
    }

    interface Presenter {
        void saveProduct(Product product);

        void updateProduct(Product product);
    }

}
