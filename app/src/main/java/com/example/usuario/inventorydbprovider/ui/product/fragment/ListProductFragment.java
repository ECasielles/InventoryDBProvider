package com.example.usuario.inventorydbprovider.ui.product.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.adapter.ProductAdapter;
import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.ui.product.ProductActivity;
import com.example.usuario.inventorydbprovider.ui.product.ProductMultichoiceModeListener;
import com.example.usuario.inventorydbprovider.ui.product.contract.ListProductContract;
import com.example.usuario.inventorydbprovider.ui.product.presenter.ListProductPresenter;

import java.util.ArrayList;


public class ListProductFragment extends ListFragment implements ListProductContract.View {
    public static final String TAG = "ListProductFragment";
    private ProductAdapter adapter;
    private ListProductFragment.OnProductSelectedListener callback;
    private ListProductContract.Presenter presenter;
    private ProductMultichoiceModeListener multichoiceModeListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnProductSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + " must implement OnProductSelectedListener interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setPresenter(new ListProductPresenter(this));
        adapter = new ProductAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_product, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(adapter);
        presenter.loadProductViews();

        //Eliminar
        //Activar el modo MULTICHOICE en la lista
        multichoiceModeListener = new ProductMultichoiceModeListener(presenter);
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        getListView().setMultiChoiceModeListener(multichoiceModeListener);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                getListView().setItemChecked(position, !presenter.getPositionChecked(position));
                return true;
            }
        });
        //Editar
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Parcelable parcel = (Parcelable) adapterView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ProductView.TAG, parcel);
                callback.viewProduct(bundle);
            }
        });
        //Añadir
        ((ProductActivity) getActivity()).fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multichoiceModeListener.cancel();
                callback.viewProduct(null);
            }
        });
    }

    @Override
    public void setPresenter(ListProductContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProductViews(ArrayList<ProductView> productViews) {
        adapter.clear();
        adapter.addAll(productViews);
    }

    @Override
    public Product getProduct(Integer position) {
        return (Product) getListView().getItemAtPosition(position);
    }

    @Override
    public void onProductsDeleted() {
        Toast.makeText(getContext(), "Productos eliminados", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDatabaseError(Throwable throwable) {
        Toast.makeText(getContext(), "Error en la BD: " + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    public interface OnProductSelectedListener {
        void viewProduct(Bundle bundle);
    }

}
