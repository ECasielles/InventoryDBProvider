package com.example.usuario.inventorydbprovider.ui.dependency.presenter;

import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.ui.dependency.contract.ListDependencyContract;
import com.example.usuario.inventorydbprovider.ui.dependency.interactor.ListDependencyInteractor;
import com.example.usuario.inventorydbprovider.ui.dependency.interactor.ListDependencyInteractorImpl;

import java.util.HashMap;
import java.util.List;


public class ListDependencyPresenter implements ListDependencyContract.Presenter,
        ListDependencyInteractor.OnLoadFinishedListener {
    public static final String TAG = "ListDependencyPresenter";

    //COMUNICACION CON MENU SELECCION MULTIPLE
    HashMap<Integer, Boolean> selection = new HashMap<>();
    private ListDependencyContract.View view;
    private ListDependencyInteractor interactor;


    public ListDependencyPresenter(ListDependencyContract.View view) {
        this.view = view;
        this.interactor = new ListDependencyInteractorImpl(this);
    }

    @Override
    public void loadDependencies() {
        view.showProgressDialog();
        interactor.loadDependencies();
    }

    @Override
    public void onDependenciesLoaded(List<Dependency> dependencies) {
        view.dismissProgressDialog();
        view.showDependencies(dependencies);
    }

    @Override
    public void deleteDependency(Dependency dependency) {
        interactor.deleteDependency(dependency);
    }

    @Override
    public void onDependencyDeleted() {
        view.showDeletedMessage();
        loadDependencies();
    }

    @Override
    public void onDatabaseError(Throwable error) {
        view.showMessage(error.getMessage());
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
        for (Integer position: selection.keySet())
            deleteDependency(view.getDependency(position));
    }
    @Override
    public boolean getPositionChecked(int position) {
        return selection.containsKey(position);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }



}
