package com.example.usuario.inventorydbprovider.ui.product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.ui.product.fragment.ProductListFragment;
import com.example.usuario.inventorydbprovider.ui.product.fragment.ProductViewFragment;

/**
 * Actividad que maneja el alta de productos
 *
 * @author Enrique Casielles Lapeira
 * @version 2.0
 * @see android.app.Activity
 * @see AppCompatActivity
 */
public class ProductActivity extends AppCompatActivity implements ProductListFragment.OnProductSelectedListener {

    ProductViewFragment productViewFragment;
    ProductListFragment productListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productListFragment = (ProductListFragment) getSupportFragmentManager().findFragmentByTag(ProductListFragment.TAG);
        if (productListFragment == null) {
            productListFragment = new ProductListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_product, productListFragment, ProductListFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void viewProduct(Bundle bundle) {
        productViewFragment = (ProductViewFragment) getSupportFragmentManager().findFragmentByTag(ProductViewFragment.TAG);
        if (productViewFragment == null) {
            productViewFragment = new ProductViewFragment();
            if (bundle != null)
                productViewFragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.activity_product, productViewFragment, ProductViewFragment.TAG)
                    .commit();
        }
    }

}
