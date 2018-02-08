package com.example.usuario.inventorydbprovider.data.provider.dao;

import android.content.ContentValues;

import com.example.usuario.inventorydbprovider.data.db.SectorDao;
import com.example.usuario.inventorydbprovider.data.db.model.Sector;

import java.util.ArrayList;

/**
 * Created by usuario on 8/02/18.
 */

public class SectorDaoImpl implements SectorDao {
    @Override
    public ArrayList<Sector> loadAll() {
        return null;
    }

    @Override
    public long add(Sector sector) {
        return 0;
    }

    @Override
    public int delete(Sector sector) {
        return 0;
    }

    @Override
    public boolean exists(Sector sector) {
        return false;
    }

    @Override
    public int update(Sector sector) {
        return 0;
    }

    @Override
    public ContentValues createContent(Sector sector) {
        return null;
    }
}
