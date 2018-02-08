package com.example.usuario.inventorydbprovider.data.db.repository;

import com.example.usuario.inventorydbprovider.data.db.ProductDao;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.data.provider.dao.ProductDaoImpl;

import java.util.ArrayList;

public class ProductRepository {

    private static ProductRepository productRepository;

    static {
        productRepository = new ProductRepository();
    }

    private ProductDao productDao;

    private ProductRepository() {
        productDao = new ProductDaoImpl();
    }

    public static ProductRepository getInstance() {
        if (productRepository == null)
            productRepository = new ProductRepository();
        return productRepository;
    }

    public ArrayList<ProductView> getProducts() {
        return productDao.loadAll();
    }

    public ProductView search(int id) {
        return productDao.search(id);
    }

}
