package com.example.usuario.inventorydbprovider.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.inventorydbprovider.data.db.InventoryContract;
import com.example.usuario.inventorydbprovider.data.db.InventoryOpenHelper;
import com.example.usuario.inventorydbprovider.data.db.model.ProductClass;

import java.util.ArrayList;

/**
 * Created by usuario on 1/02/18.
 */

public class ProductClassDao {

    public ArrayList<ProductClass> loadAll() {
        ArrayList<ProductClass> productClasses = new ArrayList<>();
        SQLiteDatabase db = InventoryOpenHelper.getInstance().openDatabase();

        //rawQuery interpreta el comando usando '?' en la consulta.
        //A medio camino entre SQL y SQLite
        Cursor cursor = db.query(
                InventoryContract.ProductClassEntry.TABLE_NAME,
                InventoryContract.ProductClassEntry.ALL_COLUMNS,
                null, null, null, null,
                InventoryContract.ProductClassEntry.DEFAULT_SORT, null
        );
        if (cursor.moveToFirst()) {
            do {
                productClasses.add(new ProductClass(
                                cursor.getInt(0),
                                cursor.getString(1)
                        )
                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        InventoryOpenHelper.getInstance().closeDatabase();
        return productClasses;
    }

}
