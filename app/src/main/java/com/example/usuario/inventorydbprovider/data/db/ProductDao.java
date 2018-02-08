package com.example.usuario.inventorydbprovider.data.db;

import android.content.ContentValues;

import com.example.usuario.inventorydbprovider.data.db.model.Product;

import java.util.ArrayList;

/**
 * Created by usuario on 8/02/18.
 */

public interface ProductDao {
    ArrayList<Product> loadAll();
    long add(Product product);
    int delete(Product product);
    boolean exists(Product product);
    int update(Product product);
    ContentValues createContent(Product product);
}
