package Persistencia;

import java.util.*;

import Backlogs.ProductBacklog;
import Backlogs.SprintBacklog;
import Miembros.MiembroDeEquipo;
import Requisitos.Requisito;
import Tareas.Tarea;

/**
 * 
 */
public interface Datos {

    /**
     * Default constructor
     */
	public static Datos getInstance() {
		return null;
	}
	
	public void cargarDB();
	public void guardarDB();
	

}