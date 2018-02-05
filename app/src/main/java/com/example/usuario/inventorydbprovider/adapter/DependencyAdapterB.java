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
import com.example.usuario.inventorydbprovider.data.db.model.Dependency;
import com.example.usuario.inventorydbprovider.data.db.repository.DependencyRepository;
import com.github.ivbaranov.mli.MaterialLetterIcon;

/**
 * Clase Adapter que maneja dependencias.
 * Solución menos óptima que reutiliza los elementos de la lista
 * cuando esta se despliega para evitar crearlos de nuevo.
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 * @see ArrayAdapter
 * @see Dependency
 */
public class DependencyAdapterB extends ArrayAdapter<Dependency>{


    /**
     * Constructor de DependencyAdapter
     * @param context Contexto de la actividad.
     */
    public DependencyAdapterB(@NonNull Context context) {
        super(context, R.layout.item_dependency, DependencyRepository.getInstance().getDependencies());
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

        MaterialLetterIcon icon;
        TextView txvName, txvShortName;

        View view = convertView;

        //Se realiza las N primeras veces por cada elemento hasta que el N-ésimo llene la pantalla
        if(convertView == null) {
            //1. Obtener el servicio del sistema en el contexto con LayoutInflater
            //De esta forma accedo directamente al servicio específico
            //LayoutInflater inflater = LayoutInflater.from(getContext());

            //Forma no recomendada, porque se obliga al contexto a venir de una actividad
            //LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();

            //Accede de forma genérica al servicio del sistema para que infle la vista del objeto vista
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            //2. Inflar la vista. Crea en memoria el objeto View con todos los widget de item_dependency.xml
            //Con null indica que no hay que introducirlo en un nuevo padre
            view = inflater.inflate(R.layout.item_dependency, null);
        }

        //3. Inicializar las variables a los objetos ya creados de los widget del xml.
        icon = (MaterialLetterIcon) view.findViewById(R.id.mliIcon);
        txvName = (TextView) view.findViewById(R.id.txvElementName);
        txvShortName = (TextView) view.findViewById(R.id.txvElementShortName);


        //4. Mostrar los datos del ArrayList mediante position.
        icon.setLetter(getItem(position).getShortname().substring(0, 1));
        txvName.setText(getItem(position).getName());
        txvShortName.setText(getItem(position).getShortname());

        return view;
    }


}
