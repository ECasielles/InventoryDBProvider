package com.example.usuario.inventorydbprovider.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.inventorydbprovider.data.db.InventoryContract;
import com.example.usuario.inventorydbprovider.data.db.InventoryOpenHelper;
import com.example.usuario.inventorydbprovider.data.db.model.Product;

import java.util.ArrayList;

/**
 * Created by icenri on 2/1/18.
 */

public class ProductDao {

    public ArrayList<Product> loadAll() {
        ArrayList<Product> products = new ArrayList<>();
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
                products.add(new Product(
                                cursor.getInt(0),
                        cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getInt(6),
                                cursor.getInt(7),
                                cursor.getInt(8),
                                cursor.getInt(9),
                                cursor.getFloat(10),
                                cursor.getString(11),
                                cursor.getInt(12),
                                cursor.getString(13),
                                cursor.getString(14),
                                cursor.getString(15),
                                cursor.getString(16)
                        )
                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        InventoryOpenHelper.getInstance().closeDatabase();
        return products;
    }

    /**
     * Método que añade una dependencia en la base de datos
     *
     * @param product Producto que se añade
     * @return Devuelve el id del nuevo elemento que se ha insertado
     */
/*
    public long add(Product product) {
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        //nullColumnHack crea filas sólo con id para evitar errores
        //por clave ajena, pero no lo vamos a usar
        long id = sqLiteDatabase.insert(
                InventoryContract.ProductEntry.TABLE_NAME,
                null,
                createContent(product)
        );
        InventoryOpenHelper.getInstance().closeDatabase();
        return id;
    }



    public int delete(Product product) {
        int result;
        try {
            SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
            String[] whereArgs = new String[]{product.get_ID() + ""};
            result = sqLiteDatabase.delete(
                    InventoryContract.ProductEntry.TABLE_NAME,
                    InventoryContract.ProductEntry.WHERE_ID,
                    whereArgs
            );
            return result;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int update(Product product) {
        SQLiteDatabase sqLiteDatabase = InventoryOpenHelper.getInstance().openDatabase();
        String[] whereArgs = new String[]{"" + product.get_ID()};
        int updatedRows = sqLiteDatabase.update(
                InventoryContract.ProductEntry.TABLE_NAME,
                createContent(product),
                null,
                null
        );
        InventoryOpenHelper.getInstance().closeDatabase();
        return updatedRows;
    }
*/

    public ContentValues createContent(Product product) {
        //ContentValues funciona como un mapa
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.ProductEntry.COLUMN_SERIAL, product.getDatePurchase());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_MODEL_CODE, product.getModelCode());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_SHORTNAME, product.getShortname());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_DESCRIPTION, product.getDescription());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_CATEGORY_ID, product.getCategory());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_CLASS_ID, product.getProductClass());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_SECTOR_ID, product.getSectorID());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_QUANTITY, product.getQuantity());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_VALUE, product.getValue());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_VENDOR, product.getVendor());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_BITMAP, product.getBitmap());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_IMAGENAME, product.getImageName());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_URL, product.getUrl());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_DATE_PURCHASE, product.getDatePurchase());
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NOTES, product.getNotes());
        return contentValues;
    }
}