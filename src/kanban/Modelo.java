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
		db = CSV.getInstance();
		pb = ProductBacklog.getInstance();
		pb.anadirConjuntoTareas(db.selectProductBacklog());
		sb = db.selectSprintBacklogActual();
		formersb = db.selectSprintBacklog();
		miembros = db.selectMiembrosDeEquipo();
		requisitos = db.selectRequisitos();

		numTareas = 0;
		for (Integer i : requisitos.keySet()) {
			numTareas += requisitos.get(i).getTareas().size();
		}
	}

	public static Modelo getInstance() {
		if (mod == null)
			mod = new Modelo();
		return mod;
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
		ProductBacklog pb = sb.finalizarSprint(this.pb);
		this.pb = pb;
		this.formersb.add(this.sb);
		this.sb = new SprintBacklog();
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
		return sb.moveraDoing(idTarea);
	}

	public boolean moverTareaDoingTesting(int idTarea) {
		return sb.moveraTesting(idTarea);
	}

	public boolean moverTareaTestingFinished(int idTarea) {
		return sb.moveraFinished(idTarea);
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

	public void guardarDB() {
		db.updateProductBacklog(pb);
		db.updateSprintBacklogActual(sb);
		db.updateSprintBacklog(formersb);
		db.updateUsuario(miembros);
		db.updateRequisito(requisitos);
	}
}