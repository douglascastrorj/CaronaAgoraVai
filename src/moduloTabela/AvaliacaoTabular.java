package moduloTabela;

import java.util.List;

import entidades.Avaliacao;
import exceptions.carona.AvaliacaoInvalida;
import exceptions.carona.AvaliacaoParaCaronaInexistente;
import exceptions.carona.AvaliacaoParaUsuarioInexistente;
import exceptions.carona.UsuarioJaAvaliadoParaEstaCarona;

public class AvaliacaoTabular {
	
	public AvaliacaoTabular(){
		
	}
	
	public Avaliacao avaliar(long idUsuario, int estrelas, long idCarona) throws AvaliacaoInvalida, AvaliacaoParaUsuarioInexistente, AvaliacaoParaCaronaInexistente{
		
		if(idUsuario < 0)
			throw new AvaliacaoParaUsuarioInexistente();
		
		if(idCarona < 0)
			throw new AvaliacaoParaCaronaInexistente();
		
		if(estrelas < 1 || estrelas > 5)
			throw new AvaliacaoInvalida();
		
		return new Avaliacao(0, estrelas, idUsuario, idCarona);
	}
}
