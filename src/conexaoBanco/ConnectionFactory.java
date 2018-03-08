package conexaoBanco;


import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class ConnectionFactory {

	private static Connection connection = null;
	//usando o xampp: startar apache e mysql
	public Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			if(connection == null){
				connection = (Connection) DriverManager.getConnection(
						"jdbc:mysql://localhost/carona", "root", "");
			}
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



}
