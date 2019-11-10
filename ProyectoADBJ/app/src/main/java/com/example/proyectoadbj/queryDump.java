package com.example.proyectoadbj;

public class queryDump {

    public queryDump() {
    }

    //Nombre de la base de datos.
    public String nomDB="gymDB";
    // Queries de creacion de roles.

    public String createRoles = "CREATE TABLE 'roles' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL )";
    public String createPlanes = "CREATE TABLE 'planes' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL, 'precio_anual' INTEGER NOT NULL, 'descripcion' TEXT )";
    public String createTipos = "CREATE TABLE 'tipos' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL )";
    public String createTrainings = "CREATE TABLE 'trainings' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL, 'id_tipo' INTEGER NOT NULL, 'path_foto' TEXT NOT NULL, FOREIGN KEY('id_tipo') REFERENCES 'tipos'('id') )";

    public String createUsuarios = "CREATE TABLE 'usuarios' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL, 'username' TEXT NOT NULL, 'password' INTEGER NOT NULL" +
            ", 'path_foto' TEXT NOT NULL, 'id_rol' INTEGER NOT NULL, 'id_plan' INTEGER NOT NULL, FOREIGN KEY('id_rol') REFERENCES 'roles'('id'), FOREIGN KEY('id_plan') REFERENCES 'planes'('id') )";

    public String createClases = "CREATE TABLE 'clases' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'fecha' TEXT NOT NULL, 'hora_inicio' INTEGER NOT NULL, 'hora_fin' INTEGER NOT NULL," +
            " 'id_training' INTEGER NOT NULL, 'id_usuarios' INTEGER NOT NULL, FOREIGN KEY('id_training') REFERENCES 'trainings'('id'), FOREIGN KEY('id_usuarios') REFERENCES 'usuarios'('id') )";

    public String createCalendario = "CREATE TABLE 'calendario' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'id_clase' INTEGER NOT NULL, 'id_usuario' INTEGER NOT NULL, FOREIGN KEY('id_usuario') REFERENCES 'usuarios'('id'), FOREIGN KEY('id_clase') REFERENCES 'clases'('id') )";


    // Queries de inicializacion de tablas

    // Tabla roles

    public String[] initRoles = {
            "insert into roles values (null,'usuario')",
            "insert into roles values (null,'trainer')",
            "insert into roles values (null,'admin')"
    };

    // Tabla planes
    public String[] initPlanes = {

            "insert into planes values (null, 'Mr Olympia Plus',100000,'Plan primer nivel')",
            "insert into planes values (null, 'Mr Olympia',80000,'Plan segundo nivel')",
            "insert into planes values (null, 'John Rambo',50000,'Plan tercer nivel')",
            "insert into planes values (null, 'Transporter',30000,'Plan cuarto nivel')",
            "insert into planes values (null, 'Remy Gaillard',15000,'Plan penca')"
    };

    // Tabla Tipos

    public String[] initTipos= {
            "insert into tipos values (null,'Personalizado')",
            "insert into tipos values (null,'Grupal')",
    };

    public String[] initUsuarios={

            "insert into usuarios values (null,'Arnold Schwarzenegger','terminator2',123,'drawable/1',2,1)",
            "insert into usuarios values (null,'Sylvester Stallone','italianstallion',456,'drawable/2',2,1)",
            "insert into usuarios values (null,'John Rambo','johnsalchichon',789,'drawable/3',1,1)",
            "insert into usuarios values (null,'Jason Statham','transporter86',123,'drawable/4',2,4)",
            "insert into usuarios values (null,'El Bananero','acm1pt',123,'drawable/4',2,4)"
    };

    public String[] initTrainings= {

            "insert into trainings values (null, 'Pesas',1,'drawable/t1')",
            "insert into trainings values (null, 'Spinning',2,'drawable/t2')",
            "insert into trainings values (null, 'Yoga',2,'drawable/t3')",
            "insert into trainings values (null, 'Zumba',2,'drawable/t4')",
            "insert into trainings values (null, 'Aerobics',2,'drawable/t5')"
    };

}