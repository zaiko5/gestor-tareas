//Donde se manejaran las consultas a SQL
package gestorTareas;
import java.sql.*;

public class Consulta { //Funcion que verifica si ya existe el usuario de la tarea nueva en la tabla de usuarios
    public static boolean verificarExistenciaUsuario(Connection conn, Tarea tarea){
        String q = "Select * from usuarios";
        try(PreparedStatement stmt = conn.prepareStatement(q)){
            ResultSet rs = stmt.executeQuery();
            if(rs.next() == false){
                return rs.next();
            }
            while(rs.next()){
                String nombre = rs.getString("nombre");
                if(nombre.toLowerCase() == tarea.getResponsable().toLowerCase()){
                    return true;
                }
            }
        }catch(SQLException e){
            System.out.println("Error " + e);
        }
        return false;
    }
}
