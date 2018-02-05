package com.example.usuario.inventorydbprovider.data.db.repository;

import com.example.usuario.inventorydbprovider.data.db.dao.ProductClassDao;
import com.example.usuario.inventorydbprovider.data.db.model.ProductClass;

import java.util.ArrayList;

/**
 * Created by usuario on 1/02/18.
 */

public class ProductClassRepository {


    private static ProductClassRepository productClassRepository;

    static {
        productClassRepository = new ProductClassRepository();
    }

    private ProductClassDao productClassDao;

    private ProductClassRepository() {
        productClassDao = new ProductClassDao();
    }


    public static ProductClassRepository getInstance() {
        if (productClassRepository == null)
            productClassRepository = new ProductClassRepository();
        return productClassRepository;
    }

    public ArrayList<ProductClass> getProductClasses() {
        return productClassDao.loadAll();
    }


}
