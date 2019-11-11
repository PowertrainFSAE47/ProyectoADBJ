package com.example.proyectoadbj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DAO extends SQLiteOpenHelper {

    //Nombre de la base de datos.
    private static String nomDB="gymDB";

    // Todos los queries reposan estáticos en la clase queryDump.
    private queryDump q=new queryDump();


    public DAO(Context context) {
        super(context, nomDB, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Creacion de todas las tablas

        db.execSQL(q.createRoles);
        db.execSQL(q.createPlanes);
        db.execSQL(q.createTipos);
        db.execSQL(q.createTrainings);
        db.execSQL(q.createUsuarios);
        db.execSQL(q.createClases);
        db.execSQL(q.createCalendario);

        //Inicializacion de tablas.
        runSQlFromArray(q.initRoles,db);
        runSQlFromArray(q.initPlanes,db);
        runSQlFromArray(q.initTipos,db);
        runSQlFromArray(q.initUsuarios,db);
        runSQlFromArray(q.initTrainings,db);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        runSQlFromArray(q.dropTables,db);
    }

    // Data access object


    public void runSQlFromArray(String[] arr,SQLiteDatabase db){

        // Esto arranca todas las queries en el array suministrado.
        for (int i = 0; i < arr.length ; i++) {
            db.execSQL(arr[i]);
        }
    }

    public boolean checkUser(String user, String pass)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT COUNT (ID) FROM USUARIOS WHERE USUARIOS.USERNAME='"+user+"' AND USUARIOS.PASSWORD="+pass;
        Cursor datos=db.rawQuery(sql,null);

        if (datos.moveToNext()) {
            return datos.getInt(0) == 1;
        }else{
            return false;
        }
    }

    public Usuario retrieveUser(String username){

        Usuario user=new Usuario();

        // Obtener cursor.
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="select * from usuarios where usuarios.username='"+username+"'";

        Cursor datos=db.rawQuery(sql,null);

        if (datos.moveToNext()) {

            user.setNombres(datos.getString(1));
            user.setApellidos(datos.getString(2));
            user.setUsername(datos.getString(3));
            user.setPassword(datos.getString(4));
            user.setGenero(datos.getString(5));
            user.setEmail(datos.getString(6));
            user.setPathFoto(datos.getString(7));
        }

        return user;

    }

}
