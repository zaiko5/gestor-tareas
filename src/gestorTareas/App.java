//Donde se manejará la logica de la app.
package gestorTareas;

import static gestorTareas.Consola.*;
import static gestorTareas.Consulta.*;
import java.sql.*;

public class App {
    public static void app(Connection conn){
        int opcion;
        do{
            opcion = menuPrincipal();
            switch(opcion){
                case 1:
                    Tarea tarea = menuRegistrarTarea();
                    boolean existeUsuario = verificarExistenciaUsuario(conn,tarea);
                    if(!existeUsuario){ //Si el usuario no existe, se registrará en ambas entidades.
                        registrarUsuario(conn,tarea);
                        System.out.println("Usuario");
                    }
                    System.out.println("Ya existe");
                    System.out.println("Tarea registrada");
                    break;
            }
        }while(opcion != 6);
    }
}
