package moduloTabela;

import java.util.List;

import entidades.Usuario;
import exceptions.usuario.EmailUsuarioInvalido;
import exceptions.usuario.ExisteUsuarioComEmail;
import exceptions.usuario.NomeTelefoneInvalido;
import exceptions.usuario.NomeUsuarioInvalido;
import exceptions.usuario.SenhaUsuarioInvalido;
import exceptions.usuario.TelefoneUsuarioInvalido;
import exceptions.usuario.UsuarioInexistente;

public class UsuarioTabular {

	private List<Usuario> dataset;
	
	
	public UsuarioTabular(List<Usuario> usuarios) {
		// TODO Auto-generated constructor stub
		this.dataset = usuarios;
	}
	
	
	public UsuarioTabular() {
		// TODO Auto-generated constructor stub
	}


	public Usuario findById(long id) throws UsuarioInexistente{
		for(Usuario u : this.dataset){
			if(u.obterIdUsuario() == id)
				return u;
		}
		
		throw new UsuarioInexistente();
	}
	
	
	public Usuario findByEmail(String email) {
		for(Usuario u : this.dataset){
			if(u.obterEmailUsuario().equals(email))
				return u;
		}
		
		return null;
	}
	
	public boolean verificarSenha(Usuario usuario, String senha){
		if( senha.equals( usuario.obterSenhaUsuario() ) ){
			return true;
		}
		return false;
	}
	
	
	//nao deve existir usuarios com mesmo email
	public Usuario insert( String nome, String email, String senha, String telefone) throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		
		if (nome == null || nome.equals(""))
			throw new NomeUsuarioInvalido();
		
		if(telefone == null || telefone.equals(""))
			throw new TelefoneUsuarioInvalido();
		
		if(senha == null || senha.equals(""))
			throw new SenhaUsuarioInvalido();
		
		if(email == null || email.equals(""))
			throw new EmailUsuarioInvalido();
		
		Usuario usuario = findByEmail(email);
		
		if(usuario != null){
			throw new ExisteUsuarioComEmail();
		}
		else{
			
			usuario = new Usuario(0, nome, email, senha, telefone);
			
			return usuario;
		}
		
	}


	public Usuario alterarUsuario(Usuario usuario, String nome, String telefone) throws NomeUsuarioInvalido, TelefoneUsuarioInvalido, UsuarioInexistente  {
		// TODO Auto-generated method stub
		
		if (usuario == null)
			throw new UsuarioInexistente();
		
		if (nome == null || nome.equals(""))
			throw new NomeUsuarioInvalido();
		
		if(telefone == null || telefone.equals(""))
			throw new TelefoneUsuarioInvalido();
		
		Usuario usuarioAlterado = new Usuario(usuario.obterIdUsuario(), nome, 
												usuario.obterEmailUsuario(), telefone);
		
		
		return usuarioAlterado;
	}
	

}
