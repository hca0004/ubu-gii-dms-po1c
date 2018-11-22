package kanban;

import java.util.*;

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

    public ProductBacklog getInstance() {
    	if(pb==null)
    		pb=new ProductBacklog();
    	return pb;	
    }
    
	@Override
	public void anadirTarea(Tarea t) {
		tareas.put(t.id, t);
	}
	
	public void anadirConjuntoTareas(HashMap<Integer,Tarea> d) {
		tareas.putAll(d);
	}
	
	public SprintBacklog moveraSB(SprintBacklog sb,int i) {
		sb.anadirTarea(tareas.get(i));
		return sb;
	}
	

}