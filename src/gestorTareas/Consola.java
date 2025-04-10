//Donde se manejaran las entradas de datos
package gestorTareas;
import java.util.ArrayList;
import java.util.Scanner;

public class Consola {
    private static int opcion;
    private static Scanner sc = new Scanner(System.in);
    
    public static int menuPrincipal(){ //Funcion que te muestra el menu y retorna la opcion a seguir
        System.out.println("MENU");
        do{
            try{
                System.out.println("1. REGISTRAR NUEVA TAREA\n2. MOSTRAR TODAS LAS TAREAS\n3. FILTRAR TAREA POR USUARIO\n4. MARCAR TAREA COMO HECHA\n5. SALIR");
                opcion = sc.nextInt();
                sc.nextLine();
                if(opcion < 1 || opcion > 6){
                    System.out.println("Digita un valor valido");
                }
            }catch(Exception e){
                System.out.println("Digita un valor valido");
                sc.nextLine();
            }
        }while(opcion < 1 || opcion > 6);
        return opcion;
    }
    
    public static Tarea menuRegistrarTarea(){ //Funcion que pide los datos de la tarea a registrar
        String descripcion, responsable;
        int dias = 0;
        System.out.println("REGISTRANDO TAREA\nDESCRIPCION: ");
        descripcion = sc.nextLine();
        System.out.println("RESPONSABLE: ");
        responsable = sc.nextLine();
        do{
            try{
                System.out.println("DIAS PARA REALIZAR TAREA: ");
                dias = sc.nextInt();
                sc.nextLine();
                if(dias < 1){
                    System.out.println("Digita un dia valido");
                }
            }catch(Exception e){
                System.out.println("Digita un dia valido");
                sc.nextLine();
            }
        }while(dias < 1);
        return new Tarea(descripcion,responsable,dias);
    }
    
    public static String pedirUsuario(){ //Funcion que pide el nombre de un usuario.
        String usuario = "";
        do{
            System.out.println("FILTRAR TAREAS POR USUARIO\nEscribe el usuario que quieres que se busque: ");
            usuario = sc.nextLine();
            if(usuario.equals("")){
                System.out.println("Debes ingresar un valor");
            }
        }while(usuario.equals(""));
        return usuario;
    }
    
    public static int pedirTarea(ArrayList ids){ //Funcion que pide el id de una tarea
        do{
            try{
                System.out.println("Digita el numero de la tarea a marcar como hecha");
                opcion = sc.nextInt();
                if(!ids.contains(opcion)){ //Si la opcion digitada no existe en la lista de ids
                    System.out.println("Digita un valor correcto");
                }
            }catch(Exception e){
                System.out.println("Digita un valor correcto");
                sc.nextLine();
            }
        }while(!ids.contains(opcion));
    return opcion;
    }
}
