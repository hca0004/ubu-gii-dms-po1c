package Backlogs;

import java.util.*;

import Tareas.Tarea;

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