package Backlogs;

import java.util.*;

import Tareas.Tarea;

/**
 * 
 */
public class ProductBacklog implements Backlog {

	private static ProductBacklog pb;
	private HashMap<Integer,Tarea> tareas;
    /**
     * Default constructor
     */
    private ProductBacklog() {
    	tareas = new HashMap<Integer,Tarea>();
    }

    public static ProductBacklog getInstance() {
    	if(pb==null)
    		pb=new ProductBacklog();
    	return pb;	
    }
    
	@Override
	public void anadirTarea(Tarea t) {
		tareas.put(t.getID(), t);
	}
	
	public void anadirConjuntoTareas(HashMap<Integer,Tarea> d) {
		tareas.putAll(d);
	}
	
	public SprintBacklog moveraSB(SprintBacklog sb, Tarea t) {
		sb.anadirTarea(t);
		tareas.remove(t.getID());
		return sb;
	}
	
	public HashMap<Integer,Tarea> getTareas(){
		return tareas;
	}
	

}