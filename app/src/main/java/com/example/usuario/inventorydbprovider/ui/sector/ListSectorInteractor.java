package com.example.usuario.inventorydbprovider.ui.sector;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;

import java.util.ArrayList;

/**
 * Created by icenri on 2/1/18.
 */

public interface ListSectorInteractor {

    void loadSectors();

    interface OnSectorsLoadedListener {
        void onSectorsLoaded(ArrayList<Sector> sectors);
    }

}
