package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import conexaoBanco.ConnectionFactory;
import entidades.Solicitacao;
import entidades.Veiculo;

public class SolicitacaoMapeador {
	private static Connection connection;

	public SolicitacaoMapeador() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adicionar(Solicitacao solicitacao) {

		String sql = "insert into solicitacao " +
				"(idCarona,idUsuario,data)" +
				" values (?,?,?)";

		//prepared statement para inser
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setLong(1,solicitacao.obterIdCarona());
			stmt.setLong(2,solicitacao.obterIdUsuario());
			stmt.setString(3,solicitacao.obterData());
			
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public void aprovar(long idCarona, long idPassageiro){
		String sql = "update solicitacao set foiAprovada = true where idCarona = ? and idUsuario = ?";
		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement(sql);

			stmt.setLong(1, idCarona);
			stmt.setLong(2, idPassageiro);
			
			// executa
			stmt.execute();
			stmt.close();
		}catch(Exception e){
			
		}
	}
	
	public List<Solicitacao> buscarSolicitacoesCarona(long idCarona) {
		PreparedStatement stmt;

		List<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("select * from solicitacao where idCarona = ? and foiAprovada = 0");

			stmt.setLong(1, idCarona);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				Solicitacao s = new Solicitacao(rs.getLong("idUsuario"), rs.getLong("idCarona"));

				// adicionando o objeto  lista
				solicitacoes.add(s);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return solicitacoes;
	}

}
