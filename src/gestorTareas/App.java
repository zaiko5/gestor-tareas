//Donde se manejará la logica de la app.
package gestorTareas;

import static gestorTareas.Consola.*;

public class App {
    public static void app(){
        int opcion;
        do{
            opcion = menuPrincipal();
        }while(opcion != 6);
    }
}
