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
            stmt.setString(1, tarea.getResponsable().toUpperCase());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error: " + e);
        }
    }
    
    public static void registrarTarea(Connection conn, Tarea tarea){ //Funcion para registrar una tarea.
        String qUsuario = "Select idUsuario from usuarios where nombre = ?";
        String q = "Insert into tareas(descripcion,idUsuario,fechaInicio,fechaFin,completada) values(?,?,?,?,?)";
        try(PreparedStatement stmtUsuario = conn.prepareStatement(qUsuario)){
            stmtUsuario.setString(1,tarea.getResponsable().toUpperCase());
            ResultSet rsUsuario = stmtUsuario.executeQuery();
            if(rsUsuario.next()) {
                int id = rsUsuario.getInt("idUsuario"); // Ahora sí declarado correctamente

                try (PreparedStatement stmt = conn.prepareStatement(q)) {
                    stmt.setString(1, tarea.getDescripcion());
                    stmt.setInt(2, id); // Usamos setInt ya que es un entero
                    stmt.setObject(3, tarea.getFechaInicial());
                    stmt.setObject(4, tarea.getFechaFinal());
                    stmt.setString(5, tarea.getEstado());
                    stmt.executeUpdate();
                }
            }
        }catch(SQLException e){
            System.out.println("Error: " + e);
        }
    }
    
    public static void mostrarTodasTareas(Connection conn){ //Funcion para mostrar todas las tareas.
        String q = "Select t.descripcion as tarea,u.nombre as nombre,t.fechaInicio as inicio,t.fechaFin as final,t.completada as estado from tareas t "
                + "join usuarios u on u.idUsuario = t.idUsuario";
        try(PreparedStatement stmt = conn.prepareStatement(q)){
            ResultSet rs = stmt.executeQuery();
            boolean hayTareas = false;
            int i = 1;
            while(rs.next()){ //Si hay taras... se muestra la tarea.
                if (i == 1){
                    System.out.println("TAREAS");
                }
                hayTareas = true; //El booleano hayTareas se vuelve true.
                String tarea = rs.getString("tarea");
                String nombre = rs.getString("nombre");
                String fechaInicio = rs.getString("inicio");
                String fechaFin = rs.getString("final");
                String estado = rs.getString("estado"); //Y se muestra la tarea.
                System.out.println(i + ". Tarea: " + tarea + " || Responsable: " + nombre + " || Fecha inicio: " + fechaInicio + " || Fehca fin: " + fechaFin + " || Estado: " + estado);
                i++;
            }
            if(!hayTareas){ //Si no hay tareas se muestra el mensaje.
                System.out.println("No hay tareas para mostrar");
            }
        }catch(SQLException e){
            System.out.println("Error: " + e);
        }
    }
    
    public static void mostrarTareasFiltradas(Connection conn, String usuario){
        String q = "Select t.descripcion as tarea,u.nombre as nombre,t.fechaInicio as inicio,t.fechaFin as final,t.completada as estado from tareas t "
                + "join usuarios u on u.idUsuario = t.idUsuario where u.nombre = ?";
        try(PreparedStatement stmt = conn.prepareStatement(q)){
            stmt.setString(1, usuario.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            boolean hayTareas = false;
            int i = 1;
            while(rs.next()){ //Si hay tareas... se muestra la tarea.
                if (i == 1){
                    System.out.println("TAREAS");
                }
                hayTareas = true; //El booleano hayTareas se vuelve true.
                String tarea = rs.getString("tarea");
                String nombre = rs.getString("nombre");
                String fechaInicio = rs.getString("inicio");
                String fechaFin = rs.getString("final");
                String estado = rs.getString("estado"); //Y se muestra la tarea.
                System.out.println(i + ". Tarea: " + tarea + " || Responsable: " + nombre + " || Fecha inicio: " + fechaInicio + " || Fehca fin: " + fechaFin + " || Estado: " + estado);
                i++;
            }
            if(!hayTareas){ //Si no hay tareas se muestra el mensaje.
                System.out.println("El usuario no existe");
            }
        }catch(SQLException e){
            System.out.println("Error: " + e);
        }
    }
    
    public static int contarTareas(Connection conn){
        String q = "Select count(*) as contador from tareas where completada = ?";
        try(PreparedStatement stmt = conn.prepareStatement(q)){
            String estado = "no hecha";
            stmt.setString(1,estado);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("contador");
            }
        }catch(SQLException e){
            System.out.println("Error: " + e);
        }
        return 0;
    }
    
    public static void marcarTarea(Connection conn, int tarea){
        if(tarea == 0){
            return;
        }
        
    }
}
