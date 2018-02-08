package com.example.usuario.inventorydbprovider.ui.product.presenter;

import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.ui.product.contract.ViewProductContract;
import com.example.usuario.inventorydbprovider.ui.product.interactor.ViewProductInteractor;
import com.example.usuario.inventorydbprovider.ui.product.interactor.ViewProductInteractorImpl;


public class ViewProductPresenter implements ViewProductContract.Presenter,
        ViewProductInteractor.OnProducViewLoadedListener {

    private ViewProductContract.View view;
    private ViewProductInteractor interactor;

    public ViewProductPresenter(ViewProductContract.View view) {
        this.view = view;
        this.interactor = new ViewProductInteractorImpl(this);
    }

    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }


}
