package com.example.proyectoadbj;

import android.os.Parcel;
import android.os.Parcelable;

public class workoutEvent implements Parcelable {


    private int id;
    private String horaInicio,horaFin,entrenador,training,fecha;

    public workoutEvent(){

    }

    public workoutEvent(int id, String horaInicio, String horaFin, String entrenador, String training, String fecha) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.entrenador = entrenador;
        this.training = training;
        this.fecha = fecha;
    }

    /*public workoutEvent workoutFromDescription(String description){
        String separadores[] ={", de ", " a ", " con: "};
        return new workoutEvent()
    }*/

    public String workoutDescription(){
        String separadores[] ={", de ", " a ", " con: "};

        String des=training+separadores[0]+horaInicio
                +separadores[1]+horaFin
                +separadores[2]+entrenador;
        System.out.println("workoutEvent::workoutDescription " + des);
        return des;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.horaInicio);
        dest.writeString(this.horaFin);
        dest.writeString(this.entrenador);
        dest.writeString(this.training);
        dest.writeString(this.fecha);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    protected workoutEvent(Parcel in) {
        this.id = in.readInt();
        this.horaInicio = in.readString();
        this.horaFin = in.readString();
        this.entrenador = in.readString();
        this.training = in.readString();
        this.fecha = in.readString();
    }

    public static final Creator<workoutEvent> CREATOR = new Creator<workoutEvent>() {
        @Override
        public workoutEvent createFromParcel(Parcel source) {
            return new workoutEvent(source);
        }

        @Override
        public workoutEvent[] newArray(int size) {
            return new workoutEvent[size];
        }
    };
}
