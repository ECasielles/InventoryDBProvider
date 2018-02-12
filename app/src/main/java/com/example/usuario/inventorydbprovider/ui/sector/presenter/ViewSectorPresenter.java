package com.example.usuario.inventorydbprovider.ui.sector.presenter;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;
import com.example.usuario.inventorydbprovider.ui.sector.contract.ViewSectorContract;
import com.example.usuario.inventorydbprovider.ui.sector.interactor.ViewSectorInteractor;
import com.example.usuario.inventorydbprovider.ui.sector.interactor.ViewSectorInteractorImpl;

public class ViewSectorPresenter implements ViewSectorContract.Presenter, ViewSectorInteractor.OnSectorsUpdatedListener {

    private ViewSectorContract.View view;
    private ViewSectorInteractor interactor;

    public ViewSectorPresenter(ViewSectorContract.View view) {
        this.view = view;
        this.interactor = new ViewSectorInteractorImpl(this);
    }

    @Override
    public void addSector(Sector sector) {
        interactor.addSector(sector);
    }

    @Override
    public void updateSector(Sector sector) {
        interactor.updateSectors(sector);
    }

    @Override
    public void onSectorsUpdated() {
        view.onSectorsUpdated();
    }

    @Override
    public void onSectorUpdateError(Throwable throwable) {
        view.onSectorUpdateError(throwable);
    }

}
