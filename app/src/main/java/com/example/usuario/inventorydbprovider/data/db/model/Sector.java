package com.example.usuario.inventorydbprovider.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Clase POJO que representa la entidad sector
 *
 * @author Enrique Casielles
 * @version 2.0
 * @see Parcelable
 */
public class Sector implements Parcelable {
    public static final Creator<Sector> CREATOR = new Creator<Sector>() {
        @Override
        public Sector createFromParcel(Parcel in) {
            return new Sector(in);
        }

        @Override
        public Sector[] newArray(int size) {
            return new Sector[size];
        }
    };
    public static String TAG = "Sector";
    int _ID;
    int dependencyId;
    String name, shortname, description;
    String imageName;
    boolean enabled = true;     //Habilitados por defecto
    boolean sectorDefault = true;    //Se visualiza un TextView en el sector por defecto

    public Sector(int ID, int dependencyId, String name, String shortname, String description, String imageName, boolean enabled, boolean sectorDefault) {
        this._ID = ID;
        this.dependencyId = dependencyId;
        this.name = name;
        this.shortname = shortname;
        this.description = description;
        this.imageName = imageName;
        this.enabled = enabled;
        this.sectorDefault = sectorDefault;
    }

    protected Sector(Parcel in) {
        _ID = in.readInt();
        dependencyId = in.readInt();
        name = in.readString();
        shortname = in.readString();
        description = in.readString();
        imageName = in.readString();
        enabled = in.readByte() != 0;
        sectorDefault = in.readByte() != 0;
    }

    public int getID() {
        return _ID;
    }
    public void setID(int _ID) {
        this._ID = _ID;
    }
    public int getDependencyId() {
        return dependencyId;
    }
    public void setDependencyId(int _dependencyId) {
        this.dependencyId = _dependencyId;
    }
    public String getName() {
        return name;
    }
    public void setName(String _name) {
        this.name = _name;
    }
    public String getShortname() {
        return shortname;
    }
    public void setShortname(String _shortname) {
        this.shortname = _shortname;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String _description) {
        this.description = _description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean _enabled) {
        this.enabled = _enabled;
    }
    public boolean isDefault() {
        return sectorDefault;
    }
    public void setDefault(boolean _default) {
        this.sectorDefault = _default;
    }


    @Override
    public String toString() {
        return shortname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_ID);
        dest.writeInt(dependencyId);
        dest.writeString(name);
        dest.writeString(shortname);
        dest.writeString(description);
        dest.writeString(imageName);
        dest.writeByte((byte) (enabled ? 1 : 0));
        dest.writeByte((byte) (sectorDefault ? 1 : 0));
    }

}
