package com.example.usuario.inventorydbprovider.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.usuario.inventorydbprovider.data.db.InventoryApplication;
import com.example.usuario.inventorydbprovider.utils.AppConstants;

/**
 * Created by usuario on 4/12/17.
 */

public class AppPreferencesHelper implements AccountPreferencesHelper, GeneralPreferencesHelper {
    private static final String TAG = "AppPreferencesHelper";

    /**
     * 1.- Se definen todas las claves posibles de los fichero preferencias (ver interfaces)
     */

    /**
     * 2.- Objeto para editar las preferencias
     */
    private final SharedPreferences preferences;
    private static AppPreferencesHelper helper;
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;

    //TODO: Plantear arquitectura de clases. ¿Quién va a usar esta clase?
    public interface AppPreferencesListener {
        void onSharedPreferencesListener();
    }


    private AppPreferencesHelper() {
        //Si es el fichero por defecto de las preferencias
        preferences = InventoryApplication.getContext().getSharedPreferences(InventoryApplication.PREF_NAME, Context.MODE_PRIVATE);
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                Log.i(TAG, "onSharedPreferenceChanged: Se ha cambiado la clave: " + key);
            }
        };
    }

    /**
     * Método de acceso a la instancia de la clase AppPreferencesHelper
     * @return
     */
    public static AppPreferencesHelper getInstance() {
        if(helper == null)
            helper = new AppPreferencesHelper();
        return helper;
    }

    /**
     * ID SQLite
     * @return
     */
    public long getCurrentUserId() {
        return preferences.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
    }
    /**
     * Name SQLite
     * @return
     */
    public String getCurrentUserName() {
        return preferences.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }
    /**
     * Password SQLite
     * @return
     */
    public String getCurrentUserPassword() {
        return preferences.getString(PREF_KEY_CURRENT_USER_PASSWORD, null);
    }
    /**
     * Boolean SQLite
     * @return
     */
    public boolean getCurrentUserRemember() {
        return preferences.getBoolean(PREF_KEY_CURRENT_USER_REMEMBER, false);
    }

    public void setCurrentUserId(long id) {
        //Como ahora mismo estamos modificando el xml de forma independiente
        //por valores, hacemos apply ya que el commit se hace para que varias operaciones se hagan
        //de forma atómica mientras que apply es para una sola edición.
        preferences.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }
    public void getCurrentUserName(String name) {
        preferences.edit().putString(PREF_KEY_CURRENT_USER_NAME, name).apply();
    }
    public void getCurrentUserPassword(String password) {
        preferences.edit().putString(PREF_KEY_CURRENT_USER_PASSWORD, password).apply();
    }
    public void getCurrentUserRemember(boolean remember) {
        preferences.edit().putBoolean(PREF_KEY_CURRENT_USER_REMEMBER, remember).apply();
    }


}
