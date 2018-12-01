package kanban;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLite implements Datos {

	private String dirDB;
	private static SQLite sqlite;

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

	@Override
	public HashMap<Integer, Tarea> selectProductBacklog() {
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
	public boolean updateSprintBacklog(List<SprintBacklog> sprintBacklog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUsuario(HashMap<String, MiembroDeEquipo> miembroDeEquipo) {
		String sql;
		for (MiembroDeEquipo i : miembroDeEquipo.values()) {
			sql = "INSERT INTO Miembro (" + "Nombre, Apellido, DNI, Tlf,Nick)" + "Values (" + i.getNombre() + ","
					+ i.getApellido() + "," + i.getDni() + "," + i.getTlf() + "," + i.getNick() + ");";
			execute(sql);
		}
		return true;
	}

	@Override
	public HashMap<String, MiembroDeEquipo> selectMiembrosDeEquipo() {
		String sql;
		ResultSet resul;
		String n,a,d,t,ni;
		HashMap<String,MiembroDeEquipo> r=new HashMap<String,MiembroDeEquipo>(); 
		sql = "SELECT * FROM Miembro " + ";";
		resul = executeQuery(sql);
		try {
		while (resul.next()) {
			n=resul.getString("Nombre");
			a=resul.getString("Apellido");
			d=resul.getString("DNI");
			t=resul.getString("Tlf");
			ni=resul.getString("Nick");
			r.put(ni, new MiembroDeEquipo(n,a,t,d,ni));
		}}catch(Exception e) {
			
		}
		return r;
	}

	@Override
	public HashMap<Integer, Requisito> selectRequisitos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateRequisito(HashMap<Integer, Requisito> requisito) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean dropTables() {
		Connection c = conectar(dirDB);
		ArrayList<String> sql = new ArrayList<String>();
		// MIEMBRO
		sql.add("DROP TABLE IF EXIST Miembro;");
		sql.add("DROP TABLE IF EXIST Requisito;");
		sql.add("DROP TABLE IF EXIST Tarea;");
		sql.add("DROP TABLE IF EXIST SprintHistorico;");
		sql.add("DROP TABLE IF EXIST SprintTarea;");

		return true;
	}

	private boolean createTables() {
		Connection c = conectar(dirDB);
		ArrayList<String> sql = new ArrayList<String>();
		// MIEMBRO
		sql.add("CREATE TABLE IF NOT EXISTS Miembro (" + "    Nombre varchar(255)," + "    Apellido varchar(255),"
				+ "    DNI varchar(255)," + "    Tlf varchar(255)," + "    Nick varchar(255)" + ");");
		// REQUISITO
		sql.add("CREATE TABLE IF NOT EXISTS Requisito (" + "    ID int," + "    Titulo varchar(255),"
				+ "    Descripcion varchar(255)," + "	 Tipo int," + ");");
		// TAREA
		sql.add("CREATE TABLE IF NOT EXISTS Tarea (" + "    ID int," + "    Titulo varchar(255),"
				+ "    Descripcion varchar(255)," + "	 Coste REAL," + "	 Coste REAL," + "	 IDRequisito int,"
				+ "	 IDMiembro int," + "	 Estado int," + ");");
		// backlog actuales Sprint y product
		sql.add("CREATE TABLE IF NOT EXISTS SprintHistorico (" + "    ID int," + ");");
		// SprintTarea
		sql.add("CREATE TABLE IF NOT EXISTS SprintTarea (" + "    IDSprint int," + "    IDTarea int," + "	 Estado int"
				+ ");");

		try {
			for (String i : sql) {
				Statement stmt = c.createStatement();
				stmt.execute(i);
			}
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
		return true;
	}

	private ResultSet executeQuery(String sql) {
		Connection c = conectar(dirDB);
		try {
			Statement stmt = c.createStatement();
			c.close();
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	private boolean execute(String sql) {
		Connection c = conectar(dirDB);
		try {
			Statement stmt = c.createStatement();
			stmt.execute(sql);
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
