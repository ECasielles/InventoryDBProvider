package com.example.usuario.inventorydbprovider.data.db;

import android.content.ContentValues;

import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;

import java.util.ArrayList;

/**
 * Created by usuario on 8/02/18.
 */

public interface ProductDao {
    ArrayList<ProductView> loadAll();
    long add(Product product);
    int delete(Product product);
    int update(Product product);
    ContentValues createContent(Product product);
    ProductView search(int id);
}
