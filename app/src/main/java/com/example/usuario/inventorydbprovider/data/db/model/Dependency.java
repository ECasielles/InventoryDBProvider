package com.example.usuario.inventorydbprovider.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Clase POJO que representa la entidad dependencia
 *
 * @author Enrique Casielles
 * @version 2.0
 * @see java.lang.Comparable
 */
public class Dependency implements Comparable, Parcelable {
    //Forma alternativa de declarar los Comparator
    public static final Comparator<Dependency> COMPARATOR_ID = new Comparator<Dependency>() {
        @Override
        public int compare(Dependency dependency, Dependency dependency2) {
            return dependency.get_ID() > dependency2.get_ID() ? 1: -1;
        }
    };
    public static final Comparator<Dependency> COMPARATOR_ID_DESC = new Comparator<Dependency>() {
        @Override
        public int compare(Dependency dependency, Dependency dependency2) {
            return dependency.get_ID() < dependency2.get_ID() ? 1: -1;
        }
    };
    public static final Comparator<Dependency> COMPARATOR_NAME = new Comparator<Dependency>() {
        @Override
        public int compare(Dependency dependency, Dependency dependency2) {
            return dependency.getName().compareToIgnoreCase(dependency2.getName());
        }
    };
    public static final Comparator<Dependency> COMPARATOR_NAME_DESC = new Comparator<Dependency>() {
        @Override
        public int compare(Dependency dependency, Dependency dependency2) {
            return -1 * dependency.getName().compareToIgnoreCase(dependency2.getName());
        }
    };
    public static final Creator<Dependency> CREATOR = new Creator<Dependency>() {
        @Override
        public Dependency createFromParcel(Parcel in) {
            return new Dependency(in);
        }

        @Override
        public Dependency[] newArray(int size) {
            return new Dependency[size];
        }
    };
    public static String TAG = "Dependency";
    private int _ID;
    private String name;
    private String shortname;
    private String description;
    private String imageName;

    public Dependency(int _ID, String name, String shortname, String description, String imageName) {
        this._ID = _ID;
        this.name = name;
        this.shortname = shortname;
        this.description = description;
        this.imageName = imageName;
    }

    //CREATOR DEL PARCELABLE
    protected Dependency(Parcel in) {
        _ID = in.readInt();
        name = in.readString();
        shortname = in.readString();
        description = in.readString();
        imageName = in.readString();
    }

    public int get_ID() {
        return _ID;
    }
    public void set_ID(int _ID) {
        this._ID = _ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortname() {
        return shortname;
    }
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return shortname;
    }

    @Override
    public boolean equals(Object obj) {
        Dependency dependency = (Dependency) obj;
        boolean result = true;

        if (!name.equals(dependency.getName()))
            result = false;
        else if (!shortname.equals(dependency.getShortname()))
            result = false;

        return result;
    }

    /**
     * Implementando la interfaz Comparable.
     * @param o
     * @return Valor menor/mayor que 0 si es anterior/posterior,
     * e igual a 0 si son iguales
     */
    @Override
    public int compareTo(@NonNull Object o) {
        return name.compareTo(((Dependency)o).name);
    }

    //IMPLEMENTANDO PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_ID);
        parcel.writeString(name);
        parcel.writeString(shortname);
        parcel.writeString(description);
        parcel.writeString(imageName);
    }

    /**
     * Clase interna de Dependency que ordena la lista de dependencias
     * por nombre corto.
     *
     * @author Enrique Casielles Lapeira
     * @version 1.0
     * @see java.util.Comparator
     */
    public static class DependencyOrderByShortName implements Comparator<Dependency> {
        /**
         * El ArrayList se ordena según el/los criterio/s
         * del método compartido de la interfaz Comparable
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(Dependency o1, Dependency o2) {
            return o1.getShortname().compareTo(o2.getShortname());
        }
    }
    /**
     * Clase interna de Dependency que ordena la lista de dependencias
     * por nombre.
     *
     * @author Enrique Casielles Lapeira
     * @version 1.0
     * @see java.util.Comparator
     */
    public static class DependencyOrderByName implements Comparator<Dependency> {
        /**
         * El ArrayList se ordena según el/los criterio/s
         * del método compartido de la interfaz Comparable
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(Dependency o1, Dependency o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    /**
     * Clase interna de Dependency que ordena la lista de dependencias
     * por ID.
     *
     * @author Enrique Casielles Lapeira
     * @version 1.0
     * @see java.util.Comparator
     */
    public static class DependencyOrderById implements Comparator<Dependency> {
        /**
         * El ArrayList se ordena según el/los criterio/s
         * del método compartido de la interfaz Comparable
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(Dependency o1, Dependency o2) {
            //Devuelve positivo si el objeto es mayor que el argumento
            return o1.get_ID() - o2.get_ID() ;
        }
    }

}
