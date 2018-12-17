package kanban;

import java.util.*;

import Backlogs.ProductBacklog;
import Backlogs.SprintBacklog;
import Miembros.MiembroDeEquipo;
import Persistencia.CSV;
import Persistencia.Datos;
import Requisitos.Defecto;
import Requisitos.HistoriaDeUsuario;
import Requisitos.Requisito;
import Tareas.Tarea;

/**
 * 
 */
public class Modelo {

	private ProductBacklog pb;
	private SprintBacklog sb;
	private List<SprintBacklog> formersb;
	private HashMap<String, MiembroDeEquipo> miembros;
	private HashMap<Integer, Requisito> requisitos;
	private Datos db;
	private int numTareas;

	private static Modelo mod;

	/**
	 * Default constructor
	 */
	private Modelo() {
	}

	public static Modelo getInstance() {
		if (mod == null)
			mod = new Modelo();
		return mod;
	}

	public void cargarDB() {
		db = CSV.getInstance();
		pb = ProductBacklog.getInstance();
		pb.anadirConjuntoTareas(db.selectProductBacklog());
		// formersb =
		formersb = new ArrayList<SprintBacklog>();
		nuevoSB();
		db.selectSprintBacklog();
		db.selectSprintBacklogActual();
		miembros = db.selectMiembrosDeEquipo();
		requisitos = db.selectRequisitos();

		numTareas = 0;
		for (Integer i : requisitos.keySet()) {
			numTareas += requisitos.get(i).getTareas().size();
		}
	}
	
	public void nuevoSB() {
		this.sb = new SprintBacklog();
	}

	public boolean nuevoMiembro(String n, String a, String d, String t, String nick) {
		if (miembros.containsKey(nick) || n.isEmpty() || a.isEmpty() || d.isEmpty() || t.isEmpty() || nick.isEmpty())
			return false;
		MiembroDeEquipo miembro = new MiembroDeEquipo(n, a, d, t, nick);
		miembros.put(nick, miembro);
		return true;
	}

	public boolean nuevoRequisito(int i, String t, String d) {
		if (t.isEmpty())
			return false;
		Requisito r;
		int tam = requisitos.size();
		if (i == 1) {
			r = new HistoriaDeUsuario(tam, t, d);
		} else if (i == 2) {
			r = new Defecto(tam, t, d);
		} else {
			return false;
		}
		requisitos.put(tam, r);
		return true;
	}

	public void finalizarSprintBacklog() {
		pb.anadirConjuntoTareas(sb.getTareasTodo());
		pb.anadirConjuntoTareas(sb.getDoing());
		pb.anadirConjuntoTareas(sb.getTesting());
		this.formersb.add(this.sb);
		nuevoSB();
	}

	public boolean nuevaTarea(int id, String t, String d, float c, float b) {
		if (t.isEmpty() || !requisitos.containsKey(id))
			return false;
		Tarea tarea = requisitos.get(id).anadirTarea(numTareas, t, d, c, b);
		numTareas++;
		pb.anadirTarea(tarea);
		return true;
	}

	public boolean asignarMiembroTarea(int backlog, int idTarea, String nick) {
		if (!miembros.containsKey(nick))
			return false;
		if (backlog == 0 && pb.getTareas().containsKey(idTarea))
			pb.getTareas().get(idTarea).asignarMiembro(miembros.get(nick));
		else if (backlog == 1 && sb.getTareasTodo().containsKey(idTarea))
			sb.getTareasTodo().get(idTarea).asignarMiembro(miembros.get(nick));
		else
			return false;
		return true;
	}

	public boolean modificarTarea(int backlog, int idTarea, String t, String d, float c, float b) {
		Tarea tarea;
		if (backlog == 0 && pb.getTareas().containsKey(idTarea))
			tarea = pb.getTareas().get(idTarea);
		else if (backlog == 1 && sb.getTareasTodo().containsKey(idTarea))
			tarea = sb.getTareasTodo().get(idTarea);
		else
			return false;
		tarea.modificarTitulo(t);
		tarea.modificarDescripcion(d);
		tarea.modificarCoste(c);
		tarea.modificarBeneficio(b);
		if (backlog == 0)
			pb.getTareas().put(tarea.getID(), tarea);
		else
			sb.getTareasTodo().put(tarea.getID(), tarea);
		return true;
	}

	public boolean moverTareaPBaSB(int idTarea) {
		if (pb.getTareas().containsKey(idTarea)) {
			sb.anadirTarea(pb.getTareas().remove(idTarea));
			return true;
		}
		return false;
	}

	public boolean moverTareaTodoDoing(int idTarea) {

		if (sb.getTareasTodo().containsKey(idTarea)) {
			sb.getDoing().put(idTarea, sb.getTareasTodo().remove(idTarea));
			return true;
		}
		return false;

	}

	public boolean moverTareaDoingTesting(int idTarea) {

		if (sb.getDoing().containsKey(idTarea)) {
			sb.getTesting().put(idTarea, sb.getDoing().remove(idTarea));
			return true;
		}
		return false;

	}

	public boolean moverTareaTestingFinished(int idTarea) {

		if (sb.getTesting().containsKey(idTarea)) {
			sb.getFinished().put(idTarea, sb.getTesting().remove(idTarea));
			return true;
		}
		return false;

	}

	public HashMap<Integer, Requisito> getRequisitos() {
		return requisitos;
	}

	public HashMap<String, MiembroDeEquipo> getMiembros() {
		return miembros;
	}

	public ProductBacklog getPB() {
		return pb;
	}

	public SprintBacklog getSB() {
		return sb;
	}

	public List<SprintBacklog> getFormerSB() {
		return formersb;
	}

	public void guardarDB() {
		db.updateProductBacklog(pb);
		db.updateSprintBacklog(formersb);
		db.updateSprintBacklogActual(sb);
		db.updateUsuario(miembros);
		db.updateRequisito(requisitos);
	}
}