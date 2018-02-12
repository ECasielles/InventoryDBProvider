package com.example.usuario.inventorydbprovider.ui.product;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.ui.product.contract.ListProductContract;


/**
 * Clase que maneja la multiselección de la lista de productos.
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 * @see android.widget.AbsListView.MultiChoiceModeListener
 * @see ListProductContract.Presenter
 */
public class ProductMultichoiceModeListener implements AbsListView.MultiChoiceModeListener {
    private static final String TAG = "ProductMultichoiceModeListener";
    private ActionMode actionMode = null;

    //Podríamos tener un Presenter sólo para multiselección.
    private ListProductContract.Presenter presenter;
    private int count;

    public ProductMultichoiceModeListener(ListProductContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean selected) {
        if (selected) {
            count++;
            presenter.setNewSelection(position);
        } else {
            if(count > 0) {
                count--;
                presenter.removeSelection(position);
            }
        }
        actionMode.setTitle(count + " seleccionados");
    }
    /**
     * Cuando se realiza una pulsación larga sobre el primer elemento.
     * Inicializaremos la barra de contexto aquí.
     * @param actionMode
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        //ActionMode no entra en el examen
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.menu_fragment_list_product_delete, menu);
        this.actionMode = actionMode;
        //True significa que debe ejecutarlo
        return true;
    }
    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }
    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_fragment_list_product_delete:
                presenter.deleteSelection();
                break;
        }
        actionMode.finish();
        return true;
    }
    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        count = 0;
        presenter.clearSelection();
    }

    public void cancel() {
        if(actionMode != null) actionMode.finish();
    }

}
