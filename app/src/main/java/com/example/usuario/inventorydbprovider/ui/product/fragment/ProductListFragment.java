package com.example.usuario.inventorydbprovider.ui.product.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.adapter.ProductAdapter;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.data.db.repository.ProductRepository;
import com.example.usuario.inventorydbprovider.ui.product.contract.ProductListContract;
import com.example.usuario.inventorydbprovider.ui.product.presenter.ProductListPresenter;

import java.util.ArrayList;

/**
 * Created by icenri on 2/1/18.
 */

public class ProductListFragment extends ListFragment implements ProductListContract.View {
    public static final String TAG = "ProductListFragment";
    private ProductAdapter adapter;
    private ProductListFragment.OnProductSelectedListener callback;
    private ProductListContract.Presenter presenter;


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
        this.adapter = new ProductAdapter(getActivity());
        setRetainInstance(true);
        setPresenter(new ProductListPresenter(this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_product, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(adapter);
        presenter.loadProductViews();

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Parcelable parcel = ProductRepository.getInstance().search(position);
                Toast.makeText(getActivity(), ProductRepository.getInstance().search(position) == null ? "NULL" : "NOT NULL", Toast.LENGTH_SHORT).show();
                //Bundle bundle = new Bundle();
                //bundle.putParcelable(ProductView.TAG, parcel);
                //callback.viewProduct(bundle);
            }
        });
    }

    @Override
    public void setPresenter(ProductListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProductViews(ArrayList<ProductView> productViews) {
        adapter.clear();
        adapter.addAll(productViews);
    }

    public interface OnProductSelectedListener {
        void viewProduct(Bundle bundle);
    }

}
