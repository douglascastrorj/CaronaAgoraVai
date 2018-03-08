package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import conexaoBanco.ConnectionFactory;
import entidades.Logradouro;
import entidades.Usuario;

public class LogradouroMapeador {
	private static Connection connection;

	public LogradouroMapeador() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Logradouro adicionar(Logradouro logradouro) {


		String sql = "insert into logradouro " +
				"(cep, estado,distrito,cidade,endereco,numero)" +
				" values (?,?,?,?,?,?)";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,logradouro.obterCep());
			stmt.setString(2,logradouro.obterEstado());
			stmt.setString(3,logradouro.obterDistrito());
			stmt.setString(4,logradouro.obterCidade());
			stmt.setString(5,logradouro.obterEndereco());
			stmt.setString(6,logradouro.obterNumero());



			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		Logradouro l = null;
		String sqlNextId = "SELECT `auto_increment`  FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'logradouro'";
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sqlNextId);
			// seta os valores
			ResultSet rs = stmt.executeQuery();

			
			while (rs.next()) {

				
				l = new Logradouro(	rs.getLong("auto_increment") -1, logradouro.obterCep(), logradouro.obterEstado(), 
									logradouro.obterCidade(), logradouro.obterDistrito(), logradouro.obterEndereco(), 
									logradouro.obterNumero());

			}
			
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		return l;

	}

	public void atualizar(Logradouro logradouro) {

		
		String sql = "update logradouro set cep = ?, estado = ?, cidade = ?, distrito = ?, endereco = ?, numero = ? where id = ?";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,logradouro.obterCep());
			stmt.setString(2,logradouro.obterEstado());
			stmt.setString(3,logradouro.obterCidade());
			stmt.setString(4,logradouro.obterDistrito());
			stmt.setString(5,logradouro.obterEndereco());
			stmt.setString(6,logradouro.obterNumero());
			stmt.setLong(7,logradouro.obterIdLogradouro());


			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}


	}

	public void deletarLogradouro(long idLogradouro){
		String sql = "delete from logradouro where id = ?";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			
			stmt.setLong(1,idLogradouro);


			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	public List<Logradouro> buscarTodosLogradouros() {
		PreparedStatement stmt;

		List<Logradouro> logradouros = new ArrayList<Logradouro>();
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("select * from logradouro");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				Logradouro logradouro = new Logradouro(rs.getLong("id"), rs.getString("cep"),
														rs.getString("estado"),rs.getString("cidade"),
														rs.getString("distrito"),rs.getString("endereco"),
														rs.getString("numero"));

				// adicionando o objeto à lista
				logradouros.add(logradouro);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return logradouros;
	}
	
	
	public Logradouro buscarLogradouro(long idLogradouro) {
		PreparedStatement stmt;

		Logradouro logradouro = null;
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("select * from logradouro where id = ?");

			stmt.setLong(1, idLogradouro);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				logradouro = new Logradouro(rs.getLong("id"), rs.getString("cep"),
														rs.getString("estado"),rs.getString("cidade"),
														rs.getString("distrito"),rs.getString("endereco"),
														rs.getString("numero"));

				// adicionando o objeto à lista
				//logradouros.add(logradouro);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return logradouro;
	}


	
	private boolean validar(Logradouro logradouro){

		return true;
	}
}
