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
    //IMPORTANTE: # solo para id, content_path de cada product
    static {
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.ProductViewEntry.CONTENT_PATH + "/#", PRODUCT_ID);
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.ProductViewEntry.CONTENT_PATH + "/", PRODUCT);
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.Dependency.CONTENT_PATH + "/", DEPENDENCY);
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.Dependency.CONTENT_PATH + "/#", DEPENDENCY_ID);
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.Sector.CONTENT_PATH + "/", SECTOR);
        uriMatcher.addURI(InventoryProviderContract.AUTHORITY,
                InventoryProviderContract.Sector.CONTENT_PATH + "/#", SECTOR_ID);
    }

    //TODO: REPASAR delete y update y hacerlo todo con los demás

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
                cursor = database.query(InventoryContract.ProductViewEntry.TABLE_NAME, projection,
                        null, null, null, null, null);
                break;
            case PRODUCT_ID:
                cursor = database.query(InventoryContract.ProductViewEntry.TABLE_NAME, projection,
                        null, null, null, null, null);
                break;
            case DEPENDENCY:
                cursor = database.query(InventoryContract.DependencyEntry.TABLE_NAME, projection,
                        null, null, null, null, null);
                break;
            case DEPENDENCY_ID:
                cursor = database.query(InventoryContract.DependencyEntry.TABLE_NAME, projection,
                        null, null, null, null, null);
                break;
            case SECTOR:
                cursor = database.query(InventoryContract.SectorEntry.TABLE_NAME, projection,
                        null, null, null, null, null);
                break;
            case SECTOR_ID:
                cursor = database.query(InventoryContract.SectorEntry.TABLE_NAME, projection,
                        null, null, null, null, null);
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Invalid uri: " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        //Devuelve tipo MIME para la acción de intent explícitos
        //vnd.android.cursor.dir/vnd.com.example.provider.table1 <- Una tabla
        //vnd.android.cursor.item/vnd.com.example.provider.table1 <- Un elemento
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                return ("vnd.android.cursor.dir/vnd." + InventoryProviderContract.AUTHORITY + "/"
                        + InventoryProviderContract.Dependency.CONTENT_PATH);
            case PRODUCT_ID:
                return ("vnd.android.cursor.item/vnd." + InventoryProviderContract.AUTHORITY + "/"
                        + InventoryProviderContract.Dependency.CONTENT_PATH);
            case DEPENDENCY:
                return ("vnd.android.cursor.dir/vnd." + InventoryProviderContract.AUTHORITY + "/"
                        + InventoryProviderContract.Dependency.CONTENT_PATH);
            case DEPENDENCY_ID:
                return ("vnd.android.cursor.item/vnd." + InventoryProviderContract.AUTHORITY + "/"
                        + InventoryProviderContract.Dependency.CONTENT_PATH);
            case SECTOR:
                return ("vnd.android.cursor.dir/vnd." + InventoryProviderContract.AUTHORITY + "/"
                        + InventoryProviderContract.Dependency.CONTENT_PATH);
            case SECTOR_ID:
                return ("vnd.android.cursor.item/vnd." + InventoryProviderContract.AUTHORITY + "/"
                        + InventoryProviderContract.Dependency.CONTENT_PATH);
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Invalid uri: " + uri);
        }
        return "";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri result = null;
        long rows;
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                rows = database.insert(InventoryContract.ProductViewEntry.TABLE_NAME,
                        null, contentValues);
                result = Uri.parse(InventoryProviderContract.AUTHORITY +
                                InventoryProviderContract.ProductViewEntry.CONTENT_PATH + "/" + rows);
                break;
            case DEPENDENCY:
                rows = database.insert(InventoryContract.DependencyEntry.TABLE_NAME,
                        null, contentValues);
                //Comprobar que no devuelva null
                result = Uri.parse(InventoryProviderContract.AUTHORITY +
                        InventoryProviderContract.Dependency.CONTENT_PATH + "/" + rows);
                break;
            case SECTOR:
                rows = database.insert(InventoryContract.SectorEntry.TABLE_NAME,
                        null, contentValues);
                result = Uri.parse(InventoryProviderContract.AUTHORITY +
                                InventoryProviderContract.Sector.CONTENT_PATH + "/" + rows);
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Invalid uri: " + uri);
        }
        return result;
    }

    //TODO: WhereClause toma el id de la uri
    @Override
    public int delete(@NonNull Uri uri, @Nullable String whereClause, @Nullable String[] whereArgs) {
        int result = -1;
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                result = database.delete(InventoryContract.ProductViewEntry.TABLE_NAME,
                        null, null);
                break;
            case DEPENDENCY:
                result = database.delete(InventoryContract.DependencyEntry.TABLE_NAME,
                        null, null);
                break;
            case SECTOR:
                result = database.delete(InventoryContract.SectorEntry.TABLE_NAME,
                        null, null);
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Invalid uri: " + uri);
        }
        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String whereClause, @Nullable String[] whereArgs) {
        int result = -1;
        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                result = database.update(InventoryContract.ProductViewEntry.TABLE_NAME,
                        contentValues, null, null);
                break;
            case DEPENDENCY:
                result = database.update(InventoryContract.DependencyEntry.TABLE_NAME,
                        contentValues, null, null);
                break;
            case SECTOR:
                result = database.update(InventoryContract.SectorEntry.TABLE_NAME,
                        contentValues, null, null);
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Invalid uri: " + uri);
        }
        return result;
    }



}
