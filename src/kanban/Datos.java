package kanban;

import java.util.*;

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
	
    public ProductBacklog selectProductBacklog();
    public boolean updateProductBacklog(ProductBacklog productBacklog);
    
    public SprintBacklog selectSprintBacklogActual();
    public boolean updateSprintBacklogActual(SprintBacklog sprintBacklog);
    
    public List<SprintBacklog> selectSprintBacklog();
    public SprintBacklog selectSprintBacklog(int id);
    
    public boolean insertUsuario(MiembroDeEquipo miembroDeEquipo);
    public HashMap<String,MiembroDeEquipo> selectMiembrosDeEquipo();

    public HashMap<Integer,Requisito> selectRequisitos();
    public boolean insertRequisito(Requisito requisito);
    
    public boolean updateTarea();
}