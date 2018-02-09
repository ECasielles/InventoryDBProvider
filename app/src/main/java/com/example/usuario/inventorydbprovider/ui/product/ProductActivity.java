package com.example.usuario.inventorydbprovider.ui.product;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.ui.product.fragment.ListProductFragment;
import com.example.usuario.inventorydbprovider.ui.product.fragment.ViewProductFragment;

/**
 * Actividad que maneja el alta de productos
 *
 * @author Enrique Casielles Lapeira
 * @version 2.0
 * @see android.app.Activity
 * @see AppCompatActivity
 */
public class ProductActivity extends AppCompatActivity implements ListProductFragment.OnProductSelectedListener,
    ViewProductFragment.OnProductLoadListener {

    ViewProductFragment viewProductFragment;
    ListProductFragment listProductFragment;
    Toolbar toolbar;
    public FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);

        listProductFragment = (ListProductFragment) getSupportFragmentManager().findFragmentByTag(ListProductFragment.TAG);
        if (listProductFragment == null) {
            listProductFragment = new ListProductFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_product, listProductFragment, ListProductFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void viewProduct(Bundle bundle) {
        viewProductFragment = (ViewProductFragment) getSupportFragmentManager().findFragmentByTag(ViewProductFragment.TAG);
        if (viewProductFragment == null) {
            viewProductFragment = new ViewProductFragment();
            if (bundle != null)
                viewProductFragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.activity_product, viewProductFragment, ViewProductFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void loadListProduct() {
        getSupportFragmentManager().popBackStack();
    }

}
