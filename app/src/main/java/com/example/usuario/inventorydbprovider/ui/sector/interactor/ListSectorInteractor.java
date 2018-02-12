package com.example.usuario.inventorydbprovider.ui.sector.interactor;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;

import java.util.ArrayList;

/**
 * Created by icenri on 2/1/18.
 */

public interface ListSectorInteractor extends SectorCallback {

    void loadSectors();
    void deleteSector(Sector sector);

    interface OnSectorsLoadedListener {
        void onSectorsLoaded(ArrayList<Sector> sectors);

        void onSectorDeleteError(Throwable throwable);
        void onSectorDeleted();
    }

}
