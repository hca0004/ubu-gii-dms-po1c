package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Backlogs.ProductBacklog;
import Backlogs.SprintBacklog;
import Miembros.MiembroDeEquipo;
import Requisitos.Defecto;
import Requisitos.HistoriaDeUsuario;
import Requisitos.Requisito;
import Tareas.Tarea;
import kanban.Modelo;

public class SQLite implements Datos {

	private String dirDB;
	private static SQLite sqlite;
	private Modelo m = Modelo.getInstance();
	private Connection c;

	private Connection conectar(String ruta) {

		try {
			Class.forName("org.sqlite.JDBC");
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + ruta);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private SQLite() {

		dirDB = "db/kanban.db";

	}

	public static Datos getInstance() {
		if (sqlite == null)
			sqlite = new SQLite();
		return sqlite;
	}

	private void selectTarea() {

		Tarea tarea;
		String sql;
		ResultSet resul;
		ResultSet resul2;
		String t, d, idr;
		int i, idm, e;
		float c, b;
		sql = "SELECT * FROM Tarea " + ";";
		resul = executeQuery(sql);
		try {
			while (resul.next()) {
				i = resul.getInt("ID");
				t = resul.getString("Titulo");
				d = resul.getString("Descripcion");
				c = resul.getFloat("Coste");
				b = resul.getFloat("Beneficio");
				idm = resul.getInt("IDRequisito");
				idr = resul.getString("IDMiembro");
				e = resul.getInt("Estado");
				tarea = new Tarea(m.getRequisitos().get(idm), i, t, d, c, b);
				m.getRequisitos().get(idm).getTareas().put(i, tarea);
				if (idr.compareTo("") != 0)
					tarea.asignarMiembro(m.getMiembros().get(idr));
				if (e == 0)
					m.getPB().anadirTarea(tarea);
				else if (e >= 1 && e <= 4) {
					m.getSB().anadirTarea(tarea);
					moverTarea(e-1, i);
				} else {
					resul2 = executeQuery("Select * from SprintTarea where IDTarea=" + i + ";");
					while (resul2.next()) {
						SprintBacklog sprint = m.getFormerSB().get(resul2.getInt("IDSprint"));
						sprint.anadirTarea(tarea);
						moverTarea(resul2.getInt("Estado"), i);

					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void moverTarea(int e, int id) {

		switch (e) {
		case 1:
			m.moverTareaTodoDoing(id);
			break;
		case 2:
			m.moverTareaTodoDoing(id);
			m.moverTareaDoingTesting(id);
			break;
		case 3:
			m.moverTareaTodoDoing(id);
			m.moverTareaDoingTesting(id);
			m.moverTareaTestingFinished(id);
			break;
		default:
			break;
		}
	}

	private boolean updateUsuario(HashMap<String, MiembroDeEquipo> miembroDeEquipo) {
		String sql;
		for (MiembroDeEquipo i : miembroDeEquipo.values()) {
			sql = "INSERT INTO Miembro (" + "Nombre, Apellido, DNI, Tlf,Nick)" + "Values ('" + i.getNombre() + "','"
					+ i.getApellido() + "','" + i.getDni() + "','" + i.getTlf() + "','" + i.getNick() + "');";
			execute(sql);
		}
		return true;
	}

	private HashMap<String, MiembroDeEquipo> selectMiembrosDeEquipo() {
		String sql;
		ResultSet resul;
		String n, a, d, t, ni;
		HashMap<String, MiembroDeEquipo> r = new HashMap<String, MiembroDeEquipo>();
		sql = "SELECT * FROM Miembro " + ";";
		resul = executeQuery(sql);
		try {
			while (resul.next()) {
				n = resul.getString("Nombre");
				a = resul.getString("Apellido");
				d = resul.getString("DNI");
				t = resul.getString("Tlf");
				ni = resul.getString("Nick");
				r.put(ni, new MiembroDeEquipo(n, a, t, d, ni));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	private HashMap<Integer, Requisito> selectRequisitos() {
		String sql;
		ResultSet resul;
		String t, d;
		int i, ti;
		HashMap<Integer, Requisito> r = new HashMap<Integer, Requisito>();
		sql = "SELECT * FROM Requisito " + ";";
		resul = executeQuery(sql);
		try {
			while (resul.next()) {
				i = resul.getInt("ID");
				t = resul.getString("Titulo");
				d = resul.getString("Descripcion");
				ti = resul.getInt("Tipo");
				if (ti == 1)
					r.put(i, new HistoriaDeUsuario(i, t, d));
				else
					r.put(i, new Defecto(i, t, d));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

	
	private boolean updateRequisito(HashMap<Integer, Requisito> requisito) {
		String sql;
		String nic="";
		int tip, estado;
		for (Requisito i : requisito.values()) {
			if (i instanceof Defecto)
				tip = 2;
			else
				tip = 1;
			sql = "INSERT INTO Requisito (" + "ID, Titulo, Descripcion, Tipo)" + "Values (" + i.getID() + ",'"
					+ i.getTitulo() + "','" + i.getDescripcion() + "'," + tip + ");";
			execute(sql);
			for (Tarea j : i.getTareas().values()) {
				if (m.getPB().getTareas().containsKey(j.getID()))
					estado = 0;
				else if ( m.getSB().getTareasTodo().containsKey(j.getID()))
					estado=1;
				else if(m.getSB().getDoing().containsKey(j.getID()))
					estado=2;
				else if( m.getSB().getTesting().containsKey(j.getID()))
					estado=3;
				else if( m.getSB().getFinished().containsKey(j.getID()))
					estado = 4;
				else
					estado = -1;
				if(j.getMiembro()!=null)
					nic=j.getMiembro().getNick();
				sql = "INSERT INTO Tarea (" + "ID, Titulo, Descripcion,Coste,Beneficio,IDRequisito,IDMiembro, Estado)"
						+ "Values (" + j.getID() + ",'" + j.getTitulo() + "','" + j.getDescripcion() + "'," + j.getCoste()
						+ "," + j.getBeneficio() + "," + i.getID() + ",'" + nic + "'," + estado
						+ ");";
				execute(sql);
				
				

			}
		}
		return true;
	}

	private boolean dropTables() {
		
		ArrayList<String> sql = new ArrayList<String>();
		// MIEMBRO
		sql.add("DROP TABLE IF EXISTS Miembro;");
		sql.add("DROP TABLE IF EXISTS Requisito;");
		sql.add("DROP TABLE IF EXISTS Tarea;");
		sql.add("DROP TABLE IF EXISTS SprintHistorico;");
		sql.add("DROP TABLE IF EXISTS SprintTarea;");
		try {
			for (String i : sql) {
				Statement stmt = c.createStatement();
				stmt.execute(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
		return true;
	}

	private boolean createTables() {
		
		ArrayList<String> sql = new ArrayList<String>();
		// MIEMBRO
		sql.add("CREATE TABLE IF NOT EXISTS Miembro (" + "    Nombre varchar(255)," + "    Apellido varchar(255),"
				+ "    DNI varchar(255)," + "    Tlf varchar(255)," + "    Nick varchar(255)" + ");");
		// REQUISITO
		sql.add("CREATE TABLE IF NOT EXISTS Requisito (" + "    ID INTEGER," + "    Titulo varchar(255),"
				+ "    Descripcion varchar(255)," + "	 Tipo INTEGER" + ");");
		// TAREA
		sql.add("CREATE TABLE IF NOT EXISTS Tarea (" + "    ID int," + "    Titulo varchar(255),"
				+ "    Descripcion varchar(255)," + "	 Coste REAL," + "	 Beneficio REAL," + "	 IDRequisito int,"
				+ "	 IDMiembro varchar(255)," + "	 Estado int" + ");");
		// backlog actuales Sprint y product
		sql.add("CREATE TABLE IF NOT EXISTS SprintHistorico (" + "    ID int" + ");");
		// SprintTarea
		sql.add("CREATE TABLE IF NOT EXISTS SprintTarea (" + "    IDSprint int," + "    IDTarea int," + "	 Estado int"
				+ ");");

		try {
			for (String i : sql) {
				Statement stmt = c.createStatement();
				stmt.execute(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
		return true;
	}

	private ResultSet executeQuery(String sql) {
		ResultSet r;
		try {
			Statement stmt = c.createStatement();
			r=stmt.executeQuery(sql);
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	private boolean execute(String sql) {
		
		try {
			Statement stmt = c.createStatement();
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void cargarDB() {
		c = conectar(dirDB);
		createTables();
		selectSprintBacklog();
		m.setMiembros(selectMiembrosDeEquipo());
		m.setRequisitos(selectRequisitos());
		selectTarea();
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void guardarDB() {
		c = conectar(dirDB);
		dropTables();
		createTables();
		this.updateUsuario(m.getMiembros());
		this.updateRequisito(m.getRequisitos());
		this.updateSprintBacklog(m.getFormerSB());
		try {
			c.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}


	private void selectSprintBacklog() {
		ResultSet resul2;
		resul2 = executeQuery("Select * from SprintHistorico;");
		try {
			while (resul2.next())
				m.getFormerSB().add(new SprintBacklog());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	private boolean updateSprintBacklog(List<SprintBacklog> sprintBacklog) {
		int conta=0;
		for(SprintBacklog sb:sprintBacklog) {
			String sql;
			sql = "INSERT INTO SprintHistorico (" + "ID)"
					+ "Values (" + conta
					+ ");";
			
			execute(sql);
			for(Tarea t: sb.getTareasTodo().values()) {
				insertSprintTarea(conta,t.getID(),0);
			}
			for(Tarea t: sb.getDoing().values()) {
				insertSprintTarea(conta,t.getID(),1);
			}
			for(Tarea t: sb.getTesting().values()) {
				insertSprintTarea(conta,t.getID(),2);
			}
			for(Tarea t: sb.getFinished().values()) {
				insertSprintTarea(conta,t.getID(),3);
			}
			
		}
		return false;
	}
	private void insertSprintTarea(int sp,int ta, int es) {
		String sql;
		sql = "INSERT INTO SprintTarea (" + "IDSprint, IDTarea, Estado)"
				+ "Values (" + sp + "," + ta + "," + es 
				+ ");";
		
		execute(sql);
	}
	

}
