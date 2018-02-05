package com.example.usuario.inventorydbprovider.ui.dependency.contract;

import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.ui.base.BasePresenter;

import java.io.Serializable;
import java.util.List;

/**
 * Aglutina distintas interfaces
 */
public interface ListDependencyContract {

    interface View {
        void setPresenter(ListDependencyContract.Presenter presenter);

        void showDependencies(List<Dependency> dependencies);

        Dependency getDependency(int position);

        void showDeletedMessage();
        void showMessage(String message);

        void showProgressDialog();

        void dismissProgressDialog();
    }

    interface Presenter extends BasePresenter, Serializable {
        void loadDependencies();

        void deleteDependency(Dependency parcelable);

        @Override
        void onDestroy();

        void setNewSelection(int position);
        void removeSelection(int position);
        void deleteSelection();
        void clearSelection();
        boolean getPositionChecked(int position);
    }

}
