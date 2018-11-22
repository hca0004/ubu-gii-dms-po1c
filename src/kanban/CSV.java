
package kanban;

import java.io.File;
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
	public ProductBacklog selectProductBacklog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateProductBacklog(ProductBacklog productBacklog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SprintBacklog selectSprintBacklogActual() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSprintBacklogActual(SprintBacklog sprintBacklog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SprintBacklog> selectSprintBacklog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SprintBacklog selectSprintBacklog(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MiembroDeEquipo insertUsuario(MiembroDeEquipo miembroDeEquipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, MiembroDeEquipo> selectMiembrosDeEquipo() {
		File fichero = new File("csv/MiembroDeEquipo.csv");
		Scanner s = null;
		ArrayList<String> auxLinea;
		HashMap<String, MiembroDeEquipo> r = new HashMap<String, MiembroDeEquipo>();

		try {
			s = new Scanner(fichero);

			while (s.hasNextLine()) {
				String linea = s.nextLine();
				auxLinea = leeLinea(linea);
				System.out.println(auxLinea);
				r.put(auxLinea.get(4), new MiembroDeEquipo(auxLinea.get(0), auxLinea.get(1), auxLinea.get(2),
						auxLinea.get(3), auxLinea.get(4)));

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
	public HashMap<Integer, Requisito> selectRequisitos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertRequisito(Requisito requisito) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTarea() {
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
}