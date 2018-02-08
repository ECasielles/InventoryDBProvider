package com.example.usuario.inventorydbprovider.data.db;

import android.content.ContentValues;

import com.example.usuario.inventorydbprovider.data.db.model.Dependency;

import java.util.ArrayList;

/**
 * Created by usuario on 7/02/18.
 */

public interface DependencyDao {
    ArrayList<Dependency> loadAll();
    long add(Dependency dependency);
    int delete(Dependency dependency);
    boolean exists(Dependency dependency);
    int update(Dependency dependency);
    ContentValues createContent(Dependency dependency);
    Dependency search(int id);
}
