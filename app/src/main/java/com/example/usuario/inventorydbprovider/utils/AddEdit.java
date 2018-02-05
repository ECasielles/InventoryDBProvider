package com.example.usuario.inventorydbprovider.utils;

/**
 * Clase que controla el modo de inserción o edición
 * de elementos de una clase que añada a una lista.
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 */
public class AddEdit {
    //interface AddEditMode
    public static final int ADD_MODE = 0;
    public static final int EDIT_MODE = 1;
    private int mode;

    public AddEdit() {
        this.mode = ADD_MODE;
    }
    public AddEdit(int mode) {
        if(mode < ADD_MODE || mode > EDIT_MODE)
            throw new IllegalArgumentException("Invalid AddEditMode: " + mode);
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
