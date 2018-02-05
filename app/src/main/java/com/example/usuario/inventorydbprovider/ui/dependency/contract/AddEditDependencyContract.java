package com.example.usuario.inventorydbprovider.ui.dependency.contract;

import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.ui.base.BasePresenter;

import java.io.Serializable;

/**
 * Created by usuario on 23/11/17.
 */

public interface AddEditDependencyContract {

    interface View {
        void setPresenter(AddEditDependencyContract.Presenter presenter);
        void navigateToListDependency();
        void setNameEmptyError();
        void setShortNameEmptyError();
        void setShortNameLengthError();
        void setDescriptionEmptyError();

        void setDatabaseError(String message);
    }
    interface Presenter extends BasePresenter, Serializable {
        void saveDependency(String name, String shortName, String description, String imageName);
        void editDependency(Dependency dependency, String description);
        @Override
        void onDestroy();
    }

}
