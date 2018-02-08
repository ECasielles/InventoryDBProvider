package com.example.usuario.inventorydbprovider.ui.sector.contract;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;

import java.util.ArrayList;

/**
 * Created by icenri on 2/1/18.
 */

public interface ListSectorContract {

    interface View {
        void setPresenter(ListSectorContract.Presenter presenter);
        void showSectors(ArrayList<Sector> sectors);
    }

    interface Presenter {
        void loadSectors();
    }

}
