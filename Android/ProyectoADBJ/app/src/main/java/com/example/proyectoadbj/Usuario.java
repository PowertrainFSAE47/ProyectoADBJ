package com.example.proyectoadbj;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private String nombres,apellidos,password,genero,email;


    public Usuario(String nombres, String apellidos, String password, String genero) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario(){

    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombres);
        dest.writeString(this.apellidos);
        dest.writeString(this.password);
        dest.writeString(this.genero);
        dest.writeString(this.email);
    }

    protected Usuario(Parcel in) {
        this.nombres = in.readString();
        this.apellidos = in.readString();
        this.password = in.readString();
        this.genero = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
