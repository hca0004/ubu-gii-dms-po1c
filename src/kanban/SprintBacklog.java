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
		todo.put(t.getID(), t);
	}
    
    public boolean moveraDoing(int id) {
    	if(todo.containsKey(id) && todo.get(id).getMiembro()!=null) {
    		doing.put(id, todo.get(id));
    		todo.remove(id);
    		return true;
    	}
    	return false;
    }
    
    public boolean moveraTesting(int id) {
    	if(doing.containsKey(id)) {
    		testing.put(id, doing.get(id));
    		doing.remove(id);
    		return true;
    	}
    	return false;
    }
    
    public boolean moveraFinished(int id) {
    	if(testing.containsKey(id)) {
    		finished.put(id, testing.get(id));
    		testing.remove(id);
    		return true;
    	}
    	return false;
    }

    public ProductBacklog finalizarSprint(ProductBacklog pb) {
    	pb.anadirConjuntoTareas(todo);
    	pb.anadirConjuntoTareas(doing);
    	pb.anadirConjuntoTareas(testing);
    	return pb;
    }
    
    public HashMap<Integer,Tarea> getTareasTodo(){
    	return todo;
    }

	public HashMap<Integer, Tarea> getDoing() {
		return doing;
	}

	public HashMap<Integer, Tarea> getTesting() {
		return testing;
	}

	public HashMap<Integer, Tarea> getFinished() {
		return finished;
	}

}