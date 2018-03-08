package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import conexaoBanco.ConnectionFactory;
import entidades.Usuario;

public class UsuarioMapeador {
	private static Connection connection;

	public UsuarioMapeador()  {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adicionar(Usuario usuario) {
		
		if(!validar(usuario)){
			System.out.println("usuario invalido");
			return;
		}
		
		String sql = "insert into usuario " +
				"(nome, email, telefone,senha)" +
				" values (?,?,?,?)";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,usuario.obterNomeUsuario());
			stmt.setString(2,usuario.obterEmailUsuario());
			stmt.setString(3,usuario.obterTelefoneUsuario());
			stmt.setString(4,usuario.obterSenhaUsuario());


			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}


	}
	
	public void atualizar(Usuario usuario) {
		
		if(!validar(usuario)) return;
		
//		String sql = "UPDATE usuario SET descricao='" +usuario.getDescricao()
//						+ "' WHERE id = "+usuario.getId();   
		String sql = "update usuario set nome = ?, telefone = ?  where id = ?";

		//prepared statement para inserção
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1,usuario.obterNomeUsuario());
			stmt.setString(2,usuario.obterTelefoneUsuario());
			stmt.setLong(3,usuario.obterIdUsuario());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}


	}

	public Usuario buscarUsuario(long idUsuario) {
		PreparedStatement stmt;
		
		Usuario usuario = null;
		try {
			stmt = (PreparedStatement) this.connection
			        .prepareStatement("select * from usuario where id = ?");
			stmt.setLong(1,idUsuario);
			
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {

			    // criando o objeto Contato
			    Usuario u = new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("email"),rs.getString("senha"), rs.getString("telefone") );

			    // adicionando o objeto à lista
			    usuario = u;
			    
			}
			

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}
		
		return usuario;
		
	}
	
	
	public Usuario buscarUsuario(String email) {
		PreparedStatement stmt;
		
		Usuario usuario = null;
		try {
			stmt = (PreparedStatement) this.connection
			        .prepareStatement("select * from usuario where email = ?");
			stmt.setString(1,email);
			
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {

			    // criando o objeto Contato
			    Usuario u = new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("email"),rs.getString("senha"), rs.getString("telefone") );

			    // adicionando o objeto à lista
			    usuario = u;
			    
			}
			

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}
		
		return usuario;
		
	}
	
	
	public List<Usuario> buscarTodosUsuarios() {
		PreparedStatement stmt;
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			stmt = (PreparedStatement) this.connection
			        .prepareStatement("select * from usuario");
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

			    // criando o objeto Contato
				Usuario usuario = new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("email"),rs.getString("senha"), rs.getString("telefone") );

			    // adicionando o objeto à lista
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
	
	

	private boolean validar(Usuario usuario){

		if(usuario.obterNomeUsuario() == null || usuario.obterNomeUsuario().equals("")){
			return false;
		}
		else if(usuario.obterEmailUsuario() == null || usuario.obterEmailUsuario().equals("") ){
			return false;
		}
		else if(usuario.obterTelefoneUsuario() == null || usuario.obterTelefoneUsuario().equals("")){
			return false;
		}

		return true;
	}
}
