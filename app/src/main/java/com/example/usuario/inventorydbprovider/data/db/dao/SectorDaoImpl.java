package com.example.usuario.inventorydbprovider.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.inventorydbprovider.data.db.InventoryContract;
import com.example.usuario.inventorydbprovider.data.db.InventoryOpenHelper;
import com.example.usuario.inventorydbprovider.data.db.SectorDao;
import com.example.usuario.inventorydbprovider.data.db.model.Sector;

import java.util.ArrayList;

/**
 * Created by usuario on 25/01/18.
 */
public class SectorDaoImpl implements SectorDao {

    public ArrayList<Sector> loadAll() {
        ArrayList<Sector> sectors = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(
                InventoryContract.SectorEntry.TABLE_NAME,
                InventoryContract.SectorEntry.ALL_COLUMNS,
                null, null, null, null,
                InventoryContract.SectorEntry.DEFAULT_SORT, null
        );
        if (cursor.moveToFirst()) {
            do {
                sectors.add(
                        new Sector(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                false,
                                false
                        )
                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        InventoryOpenHelper.getInstance().closeDatabase();
        return sectors;
    }

    public long add(Sector sector) {
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        //nullColumnHack crea filas sólo con id para evitar errores
        //por clave ajena, pero no lo vamos a usar
        long id = sqLiteDatabase.insert(
                InventoryContract.SectorEntry.TABLE_NAME,
                null,
                createContent(sector)
        );
        InventoryOpenHelper.getInstance().closeDatabase();
        return id;
    }

    @Override
    public int delete(Sector sector) {
        return 0;
    }

    @Override
    public boolean exists(Sector sector) {
        return false;
    }

    public int update(Sector sector) {
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        String[] whereArgs = new String[]{"" + sector.getID()};
        int updatedRows = sqLiteDatabase.update(
                InventoryContract.SectorEntry.TABLE_NAME,
                createContent(sector),
                null,
                null
        );
        InventoryOpenHelper.getInstance().closeDatabase();
        return updatedRows;

    }

    public ContentValues createContent(Sector sector) {
        //ContentValues funciona como un mapa
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.SectorEntry.COLUMN_DEPENDENCY_ID, sector.getDependencyId());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_NAME, sector.getName());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_SHORTNAME, sector.getShortname());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_DESCRIPTION, sector.getDescription());
        contentValues.put(InventoryContract.SectorEntry.COLUMN_IMAGENAME, sector.getImageName());
        return contentValues;
    }

}
