package com.example.proyectoadbj;

import android.os.Parcel;
import android.os.Parcelable;

public class Subscripcion implements Parcelable {

    // Subscripcion

    private int idPlan;

    private String nombrePlan,fechaInicio,fechaFin,descripcion;
    private double precioAnual;



    public Subscripcion(){
        idPlan=-1;
        nombrePlan="NO AFILIADO";
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioAnual() {
        return precioAnual;
    }

    public void setPrecioAnual(double precioAnual) {
        this.precioAnual = precioAnual;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idPlan);
        dest.writeString(this.nombrePlan);
        dest.writeString(this.fechaInicio);
        dest.writeString(this.fechaFin);
        dest.writeString(this.descripcion);
        dest.writeDouble(this.precioAnual);
    }

    protected Subscripcion(Parcel in) {
        this.idPlan = in.readInt();
        this.nombrePlan = in.readString();
        this.fechaInicio = in.readString();
        this.fechaFin = in.readString();
        this.descripcion = in.readString();
        this.precioAnual = in.readDouble();
    }

    public static final Creator<Subscripcion> CREATOR = new Creator<Subscripcion>() {
        @Override
        public Subscripcion createFromParcel(Parcel source) {
            return new Subscripcion(source);
        }

        @Override
        public Subscripcion[] newArray(int size) {
            return new Subscripcion[size];
        }
    };
}
