package moduloTabela;

import java.util.ArrayList;
import java.util.List;

import entidades.Avaliacao;
import entidades.Grupo;
import exceptions.grupo.DescricaoGrupoInvalida;
import exceptions.grupo.GrupoInexistente;
import exceptions.grupo.LimiteMinimoInvalido;
import exceptions.grupo.NomeGrupoInvalido;
import exceptions.grupo.RegrasGrupoInvalida;

public class GrupoTabular {

	
	private List<Grupo> dataset;
	
	public GrupoTabular( List<Grupo> grupos){
		this.dataset = grupos;
	}
	
	public GrupoTabular(){
		
	}
	
	private boolean naoValido(String str){
		return (str == null || str.equals(""));
	}
	
	public Grupo findById(long idGrupo) throws GrupoInexistente{
		
		for (Grupo grupo : dataset) {
			if(idGrupo == grupo.obterIdGrupo())
				return grupo;
		}
		
		throw new GrupoInexistente();
	}

	public Grupo adicionarGrupo(String nome, String descricao, int limiteMinimo, String regras) throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, RegrasGrupoInvalida{
		
		if(limiteMinimo < 0)
			throw new LimiteMinimoInvalido();
		if(naoValido(nome))
			throw new NomeGrupoInvalido();
		if(naoValido(descricao))
			throw new DescricaoGrupoInvalida();
		if(naoValido(regras))
			throw new RegrasGrupoInvalida();
		
		Grupo grupo = new Grupo(0, nome, descricao, limiteMinimo, regras);
		
		return grupo;
	}

	public Grupo alterarGrupo(long idGrupo, String nome, String descricao, int limite) throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, GrupoInexistente {

		if(limite < 0)
			throw new LimiteMinimoInvalido();
		if(naoValido(nome))
			throw new NomeGrupoInvalido();
		if(naoValido(descricao))
			throw new DescricaoGrupoInvalida();
		
		Grupo grupo = findById(idGrupo);
		
		Grupo alt = new Grupo (grupo.obterIdGrupo(), nome, descricao,limite, grupo.obterRegras() , grupo.estaAtivo() );
		
		return alt;
		
	}
	
	public List<Grupo> obterGruposUsuarioAtivos(List<Avaliacao> avaliacoes, List<Grupo> grupos){
		
		int avaliacoesRuins = 0;
		
		for (Avaliacao avaliacao : avaliacoes) {
			if(avaliacao.obterAvaliacao() < 3){
				
				avaliacoesRuins++;
			}
		}
		
		System.out.println(avaliacoesRuins);
		List<Grupo> grupoUsuarioAtivo = new ArrayList<Grupo>();
		
		for (Grupo grupo2 : grupos) {
			System.out.println(grupo2.obterNome());
			if(avaliacoesRuins <= grupo2.obterLimiteMinimo())
				grupoUsuarioAtivo.add(grupo2);
			}

		
		return grupoUsuarioAtivo;
	}
	
}
