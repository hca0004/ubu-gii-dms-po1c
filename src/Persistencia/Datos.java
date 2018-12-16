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
	
    public HashMap<Integer,Tarea> selectProductBacklog();
    public boolean updateProductBacklog(ProductBacklog productBacklog);
    
    public SprintBacklog selectSprintBacklogActual();
    public boolean updateSprintBacklogActual(SprintBacklog sprintBacklog);
    
    public List<SprintBacklog> selectSprintBacklog();
    public boolean updateSprintBacklog(List<SprintBacklog> sprintBacklog);
    
    public boolean updateUsuario(HashMap<String,MiembroDeEquipo> miembroDeEquipo);
    public HashMap<String,MiembroDeEquipo> selectMiembrosDeEquipo();

    public HashMap<Integer,Requisito> selectRequisitos();
    public boolean updateRequisito(HashMap<Integer, Requisito> requisito);
}