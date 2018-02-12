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
                InventoryProviderContract.Product._ID,
                InventoryProviderContract.Product.SERIAL,
                InventoryProviderContract.Product.MODELCODE,
                InventoryProviderContract.Product.SHORTNAME,
                InventoryProviderContract.Product.DESCRIPTION,
                InventoryProviderContract.Product.CATEGORYID,
                InventoryProviderContract.Product.CATEGORYNAME,
                InventoryProviderContract.Product.PRODUCTCLASSID,
                InventoryProviderContract.Product.PRODUCTCLASSDESCRIPTION,
                InventoryProviderContract.Product.SECTORID,
                InventoryProviderContract.Product.SECTORNAME,
                InventoryProviderContract.Product.QUANTITY,
                InventoryProviderContract.Product.VALUE,
                InventoryProviderContract.Product.VENDOR,
                InventoryProviderContract.Product.BITMAP,
                InventoryProviderContract.Product.IMAGENAME,
                InventoryProviderContract.Product.URL,
                InventoryProviderContract.Product.DATEPURCHASE,
                InventoryProviderContract.Product.NOTES
        };

        //2: Consulta al provider con la Uri d
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Cursor cursor = resolver.query(InventoryProviderContract.Product.CONTENT_URI_VIEW,
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
        Uri uri = resolver.insert(InventoryProviderContract.Product.CONTENT_URI, createContent(product));
        if(uri == null)
            return -1;
        return Long.parseLong(uri.getLastPathSegment());
    }

    @Override
    public int update(Product product) {
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        String selection = InventoryProviderContract.Product._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(product.get_ID())};
        Uri uri = InventoryProviderContract.Product.CONTENT_URI;
        return resolver.update(uri, createContent(product), selection, selectionArgs);
    }

    @Override
    public int delete(Product product) {
        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        String selection = InventoryProviderContract.Product._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(product.get_ID())};
        Uri uri = InventoryProviderContract.Product.CONTENT_URI;
        return resolver.delete(uri, selection, selectionArgs);
    }

    @Override
    public ProductView search(int id) {
        ProductView tempProductView = null;

        String[] projection = new String[] {
                InventoryProviderContract.Product._ID,
                InventoryProviderContract.Product.SERIAL,
                InventoryProviderContract.Product.MODELCODE,
                InventoryProviderContract.Product.SHORTNAME,
                InventoryProviderContract.Product.DESCRIPTION,
                InventoryProviderContract.Product.CATEGORYID,
                InventoryProviderContract.Product.CATEGORYNAME,
                InventoryProviderContract.Product.PRODUCTCLASSID,
                InventoryProviderContract.Product.PRODUCTCLASSDESCRIPTION,
                InventoryProviderContract.Product.SECTORID,
                InventoryProviderContract.Product.SECTORNAME,
                InventoryProviderContract.Product.QUANTITY,
                InventoryProviderContract.Product.VALUE,
                InventoryProviderContract.Product.VENDOR,
                InventoryProviderContract.Product.BITMAP,
                InventoryProviderContract.Product.IMAGENAME,
                InventoryProviderContract.Product.URL,
                InventoryProviderContract.Product.DATEPURCHASE,
                InventoryProviderContract.Product.NOTES
        };

        String selection = InventoryProviderContract.Product.CONTENT_PATH + "." +
                InventoryProviderContract.Product._ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(id)};

        ContentResolver resolver = InventoryApplication.getContext().getContentResolver();
        Cursor cursor = resolver.query(InventoryProviderContract.Product.CONTENT_URI_VIEW,
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
        contentValues.put(InventoryProviderContract.Product.SERIAL, product.getSerial());
        contentValues.put(InventoryProviderContract.Product.MODELCODE, product.getModelCode());
        contentValues.put(InventoryProviderContract.Product.SHORTNAME, product.getShortname());
        contentValues.put(InventoryProviderContract.Product.DESCRIPTION, product.getDescription());
        contentValues.put(InventoryProviderContract.Product.CATEGORYID, product.getCategory());
        contentValues.put(InventoryProviderContract.Product.PRODUCTCLASSID, product.getProductClass());
        contentValues.put(InventoryProviderContract.Product.SECTORID, product.getSectorID());
        contentValues.put(InventoryProviderContract.Product.QUANTITY, product.getQuantity());
        contentValues.put(InventoryProviderContract.Product.VALUE, product.getValue());
        contentValues.put(InventoryProviderContract.Product.VENDOR, product.getVendor());
        contentValues.put(InventoryProviderContract.Product.BITMAP, product.getBitmap());
        contentValues.put(InventoryProviderContract.Product.IMAGENAME, product.getImageName());
        contentValues.put(InventoryProviderContract.Product.URL, product.getUrl());
        contentValues.put(InventoryProviderContract.Product.DATEPURCHASE, product.getDatePurchase());
        contentValues.put(InventoryProviderContract.Product.NOTES, product.getNotes());
        return contentValues;
    }

}
