//Donde se manejará la logica de la app.
package gestorTareas;

import static gestorTareas.Consola.*;

public class App {
    public static void app(){
        int opcion;
        do{
            opcion = menuPrincipal();
            switch(opcion){
                case 1:
                    Tarea tarea = menuRegistrarTarea();
                    System.out.println("Tarea registrada");
                    break;
            }
        }while(opcion != 6);
    }
}
