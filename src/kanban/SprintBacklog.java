package kanban;

import java.util.*;

/**
 * 
 */
public class SprintBacklog implements Backlog {

	private HashMap<Integer,Tarea> todo;
	private HashMap<Integer,Tarea> doing;
	private HashMap<Integer,Tarea> testing;
	private HashMap<Integer,Tarea> finished;
    /**
     * Default constructor
     */
    public SprintBacklog() {
    	todo = new HashMap<Integer,Tarea>();
    	doing = new HashMap<Integer,Tarea>();
    	testing = new HashMap<Integer,Tarea>();
    	finished = new HashMap<Integer,Tarea>();
    }

    @Override
	public void anadirTarea(Tarea t) {
		todo.put(t.id, t);
	}
    
    public void moveraDoing(int id) {
    	doing.put(id, todo.get(id));
    }
    
    public void moveraTesting(int id) {
    	testing.put(id, doing.get(id));
    }
    
    public void moveraFinished(int id) {
    	finished.put(id, testing.get(id));
    }

    public ProductBacklog finalizarSprint(ProductBacklog pb) {
    	pb.anadirConjuntoTareas(todo);
    	pb.anadirConjuntoTareas(doing);
    	pb.anadirConjuntoTareas(testing);
    	return pb;
    }

}