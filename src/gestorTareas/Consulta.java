//Donde se manejaran las consultas a SQL
package gestorTareas;
import java.sql.*;
import java.util.ArrayList;

public class Consulta { //Funcion que verifica si ya existe el usuario de la tarea nueva en la tabla de usuarios
    public static boolean verificarExistenciaUsuario(Connection conn, Tarea tarea){
    String q = "Select nombre from usuarios"; // Solo necesitamos el nombre
    try (PreparedStatement stmt = conn.prepareStatement(q)) {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            if (nombre != null && nombre.equalsIgnoreCase(tarea.getResponsable())) {
                return true; // El usuario ya existe.
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
        return false; // El usuario no existe.
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
    
    public static void mostrarTareasFiltradas(Connection conn, String usuario){ //Funcion para mostrar las tareas en relacion a un usuario
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
    
    public static boolean mostrarTareasNoHechas(Connection conn){ //Funcion para mostrar las tareas no hechas
        String q = "Select t.idTarea as id, t.descripcion as tarea,u.nombre as nombre,t.fechaInicio as inicio,t.fechaFin as final,t.completada as estado from tareas t "
                + "join usuarios u on u.idUsuario = t.idUsuario where t.completada = ?";
        try(PreparedStatement stmt = conn.prepareStatement(q)){
            String estado = "No hecha";
            stmt.setString(1,estado);
            ResultSet rs = stmt.executeQuery();
            boolean hayTareas = false;
            System.out.println("TAREAS");
            while(rs.next()){ //Si hay tareas... se muestra la tarea.
                hayTareas = true; //El booleano hayTareas se vuelve true.
                int id = rs.getInt("id");
                String tarea = rs.getString("tarea");
                String nombre = rs.getString("nombre");
                String fechaInicio = rs.getString("inicio");
                String fechaFin = rs.getString("final");//Y se muestra la tarea.
                System.out.println("ID: " + id + " || Tarea: " + tarea + " || Responsable: " + nombre + " || Fecha inicio: " + fechaInicio + " || Fehca fin: " + fechaFin + " || Estado: " + estado);
            }
            if(!hayTareas){ //Si no hay tareas se muestra el mensaje.
                System.out.println("No hay tareas para mostrar");
            }
            else{
                return hayTareas;
            }
        }catch(SQLException e){
            System.out.println("Error: " + e);
        }
        return false;
    }
    
    public static ArrayList retornarIDSNoHechos(Connection conn){ //Funcion que retorna el id de las tareas no hechas para mostrar al momento de querer eliminar una
        ArrayList<Integer> ids = new ArrayList<>();
        String q = "Select idTarea from tareas where completada = ?";
        try(PreparedStatement stmt = conn.prepareStatement(q)){
            String estado = "No hecha";
            stmt.setString(1,estado);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){ //Si hay tareas... se muestra la tarea.
                int id = rs.getInt("idTarea");
                ids.add(id);
            }
        }catch(SQLException e){
            System.out.println("Error: " + e);
        }
        return ids;
    }
    
    public static void marcarTarea(Connection conn, int tarea){ //Funcion que marca una tarea como hecha
        String q = "Update tareas set completada = ? where idTarea = ? ";
        try(PreparedStatement stmt = conn.prepareStatement(q)){
            String estado = "Hecha";
            stmt.setString(1, estado);
            stmt.setInt(2, tarea);
            stmt.executeUpdate();
            System.out.println("Tarea completada correctamente!");
        }catch(SQLException e){
            System.out.println("Error: " + e);
        }
    }
}
