package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import conexaoBanco.ConnectionFactory;
import entidades.Grupo;
import entidades.Usuario;

public class GrupoMapeador {
	private static Connection connection;

	public GrupoMapeador()  {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Grupo adicionar(Grupo grupo) {

		String sql = "insert into grupo " +
				"(nome, descricao, regras, limiteMinimo, estaAtivo)" +
				" values (?,?,?,?,?)";

		Grupo g = null;
		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,grupo.obterNome());
			stmt.setString(2,grupo.obterDescricao());
			stmt.setString(3,grupo.obterRegras());
			stmt.setInt(4,grupo.obterLimiteMinimo());
			stmt.setBoolean(5, grupo.estaAtivo());



			// executa
			stmt.execute();
			stmt.close();

			String sqlGetGrupo = "(SELECT (auto_increment ) FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'grupo' )";
			PreparedStatement stmt2 = (PreparedStatement) connection.prepareStatement(sqlGetGrupo);

			ResultSet rs = stmt2.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				g = new Grupo(rs.getLong("auto_increment")-1, grupo.obterNome(), grupo.obterDescricao(), 
						grupo.obterLimiteMinimo(), grupo.obterRegras());

			}
			rs.close();
			stmt2.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return g;
	}

	public List<Grupo> buscarGruposUsuario(long idUsuario){
		String sql = "SELECT grupo.* FROM grupo,usuariogrupo,usuario WHERE grupo.id = usuariogrupo.idGrupo and usuario.id = usuariogrupo.idUsuario and usuario.id = ?";
		List<Grupo> grupos = new ArrayList<Grupo>();
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setLong(1, idUsuario);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				//new Grupo(id, nome, descricao, limiteMinimo, regras)
				Grupo grupo= new Grupo( rs.getLong("id"), rs.getString("nome"), rs.getString("descricao"),
						rs.getInt("limiteMinimo"), rs.getString("regras"), rs.getBoolean("estaAtivo"));
				grupos.add(grupo);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		return grupos;

	}


	public List<Usuario> buscarUsuariosGrupo(long idGrupo){
		String sql = "SELECT distinct usuario.* FROM usuario,grupo,usuariogrupo WHERE usuario.id = usuariogrupo.idUsuario and usuariogrupo.idGrupo = ?";


		List<Usuario> usuarios = new ArrayList<Usuario>();
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setLong(1, idGrupo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Usuario usuario = new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"));
				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		return usuarios;
	}

	public void adicionarUsuarioGrupo (Grupo grupo, Usuario usuario){


		String sql = "insert into usuariogrupo " +
				"(idUsuario, idGrupo)" +
				" values (?,?)";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setLong(1,usuario.obterIdUsuario());
			stmt.setLong(2,grupo.obterIdGrupo());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}


	}
	public void atualizar(Grupo grupo) {


		//		String sql = "UPDATE usuario SET descricao='" +usuario.getDescricao()
		//						+ "' WHERE id = "+usuario.getId();   
		String sql = "update grupo set nome = ?, descricao = ?, limiteMinimo = ? where id = ?";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,grupo.obterNome());
			stmt.setString(2,grupo.obterDescricao());
			stmt.setInt(3,grupo.obterLimiteMinimo());
			stmt.setLong(4,grupo.obterIdGrupo());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}


	}

	public List<Grupo> buscarTodosGrupos() {
		PreparedStatement stmt;

		List<Grupo> grupos = new ArrayList<Grupo>();
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("select * from grupo");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				Grupo grupo= new Grupo( rs.getLong("id"), rs.getString("nome"), rs.getString("descricao"),
						rs.getInt("limiteMinimo"), rs.getString("regras"), rs.getBoolean("estaAtivo"));

				// adicionando o objeto à lista
				grupos.add(grupo);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return grupos;
	}

	public Grupo buscarGrupo(long idGrupo) {
		PreparedStatement stmt;

		Grupo grupo = null;
		try {
			stmt = (PreparedStatement) this.connection
					.prepareStatement("select * from grupo where id = ?");

			stmt.setLong(1, idGrupo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// criando o objeto Contato
				Grupo g = new Grupo( rs.getLong("id"), rs.getString("nome"), rs.getString("descricao"),
						rs.getInt("limiteMinimo"), rs.getString("regras"), rs.getBoolean("estaAtivo"));

				// adicionando o objeto à lista
				grupo = g;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return grupo;
	}



}
