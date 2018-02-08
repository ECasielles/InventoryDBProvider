package com.example.usuario.inventorydbprovider.ui.sector.presenter;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;
import com.example.usuario.inventorydbprovider.ui.sector.contract.ListSectorContract;
import com.example.usuario.inventorydbprovider.ui.sector.interactor.ListSectorInteractor;
import com.example.usuario.inventorydbprovider.ui.sector.interactor.ListSectorInteractorImpl;

import java.util.ArrayList;


public class ListSectorPresenter implements ListSectorContract.Presenter, ListSectorInteractor.OnSectorsLoadedListener {
    ListSectorContract.View view;
    ListSectorInteractor interactor;

    public ListSectorPresenter(ListSectorContract.View view) {
        this.view = view;
        this.interactor = new ListSectorInteractorImpl(this);
    }

    @Override
    public void loadSectors() {
        interactor.loadSectors();
    }

    @Override
    public void onSectorsLoaded(ArrayList<Sector> sectors) {
        view.showSectors(sectors);
    }

}
