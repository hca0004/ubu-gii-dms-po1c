package kanban;

import java.util.*;

/**
 * 
 */
public class Defecto implements Requisito {

	private int id;
	private String titulo;
	private String descripcion;
	private List<Tarea> tareas;
    /**
     * Default constructor
     */
    public Defecto(int id, String t, String d) {
    	this.id = id;
    	this.titulo = t;
    	this.descripcion = d;
    	this.tareas = new ArrayList<Tarea>();
    }

	@Override
	public Tarea anadirTarea(int id, String t, String d, float c, float b) {
		Tarea tar = new Tarea(this,id,t,d,c,b);
		tareas.add(tar);
		return tar;
	}

}