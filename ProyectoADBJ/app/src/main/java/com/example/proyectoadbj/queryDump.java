package com.example.proyectoadbj;

public class queryDump {

    public queryDump() {
    }

    //Nombre de la base de datos.
    public String nomDB="gymDB";
    // Queries de creacion de roles.

    public String createUsuarios = "CREATE TABLE 'usuarios' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL, 'apellidos' TEXT NOT NULL, 'username' TEXT NOT NULL, 'password' TEXT NOT NULL, 'genero' TEXT NOT NULL, 'path_foto' TEXT NOT NULL)";
    public String createRoles = "CREATE TABLE 'roles' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL )";
    public String createPlanes = "CREATE TABLE 'planes' ( 'id' INTEGER NOT NULL PRIMARY KEY, 'nombre' TEXT NOT NULL, 'precio_anual' INTEGER NOT NULL, 'descripcion' TEXT )";
    public String createAfiliaciones="CREATE TABLE 'afiliaciones' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'id_usuario' INTEGER NOT NULL, 'id_rol' INTEGER NOT NULL, 'id_plan' INTEGER NOT NULL, 'desde' TEXT NOT NULL, 'hasta' TEXT NOT NULL, FOREIGN KEY('id_usuario') REFERENCES 'usuarios'('id'), FOREIGN KEY('id_plan') REFERENCES 'planes'('id'), FOREIGN KEY('id_rol') REFERENCES 'roles'('id') )";
    public String createTrainings = "CREATE TABLE 'trainings' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT NOT NULL, 'path_foto' TEXT NOT NULL )";
    public String createEventos="CREATE TABLE 'eventos' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'fecha' TEXT NOT NULL, 'hora_inicio' TEXT NOT NULL, 'hora_fin' TEXT NOT NULL, 'max_personas' INTEGER NOT NULL, 'id_training' INTEGER NOT NULL, 'id_instructor' INTEGER NOT NULL, FOREIGN KEY('id_instructor') REFERENCES 'usuarios'('id'), FOREIGN KEY('id_training') REFERENCES 'trainings'('id') )";
    public String createCalendario = "CREATE TABLE 'calendario' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'id_evento' INTEGER NOT NULL, 'id_usuario' INTEGER NOT NULL, FOREIGN KEY('id_evento') REFERENCES 'eventos'('id'), FOREIGN KEY('id_usuario') REFERENCES 'usuarios'('id') )";

    public String createLog="CREATE TABLE 'log' ( 'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'descripcion' TEXT NOT NULL, 'fecha' TEXT NOT NULL )";
    // Queries de inicializacion de tablas

    // Tabla roles

    public String[] initRoles = {
            "insert into roles values (null,'usuario')",
            "insert into roles values (null,'trainer')",
            "insert into roles values (null,'admin')"
    };

    // Tabla planes
    public String[] initPlanes = {

            "insert into planes values (0, 'Mr Olympia Plus',100000,'Acceso nacional sin restricciones, incluye piscinas')",
            "insert into planes values (1, 'Mr Olympia',80000,'Acceso nacional sin piscinas')",
            "insert into planes values (2, 'John Rambo',50000,'Acceso regional')",
            "insert into planes values (3, 'Transporter',30000,'Solo maquinas, sin trainers')",
            "insert into planes values (4, 'Remy Gaillard',15000,'Puedes pasar a saludar a la recepcionista')"
    };
    // Tabla Tipos

    public String[] initUsuarios={

            "insert into usuarios values (null,'Arnold', 'Schwarzenegger','terminator2',123,'hombre','img1')",
            "insert into usuarios values (null,'Sylvester', 'Stallone','italianstallion',456,'hombre','img2')",
            "insert into usuarios values (null,'Gordon', 'Freeman','halflifer',123,'hombre','img3')",
            "insert into usuarios values (null,'Jason', 'Statham','transporter86',123,'hombre','img4')",
            "insert into usuarios values (null,'Adrian','Nario','elbananero',123,'hombre','img5');"
    };

    public String[] initTrainings= {

            "insert into trainings values (null, 'Todas','tr0')",
            "insert into trainings values (null, 'Pesas','tr1')",
            "insert into trainings values (null, 'Spinning','tr2')",
            "insert into trainings values (null, 'Yoga','tr3')",
            "insert into trainings values (null, 'Zumba','tr4')",
            "insert into trainings values (null, 'Aerobics','tr5')",
            "insert into trainings values (null, 'Calistenia','tr6')"
    };

    public String[] dropTables= {

            "DROP TABLE IF EXISTS CALENDARIO",
            "DROP TABLE IF EXISTS EVENTOS",
            "DROP TABLE IF EXISTS AFILIACIONES",
            "DROP TABLE IF EXISTS USUARIOS",
            "DROP TABLE IF EXISTS ROLES",
            "DROP TABLE IF EXISTS PLANES",
            "DROP TABLE IF EXISTS LOG",
            "DROP TABLE IF EXISTS TRAININGS",
    };

    public String[] initAfiliaciones = {

            "insert into afiliaciones values (null, 1,2,1,'15-10-2019','15-10-2020')",
            "insert into afiliaciones values (null, 2,1,2,'15-10-2019','15-10-2020')",
            "insert into afiliaciones values (null, 3,1,3,'15-10-2019','15-10-2020')",
            "insert into afiliaciones values (null, 4,1,5,'15-10-2019','15-10-2020')",
            "insert into afiliaciones values (null, 5,3,1,'15-10-2019','15-10-2020')",
    };

    public String[] initEventos = {

            //                          id,fecha,hora_inicio,hora_fin,id_training,id_instructor
            "insert into eventos values (null, '16/10/2019','14:00','15:00',2,2,1)",
            "insert into eventos values (null, '16/10/2019','16:00','18:00',5,2,2)",
            "insert into eventos values (null, '16/10/2019','19:00','21:00',22,3,3)",
            "insert into eventos values (null, '16/10/2019','14:00','15:00',12,3,4)",
            "insert into eventos values (null, '16/10/2019','16:00','18:00',5,4,5)",
            "insert into eventos values (null, '16/10/2019','19:00','21:00',22,5,5)",
            "insert into eventos values (null, '16/10/2019','14:00','15:00',12,5,2)",
            "insert into eventos values (null, '17/10/2019','16:00','18:00',5,2,2)",
            "insert into eventos values (null, '17/10/2019','19:00','21:00',22,3,3)",
            "insert into eventos values (null, '17/10/2019','14:00','15:00',12,3,4)",
            "insert into eventos values (null, '17/10/2019','16:00','18:00',5,4,5)",
            "insert into eventos values (null, '17/10/2019','19:00','21:00',22,5,5)",
            "insert into eventos values (null, '17/10/2019','14:00','15:00',12,5,2)",
            "insert into eventos values (null, '18/1/2020','14:00','15:00',10,6,4)",
            "insert into eventos values (null, '18/1/2020','14:00','15:00',10,6,2)",
            "insert into eventos values (null, '18/1/2020','14:00','15:00',10,6,1)"
    };



    public String getTrainings="select nombre from trainings";

    public String checkUserOnCalendarEntry(int idEvento, String activeUsername){

        /*
        Consulta para determinar si el usuario activeUsername está suscrito a un evento (workoutEvent)
        particular con id idEvento. Si retorna 1, el usuario está registrado, si retorna 0, no está
         */

        return "select count(*) from usuarios,eventos,calendario,trainings " +
                "where calendario.id_usuario=usuarios.id " +
                "and eventos.id=calendario.id_evento " +
                "and trainings.id=eventos.id_training " +
                "and calendario.id_evento="+idEvento+
                " and usuarios.username='"+activeUsername+"'";
    }
    public String getIdFromUsername(String activeUsername){
        // Retorna el id en base de datos del username activeUsername
        return "select id from usuarios where username='"+activeUsername+"'";
    }

    public String insertCalendario(int eventId,int idUsuario){
        return "insert into calendario values (null,"+eventId+","+idUsuario+")";
    }

    public String getCalendarEntries(int idUsuario){

        return "select eventos.id," +
                "eventos.hora_inicio," +
                "eventos.hora_fin," +
                "usuarios.nombre," +
                "usuarios.apellidos," +
                "trainings.nombre," +
                "eventos.fecha" +
                " from calendario,trainings,eventos,usuarios " +
                "where eventos.id=calendario.id_evento " +
                "and eventos.id_training=trainings.id " +
                "and eventos.id_instructor=usuarios.id " +
                "and calendario.id_usuario='"+idUsuario+"'";
    }
    public String deleteCalendarEntry(int idUsuario,int idEvento){
        return "delete from calendario where id_usuario="+idUsuario+" and id_evento="+idEvento+"";
    }


    public String getListaPlanes(){
        return "select nombre,precio_anual from planes";
    }


    public String getSubsFromUsername(String username){
        return "select planes.id,planes.nombre,afiliaciones.desde,afiliaciones.hasta,planes.precio_anual " +
                "from planes,afiliaciones,usuarios " +
                "where planes.id=afiliaciones.id_plan " +
                "and afiliaciones.id_usuario=usuarios.id " +
                "and usuarios.username='"+ username + "'";
    }
    public String getUser(String username){
        return "select * from usuarios where usuarios.username='" + username + "'";
    }

    public String authUser(String username, String pass){
        return "SELECT COUNT (ID) FROM USUARIOS " +
                "WHERE USUARIOS.USERNAME='" + username + "' AND USUARIOS.PASSWORD=" + pass;
    }

    public String getPlanFromId(int planId){
        return "select * from planes where planes.id='"+ planId + "'";
    }
}