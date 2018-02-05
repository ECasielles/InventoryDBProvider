package com.example.usuario.inventorydbprovider.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.usuario.inventorydbprovider.R;
import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.data.db.repository.DependencyRepository;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;

/**
 * Clase Adapter que maneja dependencias
 *
 * @author Enrique Casielles Lapeira
 * @version 2.0
 * @see ArrayList
 * @see ArrayAdapter
 * @see Dependency
 * @see DependencyRepository
 */
public class DependencyAdapter extends ArrayAdapter<Dependency>{
    /**
     * Constructor de DependencyAdapter. Se crea una copia del ArrayList de
     * DependencyRepository para que la copia local del Adapter se pueda modificar
     * sin modificar el repositorio.
     * @param context Contexto de la actividad.
     */
    public DependencyAdapter(@NonNull Context context) {
        //super(context, R.layout.item_dependency, DependencyRepository.getInstance().loadDependencies());
        //sort(new Dependency.DependencyOrderByShortName());
        //Con esto los datos no se han inicializado, pero instanciamos el ArrayList interno
        super(context, R.layout.item_dependency, new ArrayList<>());
    }

    /**
     * Construye e infla la vista de cada elemento del ArrayList
     *
     * @param position Posición del elemento
     * @param convertView Objeto View que reutilizar cuando haya desplazamiento
     * @param parent Layout contenedor del elemento
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DependencyHolder dependencyHolder;
        View rootView = convertView;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.item_dependency, null);
            dependencyHolder = new DependencyHolder();
            dependencyHolder.mliIcon = rootView.findViewById(R.id.mliIcon);
            dependencyHolder.txvName = rootView.findViewById(R.id.txvElementName);
            dependencyHolder.txvShortName = rootView.findViewById(R.id.txvElementShortName);
            rootView.setTag(dependencyHolder);
        } else {
            dependencyHolder = (DependencyHolder) rootView.getTag();
        }
        dependencyHolder.mliIcon.setLetter(getItem(position).getShortname().substring(0, 1));
        dependencyHolder.txvName.setText(getItem(position).getName());
        dependencyHolder.txvShortName.setText(getItem(position).getShortname());
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/Beleren-Bold.ttf");
        dependencyHolder.txvShortName.setTypeface(typeface);
        //dependencyHolder.txvName.setTypeface(Typeface.defaultFromStyle(R.font.beleren_bold));
        return rootView;
    }

    class DependencyHolder {
        MaterialLetterIcon mliIcon;
        TextView txvName, txvShortName;
    }

}
