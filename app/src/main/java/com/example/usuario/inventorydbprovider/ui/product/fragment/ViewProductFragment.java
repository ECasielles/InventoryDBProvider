package com.example.usuario.inventorydbprovider.ui.product.fragment;

import android.app.Activity;
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
import com.example.usuario.inventorydbprovider.data.db.model.Product;
import com.example.usuario.inventorydbprovider.data.db.model.ProductView;
import com.example.usuario.inventorydbprovider.ui.product.ProductActivity;
import com.example.usuario.inventorydbprovider.ui.product.contract.ViewProductContract;
import com.example.usuario.inventorydbprovider.ui.product.presenter.ViewProductPresenter;
import com.example.usuario.inventorydbprovider.utils.AddEdit;


public class ViewProductFragment extends Fragment implements ViewProductContract.View {
    public static final String TAG = "ViewProductFragment";

    private EditText edtShortname, edtSerial, edtVendor, edtModelcode, edtDescription,
            edtPrice, edtDatePurchase, edtUrl, edtNotes;
    private TextView txvCategory, txvProductClass, txvSectorName;
    private ViewProductContract.Presenter presenter;
    private AddEdit addEditMode;
    private ViewProductFragment.OnProductLoadListener callback;

    public static ViewProductFragment getNewInstance(Bundle args) {
        ViewProductFragment viewProductFragment = new ViewProductFragment();
        if(args != null)
            viewProductFragment.setArguments(args);
        return viewProductFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnProductLoadListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + " must implement OnCreateContextMenuListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setPresenter(new ViewProductPresenter(this));
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
        addEditMode = new AddEdit();
        if(getArguments() != null) {
            addEditMode.setMode(AddEdit.EDIT_MODE);
            ProductView productView = getArguments().getParcelable(ProductView.TAG);
            if(productView != null) {
                edtSerial.setText(productView.getSerial());
                edtModelcode.setText(productView.getModelCode());
                edtShortname.setText(productView.getShortname());
                edtDescription.setText(productView.getDescription());
                edtPrice.setText(String.valueOf(productView.getValue()));
                edtVendor.setText(productView.getVendor());
                edtDatePurchase.setText(productView.getDatePurchase());
                edtUrl.setText(productView.getUrl());
                edtNotes.setText(productView.getNotes());
                txvCategory.setText(productView.getCategoryName());
                txvProductClass.setText(productView.getProductClassDescription());
                txvSectorName.setText(productView.getSectorName());
            }
        }

        ((ProductActivity) getActivity()).fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float value = Float.parseFloat(edtPrice.getText().toString());
                    Product product = new Product(
                            -1,
                            edtSerial.getText().toString(),
                            edtModelcode.getText().toString(),
                            edtShortname.getText().toString(),
                            edtDescription.getText().toString(),
                            1,1,1,1,
                            value,
                            edtVendor.getText().toString(),
                            -1, "sin imagen",
                            edtUrl.getText().toString(),
                            edtDatePurchase.getText().toString(),
                            edtNotes.getText().toString()
                    );
                    if(addEditMode.getMode() == AddEdit.ADD_MODE)
                        presenter.saveProduct(product);
                    if(addEditMode.getMode() == AddEdit.EDIT_MODE){
                        ProductView productView = getArguments().getParcelable(ProductView.TAG);
                        if(productView != null) {
                            product.setId(productView.get_ID());
                            product.setCategory(productView.getCategory());
                            product.setProductClass(productView.getProductClass());
                            product.setSectorID(productView.getSectorID());
                            product.setQuantity(productView.getQuantity());
                            presenter.updateProduct(product);
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Valor de producto no válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void setPresenter(ViewProductContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void productLoaded() {
        callback.loadListProduct();
    }

    @Override
    public void onLoadError(Throwable throwable) {
        if(throwable != null)
            Toast.makeText(getContext(), "Error de BD: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public interface OnProductLoadListener {
        void loadListProduct();
    }

}
