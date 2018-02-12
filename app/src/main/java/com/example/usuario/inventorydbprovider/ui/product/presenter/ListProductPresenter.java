package com.example.usuario.inventorydbprovider.ui.product.presenter;

import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.ui.product.contract.ListProductContract;
import com.example.usuario.inventorydbprovider.ui.product.interactor.ListProductInteractor;
import com.example.usuario.inventorydbprovider.ui.product.interactor.ListProductInteractorImpl;

import java.util.ArrayList;
import java.util.HashMap;

public class ListProductPresenter implements ListProductContract.Presenter, ListProductInteractor.OnProductLoadedListener {
    private ListProductContract.View view;
    private ListProductInteractor interactor;
    HashMap<Integer, Boolean> selection = new HashMap<>();

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

    @Override
    public void onProductViewsDeleted() {
        view.onProductsDeleted();
        loadProductViews();
    }

    @Override
    public void onProductDeletedError(Throwable throwable) {
        view.onDatabaseError(throwable);
    }

    @Override
    public void deleteProducts(ArrayList<Product> products) {
        interactor.deleteProducts(products);
    }

    @Override
    public void setNewSelection(int position) {
        selection.put(position, true);
    }
    @Override
    public void removeSelection(int position) {
        selection.remove(position);
    }
    @Override
    public void clearSelection() {
        selection.clear();
    }
    @Override
    public void deleteSelection() {
        ArrayList<Product> products = new ArrayList<>();
        for (Integer position: selection.keySet())
            products.add(view.getProduct(position));
        deleteProducts(products);

    }
    @Override
    public boolean getPositionChecked(int position) {
        return selection.containsKey(position);
    }

}
