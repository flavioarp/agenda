package agenda.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	/** Módulo de conexão **/
	private String driver = "org.mariadb.jdbc.Driver";
	private String url = "jdbc:mariadb://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";
	
	private Connection conectar() {
		Connection conn = null;
		try {
			Class.forName(driver); // Não é mais necessário (Java1.6+), mas vincula a execução à existência do driver no classpath.
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	
	public void testarConexao() {
		try {
			Connection conn = conectar();
			System.out.println(conn);
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
