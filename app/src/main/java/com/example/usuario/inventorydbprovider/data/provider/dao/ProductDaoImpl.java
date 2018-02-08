package com.example.usuario.inventorydbprovider.data.provider.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.example.usuario.inventorydbprovider.data.db.InventoryApplication;
import com.example.usuario.inventorydbprovider.data.db.ProductDao;
import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.data.provider.InventoryProviderContract;

import java.util.ArrayList;

/**
 * Created by usuario on 8/02/18.
 */

public class ProductDaoImpl implements ProductDao {

    @Override
    public ArrayList<ProductView> loadAll() {
        ArrayList<ProductView> products = new ArrayList<>();

        //1: Array projection
        String[] projection = new String[] {
                InventoryProviderContract.ProductViewEntry._ID,
                InventoryProviderContract.ProductViewEntry.SERIAL,
                InventoryProviderContract.ProductViewEntry.MODELCODE,
                InventoryProviderContract.ProductViewEntry.SHORTNAME,
                InventoryProviderContract.ProductViewEntry.DESCRIPTION,
                InventoryProviderContract.ProductViewEntry.CATEGORYID,
                InventoryProviderContract.ProductViewEntry.CATEGORYNAME,
                InventoryProviderContract.ProductViewEntry.PRODUCTCLASSID,
                InventoryProviderContract.ProductViewEntry.PRODUCTCLASSDESCRIPTION,
                InventoryProviderContract.ProductViewEntry.SECTORID,
                InventoryProviderContract.ProductViewEntry.SECTORNAME,
                InventoryProviderContract.ProductViewEntry.QUANTITY,
                InventoryProviderContract.ProductViewEntry.VALUE,
                InventoryProviderContract.ProductViewEntry.VENDOR,
                InventoryProviderContract.ProductViewEntry.BITMAP,
                InventoryProviderContract.ProductViewEntry.IMAGENAME,
                InventoryProviderContract.ProductViewEntry.URL,
                InventoryProviderContract.ProductViewEntry.DATEPURCHASE,
                InventoryProviderContract.ProductViewEntry.NOTES
        };

        //2: Consulta al provider con la Uri d
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Cursor cursor = resolver.query(InventoryProviderContract.ProductViewEntry.CONTENT_URI,
                projection, null, null, null);

        //3: Lee los datos y los devuelve
        if (cursor != null && cursor.moveToFirst()) {
            do {
                products.add(new ProductView(
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
                        cursor.getString(18))
                );
            } while (cursor.moveToNext());
        }
        return products;
    }

    @Override
    public long add(Product product) {
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Uri uri = resolver.insert(InventoryProviderContract.ProductViewEntry.CONTENT_URI, createContent(product));
        if(uri == null)
            return -1;
        return Long.parseLong(uri.getLastPathSegment());
    }

    @Override
    public int delete(Product product) {
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        String selection = InventoryProviderContract.ProductViewEntry._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(product.get_ID())};
        Uri uri = InventoryProviderContract.ProductViewEntry.CONTENT_URI;
        return resolver.delete(uri, selection, selectionArgs);
    }

    @Override
    public boolean exists(Product product) {
        Product tempProduct = null;

        String[] projection = new String[] {
                InventoryProviderContract.ProductViewEntry._ID,
                InventoryProviderContract.ProductViewEntry.SERIAL,
                InventoryProviderContract.ProductViewEntry.MODELCODE,
                InventoryProviderContract.ProductViewEntry.SHORTNAME,
                InventoryProviderContract.ProductViewEntry.DESCRIPTION,
                InventoryProviderContract.ProductViewEntry.CATEGORYID,
                InventoryProviderContract.ProductViewEntry.CATEGORYNAME,
                InventoryProviderContract.ProductViewEntry.PRODUCTCLASSID,
                InventoryProviderContract.ProductViewEntry.PRODUCTCLASSDESCRIPTION,
                InventoryProviderContract.ProductViewEntry.SECTORID,
                InventoryProviderContract.ProductViewEntry.SECTORNAME,
                InventoryProviderContract.ProductViewEntry.QUANTITY,
                InventoryProviderContract.ProductViewEntry.VALUE,
                InventoryProviderContract.ProductViewEntry.VENDOR,
                InventoryProviderContract.ProductViewEntry.BITMAP,
                InventoryProviderContract.ProductViewEntry.IMAGENAME,
                InventoryProviderContract.ProductViewEntry.URL,
                InventoryProviderContract.ProductViewEntry.DATEPURCHASE,
                InventoryProviderContract.ProductViewEntry.NOTES
        };

        String selection = InventoryProviderContract.ProductViewEntry.SERIAL + " = ? ";
        String[] selectionArgs = new String[]{product.getSerial()};

        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Cursor cursor = resolver.query(InventoryProviderContract.ProductViewEntry.CONTENT_URI,
                projection, selection, selectionArgs, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                tempProduct = new ProductView(
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
                        cursor.getString(18));
            } while (cursor.moveToNext());
        }
        return tempProduct != null;
    }

    @Override
    public int update(Product product) {
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        String selection = InventoryProviderContract.ProductViewEntry._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(product.get_ID())};
        Uri uri = InventoryProviderContract.ProductViewEntry.CONTENT_URI;
        return resolver.update(uri, createContent(product), selection, selectionArgs);
    }

    @Override
    public ProductView search(int id) {
        ProductView tempProductView = null;

        String[] projection = new String[] {
                InventoryProviderContract.ProductViewEntry._ID,
                InventoryProviderContract.ProductViewEntry.SERIAL,
                InventoryProviderContract.ProductViewEntry.MODELCODE,
                InventoryProviderContract.ProductViewEntry.SHORTNAME,
                InventoryProviderContract.ProductViewEntry.DESCRIPTION,
                InventoryProviderContract.ProductViewEntry.CATEGORYID,
                InventoryProviderContract.ProductViewEntry.CATEGORYNAME,
                InventoryProviderContract.ProductViewEntry.PRODUCTCLASSID,
                InventoryProviderContract.ProductViewEntry.PRODUCTCLASSDESCRIPTION,
                InventoryProviderContract.ProductViewEntry.SECTORID,
                InventoryProviderContract.ProductViewEntry.SECTORNAME,
                InventoryProviderContract.ProductViewEntry.QUANTITY,
                InventoryProviderContract.ProductViewEntry.VALUE,
                InventoryProviderContract.ProductViewEntry.VENDOR,
                InventoryProviderContract.ProductViewEntry.BITMAP,
                InventoryProviderContract.ProductViewEntry.IMAGENAME,
                InventoryProviderContract.ProductViewEntry.URL,
                InventoryProviderContract.ProductViewEntry.DATEPURCHASE,
                InventoryProviderContract.ProductViewEntry.NOTES
        };

        String selection = InventoryProviderContract.ProductViewEntry._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(id)};

        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Cursor cursor = resolver.query(InventoryProviderContract.ProductViewEntry.CONTENT_URI,
                projection, selection, selectionArgs, null);

        if(cursor != null && cursor.moveToFirst()) {
            do {
                tempProductView = new ProductView(
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
                        cursor.getString(18));
            } while (cursor.moveToNext());
        }
        return tempProductView;
    }

    @Override
    public ContentValues createContent(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryProviderContract.ProductViewEntry._ID, product.get_ID());
        contentValues.put(InventoryProviderContract.ProductViewEntry.SERIAL, product.getSerial());
        contentValues.put(InventoryProviderContract.ProductViewEntry.MODELCODE, product.getModelCode());
        contentValues.put(InventoryProviderContract.ProductViewEntry.SHORTNAME, product.getShortname());
        contentValues.put(InventoryProviderContract.ProductViewEntry.DESCRIPTION, product.getDescription());
        contentValues.put(InventoryProviderContract.ProductViewEntry.CATEGORYID, product.getCategory());
        contentValues.put(InventoryProviderContract.ProductViewEntry.PRODUCTCLASSID, product.getProductClass());
        contentValues.put(InventoryProviderContract.ProductViewEntry.SECTORID, product.getSectorID());
        contentValues.put(InventoryProviderContract.ProductViewEntry.QUANTITY, product.getQuantity());
        contentValues.put(InventoryProviderContract.ProductViewEntry.VALUE, product.getValue());
        contentValues.put(InventoryProviderContract.ProductViewEntry.VENDOR, product.getVendor());
        contentValues.put(InventoryProviderContract.ProductViewEntry.BITMAP, product.getBitmap());
        contentValues.put(InventoryProviderContract.ProductViewEntry.IMAGENAME, product.getImageName());
        contentValues.put(InventoryProviderContract.ProductViewEntry.URL, product.getUrl());
        contentValues.put(InventoryProviderContract.ProductViewEntry.DATEPURCHASE, product.getDatePurchase());
        contentValues.put(InventoryProviderContract.ProductViewEntry.NOTES, product.getNotes());
        return contentValues;
    }

}
