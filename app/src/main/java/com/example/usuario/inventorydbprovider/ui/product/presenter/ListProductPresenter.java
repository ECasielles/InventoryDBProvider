package com.example.usuario.inventorydbprovider.ui.product.presenter;

import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.ui.product.contract.ListProductContract;
import com.example.usuario.inventorydbprovider.ui.product.interactor.ListProductInteractor;
import com.example.usuario.inventorydbprovider.ui.product.interactor.ListProductInteractorImpl;

import java.util.ArrayList;

public class ListProductPresenter implements ListProductContract.Presenter, ListProductInteractor.OnProductLoadedListener {
    private ListProductContract.View view;
    private ListProductInteractor interactor;

    public ListProductPresenter(ListProductContract.View view) {
        this.view = view;
        this.interactor = new ListProductInteractorImpl(this);
    }

    @Override
    public void loadProductViews() {
        interactor.loadProductViews();
    }

    @Override
    public void onProductViewsLoaded(ArrayList<ProductView> productViews) {
        view.showProductViews(productViews);
    }

}
