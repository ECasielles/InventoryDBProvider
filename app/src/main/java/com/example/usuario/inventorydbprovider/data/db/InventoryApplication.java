package com.example.usuario.inventorydbprovider.data.db;

import android.app.Application;

import com.example.usuario.inventorydbprovider.data.prefs.AppPreferencesHelper;

/**
 * Clase singleton (hereda de Application) que contiene los datos.
 * Inicializa la instancia de preferencias.
 *
 * @author Enrique Casielles
 * @version 2.0
 * @see android.app.Application
 * @see AppPreferencesHelper
 */
public class InventoryApplication extends Application {

    public static final String PREF_NAME = "preferences";

    private static AppPreferencesHelper appPreferencesHelper;
    private static InventoryApplication context;

    public InventoryApplication() {
        context = this;
    }

    public static AppPreferencesHelper getAppPreferencesHelper() {
        return appPreferencesHelper;
    }

    public static InventoryApplication getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appPreferencesHelper = AppPreferencesHelper.getInstance();
        InventoryOpenHelper.getInstance().openDatabase();
    }

}
