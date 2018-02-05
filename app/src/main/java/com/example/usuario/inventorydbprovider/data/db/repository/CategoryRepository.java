package com.example.usuario.inventorydbprovider.data.db.repository;

import com.example.usuario.inventorydbprovider.data.db.dao.CategoryDao;
import com.example.usuario.inventorydbprovider.data.db.model.Category;

import java.util.ArrayList;

/**
 * Created by usuario on 1/02/18.
 */

public class CategoryRepository {

    private static CategoryRepository categoryRepository;

    static {
        categoryRepository = new CategoryRepository();
    }

    private CategoryDao categoryDao;

    private CategoryRepository() {
        categoryDao = new CategoryDao();
    }


    public static CategoryRepository getInstance() {
        if (categoryRepository == null)
            categoryRepository = new CategoryRepository();
        return categoryRepository;
    }

    public ArrayList<Category> getCategories() {
        return categoryDao.loadAll();
    }

/*    public void addCategory(Category category) {
        long id = categoryDao.add(category);
    }*/

}
