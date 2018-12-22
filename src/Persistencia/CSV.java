
package Persistencia;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

import Backlogs.ProductBacklog;
import Backlogs.SprintBacklog;
import Miembros.MiembroDeEquipo;
import Requisitos.Defecto;
import Requisitos.HistoriaDeUsuario;
import Requisitos.Requisito;
import Tareas.Tarea;
import kanban.Modelo;

/**
 * 
 */
public class CSV implements Datos {

	/**
	 * Default constructor
	 */
	private static Datos csv;
	private Modelo m = Modelo.getInstance();

	private CSV() {
	}

	public static Datos getInstance() {
		if (csv == null)
			csv = new CSV();
		return csv;
	}

	@Override
	public void cargarDB() {
		m.getPB().anadirConjuntoTareas(selectProductBacklog());
		selectSprintBacklog();
		selectSprintBacklogActual();
		m.setMiembros(selectMiembrosDeEquipo());
		m.setRequisitos(selectRequisitos());
	}
	
	@Override
	public void guardarDB() {
		updateProductBacklog(m.getPB());
		updateSprintBacklog(m.getFormerSB());
		updateSprintBacklogActual(m.getSB());
		updateUsuario(m.getMiembros());
		updateRequisito(m.getRequisitos());
	}
	
	
	private HashMap<Integer, Tarea> selectProductBacklog() {
		HashMap<Integer, Tarea> r = new HashMap<Integer, Tarea>();
		HashMap<Integer, Requisito> requisitos = selectRequisitosTareas();
		ArrayList<ArrayList<String>> todo;
		HashMap<Integer, Tarea> tareas = new HashMap<Integer, Tarea>();
		for (Requisito i : requisitos.values())
			tareas.putAll(i.getTareas());
		todo = leerCSV("csv/ProductBacklog.csv");
		for (ArrayList<String> auxLinea : todo) {
			r.put(Integer.parseInt(auxLinea.get(0)), tareas.get(Integer.parseInt(auxLinea.get(0))));
		}

		return r;
	}

	
	private boolean updateProductBacklog(ProductBacklog productBacklog) {
		FileWriter fichero = null;
		try {
			fichero = new FileWriter("csv/ProductBacklog.csv");
			fichero.write("\n");
			for (Integer i : productBacklog.getTareas().keySet())
				fichero.write(i + "\n");

			fichero.close();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	
	private SprintBacklog selectSprintBacklogActual() {
		SprintBacklog r = new SprintBacklog();
		HashMap<Integer, Requisito> requisitos = selectRequisitosTareas();
		ArrayList<ArrayList<String>> todo;
		HashMap<Integer, Tarea> tareas = new HashMap<Integer, Tarea>();
		for (Requisito i : requisitos.values())
			tareas.putAll(i.getTareas());
		todo = leerCSV("csv/SprintBacklog.csv");
		for (ArrayList<String> auxLinea : todo) {
			r.anadirTarea(tareas.get(Integer.parseInt(auxLinea.get(0))));
			m.getSB().anadirTarea(tareas.get(Integer.parseInt(auxLinea.get(0))));
			switch (Integer.parseInt(auxLinea.get(1))) {
			case 1:
				m.moverTareaTodoDoing(Integer.parseInt(auxLinea.get(0)));
				break;
			case 2:
				m.moverTareaTodoDoing(Integer.parseInt(auxLinea.get(0)));
				m.moverTareaDoingTesting(Integer.parseInt(auxLinea.get(0)));
				break;
			case 3:
				m.moverTareaTodoDoing(Integer.parseInt(auxLinea.get(0)));
				m.moverTareaDoingTesting(Integer.parseInt(auxLinea.get(0)));
				m.moverTareaTestingFinished(Integer.parseInt(auxLinea.get(0)));
				break;
			default:
				break;

			}
		}

		return r;
	}

	
	private boolean updateSprintBacklogActual(SprintBacklog sprintBacklog) {
		FileWriter fichero = null;
		try {
			fichero = new FileWriter("csv/SprintBacklog.csv");
			fichero.write("\n");
			for (Tarea i : sprintBacklog.getTareasTodo().values())
				fichero.write(i.getID() + "," + "0" + "\n");
			for (Tarea i : sprintBacklog.getDoing().values())
				fichero.write(i.getID() + "," + "1" + "\n");
			for (Tarea i : sprintBacklog.getTesting().values())
				fichero.write(i.getID() + "," + "2" + "\n");
			for (Tarea i : sprintBacklog.getFinished().values())
				fichero.write(i.getID() + "," + "3" + "\n");

			fichero.close();
		} catch (Exception ex) {

			System.out.println("Ha dejado de funcionar");
			return false;
		}
		return true;
	}

	
	private List<SprintBacklog> selectSprintBacklog() {
		ArrayList<SprintBacklog> r = new ArrayList<SprintBacklog>();
		HashMap<Integer, Requisito> requisitos = selectRequisitosTareas();
		ArrayList<ArrayList<String>> todo;
		HashMap<Integer, Tarea> tareas = new HashMap<Integer, Tarea>();
		int contador = 0;
		for (Requisito i : requisitos.values())
			tareas.putAll(i.getTareas());
		todo = leerCSV("csv/HistorialSprintBacklog.csv");
		r.add(new SprintBacklog());
		for (ArrayList<String> auxLinea : todo) {

			if (auxLinea.size() == 1) {
				if(contador>0) {
					m.getFormerSB().add(m.getSB());
					m.nuevoSB();
				}
				contador++;
			} else {
				//r.get(contador).anadirTarea(tareas.get(Integer.parseInt(auxLinea.get(0))));
				m.getSB().anadirTarea(tareas.get(Integer.parseInt(auxLinea.get(0))));
				switch (Integer.parseInt(auxLinea.get(1))) {
				case 1:
					m.moverTareaTodoDoing(Integer.parseInt(auxLinea.get(0)));
					//r.get(contador).moveraDoing(Integer.parseInt(auxLinea.get(0)));
					break;
				case 2:
					m.moverTareaTodoDoing(Integer.parseInt(auxLinea.get(0)));
					m.moverTareaDoingTesting(Integer.parseInt(auxLinea.get(0)));
					//r.get(contador).moveraTesting(Integer.parseInt(auxLinea.get(0)));
					break;
				case 3:
					m.moverTareaTodoDoing(Integer.parseInt(auxLinea.get(0)));
					m.moverTareaDoingTesting(Integer.parseInt(auxLinea.get(0)));
					m.moverTareaTestingFinished(Integer.parseInt(auxLinea.get(0)));
					//r.get(contador).moveraFinished(Integer.parseInt(auxLinea.get(0)));
					break;
				default:
					break;

				}
			}
		}
		m.getFormerSB().add(m.getSB());
		m.nuevoSB();
		return r;
	}

	
	private boolean updateUsuario(HashMap<String, MiembroDeEquipo> miembroDeEquipo) {
		FileWriter fichero = null;
		try {
			fichero = new FileWriter("csv/MiembroDeEquipo.csv");
			fichero.write("\n");
			for (MiembroDeEquipo i : miembroDeEquipo.values())
				fichero.write(i.getNombre() + "," + i.getApellido() + "," + i.getDni() + "," + i.getTlf() + ","
						+ i.getNick() + "\n");

			fichero.close();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	
	private HashMap<String, MiembroDeEquipo> selectMiembrosDeEquipo() {
		HashMap<String, MiembroDeEquipo> r = new HashMap<String, MiembroDeEquipo>();
		ArrayList<ArrayList<String>> todo;
		todo = leerCSV("csv/MiembroDeEquipo.csv");
		for (ArrayList<String> auxLinea : todo)
			r.put(auxLinea.get(4), new MiembroDeEquipo(auxLinea.get(0), auxLinea.get(1), auxLinea.get(2),
					auxLinea.get(3), auxLinea.get(4)));
		return r;
	}

	
	private HashMap<Integer, Requisito> selectRequisitos() {
		return selectRequisitosTareas();
	}

	
	private boolean updateRequisito(HashMap<Integer, Requisito> requisito) {
		FileWriter fichero = null;
		HashMap<Integer, Requisito> hdu = new HashMap<Integer, Requisito>();
		HashMap<Integer, Requisito> defec = new HashMap<Integer, Requisito>();
		String aux = "";
		ArrayList<String> tarea = new ArrayList<String>();
		try {

			for (Requisito i : requisito.values()) {
				if (i instanceof HistoriaDeUsuario) {
					hdu.put(i.getID(), i);
				} else {
					defec.put(i.getID(), i);
				}
			}

			fichero = new FileWriter("csv/HistoriaDeUsuario.csv");
			fichero.write("\n");
			for (Requisito i : hdu.values()) {
				for (Tarea j : i.getTareas().values()) {
					if (j.getMiembro() != null)
						tarea.add(j.getID() + "," + j.getTitulo() + "," + j.getDescripcion() + "," + j.getCoste() + ","
								+ j.getBeneficio() + "," + j.getRequisito().getID() + "," + j.getMiembro().getNick());
					else
						tarea.add(j.getID() + "," + j.getTitulo() + "," + j.getDescripcion() + "," + j.getCoste() + ","
								+ j.getBeneficio() + "," + j.getRequisito().getID() + "," + -1);
				}
				fichero.write(aux = i.getID() + "," + i.getTitulo() + "," + i.getDescripcion() + "\n");
			}
			fichero.close();
			fichero = new FileWriter("csv/Defecto.csv");
			fichero.write("\n");
			for (Requisito i : defec.values()) {
				for (Tarea j : i.getTareas().values()) {
					if (j.getMiembro() != null)
						tarea.add(j.getID() + "," + j.getTitulo() + "," + j.getDescripcion() + "," + j.getCoste() + ","
								+ j.getBeneficio() + "," + j.getRequisito().getID() + "," + j.getMiembro().getNick());
					else
						tarea.add(j.getID() + "," + j.getTitulo() + "," + j.getDescripcion() + "," + j.getCoste() + ","
								+ j.getBeneficio() + "," + j.getRequisito().getID() + "," + -1);
				}
				//System.out.println("holaaaa");
				fichero.write(aux = i.getID() + "," + i.getTitulo() + "," + i.getDescripcion() + "\n");
			}
			fichero.close();

			fichero = new FileWriter("csv/Tarea.csv");
			fichero.write("\n");
			for (String i : tarea)
				fichero.write(i + "\n");
			fichero.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	private ArrayList<String> leeLinea(String l) {
		ArrayList<String> r = new ArrayList<String>();
		char[] c;
		c = l.toCharArray();
		String aux = "";

		for (Character i : c) {
			if (i == ',') {
				r.add(aux);

				aux = "";
			} else {
				aux = aux.concat(i.toString());
			}
		}
		r.add(aux);
		return r;
	}

	private HashMap<Integer, Requisito> selectRequisitosTareas() {
		ArrayList<ArrayList<String>> todo;
		HashMap<Integer, Requisito> requisitos = new HashMap<Integer, Requisito>();
		HashMap<String, MiembroDeEquipo> miembros = selectMiembrosDeEquipo();
		requisitos = selectRequisito();
		todo = leerCSV("csv/Tarea.csv");
		for (ArrayList<String> auxLinea : todo) {
			//System.out.println(requisitos.get(Integer.parseInt(auxLinea.get(5))));
			requisitos.get(Integer.parseInt(auxLinea.get(5))).anadirTarea(Integer.parseInt(auxLinea.get(0)),
					auxLinea.get(1), auxLinea.get(2), Float.parseFloat(auxLinea.get(3)),
					Float.parseFloat(auxLinea.get(4)));
			if (Integer.parseInt(auxLinea.get(5)) != -1) {
				requisitos.get(Integer.parseInt(auxLinea.get(5))).getTareas().get(Integer.parseInt(auxLinea.get(0)))
						.asignarMiembro(miembros.get(auxLinea.get(6)));
				;
			} else
				requisitos.get(Integer.parseInt(auxLinea.get(5)));

		}
		return requisitos;
	}

	private HashMap<Integer, Requisito> selectRequisito() {
		HashMap<Integer, Requisito> r = new HashMap<Integer, Requisito>();
		ArrayList<ArrayList<String>> todo;
		todo = leerCSV("csv/HistoriaDeUsuario.csv");
		for (ArrayList<String> auxLinea : todo)
			r.put(Integer.parseInt(auxLinea.get(0)),
					new HistoriaDeUsuario(Integer.parseInt(auxLinea.get(0)), auxLinea.get(1), auxLinea.get(2)));
		todo = leerCSV("csv/Defecto.csv");
		for (ArrayList<String> auxLinea : todo)
			r.put(Integer.parseInt(auxLinea.get(0)),
					new Defecto(Integer.parseInt(auxLinea.get(0)), auxLinea.get(1), auxLinea.get(2)));

		return r;
	}

	private ArrayList<ArrayList<String>> leerCSV(String dirF) {
		File fichero = new File(dirF);
		Scanner s = null;
		ArrayList<ArrayList<String>> r = new ArrayList<ArrayList<String>>();
		try {
			s = new Scanner(fichero);
			if (s.hasNextLine())
				s.nextLine();
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				r.add(leeLinea(linea));
			}
		} catch (Exception ex) {
			System.out.println(ex);
			r = null;
		} finally {
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
			}
		}
		return r;
	}

	
	private boolean updateSprintBacklog(List<SprintBacklog> sprintBacklog) {
		FileWriter fichero = null;
		try {
			fichero = new FileWriter("csv/HistorialSprintBacklog.csv");

			for (SprintBacklog j : sprintBacklog) {
				fichero.write("\n");
				for (Tarea i : j.getTareasTodo().values())
					fichero.write(i.getID() + "," + "0" + "\n");
				for (Tarea i : j.getDoing().values())
					fichero.write(i.getID() + "," + "1" + "\n");
				for (Tarea i : j.getTesting().values())
					fichero.write(i.getID() + "," + "2" + "\n");
				for (Tarea i : j.getFinished().values())
					fichero.write(i.getID() + "," + "3" + "\n");
			}
			fichero.close();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
}