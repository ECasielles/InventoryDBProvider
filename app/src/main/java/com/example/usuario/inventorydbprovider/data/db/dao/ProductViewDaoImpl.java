package com.example.usuario.inventorydbprovider.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.usuario.inventorydbprovider.data.db.*;
import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;

import java.util.ArrayList;

/**
 * Created by usuario on 2/02/18.
 */

public class ProductViewDaoImpl implements ProductDao {

    public ArrayList<ProductView> loadAll() {
        ArrayList<ProductView> productViews = new ArrayList<>();
        SQLiteDatabase db = InventoryOpenHelper.getInstance().openDatabase();

        //rawQuery interpreta el comando usando '?' en la consulta.
        //A medio camino entre SQL y SQLite
        Cursor cursor = db.query(
                InventoryContract.ProductEntry.TABLE_NAME,
                InventoryContract.ProductEntry.ALL_COLUMNS,
                null, null, null, null,
                InventoryContract.ProductEntry.DEFAULT_SORT, null
        );
        if (cursor.moveToFirst()) {
            do {
                productViews.add(new ProductView(new Product(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getInt(5),
                                cursor.getInt(6),
                                cursor.getInt(7),
                                cursor.getInt(8),
                                cursor.getFloat(9),
                                cursor.getString(10),
                                cursor.getInt(11),
                                cursor.getString(12),
                                cursor.getString(13),
                                cursor.getString(14),
                                cursor.getString(15)
                        ))
                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        InventoryOpenHelper.getInstance().closeDatabase();
        return productViews;
    }

    @Override
    public long add(Product product) {
        return 0;
    }

    @Override
    public int delete(Product product) {
        return 0;
    }

    @Override
    public boolean exists(Product product) {
        return false;
    }

    @Override
    public int update(Product product) {
        return 0;
    }

    @Override
    public ContentValues createContent(Product product) {
        return null;
    }

    public ProductView search(int id) {
        SQLiteDatabase database = InventoryOpenHelper.getInstance().openDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String selection = InventoryContract.ProductViewEntry.TABLE_NAME + "." + BaseColumns._ID + " = ?";
        String[] selectionArgs = new String[] {String.valueOf(id + 1)};
        ProductView productView = null;

        queryBuilder.setTables(InventoryContract.ProductViewEntry.PRODUCT_INNER);
        queryBuilder.setProjectionMap(InventoryContract.ProductViewEntry.sProductViewProjectionMap);

        Log.i("ProductViewDaoImpl", queryBuilder.buildQuery(
                InventoryContract.ProductViewEntry.ALL_COLUMNS,
                null, null, null, null, null));

        Cursor cursor = queryBuilder.query(database, InventoryContract.ProductViewEntry.ALL_COLUMNS,
                selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                productView = new ProductView(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getInt(9),
                        cursor.getString(10),
                        cursor.getInt(11),
                        cursor.getFloat(12),
                        cursor.getString(13),
                        cursor.getInt(14),
                        cursor.getString(15),
                        cursor.getString(16),
                        cursor.getString(17),
                        cursor.getString(18)
                );
            } while (cursor.moveToNext());
        }

        InventoryOpenHelper.getInstance().closeDatabase();
        return productView;
    }


}
