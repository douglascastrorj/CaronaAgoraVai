package moduloTabela;

import java.util.List;

import entidades.Solicitacao;
import exceptions.carona.CaronaNaoPodeSerSolicitada;
import exceptions.carona.SolicitanteInexistente;
import exceptions.carona.UsuarioJaSeCandidatouACarona;

public class SolicitacaoTabular {
	
	public Solicitacao incluirSolicitacao(long idUsuario,long idCarona,List<Solicitacao> solicitacoes) throws UsuarioJaSeCandidatouACarona, SolicitanteInexistente, CaronaNaoPodeSerSolicitada {
		// TODO Auto-generated method stub
		
		if(idUsuario < 0 || (Long)idUsuario == null)
			throw new SolicitanteInexistente();
		
		if(idCarona < 0 || (Long)idCarona == null)
			throw new CaronaNaoPodeSerSolicitada();
		
		
		for(Solicitacao s : solicitacoes){
			if(s.obterIdUsuario() == idUsuario){
				throw new UsuarioJaSeCandidatouACarona();
			}
		}
		
		return new Solicitacao(idUsuario, idCarona);
	}
	
	

}
