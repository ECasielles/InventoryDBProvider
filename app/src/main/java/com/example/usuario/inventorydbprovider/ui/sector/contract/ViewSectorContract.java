package com.example.usuario.inventorydbprovider.ui.sector.contract;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;

/**
 * Created by icenri on 2/1/18.
 */

public interface ViewSectorContract {

    interface View {
        void setPresenter(ViewSectorContract.Presenter presenter);
        void onSectorsUpdated();
        void onSectorUpdateError(Throwable throwable);
    }

    interface Presenter {
        void addSector(Sector sector);

        void updateSector(Sector sector);
    }

}
