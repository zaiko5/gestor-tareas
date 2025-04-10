//Donde se manejará la logica de la app.
package gestorTareas;

import static gestorTareas.Consola.*;
import static gestorTareas.Consulta.*;
import java.sql.*;
import java.util.ArrayList;

public class App { //Funcion donde se manejará la logica del programa
    public static void app(Connection conn){
        int opcion;
        do{
            opcion = menuPrincipal(); //Se pide una opcion para realizar
            switch(opcion){
                case 1: //Registrar una nueva tarea
                    Tarea tarea = menuRegistrarTarea(); //Pedir los datos de la tarea
                    boolean existeUsuario = verificarExistenciaUsuario(conn,tarea); //Verificar si el usuario de la tarea existe en la BBDD
                    if(!existeUsuario){ //Si el usuario no existe, se registrará en ambas entidades.
                        registrarUsuario(conn,tarea); //Registrando al usuario en la BBDD
                    }
                    registrarTarea(conn,tarea); //Registrando la tarea en la BBDD.
                    System.out.println("Tarea registrada");
                    break;
                case 2: //Mostrar todas las tareas.
                    mostrarTodasTareas(conn);
                    break;
                case 3: //Mostrar tareas filtradas por usuarios
                    String usuario = pedirUsuario(); //Funcion que pide el nombre del usuario a filtrar
                    mostrarTareasFiltradas(conn,usuario); //Mostrar las tareas de ese usuario
                    break;
                case 4: //Marcar tarea como hecha.
                    boolean hayTareas = mostrarTareasNoHechas(conn); //Verificar si hay tareas no hechas
                    ArrayList<Integer> ids = retornarIDSNoHechos(conn); //Retornando una lista con los ids de las tareas no hechas
                    if(hayTareas){ //Si si hay tareas no hechas...
                        int tareaModificada = pedirTarea(ids); //Pedimos la entrada de la tarea no hecha
                        marcarTarea(conn,tareaModificada); //Marcamos la tarea como hecha.
                    }
                    break;
                case 5: //Eliminar tareas independientemente de su estado
                    System.out.println("Saliendo de la BBDD");
                    break;
            }
        }while(opcion != 5);
    }
}
