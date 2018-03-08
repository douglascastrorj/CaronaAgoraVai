package moduloTabela;

import java.util.List;

import entidades.Logradouro;
import exceptions.logradouro.LogradouroInvalido;

public class LogradouroTabular {

	private List<Logradouro> dataset;
	public LogradouroTabular(List<Logradouro> logradouros) {
		// TODO Auto-generated constructor stub
		this.dataset = logradouros;
	}
	
	public LogradouroTabular() {
		// TODO Auto-generated constructor stub
	}

	private boolean naoValido(String str){
		return (str == null ||str.equals(""));
	}

	public Logradouro adicionarLogradouro(String cep, String uf, String cidade, String distrito, String endereco, String numero ) throws LogradouroInvalido{
		
		if(naoValido(distrito) || naoValido(numero) || naoValido(endereco) || naoValido(cidade) || naoValido(uf)  || naoValido(cep)){
			throw new LogradouroInvalido();
		}
		
		Logradouro logradouro = new Logradouro(0, cep, uf, cidade, distrito, endereco, numero);
		
		return logradouro;
			
	}

}
