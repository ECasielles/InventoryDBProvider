package com.example.usuario.inventorydbprovider.ui.product.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.ui.product.contract.ProductViewContract;
import com.example.usuario.inventorydbprovider.ui.product.presenter.ProductViewPresenter;

/**
 * Created by icenri on 1/31/18.
 */

public class ProductViewFragment extends Fragment implements ProductViewContract.View {
    public static final String TAG = "ProductViewFragment";

    private EditText edtShortname, edtSerial, edtVendor, edtModelcode, edtDescription, edtPrice, edtDatePurchase, edtUrl, edtNotes;
    private TextView txvCategory, txvProductClass, txvSectorName;
    private ProductViewContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setPresenter(new ProductViewPresenter(this));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_product, container, false);
        edtShortname = view.findViewById(R.id.edtProductName);
        edtSerial = view.findViewById(R.id.edtSerial);
        edtVendor = view.findViewById(R.id.edtVendor);
        edtModelcode = view.findViewById(R.id.edtModelCode);
        edtDescription = view.findViewById(R.id.edtDescription);
        edtPrice = view.findViewById(R.id.edtPrice);
        edtDatePurchase = view.findViewById(R.id.edtDatePurchase);
        edtUrl = view.findViewById(R.id.edtUrl);
        edtNotes = view.findViewById(R.id.edtNotes);
        txvCategory = view.findViewById(R.id.txvCategory);
        txvProductClass = view.findViewById(R.id.txvProductClass);
        txvSectorName = view.findViewById(R.id.txvSectorName);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            ProductView productView = getArguments().getParcelable(ProductView.TAG);
            if(productView == null)
                Toast.makeText(getContext(), "ASD", Toast.LENGTH_SHORT).show();

            edtShortname.setText(productView.getShortname());
            edtSerial.setText(productView.getSerial());
            edtVendor.setText(productView.getVendor());
            edtModelcode.setText(productView.getModelCode());
            edtDescription.setText(productView.getDescription());
            edtPrice.setText(String.valueOf(productView.getValue()));
            edtDatePurchase.setText(productView.getDatePurchase());
            edtUrl.setText(productView.getUrl());
            edtNotes.setText(productView.getNotes());
            txvCategory.setText(productView.getCategoryName());
            txvProductClass.setText(productView.getProductClassDescription());
            txvSectorName.setText(productView.getSectorName());
        }
    }

    @Override
    public void setPresenter(ProductViewContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
