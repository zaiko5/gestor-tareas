//Para maneja las tareas
package gestorTareas;
import java.time.LocalDate;

public class Tarea {
    //Atributos de la clase tarea
    private String descripcion;
    private boolean estado;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private String responsable;

    public Tarea(String descripcion, String responsable, int dias) {
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.estado = false;
        this.fechaInicial = LocalDate.now(); //Obteniendo la fecha del dia de la tarea
        this.fechaFinal = this.fechaInicial.plusDays(dias); //Obteniendo la fecha final a partir de los dias ingresados
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }
    
    public LocalDate getFechaFinal(){
        return fechaFinal;
    }

    public String getResponsable() {
        return responsable;
    }
}
