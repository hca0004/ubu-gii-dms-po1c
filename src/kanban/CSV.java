
package kanban;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * 
 */
public class CSV implements Datos {

	/**
	 * Default constructor
	 */
	private static Datos csv;

	private CSV() {
	}

	public static Datos getInstance() {
		if (csv == null)
			csv = new CSV();
		return csv;
	}

	@Override
	public HashMap<Integer,Tarea> selectProductBacklog() {
		HashMap<Integer,Tarea> r= new HashMap<Integer,Tarea>();
		HashMap<Integer, Requisito> requisitos=selectRequisitosTareas();
		ArrayList<ArrayList<String>> todo;
		HashMap<Integer,Tarea> tareas=new HashMap<Integer,Tarea>();
		for(Requisito i:requisitos.values())
			tareas.putAll(i.getTareas());		
		todo = leerCSV("csv/ProductBacklog.csv");
		for (ArrayList<String> auxLinea : todo) {
			r.put(Integer.parseInt(auxLinea.get(0)), tareas.get(Integer.parseInt(auxLinea.get(0))));
		}
			
		return r;
	}

	@Override
	public boolean updateProductBacklog(ProductBacklog productBacklog) {
		FileWriter fichero = null;
		try {
			fichero = new FileWriter("csv/ProductBacklog.csv");
			fichero.write("\n");
			for (Integer i : productBacklog.getTareas().keySet())
				fichero.write(i+ "\n");
			
			fichero.close();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public SprintBacklog selectSprintBacklogActual() {
		SprintBacklog r= new SprintBacklog();
		HashMap<Integer, Requisito> requisitos=selectRequisitosTareas();
		ArrayList<ArrayList<String>> todo;
		HashMap<Integer,Tarea> tareas=new HashMap<Integer,Tarea>();
		for(Requisito i:requisitos.values())
			tareas.putAll(i.getTareas());		
		todo = leerCSV("csv/SprintBacklog.csv");
		for (ArrayList<String> auxLinea : todo) {
			r.anadirTarea(tareas.get(Integer.parseInt(auxLinea.get(0))));
			switch(Integer.parseInt(auxLinea.get(1))) {
			case 1:
				r.moveraDoing(Integer.parseInt(auxLinea.get(0)));
				break;
			case 2:
				r.moveraTesting(Integer.parseInt(auxLinea.get(0)));
				break;
			case 3:
				r.moveraFinished(Integer.parseInt(auxLinea.get(0)));
				break;
			default:
				break;
			
			}
		}
			
		return r; 
	}

	@Override
	public boolean updateSprintBacklogActual(SprintBacklog sprintBacklog) {
		FileWriter fichero = null;
		try {
			fichero = new FileWriter("csv/ProductBacklog.csv");
			fichero.write("\n");
			for (Tarea i : sprintBacklog.getTareasTodo().values())
				fichero.write(i+ "\n");
			
			fichero.close();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public List<SprintBacklog> selectSprintBacklog() {
		ArrayList<SprintBacklog> r= new ArrayList<SprintBacklog>();
		HashMap<Integer, Requisito> requisitos=selectRequisitosTareas();
		ArrayList<ArrayList<String>> todo;
		HashMap<Integer,Tarea> tareas=new HashMap<Integer,Tarea>();
		int contador=0;
		for(Requisito i:requisitos.values())
			tareas.putAll(i.getTareas());		
		todo = leerCSV("csv/HistorialSprintBacklog.csv");
		r.add(new SprintBacklog());
		for (ArrayList<String> auxLinea : todo) {
			
			if (auxLinea.size()==1) {
				r.add(new SprintBacklog());
				contador++;
			}else {
			r.get(contador).anadirTarea(tareas.get(Integer.parseInt(auxLinea.get(0))));
			switch(Integer.parseInt(auxLinea.get(1))) {
			case 1:
				r.get(contador).moveraDoing(Integer.parseInt(auxLinea.get(0)));
				break;
			case 2:
				r.get(contador).moveraTesting(Integer.parseInt(auxLinea.get(0)));
				break;
			case 3:
				r.get(contador).moveraFinished(Integer.parseInt(auxLinea.get(0)));
				break;
			default:
				break;
			
			}}
		}
			
		return r;
	}


	@Override
	public boolean updateUsuario(HashMap<String,MiembroDeEquipo> miembroDeEquipo) {		
		FileWriter fichero = null;
		try {
			fichero = new FileWriter("csv/MiembroDeEquipo.csv");
			fichero.write("\n");
			for (MiembroDeEquipo i : miembroDeEquipo.values())
				fichero.write(i.getNombre() + ',' + i.getApellido() + ',' + i.getDni() + ',' + i.getTlf() + ','
						+ i.getNick() + "\n");
			
			fichero.close();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public HashMap<String, MiembroDeEquipo> selectMiembrosDeEquipo() {
		HashMap<String, MiembroDeEquipo> r = new HashMap<String, MiembroDeEquipo>();
		ArrayList<ArrayList<String>> todo;
		todo = leerCSV("csv/MiembroDeEquipo.csv");
		for (ArrayList<String> auxLinea : todo)
			r.put(auxLinea.get(4), new MiembroDeEquipo(auxLinea.get(0), auxLinea.get(1), auxLinea.get(2),
					auxLinea.get(3), auxLinea.get(4)));
		return r;
	}

	@Override
	public HashMap<Integer, Requisito> selectRequisitos() {
		return selectRequisitosTareas();
	}

	@Override
	public boolean updateRequisito(HashMap<Integer,Requisito> requisito) {
		// TODO Auto-generated method stub
		return false;
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
		HashMap<Integer, Requisito> requisitos;
		HashMap<String, MiembroDeEquipo> miembros=selectMiembrosDeEquipo();
		requisitos = selectRequisito();
		todo = leerCSV("csv/Tarea.csv");
		for (ArrayList<String> auxLinea : todo) {
			requisitos.get(Integer.parseInt(auxLinea.get(5))).anadirTarea(Integer.parseInt(auxLinea.get(0)),
					auxLinea.get(1), auxLinea.get(2), Float.parseFloat(auxLinea.get(3)),
					Float.parseFloat(auxLinea.get(4)));
			requisitos.get(Integer.parseInt(auxLinea.get(5))).getTareas().get(Integer.parseInt(auxLinea.get(0))).asignarMiembro(miembros.get(auxLinea.get(6)));;
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

	@Override
	public boolean updateSprintBacklog(List<SprintBacklog> sprintBacklog) {
		// TODO Auto-generated method stub
		return false;
	}
}