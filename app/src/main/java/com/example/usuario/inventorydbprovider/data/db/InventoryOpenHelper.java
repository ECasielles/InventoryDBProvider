package com.example.usuario.inventorydbprovider.data.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que trabaja con la BD.
 */

public class InventoryOpenHelper extends SQLiteOpenHelper {

    //Al ser volátil, no se guarda una copia del objeto BD y se
    //actualiza su estado en tiempo real.
    private volatile static InventoryOpenHelper helper;
    //AtomicInteger garantiza la actualización en tiempo real del número de
    //hilos que acceden a la BD.
    private AtomicInteger openCounter = new AtomicInteger(0);
    private SQLiteDatabase sqLiteDatabase;

    public InventoryOpenHelper() {
        //Se le pasa el CONTEXTO de la APLICACION
        //No usamos factory porque no usamos el patrón Factory para varios Contract
        super(InventoryApplication.getContext(), InventoryContract.DATABASE_NAME,
                null, InventoryContract.DATABASE_VERSION);
    }

    /**
     * Devuelve instancia única. Puede ser accedido por varios hilos.
     */
    public synchronized static InventoryOpenHelper getInstance() {
        if(helper == null)
            helper = new InventoryOpenHelper();
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            //Si lo hacemos con Transaction, no se va a crear nada si no está perfecto.
            //Nos daremos cuenta del error cuando no se cree algo.
            //Por eso en principio no usaremos atomicidad.
            db.execSQL(InventoryContract.DependencyEntry.SQL_CREATE_ENTRIES);
            db.execSQL(InventoryContract.DependencyEntry.SQL_INSERT_ENTRIES);
            db.execSQL(InventoryContract.SectorEntry.SQL_CREATE_ENTRIES);
            db.execSQL(InventoryContract.SectorEntry.SQL_INSERT_ENTRIES);
            db.execSQL(InventoryContract.ProductClassEntry.SQL_CREATE_ENTRIES);
            db.execSQL(InventoryContract.ProductClassEntry.SQL_INSERT_ENTRIES);
            db.execSQL(InventoryContract.CategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(InventoryContract.CategoryEntry.SQL_INSERT_ENTRIES);
            db.execSQL(InventoryContract.ProductEntry.SQL_CREATE_ENTRIES);
            db.execSQL(InventoryContract.ProductEntry.SQL_INSERT_ENTRIES);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("InventoryOpenHelper: ", e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.beginTransaction();
            //IMPORTANTE: A la hora de eliminar hay que tener en cuenta el orden por
            //claves ajenas.
            //Esta es la forma rápida.
            db.execSQL(InventoryContract.ProductEntry.SQL_DELETE_ENTRIES);
            db.execSQL(InventoryContract.ProductClassEntry.SQL_DELETE_ENTRIES);
            db.execSQL(InventoryContract.CategoryEntry.SQL_DELETE_ENTRIES);
            db.execSQL(InventoryContract.SectorEntry.SQL_DELETE_ENTRIES);
            db.execSQL(InventoryContract.DependencyEntry.SQL_DELETE_ENTRIES);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("InventoryOpenHelper: ", e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Transacciones obligatorias. Siempre con try.
        try {
            db.beginTransaction();
            //IMPORTANTE: A la hora de eliminar hay que tener en cuenta el orden por
            //claves ajenas.
            //Esta es la forma rápida.
            db.execSQL(InventoryContract.ProductEntry.SQL_DELETE_ENTRIES);
            db.execSQL(InventoryContract.ProductClassEntry.SQL_DELETE_ENTRIES);
            db.execSQL(InventoryContract.CategoryEntry.SQL_DELETE_ENTRIES);
            db.execSQL(InventoryContract.SectorEntry.SQL_DELETE_ENTRIES);
            db.execSQL(InventoryContract.DependencyEntry.SQL_DELETE_ENTRIES);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("InventoryOpenHelper: ", e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Va a representar la BD. Va a contar cuántos hilos acceden a la BD.
     * Sólo el primer hilo abre la BD.
     * @return
     */
    public synchronized SQLiteDatabase openDatabase() {
        //Aquí es cuando se crean las llamadas callback y diferentes métodos
        //Se crea y/o abre la BD.
        if(openCounter.incrementAndGet() == 1)
            //getWritableDatabase cierra lo que hubiera y abre de nuevo
            //Por lo que hay que evitar llamarlo varias veces
            sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase;
    }

    /**
     * Sólo el último hilo cierra la BD.
     */
    public synchronized void closeDatabase() {
        if(openCounter.decrementAndGet() == 0)
            //Cierro la BD.
            sqLiteDatabase.close();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly())
            //Para no tener que declarar cada sentencia con PRAGMA con claves ajenas
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                db.setForeignKeyConstraintsEnabled(true);
            else
                db.execSQL("PRAGMA foreign_keys=1");
    }

}
