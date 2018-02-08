package com.example.usuario.inventorydbprovider.ui.dependency.interactor;

import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.data.db.repository.DependencyRepository;

/**
 * Created by usuario on 27/11/17.
 */

public class AddEditDependencyInteractorImpl implements AddEditDependencyInteractor {

    OnAddEditFinishedListener listener;

    public AddEditDependencyInteractorImpl(OnAddEditFinishedListener listener) {
        this.listener = listener;
    }

    //Aquí habría que controlarlo con excepciones en lugar de elseif
    @Override
    public void validateDependency(String name, String shortname, String description, String imageName) {
        Dependency dependency = new Dependency(-1, name, shortname, description, imageName);
        if (name.isEmpty())
            listener.onNameEmptyError();
        else if (shortname.isEmpty())
            listener.onShortNameEmptyError();
        else if (shortname.length() < 2 && shortname.length() > 5)
            listener.onShortNameLengthError();
        else if (description.isEmpty())
            listener.onDescriptionEmptyError();
        else if (!DependencyRepository.getInstance().exists(dependency))
            addDependency(name, shortname, description, imageName);
    }

    @Override
    public void addDependency(String name, String shortname, String description, String imageName) {
        Dependency dependency = new Dependency(-1, name, shortname, description, imageName);
        DependencyRepository.getInstance().addDependency(dependency, this);
    }

    @Override
    public void editDependency(Dependency dependency, String description) {
        if (!description.isEmpty())
            DependencyRepository.getInstance().updateDependency(dependency, this);
        else
            listener.onDescriptionEmptyError();
    }

    @Override
    public void onSuccess() {
        listener.onSuccess();
    }

    @Override
    public void onError(Throwable error) {
        listener.onDatabaseError(error);
    }

}
