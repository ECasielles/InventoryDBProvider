package com.example.usuario.inventorydbprovider.data.db.repository;

import com.example.usuario.inventorydbprovider.data.db.ProductDao;
import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.data.provider.dao.ProductDaoImpl;
import com.example.usuario.inventorydbprovider.ui.product.interactor.ProductCallback;

import java.util.ArrayList;
import java.util.Iterator;

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
            callback.onError(new Throwable("Fallo al añadir"));
    }

    public void updateProduct(Product product, ProductCallback callback) {
        if (productDao.update(product) != 0)
            callback.onSuccess();
        else
            callback.onError(new Throwable("Fallo al editar"));
    }

    public void deleteProduct(ArrayList<Product> products, ProductCallback callback) {
        boolean success = true;
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()) {
            Product temp = iterator.next();
            if (productDao.delete(temp) == 0) {
                success = false;
                callback.onError(new Throwable("Fallo al eliminar " + temp.getShortname()));
            }
        }
        if(success)
            callback.onSuccess();
    }

    public ProductView search(int id) {
        return productDao.search(id);
    }

}
