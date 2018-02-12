package com.example.usuario.inventorydbprovider.ui.product.contract;

import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;

import java.util.ArrayList;

/**
 * Created by usuario on 1/02/18.
 */

public interface ListProductContract {

    interface View {
        void setPresenter(ListProductContract.Presenter presenter);

        void showProductViews(ArrayList<ProductView> productViews);
        Product getProduct(Integer position);

        void onProductsDeleted();
        void onDatabaseError(Throwable throwable);
    }

    interface Presenter {
        void loadProductViews();

        void deleteProducts(ArrayList<Product> product);

        void setNewSelection(int position);
        void removeSelection(int position);
        void clearSelection();
        void deleteSelection();
        boolean getPositionChecked(int position);
    }

}
