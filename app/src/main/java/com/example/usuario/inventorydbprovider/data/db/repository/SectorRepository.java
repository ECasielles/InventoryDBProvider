package com.example.usuario.inventorydbprovider.data.db.repository;

import com.example.usuario.inventorydbprovider.data.db.SectorDao;
import com.example.usuario.inventorydbprovider.data.db.model.Sector;
import com.example.usuario.inventorydbprovider.data.provider.dao.SectorDaoImpl;
import com.example.usuario.inventorydbprovider.ui.sector.interactor.SectorCallback;

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
        sectorDao = new SectorDaoImpl();
    }

    public static SectorRepository getInstance(){
        if(sectorRepository == null)
            sectorRepository = new SectorRepository();
        return sectorRepository;
    }

    public ArrayList<Sector> getSectors() {
        return sectorDao.loadAll();
    }

    public void addSector(Sector sector, SectorCallback callback) {
        if (sectorDao.add(sector) != -1)
            callback.onSuccess();
        else
            callback.onError(new Throwable());
    }

    public void updateSector(Sector sector, SectorCallback callback) {
        if (sectorDao.update(sector) != -1)
            callback.onSuccess();
        else
            callback.onError(new Throwable());
    }

    public void deleteSector(Sector sector, SectorCallback callback) {
        if (sectorDao.delete(sector) > 0)
            callback.onSuccess();
        else
            callback.onError(new Throwable("No se pudo eliminar. Pruebe a eliminar los productos de ese sector."));
    }

    public Sector search(int id) {
        return sectorDao.search(id);
    }

}
