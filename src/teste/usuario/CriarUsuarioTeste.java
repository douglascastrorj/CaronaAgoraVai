package teste.usuario;

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

public class CriarUsuarioTeste {

	
	UsuarioTabular uTab;
	
	@Before
	public void initialize(){
		
		
		uTab = new UsuarioTabular();
		
	}
	
	@Test (expected=NomeUsuarioInvalido.class)
	public void campoNomeInvalido() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		uTab.insert(null, "default@default", "1234", "11111111");
	}
	
	@Test (expected=NomeUsuarioInvalido.class)
	public void campoNomeInvalido2() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		uTab.insert("", "default@default", "1234", "11111111");
	}
	
	@Test (expected=TelefoneUsuarioInvalido.class)
	public void campoTelefoneInvalido() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		uTab.insert("Default", "default@default", "1234", null);
	}
	
	@Test (expected=TelefoneUsuarioInvalido.class)
	public void campoTelefoneInvalido2() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		uTab.insert("Default", "default@default", "1234", "");
	}
	
	@Test (expected=SenhaUsuarioInvalido.class)
	public void campoSenhaInvalido() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		uTab.insert("Default", "default@default", "", "11111111");
	}
	
	@Test (expected=SenhaUsuarioInvalido.class)
	public void campoSenhaInvalido2() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		uTab.insert("Default", "default@default", null, "11111111");
	}
	
	@Test (expected=EmailUsuarioInvalido.class)
	public void campoEmailInvalido() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		uTab.insert("Default", null, "1234", "11111111");
	}

	@Test (expected=EmailUsuarioInvalido.class)
	public void campoEmailInvalido2() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		uTab.insert("Default", "", "1234", "11111111");
	}
	
	@Test 
	public void agoraVai() throws ExisteUsuarioComEmail, NomeUsuarioInvalido, TelefoneUsuarioInvalido, SenhaUsuarioInvalido, EmailUsuarioInvalido{
		
		uTab.insert("Eu", "eu@eu.com", "1234", "99999999");
	}

	
}
