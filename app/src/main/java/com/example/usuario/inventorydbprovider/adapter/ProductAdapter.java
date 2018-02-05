package com.example.usuario.inventorydbprovider.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.data.db.model.Product;

import java.util.ArrayList;

/**
 * Created by icenri on 2/1/18.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context) {
        super(context, R.layout.item_product, new ArrayList<>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ProductHolder productHolder;
        View rootView = convertView;

        if (convertView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
            productHolder = new ProductHolder();
            productHolder.txvProduct = rootView.findViewById(R.id.txvProductName);
            rootView.setTag(productHolder);
        } else {
            productHolder = (ProductHolder) rootView.getTag();
        }
        productHolder.txvProduct.setText(getItem(position).getShortname());
        return rootView;
    }

    class ProductHolder {
        TextView txvProduct;
    }

}
