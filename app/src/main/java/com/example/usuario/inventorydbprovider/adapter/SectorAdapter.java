package com.example.usuario.inventorydbprovider.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.data.db.model.Sector;
import com.example.usuario.inventorydbprovider.data.db.repository.SectorRepository;

import java.util.ArrayList;

/**
 * Clase Adapter que maneja secciones o armarios de productos.
 *
 * @author Enrique Casielles Lapeira
 * @version 3.0
 * @see ArrayList
 * @see Sector
 * @see SectorRepository
 * @see RecyclerView.Adapter
 */
public class SectorAdapter extends RecyclerView.Adapter<SectorAdapter.SectorViewHolder> {

    private ArrayList<Sector> sectors = new ArrayList<>();
    private final OnItemActionListener listener;

    public SectorAdapter(OnItemActionListener listener) {
        this.listener = listener;
    }

    @Override
    public SectorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SectorViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_sector, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(final SectorViewHolder sectorViewHolder, int position) {
        sectorViewHolder.swtEnabled.setChecked(sectors.get(position).isEnabled());
        sectorViewHolder.swtEnabled.setTag(sectors.get(position));
        sectorViewHolder.txvName.setText(sectors.get(position).getShortname());
        sectorViewHolder.bind(sectors.get(position), listener);

        if (sectors.get(position).isDefault())
            sectorViewHolder.txvSectorDefault.setText(R.string.txvSectorDefault);
    }

    @Override
    public int getItemCount() {
        return sectors.size();
    }

    public void clear() {
        sectors.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<Sector> newSectors) {
        if (newSectors != null) {
            this.sectors.addAll(newSectors);
            notifyDataSetChanged();
        }
    }

    public class SectorViewHolder extends RecyclerView.ViewHolder {
        private Switch swtEnabled;
        private TextView txvName, txvSectorDefault;

        public SectorViewHolder(View itemView) {
            super(itemView);
            swtEnabled = itemView.findViewById(R.id.swtSector);
            txvName = itemView.findViewById(R.id.txvSectorName);
            txvSectorDefault = itemView.findViewById(R.id.txvSectorDefault);
        }

        public void bind(Sector sector, OnItemActionListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(sector);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(sector);
                    return true;
                }
            });
        }
    }

}
