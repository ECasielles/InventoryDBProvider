package com.example.usuario.inventorydbprovider.ui.sector.interactor;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;

/**
 * Created by icenri on 2/1/18.
 */

public interface ViewSectorInteractor extends SectorCallback {

    void updateSectors(Sector sector);

    void addSector(Sector sector);

    interface OnSectorsUpdatedListener {
        void onSectorsUpdated();
    }

}
