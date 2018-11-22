package kanban;

import java.util.*;

/**
 * 
 */
public interface Requisito {
	
	public Tarea anadirTarea(int id, String t, String d, float c, float b);
	public HashMap<Integer,Tarea> getTareas();
	public Integer getID();
	public String getTitulo();
	public String getDescripcion();
	
}