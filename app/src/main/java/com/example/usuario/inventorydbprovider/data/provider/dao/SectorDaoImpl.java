package com.example.usuario.inventorydbprovider.data.provider.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.usuario.inventorydbprovider.data.db.InventoryApplication;
import com.example.usuario.inventorydbprovider.data.db.SectorDao;
import com.example.usuario.inventorydbprovider.data.db.model.Sector;
import com.example.usuario.inventorydbprovider.data.provider.InventoryProviderContract;

import java.util.ArrayList;

/**
 * Created by usuario on 8/02/18.
 */

public class SectorDaoImpl implements SectorDao {

    @Override
    public ArrayList<Sector> loadAll() {
        ArrayList<Sector> sectors = new ArrayList<>();

        //1: Array projection (es el mismo que el contract, pero
        // luego se han dado cuenta de que al final hay demasiados y
        // no merece la pena mantenerlo como constante
        String[] projection = new String[] {
                InventoryProviderContract.Sector._ID,
                InventoryProviderContract.Sector.NAME,
                InventoryProviderContract.Sector.DEPENDENCYID,
                InventoryProviderContract.Sector.SHORTNAME,
                InventoryProviderContract.Sector.DESCRIPTION,
                InventoryProviderContract.Sector.IMAGENAME
        };

        //2: Consulta al provider con la Uri de Dependency
        // Content Provider está registrado en el sistema
        //IMPORTANTE: getContentResolver hace la petición y devuelve el provider
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Cursor cursor = resolver.query(InventoryProviderContract.Sector.CONTENT_URI,
                projection, null, null, null);

        //3: Lee los datos y los devuelve
        if (cursor != null && cursor.moveToFirst()) {
            do {
                sectors.add(new Sector(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        false,
                        false
                ));
            } while (cursor.moveToNext());
        }
        return sectors;
    }

    @Override
    public long add(Sector sector) {
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Uri uri = resolver.insert(InventoryProviderContract.Sector.CONTENT_URI, createContent(sector));
        if(uri == null)
            return -1;
        return Long.parseLong(uri.getLastPathSegment());
    }

    @Override
    public int update(Sector sector) {
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        String selection = InventoryProviderContract.Sector._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(sector.getID())};
        Uri uri = InventoryProviderContract.Sector.CONTENT_URI;
        return resolver.update(uri, createContent(sector), selection, selectionArgs);
    }

    @Override
    public int delete(Sector sector) {
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        String selection = InventoryProviderContract.Sector._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(sector.getID())};
        Uri uri = InventoryProviderContract.Sector.CONTENT_URI;
        return resolver.delete(uri, selection, selectionArgs);
    }

    @Override
    public boolean exists(Sector sector) {
        Sector tempSector = null;

        String[] projection = new String[] {
                InventoryProviderContract.Sector._ID,
                InventoryProviderContract.Sector.NAME,
                InventoryProviderContract.Sector.DEPENDENCYID,
                InventoryProviderContract.Sector.SHORTNAME,
                InventoryProviderContract.Sector.DESCRIPTION,
                InventoryProviderContract.Sector.IMAGENAME
        };
        String selection = InventoryProviderContract.Sector.NAME + " = ? ";
        String[] selectionArgs = new String[]{sector.getName()};

        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Cursor cursor = resolver.query(InventoryProviderContract.Sector.CONTENT_URI,
                projection, selection, selectionArgs, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                tempSector = new Sector(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        false,
                        false
                );
            } while (cursor.moveToNext());
        }
        return tempSector != null;
    }

    @Override
    public Sector search(int id) {
        Sector tempSector = null;

        String[] projection = new String[] {
                InventoryProviderContract.Sector._ID,
                InventoryProviderContract.Sector.NAME,
                InventoryProviderContract.Sector.DEPENDENCYID,
                InventoryProviderContract.Sector.SHORTNAME,
                InventoryProviderContract.Sector.DESCRIPTION,
                InventoryProviderContract.Sector.IMAGENAME
        };

        String selection = InventoryProviderContract.Sector.CONTENT_PATH + "." +
                InventoryProviderContract.Sector._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(id)};

        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Cursor cursor = resolver.query(InventoryProviderContract.Sector.CONTENT_URI,
                projection, selection, selectionArgs, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                tempSector = new Sector(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        false,
                        false
                );
            } while (cursor.moveToNext());
        }
        return tempSector;
    }

    @Override
    public ContentValues createContent(Sector sector) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryProviderContract.Sector.NAME, sector.getName());
        contentValues.put(InventoryProviderContract.Sector.DEPENDENCYID, sector.getDependencyId());
        contentValues.put(InventoryProviderContract.Sector.SHORTNAME, sector.getShortname());
        contentValues.put(InventoryProviderContract.Sector.DESCRIPTION, sector.getDescription());
        contentValues.put(InventoryProviderContract.Sector.IMAGENAME, sector.getImageName());
        return contentValues;
    }

}
