package com.example.usuario.inventorydbprovider.data.db.repository;

import com.example.usuario.inventorydbprovider.data.db.dao.SectorDao;
import com.example.usuario.inventorydbprovider.data.db.model.Sector;
import com.example.usuario.inventorydbprovider.ui.sector.SectorRepositoryCallback;

import java.util.ArrayList;

/**
 * Almacena las distintas secciones donde guardar elementos del inventario
 *
 * @author Enrique Casielles Lapeira
 * @version 2.0
 * @see Sector
 */
public class SectorRepository {

    private static SectorRepository sectorRepository;

    static {
        sectorRepository = new SectorRepository();
    }

    private SectorDao sectorDao;

    private SectorRepository() {
        sectorDao = new SectorDao();
    }

    public static SectorRepository getInstance(){
        if(sectorRepository == null)
            sectorRepository = new SectorRepository();
        return sectorRepository;
    }

    public ArrayList<Sector> getSectors() {
        return sectorDao.loadAll();
    }

    public void addSector(Sector sector, SectorRepositoryCallback callback) {
        if (sectorDao.add(sector) != -1)
            callback.onSuccess();
    }

    public void updateSector(Sector sector, SectorRepositoryCallback callback) {
        if (sectorDao.update(sector) != 0)
            callback.onSuccess();
    }

}
