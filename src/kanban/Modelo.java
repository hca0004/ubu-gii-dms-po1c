package kanban;

import java.util.*;

/**
 * 
 */
public class Modelo {

	
	private ProductBacklog pb;
	private SprintBacklog sb;
	private List<SprintBacklog> formersb;
	private HashMap<String,MiembroDeEquipo> miembros;
	private HashMap<Integer,Requisito> requisitos;
	private Datos db;

	private static Modelo mod;
    /**
     * Default constructor
     */
    private Modelo() {
    	
    }
    
    public static Modelo getInstance() {
    	if (mod==null)
    		mod = new Modelo();
    	return mod;
    }
    
    public void nuevoMiembro() {}
    
    public void nuevoRequisito() {}
    
    public void finalizarSprintBacklog() {}
    
    public void nuevaTarea() {}
    
    public void asignarMiembroTarea() {}
    
    public void modificarTarea() {}
    
    public void moverTareaPBaSB() {}
    
    public void moverTareasEnSB() {}
    
    public void moverTareaTodoDoing() {}
    
    public void moverTareaDoingQA() {}
    
    public void moverTareaQAFinished() {}
    
}