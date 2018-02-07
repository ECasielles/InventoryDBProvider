package com.example.usuario.inventorydbprovider.data.provider.dao;

import com.example.usuario.inventorydbprovider.data.db.DependencyDao;
import com.example.usuario.inventorydbprovider.data.db.model.Dependency;

import java.util.ArrayList;

/**
 * Created by usuario on 7/02/18.
 */

public class DependencyDaoImpl implements DependencyDao {

    @Override
    public ArrayList<Dependency> loadAll() {
        return null;
    }

    @Override
    public long add(Dependency dependency) {
        return 0;
    }

    @Override
    public int delete(Dependency dependency) {
        return 0;
    }

    @Override
    public boolean exists(Dependency dependency) {
        return false;
    }

    @Override
    public int update(Dependency dependency) {
        return 0;
    }
}
