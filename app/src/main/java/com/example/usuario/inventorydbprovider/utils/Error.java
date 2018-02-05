package com.example.usuario.inventorydbprovider.utils;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.data.db.InventoryApplication;

/**
 * Clase de errores con código y mensaje personalizados.
 */
public class Error {

    public static int NOT_FOUND = 0;
    public static String NOT_FOUND_RESOURCE = InventoryApplication.getContext().getResources().getString(R.string.error_db_not_found);
    public static String[] MESSAGES = {
            NOT_FOUND_RESOURCE
    };

    int value;
    String message;

    public Error(int value) {
        this.value = value;
        this.message = MESSAGES[value];
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
