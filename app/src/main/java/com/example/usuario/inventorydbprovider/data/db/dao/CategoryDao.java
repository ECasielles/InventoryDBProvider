package com.example.usuario.inventorydbprovider.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.inventorydbprovider.data.db.InventoryContract;
import com.example.usuario.inventorydbprovider.data.db.InventoryOpenHelper;
import com.example.usuario.inventorydbprovider.data.db.model.Category;

import java.util.ArrayList;

/**
 * Created by usuario on 1/02/18.
 */

public class CategoryDao {

    public ArrayList<Category> loadAll() {
        ArrayList<Category> categories = new ArrayList<>();
        SQLiteDatabase db = InventoryOpenHelper.getInstance().openDatabase();

        //rawQuery interpreta el comando usando '?' en la consulta.
        //A medio camino entre SQL y SQLite
        Cursor cursor = db.query(
                InventoryContract.CategoryEntry.TABLE_NAME,
                InventoryContract.CategoryEntry.ALL_COLUMNS,
                null, null, null, null,
                InventoryContract.CategoryEntry.DEFAULT_SORT, null
        );
        if (cursor.moveToFirst()) {
            do {
                categories.add(new Category(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3)
                        )
                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        InventoryOpenHelper.getInstance().closeDatabase();
        return categories;
    }

}
