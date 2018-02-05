package com.example.usuario.inventorydbprovider.ui.dependency.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.adapter.DependencyAdapter;
import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.ui.dependency.DependencyMultichoiceModeListener;
import com.example.usuario.inventorydbprovider.ui.dependency.contract.ListDependencyContract;
import com.example.usuario.inventorydbprovider.ui.dependency.presenter.ListDependencyPresenter;
import com.example.usuario.inventorydbprovider.utils.CommonDialog;
import com.example.usuario.inventorydbprovider.utils.CommonUtils;

import java.util.List;

public class ListDependencyFragment extends ListFragment implements ListDependencyContract.View {

    public static final String TAG = "ListDependencyFragment";
    private ListDependencyContract.Presenter presenter;
    private ListDependencyListener callback;
    private DependencyAdapter adapter;
    private DependencyMultichoiceModeListener multichoiceModeListener;
    private FloatingActionButton fab;
    private ProgressDialog progressDialog;


    //CONSTRUCTORES
    public ListDependencyFragment(){
        setRetainInstance(true);
    }

    public static ListDependencyFragment newInstance(Bundle arguments) {
        ListDependencyFragment listDependencyFragment = new ListDependencyFragment();
        if(arguments != null) {
            listDependencyFragment.setArguments(arguments);
        }
        return listDependencyFragment;
    }

    //CICLO DE VIDA: INICIO
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (ListDependencyListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    " must implement ListDependencyListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Asignamos aquí el adaptador porque en el constructor no podemos porque
        //aún no está creada la activity
        this.adapter = new DependencyAdapter(getActivity());
        //Si esta vista guardase el presenter...
        //this.presenter = new ListPresenter(this);
        //setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_dependency, container, false);

        //Como se encuentra en el Fragment usamos rootView
        fab = rootView.findViewById(R.id.fab);

        return rootView;
    }

    /**
     * Asigna el adapter sin datos a la vista.
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //No se puede añadir el adaptador en el onCreateView porque aún no existe la vista
        setListAdapter(adapter);
        presenter.loadDependencies();

        //Le asignamos un menú contextual a la lista (viewGroup)
        //Hay que hacerlo aquí porque antes no está creada la vista
        //Comentado porque usamos pulsación multichoice.
        //registerForContextMenu(getListView());
        multichoiceModeListener = new DependencyMultichoiceModeListener(presenter);

        //Activar el modo MULTICHOICE en la lista
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        getListView().setMultiChoiceModeListener(multichoiceModeListener);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                getListView().setItemChecked(position, !presenter.getPositionChecked(position));
                return true;
            }
        });

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * @param adapterView Lista que hereda de AdapterView
             * @param view Vista que contiene el layout elemento
             * @param position Posición en la lista
             * @param id Id del cursor
             * @return
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Parcelable parcel = (Parcelable) adapterView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Dependency.TAG, parcel);
                callback.addNewDependency(bundle);
            }
        });

        //Si el fab se encontrara en el xml de la Activity
        //FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Buscar cómo hacerlo con finish
                //getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);
                multichoiceModeListener.cancel();
                callback.addNewDependency(null);
            }
        });

    }

    //GUARDAR EL ESTADO
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ListDependencyPresenter.TAG, presenter);
    }

    /**
     * Recupera el presenter cuando la vista se restaura tras un cambio de configuración.
     * @param savedInstanceState
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //Podría hacer que esta vista cree el Presenter y que no lo maneje la Activity
        //Se podría recuperarlo también en el onCreateView
        if(savedInstanceState != null)
            this.presenter = (ListDependencyContract.Presenter) savedInstanceState.get(ListDependencyPresenter.TAG);
    }

    /**
     * Muestra el menú contextual
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Aquí se le declaran elementos al menú, como el título
        menu.setHeaderTitle(R.string.listDependencyFragmentContextTitle);
        getActivity().getMenuInflater().inflate(R.menu.menu_fragment_list_dependency_delete, menu);
    }

    //MENU CONTEXTUAL

    /**
     * Implementa las diferentes acciones de las opciones del menú contextual
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fragment_listdependency_delete:
                //Recupero el objeto
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Dependency dependency = (Dependency) getListView().getItemAtPosition(adapterContextMenuInfo.position);

                //Cuadro de diálogo de confirmación con patrón Builder
                Bundle bundle = new Bundle();
                bundle.putParcelable(Dependency.TAG, dependency);
                bundle.putString(CommonDialog.TITLE, "Eliminar dependencia?");
                bundle.putInt(CommonDialog.TYPE, CommonDialog.DELETE_DIALOG);
                bundle.putString(CommonDialog.MESSAGE, "¿Desea eliminar la dependencia?");
                Dialog dialog = CommonDialog.showConfirmDialog(bundle, getActivity(), presenter);
                dialog.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * Asigna el presentador a la vista
     * @param presenter
     */
    @Override
    public void setPresenter(ListDependencyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    //COMUNICACION ENTRE CLASES MVPI

    /**
     * Este método es el que usa la vista para cargar los datos del repositorio
     * a través del esquema MVP
     * @param dependencies
     */
    @Override
    public void showDependencies(List<Dependency> dependencies) {
        //Limpio el adaptador por si hubiera datos anteriores
        adapter.clear();
        adapter.addAll(dependencies);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = CommonUtils.showLoadDialog(getContext());
    }
    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public Dependency getDependency(int position) {
        return (Dependency) getListView().getItemAtPosition(position);
    }

    @Override
    public void showDeletedMessage() {
        showMessage(getResources().getString(R.string.dependency_deleted));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        adapter = null;
    }

    //INTERFAZ COMUNICACION CON LA ACTIVITY
    public interface ListDependencyListener {
        void addNewDependency(Bundle bundle);
    }

}
