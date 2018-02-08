package com.example.usuario.inventorydbprovider.data.db.repository;

import com.example.usuario.inventorydbprovider.data.db.ProductDao;
import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.data.provider.dao.ProductDaoImpl;
import com.example.usuario.inventorydbprovider.ui.product.interactor.ProductCallback;
import com.example.usuario.inventorydbprovider.ui.sector.interactor.SectorCallback;

import java.util.ArrayList;

public class ProductRepository {

    private static ProductRepository productRepository;
    private ProductDao productDao;

    static {
        productRepository = new ProductRepository();
    }

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

    public void addProduct(Product product, ProductCallback callback) {
        if (productDao.add(product) != -1)
            callback.onSuccess();
        else
            callback.onError(new Throwable());
    }

    public void updateProduct(Product product, SectorCallback callback) {
        if (productDao.update(product) != 0)
            callback.onSuccess();
        else
            callback.onError(new Throwable());
    }

    public void deleteProduct(Product product, SectorCallback callback) {
        int result = productDao.delete(product);
        if (result != 0)
            callback.onSuccess();
        else
            callback.onError(new Throwable());
    }

    public boolean exists(Product product) {
        return productDao.exists(product);
    }

    public ProductView search(int id) {
        return productDao.search(id);
    }

}
