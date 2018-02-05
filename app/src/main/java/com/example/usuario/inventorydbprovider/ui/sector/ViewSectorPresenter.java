package com.example.usuario.inventorydbprovider.ui.sector;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;

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

}
