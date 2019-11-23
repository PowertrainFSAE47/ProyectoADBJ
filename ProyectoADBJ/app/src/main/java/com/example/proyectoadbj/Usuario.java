package com.example.proyectoadbj;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private int id;
    private String nombres,apellidos,password,genero,email,pathFoto,username;
    private Subscripcion subscripcion;

    public Usuario(){
        id=-1;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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



    public Subscripcion getSubscripcion() {
        return subscripcion;
    }

    public void setSubscripcion(Subscripcion subscripcion) {
        this.subscripcion = subscripcion;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombres);
        dest.writeString(this.apellidos);
        dest.writeString(this.password);
        dest.writeString(this.genero);
        dest.writeString(this.email);
        dest.writeString(this.pathFoto);
        dest.writeString(this.username);
        dest.writeParcelable(this.subscripcion, flags);
    }

    protected Usuario(Parcel in) {
        this.id = in.readInt();
        this.nombres = in.readString();
        this.apellidos = in.readString();
        this.password = in.readString();
        this.genero = in.readString();
        this.email = in.readString();
        this.pathFoto = in.readString();
        this.username = in.readString();
        this.subscripcion = in.readParcelable(Subscripcion.class.getClassLoader());
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
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
