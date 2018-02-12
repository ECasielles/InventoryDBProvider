package com.example.usuario.inventorydbprovider.ui.sector;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.ui.sector.fragment.ListSectorFragment;
import com.example.usuario.inventorydbprovider.ui.sector.fragment.ViewSectorFragment;

/**
 * Actividad que maneja el alta de secciones
 *
 * @author Enrique Casielles Lapeira
 * @version 2.0
 * @see android.app.Activity
 * @see AppCompatActivity
 */
public class SectorActivity extends AppCompatActivity implements ListSectorFragment.OnViewSectorListener,
        ViewSectorFragment.OnSectorsUpdatedListener {

    ListSectorFragment listSectorFragment;
    ViewSectorFragment viewSectorFragment;
    Toolbar toolbar;
    public FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sector);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);

        listSectorFragment = (ListSectorFragment) getSupportFragmentManager().findFragmentByTag(ListSectorFragment.TAG);
        if (listSectorFragment == null) {
            listSectorFragment = new ListSectorFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_content, listSectorFragment, ListSectorFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void viewSector(Bundle bundle) {
        viewSectorFragment = (ViewSectorFragment) getSupportFragmentManager().findFragmentByTag(ViewSectorFragment.TAG);
        if (viewSectorFragment == null) {
            viewSectorFragment = ViewSectorFragment.newInstance(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame_content, viewSectorFragment, ViewSectorFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void onSectorsUpdated() {
        getSupportFragmentManager().popBackStack();
    }

}
