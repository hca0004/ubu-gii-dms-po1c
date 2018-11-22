package kanban;

import java.util.*;

/**
 * 
 */
public interface Datos {

    /**
     * Default constructor
     */
	public Datos getInstance();
	
    public ProductBacklog selectProductBacklog();
    public boolean updateProductBacklog(ProductBacklog productBacklog);
    
    public SprintBacklog selectSprintBacklogActual();
    public boolean updateSprintBacklogActual(SprintBacklog sprintBacklog);
    
    public List<SprintBacklog> selectSprintBacklog();
    public SprintBacklog selectSprintBacklog(int id);
    
    public MiembroDeEquipo insertUsuario(MiembroDeEquipo miembroDeEquipo);
    public List<MiembroDeEquipo> selectMiembrosDeEquipo();

    public List<Requisito> selectRequisitos();
    public boolean insertRequisito(Requisito requisito);
    
    public boolean updateTarea();
}