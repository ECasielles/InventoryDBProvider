package com.example.usuario.inventorydbprovider.adapter;

import com.example.usuario.inventorydbprovider.data.db.model.Sector;

/**
 * Created by icenri on 2/12/18.
 */

public interface OnItemActionListener {

    void onItemClick(Sector sector);
    void onItemLongClick(Sector sector);

}
