package com.example.usuario.inventorydbprovider.ui.dependency.interactor;

import com.example.usuario.inventorydbprovider.data.db.model.Dependency;

/**
 * Created by usuario on 24/11/17.
 */

public interface AddEditDependencyInteractor extends DependencyCallback {

    void validateDependency(String name, String shortname, String description, String imageName);

    void addDependency(String name, String shortname, String description, String imageName);

    void editDependency(Dependency dependency, String description);

    interface OnAddEditFinishedListener {
        void onNameEmptyError();
        void onShortNameEmptyError();
        void onShortNameLengthError();
        void onDescriptionEmptyError();

        void onSuccess();
        void onDatabaseError(Throwable error);
    }

}
