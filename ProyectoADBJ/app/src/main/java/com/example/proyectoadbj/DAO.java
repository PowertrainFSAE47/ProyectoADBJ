package com.example.proyectoadbj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DAO extends SQLiteOpenHelper {

    //Nombre de la base de datos.
    private static String nomDB="gymDB";

    // Todos los queries reposan estáticos en la clase queryDump.
    private queryDump q=new queryDump();


    public DAO(Context context) {
        super(context, nomDB, null,3);
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
        runSQlFromArray(q.initRoles,db);
        runSQlFromArray(q.initPlanes,db);
        runSQlFromArray(q.initUsuarios,db);
        runSQlFromArray(q.initTrainings,db);
        runSQlFromArray(q.initAfiliaciones,db);
        runSQlFromArray(q.initEventos,db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        runSQlFromArray(q.dropTables,db);
    }

    // Data access object


    public void runSQlFromArray(String[] arr,SQLiteDatabase db){

        // Esto arranca todas las queries en el array suministrado.
        for (int i = 0; i < arr.length ; i++) {
            System.out.println(arr[i]);
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
            user.setPathFoto(datos.getString(6));
        }

        return user;

    }

    public String getPlan(String username)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="select planes.nombre from planes,usuarios,afiliaciones where planes.id=afiliaciones.id_plan and usuarios.id=afiliaciones.id_usuario and usuarios.username='"+username+"'";
        Cursor datos=db.rawQuery(sql,null);

        if (datos.moveToNext()) {
            String plan=datos.getString(0);
            //datos.close();
            return plan;
        }else{
            return "NO AFILIADO";
        }
    }


    public boolean registerUser(Usuario user) {

        SQLiteDatabase db=this.getWritableDatabase();

        String sql="insert into usuarios values (null,'"
                +user.getNombres()+"','"
                +user.getApellidos()+"','"
                +user.getUsername()+"','"
                +user.getPassword()+"','"
                +user.getGenero()+"','"
                +user.getPathFoto()+"')";
        try {
            System.out.println("Registrando usuario: "+sql);
            db.execSQL(sql);
            return true;
        }catch(Exception e){
            return false;
        }
    }


    public ArrayList<String> getColumn(String sql){

        ArrayList<String> column=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor datos=db.rawQuery(sql,null);

        while (datos.moveToNext()) {

            String elemento=datos.getString(0);
            System.out.println("getColumn iteration: "+datos.getString(0));
            column.add(elemento);
        }
        //datos.close();
        return column;
    }

    public ArrayList<String> getConcatRows(String sql,String[] sep){

        ArrayList<String> rows=new ArrayList<>();
        String row;

        // Conectar a DB
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor datos=db.rawQuery(sql,null);

        System.out.println("Encontrados "+datos.getCount()+" registros");
        while (datos.moveToNext()) {
            row="";
            for (int i = 0; i < datos.getColumnCount(); i++) {
                row=row+sep[i]+datos.getString(i);
            }
            System.out.println(row);
            rows.add(row);
        }
        return rows;
    }
}
