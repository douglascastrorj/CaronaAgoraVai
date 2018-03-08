package moduloTabela;

import java.util.List;

import entidades.Carona;
import entidades.Parada;
import exceptions.carona.NomeParadaInvalido;
import exceptions.carona.UsuarioNaoPertenceACarona;

public class ParadaTabular {

	
	List<Parada> dataset;
	
	public ParadaTabular(List<Parada> paradasCaronasUsuario){
		
		this.dataset = paradasCaronasUsuario;
	}
	
	
	public ParadaTabular(){
		
		
	}
	
	public Parada criarParada(String nome, long idUsuario, long idCarona, long idLogradouro, long idParada) throws NomeParadaInvalido{
		
		if (nome == null || nome.equals(""))
			throw new NomeParadaInvalido();
		
		
		return new Parada(idParada, idCarona, idLogradouro, idUsuario, nome);
	}
	
	
	public Parada buscarUsuarioParada(long idUsuario) throws UsuarioNaoPertenceACarona{
		
		for (Parada p : dataset) {
			
			if(p.obterIdUsuario() == idUsuario)
				return p;
		}
		
		throw new UsuarioNaoPertenceACarona();
		
	}
}
