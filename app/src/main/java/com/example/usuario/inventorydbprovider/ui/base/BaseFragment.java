package com.example.usuario.inventorydbprovider.ui.base;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

/**
 * Created by usuario on 24/11/17.
 */

public class BaseFragment extends Fragment {

    /**
     * Crea un Snackbar con mensaje o id de string
     *
     * @param message
     */
    public void onError(String message) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), message,
                Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Crea un Snackbar con mensaje o id de string
     *
     * @param resourceId
     */
    public void onError(int resourceId) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), getResources().getString(resourceId),
                Snackbar.LENGTH_SHORT).show();
    }

}
