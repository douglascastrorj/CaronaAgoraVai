package conexaoBanco;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class TestaConnectionFactory {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection con = new ConnectionFactory().getConnection();
		PreparedStatement stmt = (PreparedStatement) con.prepareStatement("select * from usuario");

		// executa um select
		ResultSet rs = stmt.executeQuery();

		// itera no ResultSet
		while (rs.next()) {
			String nome = rs.getString("nome");
			String email = rs.getString("email");
			String telefone = rs.getString("telefone");
			System.out.println(nome + " - " + email + " - " + telefone);
		}

		rs.close();
		stmt.close();
		con.close();
	}
}
