package com.example.proyectoadbj;

public class queryDump {



    public queryDump() {
    }

    //Nombre de la base de datos.
    public String nomDB="gymDB";
    // Queries de creacion de roles.

    public String createUsuarios = "CREATE TABLE 'usuarios' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL, 'apellidos' TEXT NOT NULL, 'username' TEXT NOT NULL, 'password' TEXT NOT NULL, 'genero' TEXT NOT NULL, 'email' TEXT NOT NULL, 'path_foto' TEXT NOT NULL)";
    public String createRoles = "CREATE TABLE 'roles' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL )";
    public String createPlanes = "CREATE TABLE 'planes' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL, 'precio_anual' INTEGER NOT NULL, 'descripcion' TEXT )";
    public String createAfiliaciones="CREATE TABLE 'afiliaciones' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'id_usuario' INTEGER NOT NULL, 'id_rol' INTEGER NOT NULL, 'id_plan' INTEGER NOT NULL, 'desde' TEXT NOT NULL, 'hasta' TEXT NOT NULL, FOREIGN KEY('id_usuario') REFERENCES 'usuarios'('id'), FOREIGN KEY('id_plan') REFERENCES 'planes'('id'), FOREIGN KEY('id_rol') REFERENCES 'roles'('id') )";
    public String createTrainings = "CREATE TABLE 'trainings' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL, 'path_foto' TEXT NOT NULL )";
    public String createEventos="CREATE TABLE 'eventos' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'fecha' TEXT NOT NULL, 'hora_inicio' TEXT NOT NULL, 'hora_fin' TEXT NOT NULL, 'max_personas' INTEGER NOT NULL DEFAULT 1, 'id_training' INTEGER NOT NULL, 'id_instructor' INTEGER NOT NULL, FOREIGN KEY('id_instructor') REFERENCES 'usuarios'('id'), FOREIGN KEY('id_training') REFERENCES 'trainings'('id') )";
    public String createCalendario = "CREATE TABLE 'eventos' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'fecha' TEXT NOT NULL, 'hora_inicio' TEXT NOT NULL, 'hora_fin' TEXT NOT NULL, 'max_personas' INTEGER NOT NULL DEFAULT 1, 'id_training' INTEGER NOT NULL, 'id_instructor' INTEGER NOT NULL, FOREIGN KEY('id_instructor') REFERENCES 'usuarios'('id'), FOREIGN KEY('id_training') REFERENCES 'trainings'('id') )";

    public String createLog="";
    // Queries de inicializacion de tablas

    // Tabla roles

    public String[] initRoles = {
            "insert into roles values (null,'usuario')",
            "insert into roles values (null,'trainer')",
            "insert into roles values (null,'admin')"
    };

    // Tabla planes
    public String[] initPlanes = {

            "insert into planes values (null, 'Mr Olympia Plus',100000,'Acceso nacional sin restricciones, incluye piscinas')",
            "insert into planes values (null, 'Mr Olympia',80000,'Acceso nacional sin piscinas')",
            "insert into planes values (null, 'John Rambo',50000,'Acceso regional')",
            "insert into planes values (null, 'Transporter',30000,'Solo maquinas, sin trainers')",
            "insert into planes values (null, 'Remy Gaillard',15000,'Puedes pasar a saludar a la recepcionista')"
    };
    // Tabla Tipos

    public String[] initUsuarios={

            "insert into usuarios values (null,'Arnold', 'Schwarzenegger','terminator2',123,'hombre','awhnold@califohnia.gov','img1','555-555-555')",
            "insert into usuarios values (null,'Sylvester', 'Stallone','italianstallion',456,'hombre','sly@rambo.com','img2','555-555-555')",
            "insert into usuarios values (null,'Gordon', 'Freeman','halflifer',123,'hombre','gordon.freeman@blackmesa.gov','img3','555-555-555')",
            "insert into usuarios values (null,'Jason', 'Statham','transporter86',123,'hombre','baldguy@hitman.com','img4','555-555-555')",
            "insert into usuarios values (null,'Adrian','Nario','elbananero',123,'hombre','lanegranoshace2x1@bananero.net','img5','555-555-555');"
    };

    public String[] initTrainings= {

            "insert into trainings values (null, 'Pesas',1,'tr1')",
            "insert into trainings values (null, 'Spinning',2,'tr2')",
            "insert into trainings values (null, 'Yoga',2,'tr3')",
            "insert into trainings values (null, 'Zumba',2,'tr4')",
            "insert into trainings values (null, 'Aerobics',2,'tr5')"
    };

    public String[] dropTables= {
            "DROP TABLE IF EXISTS USUARIOS",
    };

    public String[] initAfiliaciones = {

            "insert into afiliaciones values (null, 1,2,1,'15-10-2019','15-10-2020')",
            "insert into afiliaciones values (null, 2,1,2,'15-10-2019','15-10-2020')",
            "insert into afiliaciones values (null, 3,1,3,'15-10-2019','15-10-2020')",
            "insert into afiliaciones values (null, 4,1,5,'15-10-2019','15-10-2020')",
            "insert into afiliaciones values (null, 5,3,1,'15-10-2019','15-10-2020')",
    };

}