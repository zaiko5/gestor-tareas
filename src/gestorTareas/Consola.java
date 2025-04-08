//Donde se manejaran las entradas de datos
package gestorTareas;
import java.util.Scanner;

public class Consola {
    private static int opcion;
    private static Scanner sc = new Scanner(System.in);
    
    public static int menuPrincipal(){ //Funcion que te muestra el menu y retorna la opcion a seguir
        System.out.println("MENU");
        try{
            do{
                System.out.println("1. REGISTRAR NUEVA TAREA\n2. MOSTRAR TODAS LAS TAREAS\n3. FILTRAR TAREA POR USUARIO\n4. MARCAR TAREA COMO HECHA\n5. ELIMINAR TAREAS\n6. SALIR");
                opcion = sc.nextInt();
                sc.nextLine();
                if(opcion < 1 || opcion > 6){
                    System.out.println("Digita un valor valido");
                }
            }while(opcion < 1 || opcion > 6);
        }catch(Exception e){
            System.out.println("Digita un valor valido");
            sc.nextLine();
        }
        return opcion;
    }
    
    public static Tarea menuRegistrarTarea(){
        String descripcion, responsable;
        int dias = 0;
        System.out.println("REGISTRANDO TAREA\nDESCRIPCION: ");
        descripcion = sc.nextLine();
        System.out.println("RESPONSABLE: ");
        responsable = sc.nextLine();
        try{
            do{
                System.out.println("DIAS PARA REALIZAR LA TAREA: ");
                dias = sc.nextInt();
                sc.nextLine();
                if(dias < 1){
                    System.out.println("Digita un valor valido");
                }
            }while(dias < 1);
        }catch(Exception e){
            System.out.println("Digita un dia valido");
            sc.nextLine();
        }
        return new Tarea(descripcion,responsable,dias);
    }
}
