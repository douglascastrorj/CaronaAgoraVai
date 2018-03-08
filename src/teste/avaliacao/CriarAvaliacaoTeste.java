package teste.avaliacao;

import moduloTabela.AvaliacaoTabular;

import org.junit.Before;
import org.junit.Test;

import exceptions.carona.AvaliacaoInvalida;
import exceptions.carona.AvaliacaoParaCaronaInexistente;
import exceptions.carona.AvaliacaoParaUsuarioInexistente;

public class CriarAvaliacaoTeste {

	AvaliacaoTabular aTab;
	
	@Before
	public void initialize(){
		
		aTab = new AvaliacaoTabular();
		
	}
	
	@Test (expected = AvaliacaoInvalida.class)
	public void avaliacaoErrada() throws AvaliacaoInvalida, AvaliacaoParaUsuarioInexistente, AvaliacaoParaCaronaInexistente{
		
		aTab.avaliar(0, -1, 0);
	}
	
	@Test (expected = AvaliacaoParaUsuarioInexistente.class)
	public void avaliacaoUsuarioInexistente() throws AvaliacaoInvalida, AvaliacaoParaUsuarioInexistente, AvaliacaoParaCaronaInexistente{
		
		aTab.avaliar(-1, 2, 1);
	}

	@Test (expected = AvaliacaoParaCaronaInexistente.class)
	public void avaliacaoCaronaInexistente() throws AvaliacaoInvalida, AvaliacaoParaUsuarioInexistente, AvaliacaoParaCaronaInexistente{
		
		aTab.avaliar(1, 2, -1);
	}
	
	@Test
	public void agoraVai() throws AvaliacaoInvalida, AvaliacaoParaUsuarioInexistente, AvaliacaoParaCaronaInexistente{
		
		aTab.avaliar(1, 5, 1);
	}
}
