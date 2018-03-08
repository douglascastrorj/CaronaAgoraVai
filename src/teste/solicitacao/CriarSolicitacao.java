package teste.solicitacao;

import java.util.ArrayList;
import java.util.List;

import moduloTabela.SolicitacaoTabular;

import org.junit.Before;
import org.junit.Test;

import entidades.Solicitacao;
import exceptions.carona.CaronaNaoPodeSerSolicitada;
import exceptions.carona.SolicitanteInexistente;
import exceptions.carona.UsuarioJaSeCandidatouACarona;

public class CriarSolicitacao {

	SolicitacaoTabular solTab;
	List<Solicitacao> solicitacoes;
	
	@Before
	public void initialize(){
		solTab = new SolicitacaoTabular();
		
		solicitacoes = new ArrayList<Solicitacao>();
		
		solicitacoes.add(new Solicitacao(1, 1)); 
		solicitacoes.add(new Solicitacao(2, 1));
	}
	
	@Test (expected=SolicitanteInexistente.class)
	public void solicitaUsuarioInexistente() throws UsuarioJaSeCandidatouACarona, SolicitanteInexistente, CaronaNaoPodeSerSolicitada{
		
		solTab.incluirSolicitacao(-1, 1, solicitacoes);
	}
	
	@Test (expected=CaronaNaoPodeSerSolicitada.class)
	public void solicitaCaronaInexistente() throws UsuarioJaSeCandidatouACarona, SolicitanteInexistente, CaronaNaoPodeSerSolicitada{
		
		solTab.incluirSolicitacao(3, -1, solicitacoes);
	}

	@Test (expected=UsuarioJaSeCandidatouACarona.class)
	public void solicitaACaronaMaisDeUmaVez() throws UsuarioJaSeCandidatouACarona, SolicitanteInexistente, CaronaNaoPodeSerSolicitada{
		
		solTab.incluirSolicitacao(1, 1, solicitacoes);
	}
	
	@Test
	public void agoraVai() throws UsuarioJaSeCandidatouACarona, SolicitanteInexistente, CaronaNaoPodeSerSolicitada{
		
		solTab.incluirSolicitacao(3, 1, solicitacoes);
	}
}
