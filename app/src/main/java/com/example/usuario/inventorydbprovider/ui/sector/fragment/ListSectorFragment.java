package com.example.usuario.inventorydbprovider.ui.sector.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.adapter.SectorAdapter;
import com.example.usuario.inventorydbprovider.data.db.model.Sector;
import com.example.usuario.inventorydbprovider.ui.sector.SectorActivity;
import com.example.usuario.inventorydbprovider.ui.sector.contract.ListSectorContract;
import com.example.usuario.inventorydbprovider.ui.sector.presenter.ListSectorPresenter;

import java.util.ArrayList;


public class ListSectorFragment extends Fragment implements ListSectorContract.View {
    public static final String TAG = "ListSectorFragment";
    private RecyclerView recyclerSector;
    private SectorAdapter adapter;
    private OnViewSectorListener callback;
    private ListSectorContract.Presenter presenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnViewSectorListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + "must implement OnViewSectorListener interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setPresenter(new ListSectorPresenter(this));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sector, container, false);
        recyclerSector = view.findViewById(android.R.id.list);
        adapter = new SectorAdapter();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerSector.setHasFixedSize(true);
        recyclerSector.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerSector.setAdapter(adapter);
        presenter.loadSectors();
        ((SectorActivity) getActivity()).fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.viewSector(null);
            }
        });
    }

    @Override
    public void setPresenter(ListSectorContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showSectors(ArrayList<Sector> sectors) {
        adapter.clear();
        adapter.addAll(sectors);
    }

    public interface OnViewSectorListener {
        void viewSector(Bundle bundle);
    }

}
