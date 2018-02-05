package com.example.usuario.inventorydbprovider.data.db.model;

/**
 * Created by usuario on 1/02/18.
 */

public class ProductClass {
    int _ID;
    String description;

    public ProductClass(int _ID, String description) {
        this._ID = _ID;
        this.description = description;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
