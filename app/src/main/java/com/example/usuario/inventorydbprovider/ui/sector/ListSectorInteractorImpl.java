package com.example.usuario.inventorydbprovider.ui.sector;

import com.example.usuario.inventorydbprovider.data.db.repository.SectorRepository;

/**
 * Created by icenri on 2/1/18.
 */

class ListSectorInteractorImpl implements ListSectorInteractor {

    private OnSectorsLoadedListener listener;

    public ListSectorInteractorImpl(ListSectorInteractor.OnSectorsLoadedListener listener) {
        this.listener = listener;
    }

    @Override
    public void loadSectors() {
        listener.onSectorsLoaded(SectorRepository.getInstance().getSectors());
    }

}
