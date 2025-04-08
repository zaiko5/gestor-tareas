//Clase en donde se correra la aplicacion
package gestorTareas;
import static gestorTareas.App.app;
import java.sql.*;

public class Main {
    public static void main(String [] args){
        try{
            Class.forName("org.sqlite.JDBC"); //Conectando con el archivo jar de sqlite
            String url = "jdbc:sqlite:C:/Users/HP/Desktop/o/PROYECTOS/gestorTareas/tareasDB.db"; //Direccion url de la base de datos
            try (Connection conn = DriverManager.getConnection(url)) { //Representando la conexcion con un obejto connection.
                if (conn != null) { //Si la conexion funciona
                    app();
                    conn.close();
                }
            } catch(SQLException e) { //Atrapamos la excepcion del tipo SQL si no se pudo conectar con la DB
                System.out.println("Error al conectar la base de datos");
            }
        }catch(ClassNotFoundException e){
            System.out.println("Error al encontrar el driver de la BBDD");
        }
    }
}
