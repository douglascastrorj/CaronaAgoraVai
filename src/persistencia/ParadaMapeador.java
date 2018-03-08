package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import conexaoBanco.ConnectionFactory;
import entidades.Logradouro;
import entidades.Parada;

public class ParadaMapeador {
	private static Connection connection;

	public ParadaMapeador() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Parada adicionar(long idLogradouro, long idCarona, long idUsuario, String nome){
		
		String sql = "insert into parada " +
				"(idCarona, idUsuario, idLogradouro, nome)" +
				" values (?,?,?,?)";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setLong(1, idCarona);
			stmt.setLong(2, idUsuario);
			stmt.setLong(3, idLogradouro);
			stmt.setString(4, nome);
		
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		Parada p = null;
		String sqlNextId = "SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'parada'";
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sqlNextId);
			// seta os valores
			ResultSet rs = stmt.executeQuery();

			
			while (rs.next()) {
			
				p = new Parada(rs.getLong("auto_increment") - 1, idCarona, idLogradouro, idUsuario, nome);

			}
			
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		return p;

	}
	
	
	
public void atualizar(Parada parada) {

		
		String sql = "update parada set nome = ? where id = ?";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,parada.obterNome());
			stmt.setLong(2,parada.obterIdParada());


			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}


	}



	public void vincularParadaCarona(Parada parada, long idCarona) {
		// TODO Auto-generated method stub

		//		String sql = "UPDATE usuario SET descricao='" +usuario.getDescricao()
		//						+ "' WHERE id = "+usuario.getId();   
		String sql = "update parada set idCarona = ? where id = ?";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setLong(1,idCarona);
			stmt.setLong(2,parada.obterIdParada());
			

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public void deletarParada(long idParada){
		String sql = "delete from Parada where id = ?";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			
			stmt.setLong(1,idParada);


			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}
	
	
	public Parada buscarParadaPorId(long idParada) {
		PreparedStatement stmt;
		
		try {
			stmt = (PreparedStatement) this.connection
			        .prepareStatement("select * from parada where id = ?");
			stmt.setLong(1,idParada);
			
			ResultSet rs = stmt.executeQuery();
			
			Parada parada = null;
			while (rs.next()) {

			    // criando o objeto Contato
			   parada = new Parada(	rs.getLong("id"), rs.getLong("idCarona"),
					   						rs.getLong("idLogradouro"), rs.getLong("idUsuario"), 
					   						rs.getString("nome"));
			    return parada;
			}
			

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}
	
	
	public List<Parada> buscarParada(long idUsuario) {
		PreparedStatement stmt;
		
		List<Parada> paradas = new ArrayList<Parada>();
		try {
			stmt = (PreparedStatement) this.connection
			        .prepareStatement("select * from parada where idUsuario = ?");
			stmt.setLong(1,idUsuario);
			
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {

			    // criando o objeto Contato
			   Parada parada = new Parada(	rs.getLong("id"), rs.getLong("idCarona"),
					   						rs.getLong("idLogradouro"), rs.getLong("idUsuario"), 
					   						rs.getString("nome"));
			   
			   paradas.add(parada);
			    
			}
			

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}
		
		return paradas;
		
	}
	
	
	public List<Parada> buscarParadaCarona(long idCarona) {
		PreparedStatement stmt;
		
		List<Parada> paradas = new ArrayList<Parada>();
		try {
			stmt = (PreparedStatement) this.connection
			        .prepareStatement("select * from parada where idCarona = ?");
			stmt.setLong(1,idCarona);
			
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {

			    // criando o objeto Contato
			   Parada parada = new Parada(	rs.getLong("id"), rs.getLong("idCarona"),
					   						rs.getLong("idLogradouro"), rs.getLong("idUsuario"), 
					   						rs.getString("nome"));
			   
			   paradas.add(parada);
			    
			}
			

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}
		
		return paradas;
		
	}
	
	public List<Parada> buscarParadaCaronaUsuario(long idCarona, long idUsuario) {
		PreparedStatement stmt;
		
		List<Parada> paradas = new ArrayList<Parada>();
		try {
			stmt = (PreparedStatement) this.connection
			        .prepareStatement("select * from parada where idCarona = ? and idUsuario = ?");
			
			
			stmt.setLong(1,idCarona);
			stmt.setLong(2,idUsuario);

			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {

			    // criando o objeto Contato
			   Parada parada = new Parada(	rs.getLong("id"), rs.getLong("idCarona"),
					   						rs.getLong("idLogradouro"), rs.getLong("idUsuario"), 
					   						rs.getString("nome"));
			   
			   paradas.add(parada);
			    
			}
			

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}
		
		return paradas;
		
	}
}
