package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import conexaoBanco.ConnectionFactory;
import entidades.Motorista;
import entidades.Usuario;
import entidades.Veiculo;

public class VeiculoMapeador {
	private static Connection connection;

	public VeiculoMapeador() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adicionar(Veiculo veiculo, Usuario usuario) {

		String sql = "insert into veiculo " +
				"(modelo,placa,cor,vagas,idUsuario)" +
				" values (?,?,?,?,?)";

		//prepared statement para inser
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,veiculo.obterModelo());
			stmt.setString(2,veiculo.obterPlaca());
			stmt.setString(3,veiculo.obterCor());
			stmt.setInt(4,veiculo.obterNumVagas());
			stmt.setLong(5,usuario.obterIdUsuario());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}


	}

	public void atualizar(Veiculo veiculo) {


		//		String sql = "UPDATE mobilia SET descricao='" +mobilia.getDescricao()
		//						+ "' WHERE id = "+mobilia.getId();   
		String sql = "update veiculo set cor = ? where id = ?";

		//prepared statement para insero
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,veiculo.obterCor());
			stmt.setLong(2,veiculo.obterId());


			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}


	}

	public Veiculo buscarVeiculo(long idVeiculo) {
		PreparedStatement stmt;

		Veiculo veiculo = null;
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("select * from veiculo where id = ?");

			stmt.setLong(1, idVeiculo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				veiculo = new Veiculo(rs.getLong("id"),rs.getString("modelo"),rs.getString("placa"),rs.getString("cor"), rs.getInt("vagas") ,rs.getLong("idUsuario"));

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return veiculo;
	}

	public List<Veiculo> buscarTodosVeiculos() {
		PreparedStatement stmt;

		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("select * from veiculo");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				Veiculo veiculo = new Veiculo(rs.getLong("id"),rs.getString("modelo"),rs.getString("placa"),rs.getString("cor"), rs.getInt("vagas") ,rs.getLong("idUsuario"));

				// adicionando o objeto  lista
				veiculos.add(veiculo);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return veiculos;
	}
	
	
	public List<Veiculo> buscarVeiculosUsuario( long idUsuario) {
		PreparedStatement stmt;

		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("select * from veiculo where idUsuario = ?");
			stmt.setLong(1, idUsuario);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				Veiculo veiculo = new Veiculo(rs.getLong("id"),rs.getString("modelo"),rs.getString("placa"),rs.getString("cor"), rs.getInt("vagas") ,rs.getLong("idUsuario"));

				// adicionando o objeto  lista
				veiculos.add(veiculo);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return veiculos;
	}
	
	
}
