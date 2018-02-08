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

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.adapter.ProductAdapter;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.data.db.repository.ProductRepository;
import com.example.usuario.inventorydbprovider.ui.product.ProductActivity;
import com.example.usuario.inventorydbprovider.ui.product.contract.ListProductContract;
import com.example.usuario.inventorydbprovider.ui.product.presenter.ListProductPresenter;

import java.util.ArrayList;


public class ListProductFragment extends ListFragment implements ListProductContract.View {
    public static final String TAG = "ListProductFragment";
    private ProductAdapter adapter;
    private ListProductFragment.OnProductSelectedListener callback;
    private ListProductContract.Presenter presenter;


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
        setPresenter(new ListProductPresenter(this));
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

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Parcelable parcel = ProductRepository.getInstance().search(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ProductView.TAG, parcel);
                callback.viewProduct(bundle);
            }
        });
        ((ProductActivity) getActivity()).fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    public interface OnProductSelectedListener {
        void viewProduct(Bundle bundle);
    }

}
