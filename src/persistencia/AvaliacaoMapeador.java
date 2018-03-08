package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import conexaoBanco.ConnectionFactory;
import entidades.Avaliacao;
import entidades.Usuario;

public class AvaliacaoMapeador {

	private static Connection connection;

	public AvaliacaoMapeador() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adicionar(Avaliacao avaliacao) {
		
		
		String sql = "insert into avaliacao " +
				"(estrelas, idUsuario, idCarona)" +
				" values (?,?,?)";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setInt(1,avaliacao.obterAvaliacao());
			stmt.setLong(2,avaliacao.obterIdUsuario());
			stmt.setLong(3,avaliacao.obterIdCarona());
			


			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}


	}
	
	
	public List<Avaliacao> buscarTodasAvaliacoesUsuarioCarona(long idUsuario, long idCarona) {
		PreparedStatement stmt;
		
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		try {
			stmt = (PreparedStatement) this.connection
			        .prepareStatement("select * from avaliacao where idUsuario = ? and idCarona = ?");
			
			stmt.setLong(1,  idUsuario);
			stmt.setLong(2,  idCarona);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

			    // criando o objeto Contato
				Avaliacao avaliacao = new Avaliacao(rs.getLong("id"), rs.getInt("estrelas"), rs.getLong("idUsuario"),rs.getLong("idCarona"));
			    
			    // adicionando o objeto à lista
			    avaliacoes.add(avaliacao);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return avaliacoes;
	}
	
	public List<Avaliacao> buscarTodasAvaliacoesUsuario(long idUsuario) {
		PreparedStatement stmt;
		
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		try {
			stmt = (PreparedStatement) this.connection
			        .prepareStatement("select * from avaliacao where idUsuario = ?");
			
			stmt.setLong(1,  idUsuario);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

			    // criando o objeto Contato
				Avaliacao avaliacao = new Avaliacao(rs.getLong("id"), rs.getInt("estrelas"), rs.getLong("idUsuario"),rs.getLong("idCarona"));
			    
			    // adicionando o objeto à lista
			    avaliacoes.add(avaliacao);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return avaliacoes;
	}

}
