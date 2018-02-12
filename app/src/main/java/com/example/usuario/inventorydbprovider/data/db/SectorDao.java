package com.example.usuario.inventorydbprovider.data.db;

import android.content.ContentValues;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;

import java.util.ArrayList;

/**
 * Created by usuario on 8/02/18.
 */

public interface SectorDao {
    ArrayList<Sector> loadAll();
    long add(Sector sector);
    int delete(Sector sector);
    int update(Sector sector);
    ContentValues createContent(Sector sector);
    Sector search(int id);
}
