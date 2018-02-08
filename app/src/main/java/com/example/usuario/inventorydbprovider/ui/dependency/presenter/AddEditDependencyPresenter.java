package com.example.usuario.inventorydbprovider.ui.dependency.presenter;

import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.ui.dependency.contract.AddEditDependencyContract;
import com.example.usuario.inventorydbprovider.ui.dependency.interactor.AddEditDependencyInteractor;
import com.example.usuario.inventorydbprovider.ui.dependency.interactor.AddEditDependencyInteractorImpl;

/**
 * Created by usuario on 23/11/17.
 */

public class AddEditDependencyPresenter implements AddEditDependencyContract.Presenter,
        AddEditDependencyInteractor.OnAddEditFinishedListener {
    public static final String TAG = "AddEditDependencyPresenter";
    private AddEditDependencyContract.View view;
    private AddEditDependencyInteractorImpl interactor;


    public AddEditDependencyPresenter(AddEditDependencyContract.View view) {
        this.view = view;
        this.interactor = new AddEditDependencyInteractorImpl(this);
    }

    @Override
    public void saveDependency(String name, String shortname, String description, String imageName) {
        interactor.validateDependency(name, shortname, description, imageName);
    }

    @Override
    public void editDependency(Dependency dependency, String description) {
        dependency.setDescription(description);
        interactor.editDependency(dependency, description);
    }

    @Override
    public void onNameEmptyError() {
        view.setNameEmptyError();
    }
    @Override
    public void onShortNameEmptyError() {
        view.setShortNameEmptyError();
    }
    @Override
    public void onShortNameLengthError() {
        view.setShortNameLengthError();
    }
    @Override
    public void onDescriptionEmptyError() {
        view.setDescriptionEmptyError();
    }

    @Override
    public void onSuccess() {
        view.navigateToListDependency();
    }
    @Override
    public void onDatabaseError(Throwable error) {
        view.setDatabaseError(error.getMessage());
    }

    /**
     * Se llama cuando se destruya la Activity
     */
    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }
}
