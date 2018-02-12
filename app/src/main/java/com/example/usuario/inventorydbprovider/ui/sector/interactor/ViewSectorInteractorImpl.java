package com.example.usuario.inventorydbprovider.ui.sector.interactor;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;
import com.example.usuario.inventorydbprovider.data.db.repository.SectorRepository;

/**
 * Created by icenri on 2/1/18.
 */

public class ViewSectorInteractorImpl implements ViewSectorInteractor {
    private OnSectorsUpdatedListener listener;

    public ViewSectorInteractorImpl(ViewSectorInteractor.OnSectorsUpdatedListener listener) {
        this.listener = listener;
    }

    @Override
    public void updateSectors(Sector sector) {
        SectorRepository.getInstance().updateSector(sector, this);
    }

    @Override
    public void addSector(Sector sector) {
        SectorRepository.getInstance().addSector(sector, this);
    }

    @Override
    public void onError(Throwable throwable) {
        listener.onSectorUpdateError(throwable);
    }

    @Override
    public void onSuccess() {
        listener.onSectorsUpdated();
    }
}
