package com.example.usuario.inventorydbprovider.ui.dependency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.ui.base.BaseActivity;
import com.example.usuario.inventorydbprovider.ui.dependency.fragment.AddEditDependencyFragment;
import com.example.usuario.inventorydbprovider.ui.dependency.fragment.ListDependencyFragment;
import com.example.usuario.inventorydbprovider.ui.dependency.presenter.AddEditDependencyPresenter;
import com.example.usuario.inventorydbprovider.ui.dependency.presenter.ListDependencyPresenter;


/**
 * Actividad contenedora de las dependencias.
 *
 * @author Enrique Casielles Lapeira
 * @version 2.0
 * @see com.example.usuario.inventorydbprovider.ui.base.BaseActivity
 */
//Como la actividad es contenedor y nunca se pasa como parámetro
//no implementará una interfaz
public class DependencyActivity extends BaseActivity implements ListDependencyFragment.ListDependencyListener,
        AddEditDependencyFragment.AddEditDependencyListener {
    //La actividad inicializa cada fragment con su presentador
    private ListDependencyFragment listDependencyFragment;
    private ListDependencyPresenter listPresenter;
    private AddEditDependencyFragment addeditDependencyFragment;
    private AddEditDependencyPresenter addEditPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependency);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //1.- Se crea la vista
        listDependencyFragment = (ListDependencyFragment) fragmentManager.findFragmentByTag(ListDependencyFragment.TAG);
        if (listDependencyFragment == null){
            listDependencyFragment = ListDependencyFragment.newInstance(null);
            fragmentTransaction.add(android.R.id.content, listDependencyFragment, ListDependencyFragment.TAG);
            fragmentTransaction.commit();
        }
        //2.- Se crea el presentador, y se le pasa en el constructor la vista correspondiente
        listPresenter = new ListDependencyPresenter(listDependencyFragment);

        //3.- Si fuera necesario, se asigan el presentador a su fragment
        listDependencyFragment.setPresenter(listPresenter);
    }

    /**
     * Método que se ejecuta cuando se crea una nueva Dependencia
     */
    @Override
    public void addNewDependency(Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //1.- Se crea la vista
        addeditDependencyFragment = (AddEditDependencyFragment) fragmentManager.findFragmentByTag(AddEditDependencyFragment.TAG);
        if (addeditDependencyFragment == null){
            if(bundle != null)
                addeditDependencyFragment = AddEditDependencyFragment.newInstance(bundle);
            else
                addeditDependencyFragment = AddEditDependencyFragment.newInstance(null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(android.R.id.content, addeditDependencyFragment, AddEditDependencyFragment.TAG).commit();
        }
        //2.- Se crea el presentador, y se le pasa en el constructor la vista correspondiente
        addEditPresenter = new AddEditDependencyPresenter(addeditDependencyFragment);

        //3.- Si fuera necesario, se asigan el presentador a su fragment
        addeditDependencyFragment.setPresenter(addEditPresenter);
    }

    @Override
    public void listDependency() {
        getSupportFragmentManager().popBackStack();
    }

}
