package teste.logradouro;

import static org.junit.Assert.*;
import moduloTabela.LogradouroTabular;

import org.junit.Before;
import org.junit.Test;

import exceptions.logradouro.LogradouroInvalido;

public class CriarLogradouroTeste {

	LogradouroTabular logTab;
	
	@Before
	public void initialize(){
		
		logTab = new LogradouroTabular();
	}

	
	@Test (expected=LogradouroInvalido.class)
	public void campoCepInvalido() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro(null, "RJ", "Nova Iguaçu", "Nova Iguaçu", "Alguma Rua ai", "22");
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoCepInvalido2() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("", "RJ", "Nova Iguaçu", "Nova Iguaçu", "Alguma Rua ai", "22");
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoUfInvalido() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", null, "Nova Iguaçu", "Nova Iguaçu", "Alguma Rua ai", "22");
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoUf2Invalido() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", "", "Nova Iguaçu", "Nova Iguaçu", "Alguma Rua ai", "22");
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoCidadeInvalido() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", "RJ", null, "Nova Iguaçu", "Alguma Rua ai", "22");
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoCidadeInvalido2() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", "RJ", "", "Nova Iguaçu", "Alguma Rua ai", "22");
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoDistritoInvalido() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", "RJ", "Nova Iguaçu", null, "Alguma Rua ai", "22");
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoDistritoInvalido2() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", "RJ", "Nova Iguaçu", "", "Alguma Rua ai", "22");
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoEnderecoInvalido() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", "RJ", "Nova Iguaçu", "Nova Iguaçu", null, "22");
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoEnderecoInvalido2() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", "RJ", "Nova Iguaçu", "Nova Iguaçu", "", "22");
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoNumeroInvalido() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", "RJ", "Nova Iguaçu", "Nova Iguaçu", "Alguma Rua ai", null);
	}
	
	@Test (expected=LogradouroInvalido.class)
	public void campoNumeroInvalido2() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", "RJ", "Nova Iguaçu", "Nova Iguaçu", "Alguma Rua ai", "");
	}
	
	@Test 
	public void agoraVai() throws LogradouroInvalido{
		
		logTab.adicionarLogradouro("26011352", "RJ", "Nova Iguaçu", "Nova Iguaçu", "Alguma Rua ai", "22");
	}
}
