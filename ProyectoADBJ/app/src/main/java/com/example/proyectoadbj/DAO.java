package com.example.proyectoadbj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DAO extends SQLiteOpenHelper {

    //Nombre de la base de datos.
    private static String nomDB = "gymDB";

    // Todos los queries reposan estáticos en la clase queryDump.
    private queryDump q = new queryDump();


    public DAO(Context context) {
        super(context, nomDB, null, 3);
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

    public boolean checkUser(String user, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT COUNT (ID) FROM USUARIOS WHERE USUARIOS.USERNAME='" + user + "' AND USUARIOS.PASSWORD=" + pass;
        Cursor datos = db.rawQuery(sql, null);

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
        String sql = "select * from usuarios where usuarios.username='" + username + "'";

        Cursor datos = db.rawQuery(sql, null);

        if (datos.moveToNext()) {

            user.setNombres(datos.getString(1));
            user.setApellidos(datos.getString(2));
            user.setUsername(datos.getString(3));
            user.setPassword(datos.getString(4));
            user.setGenero(datos.getString(5));
            user.setPathFoto(datos.getString(6));
        }

        return user;

    }

    public String getPlan(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select planes.nombre from planes,usuarios,afiliaciones where planes.id=afiliaciones.id_plan and usuarios.id=afiliaciones.id_usuario and usuarios.username='" + username + "'";
        Cursor datos = db.rawQuery(sql, null);

        if (datos.moveToNext()) {
            String plan = datos.getString(0);
            //datos.close();
            return plan;
        } else {
            return "NO AFILIADO";
        }
    }


    public boolean registerUser(Usuario user) {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "insert into usuarios values (null,'"
                + user.getNombres() + "','"
                + user.getApellidos() + "','"
                + user.getUsername() + "','"
                + user.getPassword() + "','"
                + user.getGenero() + "','"
                + user.getPathFoto() + "')";
        try {
            System.out.println("Registrando usuario: " + sql);
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public ArrayList<String> getColumn(String sql) {

        ArrayList<String> column = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor datos = db.rawQuery(sql, null);

        while (datos.moveToNext()) {

            String elemento = datos.getString(0);
            System.out.println("getColumn iteration: " + datos.getString(0));
            column.add(elemento);
        }
        //datos.close();
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

    public boolean checkIfUserOnCalendarEntry(String fecha, workoutEvent workoutevent, String activeUsername) {

        // Agregar al calendario un evento workout

        // Conectar a DB y ejecutar consulta.
        SQLiteDatabase db = this.getReadableDatabase();

        // Contamos la cantidad de personas
        String sql = q.checkUserOnCalendarEntry(workoutevent.getId(), activeUsername);

        Cursor datos = db.rawQuery(sql, null);

        if (datos.moveToNext()) {
            if (datos.getInt(0) == 1) {
                // El usuario ya está registrado en este workout
                return true;
            }
        }
        // Esta usuario no está registrado en el workout
        return false;
    }

    public int joinEvent(workoutEvent workoutevent, String username) {

        // Si el usuario ya está registrado, retorna -1. Si se registra, retorna 1, si hay algún error, retorna 0
        // Conectar a DB.
        SQLiteDatabase db = this.getReadableDatabase();

        //Obtener id del usuario y del evento
        int idUsuario = getIdFromUsuario(username);
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

    public int getIdFromUsuario(String activeUserName) {

        // Obtener database
        SQLiteDatabase db = this.getReadableDatabase();
        //Obtener id del usuario
        Cursor datos = db.rawQuery(q.getUsernameId(activeUserName), null);

        if (datos.moveToNext()) {
            return datos.getInt(0);
        } else {
            return -1;
        }

    }

    public void getCalendarEvents(){

    }


}
