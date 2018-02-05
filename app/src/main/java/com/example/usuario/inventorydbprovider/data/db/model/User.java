package com.example.usuario.inventorydbprovider.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Clase POJO que representa la entidad usuario
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 */

public class User implements Parcelable {
    private int _ID;
    private String user;
    private String password;
    private String name;
    private String email;
    //Si tuviéramos más de dos permisos usaríamos una tabla de permisos
    //para gestionarlos según los tipos de usuario (que será otra tabla)
    //ACL/AAC: Lista de acceso de control/a la aplicación
    //private ArrayList<Permission> permission (creando mi propia clase Permission)
    private boolean isRoot;
    private boolean isManager;
    public static String TAG = "User";

    public User(int _ID, String user, String password, String name, String email, boolean isRoot, boolean isManager) {
        this._ID = _ID;
        this.user = user;
        this.password = password;
        this.name = name;
        this.email = email;
        this.isRoot = isRoot;
        this.isManager = isManager;
    }

    protected User(Parcel in) {
        _ID = in.readInt();
        user = in.readString();
        password = in.readString();
        name = in.readString();
        email = in.readString();
        isRoot = in.readByte() != 0;
        isManager = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int get_ID() {
        return _ID;
    }
    public void set_ID(int _ID) {
        this._ID = _ID;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isRoot() {
        return isRoot;
    }
    public void setRoot(boolean root) {
        isRoot = root;
    }
    public boolean isManager() {
        return isManager;
    }
    public void setManager(boolean manager) {
        isManager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user1 = (User) o;

        if (getUser().equals(user1.getUser())) return true;
        if (getEmail().equals(user1.getEmail())) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = getUser().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "_ID=" + _ID +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isRoot=" + isRoot +
                ", isManager=" + isManager +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_ID);
        parcel.writeString(user);
        parcel.writeString(password);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeByte((byte) (isRoot ? 1 : 0));
        parcel.writeByte((byte) (isManager ? 1 : 0));
    }
}
