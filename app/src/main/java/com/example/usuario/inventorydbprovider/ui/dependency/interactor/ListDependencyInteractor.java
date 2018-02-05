package com.example.usuario.inventorydbprovider.ui.dependency.interactor;

import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.utils.Error;

import java.util.List;

/**
 * Created by usuario on 27/11/17.
 */

public interface ListDependencyInteractor extends DependencyCallback {

    void deleteDependency(Dependency dependency);

    void loadDependencies();

    interface OnLoadFinishedListener {
        void onDependencyDeleted();
        void onDependenciesLoaded(List<Dependency> dependencies);
        void onDatabaseError(Error error);
    }

}
