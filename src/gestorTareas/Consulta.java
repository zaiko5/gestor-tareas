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
                if(nombre != null && nombre.equalsIgnoreCase(tarea.getResponsable())){
                    return true;
                }
            }
            rs.close();
        }catch(SQLException e){
            System.out.println("Error " + e);
        }
        return false;
    }
    
    public static void registrarUsuario(Connection conn, Tarea tarea){ //Funcion que inserta al usuario en la entidad de usuarios si es que no 
        String q = "Insert into usuarios(nombre) values (?)";
        try(PreparedStatement stmt = conn.prepareStatement(q)){
            stmt.setString(1, tarea.getResponsable());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error: " + e);
        }
    }
    
    public static void registrarTarea(Connection conn, Tarea tarea){
        String q = "Insert into tareas(descripcion,idUsuario,fechaInicio,fechaFin,completada) values(?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(q)){
            stmt.setString(1, tarea.getDescripcion()); //Insertando todos los datos
            stmt.setString(2, tarea.getResponsable());
            stmt.setObject(3, tarea.getFechaInicial());
            stmt.setObject(4, tarea.getFechaFinal());
            stmt.setString(5, tarea.getEstado());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error: " + e);
        }
    }
}
