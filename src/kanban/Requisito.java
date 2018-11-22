package kanban;

import java.util.*;

/**
 * 
 */
public interface Requisito {
	
	public Tarea anadirTarea(int id, String t, String d, float c, float b);
	public List<Tarea> getTareas();
	
}