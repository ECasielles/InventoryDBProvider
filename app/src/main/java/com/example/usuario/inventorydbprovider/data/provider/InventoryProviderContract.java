package com.example.usuario.inventorydbprovider.data.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.usuario.inventorydbprovider.data.db.InventoryContract;

import java.util.HashMap;

/**
 * Contrato de la BD con ContentProvider. Devuelve vistas.
 * No se puede instanciar.
 */
public final class InventoryProviderContract {

    //debemos crear un atributo projection siempre que haya un inner join.
    public static final String AUTHORITY = "com.example.usuario.inventorydbprovider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    private InventoryProviderContract(){}

    public static class Dependency implements BaseColumns {
        public static final String CONTENT_PATH = "dependency";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(
                InventoryProviderContract.AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "name";
        public static final String SHORTNAME = "shortname";
        public static final String DESCRIPTION = "description";
        public static final String IMAGENAME = "imageName";

        public static final String[] PROJECTION = new String[] {
                BaseColumns._ID, NAME, SHORTNAME, DESCRIPTION, IMAGENAME
        };
    }
    public static class Sector implements BaseColumns {
        public static final String CONTENT_PATH = "sector";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(
                InventoryProviderContract.AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "name";
        public static final String DEPENDENCYID = "dependencyId";
        public static final String SHORTNAME = "shortname";
        public static final String DESCRIPTION = "description";
        public static final String IMAGENAME = "imageName";

        public static final String[] PROJECTION = new String[] {
                BaseColumns._ID, DEPENDENCYID, NAME, SHORTNAME, DESCRIPTION, IMAGENAME
        };
    }
    public static class ProductViewEntry implements BaseColumns {
        public static final String CONTENT_PATH = "product";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(
                InventoryProviderContract.AUTHORITY_URI, CONTENT_PATH);
        public static final String SERIAL = "serial";
        public static final String MODELCODE = "modelCode";
        public static final String SHORTNAME = "shortname";
        public static final String DESCRIPTION = "description";
        public static final String CATEGORYID = "category";
        public static final String CATEGORYNAME = "categoryName";
        public static final String PRODUCTCLASSID = "productClass";
        public static final String PRODUCTCLASSDESCRIPTION = "productClassDescription";
        public static final String SECTORID = "sector";
        public static final String SECTORNAME = "sectorName";
        public static final String QUANTITY = "quantity";
        public static final String VALUE = "value";
        public static final String VENDOR = "vendor";
        public static final String BITMAP = "bitmap";
        public static final String IMAGENAME = "imageName";
        public static final String URL = "url";
        public static final String DATEPURCHASE = "datePurchase";
        public static final String NOTES = "notes";

        public static final String[] ALL_COLUMNS = new String[]{
                _ID, SERIAL, MODELCODE, SHORTNAME, DESCRIPTION,
                CATEGORYID, CATEGORYNAME, PRODUCTCLASSID,
                PRODUCTCLASSDESCRIPTION, SECTORID, SECTORNAME, QUANTITY,
                VALUE, VENDOR, BITMAP, IMAGENAME, URL, DATEPURCHASE, NOTES
        };

        public static HashMap<String, String> sProductViewProjectionMap;

        private static final String NAME = "";

        static {
            sProductViewProjectionMap = new HashMap<>();
            // Si se repite se recomienda poner la BD por si cambia algo
            // aunque parezca peor que sin provider
            sProductViewProjectionMap.put(BaseColumns._ID, InventoryContract.ProductViewEntry.TABLE_NAME + "." + BaseColumns._ID);
            sProductViewProjectionMap.put(SERIAL, InventoryContract.ProductViewEntry.COLUMN_SERIAL);
            sProductViewProjectionMap.put(MODELCODE, InventoryContract.ProductViewEntry.COLUMN_MODEL_CODE);
            sProductViewProjectionMap.put(DESCRIPTION, InventoryContract.ProductViewEntry.TABLE_NAME + "." + InventoryContract.ProductViewEntry.COLUMN_DESCRIPTION);
            sProductViewProjectionMap.put(SHORTNAME, InventoryContract.ProductViewEntry.TABLE_NAME + "." + InventoryContract.ProductViewEntry.COLUMN_SHORTNAME);
            sProductViewProjectionMap.put(CATEGORYID, InventoryContract.CategoryEntry.TABLE_NAME + "." + InventoryContract.CategoryEntry._ID);
            sProductViewProjectionMap.put(CATEGORYNAME, InventoryContract.CategoryEntry.TABLE_NAME + "." + InventoryContract.CategoryEntry.COLUMN_NAME);
            sProductViewProjectionMap.put(PRODUCTCLASSID, InventoryContract.ProductClassEntry.TABLE_NAME + "." + InventoryContract.ProductClassEntry._ID);
            sProductViewProjectionMap.put(PRODUCTCLASSDESCRIPTION, InventoryContract.ProductClassEntry.TABLE_NAME + "." + InventoryContract.ProductClassEntry.COLUMN_DESCRIPTION);
            sProductViewProjectionMap.put(SECTORID, InventoryContract.SectorEntry.TABLE_NAME + "." + InventoryContract.SectorEntry._ID);
            sProductViewProjectionMap.put(SECTORNAME, InventoryContract.SectorEntry.TABLE_NAME + "." + InventoryContract.SectorEntry.COLUMN_NAME);
            sProductViewProjectionMap.put(QUANTITY, InventoryContract.ProductViewEntry.COLUMN_QUANTITY);
            sProductViewProjectionMap.put(VALUE, InventoryContract.ProductViewEntry.COLUMN_VALUE);
            sProductViewProjectionMap.put(VENDOR, InventoryContract.ProductViewEntry.COLUMN_VENDOR);
            sProductViewProjectionMap.put(BITMAP, InventoryContract.ProductViewEntry.COLUMN_BITMAP);
            sProductViewProjectionMap.put(IMAGENAME, InventoryContract.ProductViewEntry.TABLE_NAME + "." + InventoryContract.ProductViewEntry.COLUMN_IMAGENAME);
            sProductViewProjectionMap.put(URL, InventoryContract.ProductViewEntry.COLUMN_URL);
            sProductViewProjectionMap.put(DATEPURCHASE, InventoryContract.ProductViewEntry.COLUMN_DATE_PURCHASE);
            sProductViewProjectionMap.put(NOTES, InventoryContract.ProductViewEntry.COLUMN_NOTES);
        }


    }



}
