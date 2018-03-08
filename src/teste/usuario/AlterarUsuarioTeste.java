package teste.usuario;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import moduloTabela.UsuarioTabular;

import org.junit.Before;
import org.junit.Test;

import entidades.Usuario;
import exceptions.usuario.EmailUsuarioInvalido;
import exceptions.usuario.ExisteUsuarioComEmail;
import exceptions.usuario.NomeUsuarioInvalido;
import exceptions.usuario.SenhaUsuarioInvalido;
import exceptions.usuario.TelefoneUsuarioInvalido;
import exceptions.usuario.UsuarioInexistente;

public class AlterarUsuarioTeste {


	UsuarioTabular uTab;
	UsuarioTabular userTab;
	Usuario u;
	
	@Before
	public void initialize() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		
		uTab = new UsuarioTabular();
		u = new Usuario(1, "Defaul","default@default", "1234", "11111111");

//		Usuario usuario = uTab.insert("Defaul", "default@default", "1234", "11111111");
		List<Usuario> usuarios = new ArrayList <Usuario>();
		usuarios.add(u);
		
		userTab = new UsuarioTabular(usuarios);
		
	}	
	
	@Test (expected = ExisteUsuarioComEmail.class)
	public void erroPorEmail() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		userTab.insert("Nome", "default@default", "de", "0000000000");
	}
	
	@Test 
	public void acharUsuarioPorEmail() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		userTab.findByEmail("eu@eu.com");
	}
	
	@Test (expected = NomeUsuarioInvalido.class)
	public void alterarNomeUsuario() throws NomeUsuarioInvalido, TelefoneUsuarioInvalido, UsuarioInexistente{
		
		uTab.alterarUsuario(u, "", "11111111");
	}

	@Test (expected = NomeUsuarioInvalido.class)
	public void alterarNomeUsuario2() throws NomeUsuarioInvalido, TelefoneUsuarioInvalido, UsuarioInexistente{
		
		uTab.alterarUsuario(u, null, "11111111");
	}
	
	@Test (expected = TelefoneUsuarioInvalido.class)
	public void alterarTelefoneUsuario() throws NomeUsuarioInvalido, TelefoneUsuarioInvalido, UsuarioInexistente{
		
		uTab.alterarUsuario(u, "Alt", "");
	}
	
	@Test (expected = TelefoneUsuarioInvalido.class)
	public void alterarTelefoneUsuario2() throws NomeUsuarioInvalido, TelefoneUsuarioInvalido, UsuarioInexistente{
		
		uTab.alterarUsuario(u, "Alt", null);
	}
	
	@Test (expected = UsuarioInexistente.class)
	public void alterarUsuarioInexistente() throws NomeUsuarioInvalido, TelefoneUsuarioInvalido, UsuarioInexistente{
//		u1
		uTab.alterarUsuario(null, "Alt", "00000000");
	}
	
	@Test
	public void agoraVai() throws NomeUsuarioInvalido, TelefoneUsuarioInvalido, UsuarioInexistente{

		uTab.alterarUsuario(u, "Alt", "00000000");
	}
	
	
}
