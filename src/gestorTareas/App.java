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
                    Tarea tarea = menuRegistrarTarea(); //Registrar una tarea.
                    boolean existeUsuario = verificarExistenciaUsuario(conn,tarea);
                    if(!existeUsuario){ //Si el usuario no existe, se registrará en ambas entidades.
                        registrarUsuario(conn,tarea);
                    }
                    registrarTarea(conn,tarea);
                    System.out.println("Tarea registrada");
                    break;
                case 2: //Mostrar todas las tareas.
                    mostrarTodasTareas(conn);
                    break;
                case 3: //Mostrar tareas filtradas por usuarios
                    String usuario = pedirUsuario();
                    mostrarTareasFiltradas(conn,usuario);
                case 4: //Marcar tarea como hecha.
                    mostrarTodasTareas(conn);
                    int contadorTareas = contarTareas(conn);
                    int tareaEliminada = pedirTarea(contadorTareas);
            }
        }while(opcion != 6);
    }
}
