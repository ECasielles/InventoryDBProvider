package com.example.usuario.inventorydbprovider.data.provider.dao;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.usuario.inventorydbprovider.data.db.DependencyDao;
import com.example.usuario.inventorydbprovider.data.db.InventoryApplication;
import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.data.provider.InventoryProviderContract;

import java.util.ArrayList;

/**
 * Created by usuario on 7/02/18.
 */

public class DependencyDaoImpl implements DependencyDao {

    @Override
    public ArrayList<Dependency> loadAll() {
        ArrayList<Dependency> dependencies = new ArrayList<>();

        //1: Array projection (es el mismo que el contract, pero
        // luego se han dado cuenta de que al final hay demasiados y
        // no merece la pena mantenerlo como constante
        String[] projection = new String[] {
                InventoryProviderContract.Dependency._ID,
                InventoryProviderContract.Dependency.NAME,
                InventoryProviderContract.Dependency.SHORTNAME,
                InventoryProviderContract.Dependency.DESCRIPTION,
                InventoryProviderContract.Dependency.IMAGENAME
        };

        //2: Consulta al provider con la Uri de Dependency
        // Content Provider está registrado en el sistema
        //IMPORTANTE: getContentResolver hace la petición y devuelve el provider
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Cursor cursor = resolver.query(InventoryProviderContract.Dependency.CONTENT_URI,
                projection, null, null, null);

        //3: Lee los datos y los devuelve
        if(cursor.moveToFirst()) {
            do {
                dependencies.add(new Dependency(
                        cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4))
                );
            } while (cursor.moveToNext());
        }
        return dependencies;
    }

    @Override
    public long add(Dependency dependency) {
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Uri uri = resolver.insert(InventoryProviderContract.Dependency.CONTENT_URI, createContent(dependency));
        if(uri == null)
            return -1;
        //getLastPathSegment corresponde al último elemento de la uri (id del elemento)
        return Long.parseLong(uri.getLastPathSegment());
    }

    @Override
    public int delete(Dependency dependency) {
        return 0;
    }

    @Override
    public boolean exists(Dependency dependency) {
        return false;
    }

    @Override
    public int update(Dependency dependency) {
        return 0;
    }

    @Override
    public ContentValues createContent(Dependency dependency) {
        //ContentValues funciona como un mapa
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryProviderContract.Dependency.NAME, dependency.getName());
        contentValues.put(InventoryProviderContract.Dependency.SHORTNAME, dependency.getShortname());
        contentValues.put(InventoryProviderContract.Dependency.DESCRIPTION, dependency.getDescription());
        contentValues.put(InventoryProviderContract.Dependency.IMAGENAME, dependency.getImageName());
        return contentValues;
    }

}
