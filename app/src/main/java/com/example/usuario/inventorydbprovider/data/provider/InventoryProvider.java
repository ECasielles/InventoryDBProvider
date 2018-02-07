package com.example.usuario.inventorydbprovider.data.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.usuario.inventorydbprovider.data.db.InventoryContract;
import com.example.usuario.inventorydbprovider.data.db.InventoryOpenHelper;

/**
 * Único para todos los datos que se quieran obtener.
 */
public class InventoryProvider extends ContentProvider {

    //Se debe crear una constante por cada petición/uri
    //que pueda recoger el content provider
    private static final int PRODUCT = 1;
    private static final int PRODUCT_ID = 2;
    private static final int DEPENDENCY = 3;
    private static final int DEPENDENCY_ID = 4;
    private static final int SECTOR = 5;
    private static final int SECTOR_ID = 6;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private SQLiteDatabase database;

    //Añadimos toda la cadena de petición con la uri
    //IMPORTANTE: IR AL MANIFEST Y VER EL REGISTRO DEL PROVIDER
    static {
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.ProductViewEntry.CONTENT_PATH + "/#", PRODUCT_ID);
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.ProductViewEntry.CONTENT_PATH + "/#", PRODUCT);
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.ProductViewEntry.CONTENT_PATH + "/#", DEPENDENCY);
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.ProductViewEntry.CONTENT_PATH + "/#", DEPENDENCY_ID);
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.ProductViewEntry.CONTENT_PATH + "/#", SECTOR);
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.ProductViewEntry.CONTENT_PATH + "/#", SECTOR_ID);
    }


    @Override
    public boolean onCreate() {
        database = InventoryOpenHelper.getInstance().openDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;

        //Switch obligatorio. Están todas las peticiones de datos.
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                break;
            case PRODUCT_ID:
                break;
            case DEPENDENCY:
                cursor = database.query(InventoryContract.DependencyEntry.TABLE_NAME, projection,
                        null, null, null, null, null);
                break;
            case DEPENDENCY_ID:
                break;
            case SECTOR:
                break;
            case SECTOR_ID:
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Invalid uri: " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

}
