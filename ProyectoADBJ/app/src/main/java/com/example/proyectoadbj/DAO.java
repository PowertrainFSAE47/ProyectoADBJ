package com.example.proyectoadbj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.widget.ArrayAdapter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class DAO extends SQLiteOpenHelper {

    //Nombre de la base de datos.
    private static String nomDB = "gymDB";

    // Todos los queries reposan estáticos en la clase queryDump.
    private queryDump q = new queryDump();

    // Fechas

    int day,year,month;


    public DAO(Context context) {
        super(context, nomDB, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Creacion de todas las tablas


        db.execSQL(q.createUsuarios);
        db.execSQL(q.createRoles);
        db.execSQL(q.createPlanes);
        db.execSQL(q.createAfiliaciones);
        db.execSQL(q.createTrainings);
        db.execSQL(q.createEventos);
        db.execSQL(q.createCalendario);
        db.execSQL(q.createLog);


        //Inicializacion de tablas.
        runSQlFromArray(q.initRoles, db);
        runSQlFromArray(q.initPlanes, db);
        runSQlFromArray(q.initUsuarios, db);
        runSQlFromArray(q.initTrainings, db);
        runSQlFromArray(q.initAfiliaciones, db);
        runSQlFromArray(q.initEventos, db);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        runSQlFromArray(q.dropTables, db);
    }

    // Data access object


    public void runSQlFromArray(String[] arr, SQLiteDatabase db) {

        // Esto arranca todas las queries en el array suministrado.
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
            db.execSQL(arr[i]);
        }
    }

    public boolean authLogin(String username, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor datos = db.rawQuery(q.authUser(username, pass), null);
        if (datos.moveToNext()) {
            return datos.getInt(0) == 1;
        } else {
            return false;
        }
    }

    public Usuario retrieveUser(String username) {

        Usuario user = new Usuario();

        // Obtener cursor.
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dataUsuario = db.rawQuery(q.getUser(username), null);
        if (dataUsuario.moveToNext()) {

            user.setNombres(dataUsuario.getString(1));
            user.setApellidos(dataUsuario.getString(2));
            user.setUsername(dataUsuario.getString(3));
            user.setPassword(dataUsuario.getString(4));
            user.setGenero(dataUsuario.getString(5));
            user.setPathFoto(dataUsuario.getString(6));
        }

        Subscripcion sub = getSubscripcionFromUsername(username);
        if (!(sub == null)) {
            // Usuario posee una subscripcion activa en la tabla afiliaciones
            user.setSubscripcion(sub);
        }

        return user;
    }

    public Subscripcion getSubscripcionFromUsername(String username) {

        Subscripcion sub = new Subscripcion();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor datos = db.rawQuery(q.getSubsFromUsername(username), null);

        if (datos.moveToNext()) {
            sub.setIdPlan(datos.getInt(0));
            sub.setNombrePlan(datos.getString(1));
            sub.setFechaInicio(datos.getString(2));
            sub.setFechaFin(datos.getString(3));
            sub.setPrecioAnual(datos.getDouble(4));
        }

        db.close();
        return sub;
    }

    public Subscripcion getPlanFromId(int idPlan){
        Subscripcion plan = new Subscripcion();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor datos = db.rawQuery(q.getPlanFromId(idPlan), null);

        // Crear objeto del plan nuevo

        if (datos.moveToNext()) {
            plan.setIdPlan(datos.getInt(0));
            plan.setNombrePlan(datos.getString(1));
            plan.setPrecioAnual(datos.getDouble(2));
            plan.setDescripcion(datos.getString(3));
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month= calendar.get(Calendar.MONTH)+1;
            day = calendar.get(Calendar.DAY_OF_MONTH);
            plan.setFechaInicio(day+"/"+month+"/"+year);
            plan.setFechaFin(day+"/"+month+"/"+(year+1));
        }

        return plan;
    }


    public boolean registerUser(Usuario user) {

        // registrar nuevo usuario

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contUsuario=new ContentValues();
        ContentValues contAfiliacion =new ContentValues();

        contUsuario.putNull("id");
        contUsuario.put("nombre",user.getNombres());
        contUsuario.put("apellidos",user.getApellidos());
        contUsuario.put("username",user.getUsername());
        contUsuario.put("password",user.getPassword());
        contUsuario.put("genero",user.getGenero());
        contUsuario.put("path_foto",user.getPathFoto());

        //Registrar usuario
        long flagU=db.insert("usuarios",null,contUsuario);

        if (flagU!=-1){
            // Usuario registrado, ahora afiliar
            contAfiliacion.putNull("id");
            contAfiliacion.put("id_usuario",getIdFromUsername(user.getUsername()));
            contAfiliacion.put("id_rol",1);
            contAfiliacion.put("id_plan",user.getSubscripcion().getIdPlan());
            contAfiliacion.put("desde",user.getSubscripcion().getFechaInicio());
            contAfiliacion.put("hasta",user.getSubscripcion().getFechaFin());

            long flagS=db.insert("afiliaciones",null,contAfiliacion);
            if (flagS!=-1){
                // usuario y plan registrados
                return true;
            }else{
                // No se pudo registrar el plan.
                return false;
            }
        }else{
            // El usuario no se pudo registrar, retornar.
            return false;
        }

    }


    public ArrayList<String> getColumn(String sql) {

        ArrayList<String> column = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor datos = db.rawQuery(sql, null);

        while (datos.moveToNext()) {

            String elemento = datos.getString(0);
            column.add(elemento);
        }
        return column;
    }

    public ArrayList<workoutEvent> getWorkoutEventList(String fecha, String tipoWorkout) {

        // Retorna un arraylist con todos los workoutEvent que satisfagan fecha y filtro.
        String sql = "";
        if (tipoWorkout.compareTo("Todas") == 0) {
            // Mostrar todos los eventos de ejercicio sin filtrar
            sql = "select eventos.id,hora_inicio,hora_fin,usuarios.nombre,usuarios.apellidos,trainings.nombre " +
                    "from eventos,usuarios,trainings " +
                    "where eventos.id_training=trainings.id " +
                    "and eventos.id_instructor=usuarios.id " +
                    "and eventos.fecha='" + fecha + "'";

        } else {
            //Mostrar los eventos filtrados por tipo (spinning,yoga,etc)
            sql = "select eventos.id,hora_inicio,hora_fin,usuarios.nombre,usuarios.apellidos,trainings.nombre " +
                    "from eventos,usuarios,trainings " +
                    "where eventos.id_training=trainings.id " +
                    "and eventos.id_instructor=usuarios.id " +
                    "and eventos.fecha='" + fecha + "'" +
                    "and trainings.nombre='" + tipoWorkout + "'";
        }
        ArrayList<workoutEvent> workouts = new ArrayList<>();

        // Conectar a DB y ejecutar consulta.
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor datos = db.rawQuery(sql, null);

        while (datos.moveToNext()) {

            workoutEvent w = new workoutEvent();
            w.setId(datos.getInt(0));
            w.setHoraInicio(datos.getString(1));
            w.setHoraFin(datos.getString(2));
            w.setEntrenador(datos.getString(3) + " " + datos.getString(4));
            w.setTraining(datos.getString(5));
            System.out.println("w.setTraining: " + datos.getString(5));
            workouts.add(w);
        }
        return workouts;

    }


    // Unirse a un workout event del calendario
    public int joinWorkoutEvent(workoutEvent workoutevent, String username) {

        // Si el usuario ya está registrado, retorna -1. Si se registra, retorna 1, si hay algún error, retorna 0
        // Conectar a DB.
        SQLiteDatabase db = this.getReadableDatabase();

        //Obtener id del usuario y del evento
        int idUsuario = getIdFromUsername(username);
        int idEvento = workoutevent.getId();

        // Verificar si el usuario ya está registrado para este evento
        String sql = q.checkUserOnCalendarEntry(idEvento, username);
        Cursor datos = db.rawQuery(sql, null);

        if (datos.moveToNext()) {
            if (datos.getInt(0) == 1) {
                // El usuario ya está registrado en este workout
                return -1;
            } else {
                // Usuario no está registrado, registramos
                try {
                    db.execSQL(q.insertCalendario(idEvento, idUsuario));
                    // Usuario registrado exitosamente en el calendario para un evento particular
                    return 1;
                } catch (Exception e) {
                    // Error al registrar usuario.
                    return 0;
                }
            }
        }
        return 0;
    }

    // Salirse de un workout event al que nos unimos
    public boolean unjoinWorkoutEvent(int idUsuario, int idEvento) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = q.deleteCalendarEntry(idUsuario, idEvento);
        System.out.println("Executing SQL: " + sql);

        try {
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    // Obtener el ID de un usermane particular.
    public int getIdFromUsername(String username) {

        // Obtener database
        SQLiteDatabase db = this.getReadableDatabase();
        //Obtener id del usuario
        Cursor datos = db.rawQuery(q.getIdFromUsername(username), null);

        if (datos.moveToNext()) {
            return datos.getInt(0);
        } else {
            return -1;
        }

    }

    // Retorna un arraylist de workoutEvent en los que está registrado el usuario
    public ArrayList<workoutEvent> getCalendarEvents(String username) {

        int idUsuario = getIdFromUsername(username);
        ArrayList<workoutEvent> workoutEventList = new ArrayList<>();
        // Obtener database
        SQLiteDatabase db = this.getReadableDatabase();
        //Obtener los campos de calendarios en donde id_usuario==idUsuario
        Cursor datos = db.rawQuery(q.getCalendarEntries(idUsuario), null);

        while (datos.moveToNext()) {

            workoutEvent workoutevent = new workoutEvent(
                    datos.getInt(0),
                    datos.getString(1),
                    datos.getString(2),
                    datos.getString(3) + " " + datos.getString(4),
                    datos.getString(5),
                    datos.getString(6));

            workoutEventList.add(workoutevent);
        }
        return workoutEventList;
    }

    public ArrayList<String> getPlanes() {

        ArrayList<String> listaPlanes = new ArrayList<>();
        // Obtener database
        SQLiteDatabase db = this.getReadableDatabase();
        //Obtener id del usuario
        Cursor datos = db.rawQuery(q.getListaPlanes(), null);

        while (datos.moveToNext()) {

            listaPlanes.add(datos.getString(0)+" --Costo anual: "
                    +UIHelpers.moneyFormatter((double)datos.getInt(1)));
        }
        return listaPlanes;
    }
}