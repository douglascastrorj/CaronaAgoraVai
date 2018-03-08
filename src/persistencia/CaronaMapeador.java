package persistencia;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import conexaoBanco.ConnectionFactory;
import entidades.Carona;
import entidades.Grupo;
import entidades.Parada;
import entidades.Usuario;

public class CaronaMapeador {
	private static Connection connection;

	public CaronaMapeador()  {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Carona adicionar(Carona carona) {


		String sql = "insert into carona " +
				"(idVeiculo, data, horaSaida, vagas, paradaOrigem, paradaDestino, idGrupo, foiFinalizada)" +
				" values (?,?,?,?,?,?,?,?)";

		//		Carona c = null;
		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setLong(1, carona.obterIdVeiculo());
			stmt.setString(2, carona.obterData());
			stmt.setString(3, carona.obterHoraSaida());
			stmt.setInt (4, carona.obterVagas());
			stmt.setLong(5, carona.obterIdOrigem());
			stmt.setLong(6, carona.obterIdDestino());
			stmt.setLong(7, carona.obterIdGrupo());
			stmt.setBoolean(8, carona.foiFinalizada());

			// executa
			stmt.execute();
			stmt.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		Carona c = null;
		String sqlNextId = "SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'carona'";
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sqlNextId);
			// seta os valores
			ResultSet rs = stmt.executeQuery();

			
			while (rs.next()) {
			
				c = new Carona(rs.getLong("auto_increment") - 1, carona.obterIdVeiculo(), carona.obterData(), carona.obterHoraSaida(),
						carona.obterIdOrigem(), carona.obterIdDestino(), carona.obterVagas(), carona.obterIdGrupo(), carona.foiFinalizada());

			}
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		return c;
	}
	
	public void atualizar(Carona carona) {


		String sql = "update carona set idVeiculo = ?, data = ?, horaSaida = ?, vagas = ?, paradaOrigem = ?, paradaDestino = ? where id = ?";

		//		Carona c = null;
		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setLong(1, carona.obterIdVeiculo());
			stmt.setString(2, carona.obterData());
			stmt.setString(3, carona.obterHoraSaida());
			stmt.setInt (4, carona.obterVagas());
			stmt.setLong(5, carona.obterIdOrigem());
			stmt.setLong(6, carona.obterIdDestino());
			stmt.setLong(7, carona.obterIdCarona());


			// executa
			stmt.execute();
			stmt.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}
	
	
	public List<Carona> caronasOfertadasPor(long idUsuario){
		String sql = "select * from carona where carona.idVeiculo in ( select veiculo.id from veiculo,usuario WHERE veiculo.idUsuario = usuario.id and usuario.id = ? )";
		PreparedStatement stmt;

		List<Carona> caronas = new ArrayList<Carona>();
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement(sql);

			stmt.setLong(1, idUsuario);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				Carona c = new Carona(rs.getLong("id"),rs.getLong("idVeiculo"), rs.getString("data"), rs.getString("horaSaida"),
						rs.getLong("paradaOrigem"), rs.getLong("paradaDestino"), rs.getInt("vagas"),rs.getLong("idGrupo"), rs.getBoolean("foiFinalizada"));

				// adicionando o objeto à lista
				caronas.add(c);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return caronas;
	}
	
	
	public List<Carona> buscarCaronasUsuario(long idUsuario){
		
		String sql = "SELECT distinct carona.* FROM carona,usuario,parada where parada.idCarona = carona.id and parada.idUsuario = usuario.id and usuario.id = ? and carona.paradaOrigem <> parada.id and carona.paradaDestino <> parada.id";
		PreparedStatement stmt;

		List<Carona> caronas = new ArrayList<Carona>();
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement(sql);

			stmt.setLong(1, idUsuario);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Carona c = new Carona(rs.getLong("id"),rs.getLong("idVeiculo"), rs.getString("data"), rs.getString("horaSaida"),
						rs.getLong("paradaOrigem"), rs.getLong("paradaDestino"), rs.getInt("vagas"),rs.getLong("idGrupo"), rs.getBoolean("foiFinalizada"));

				// adicionando o objeto à lista
				caronas.add(c);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return caronas;
	}
	
	
	public List<Usuario> buscarUsuariosCarona(long idCarona) {
		PreparedStatement stmt;

		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("SELECT distinct usuario.* FROM carona,usuario,parada where parada.idCarona = carona.id and parada.idUsuario = usuario.id and carona.id = ?");

			stmt.setLong(1, idCarona);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				Usuario c = new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"));

				// adicionando o objeto à lista
				usuarios.add(c);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return usuarios;
	}


	public Carona buscarCarona(long idCarona) {
		PreparedStatement stmt;

		Carona carona = null;
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("select * from carona where id = ?");

			stmt.setLong(1, idCarona);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				Carona c = new Carona(rs.getLong("id"),rs.getLong("idVeiculo"), rs.getString("data"), rs.getString("horaSaida"),
						rs.getLong("paradaOrigem"), rs.getLong("paradaDestino"), rs.getInt("vagas"),rs.getLong("idGrupo"), rs.getBoolean("foiFinalizada"));

				// adicionando o objeto à lista
				carona = c;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return carona;
	}


	public List<Carona> buscarCaronasGrupo( long idGrupo) {
		PreparedStatement stmt;

		List<Carona> caronas = new ArrayList<Carona>();
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("select * from carona where idGrupo = ?");

			stmt.setLong(1, idGrupo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				Carona c = new Carona(rs.getLong("id"),rs.getLong("idVeiculo"), rs.getString("data"), rs.getString("horaSaida"),
						rs.getLong("paradaOrigem"), rs.getLong("paradaDestino"), rs.getInt("vagas"),rs.getLong("idGrupo"), rs.getBoolean("foiFinalizada"));

				// adicionando o objeto à lista
				caronas.add(c);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return caronas;
	}
	
	public void finalizar(long idCarona ){
		String sql = "update carona set foiFinalizada = TRUE where id = ?";
		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement(sql);

			stmt.setLong(1, idCarona);
			
			// executa
			stmt.execute();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int obterNumPassageirosCarona(long idCarona){
		String sql = "select count(parada.idCarona) as idCarona from parada where parada.idCarona = ?";
		PreparedStatement stmt;
		
		int numPassageiros = 0;
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement(sql);

			stmt.setLong(1, idCarona);
			ResultSet rs = stmt.executeQuery();

			
			while (rs.next()) {

				return rs.getInt("idCarona") - 2;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}


}
