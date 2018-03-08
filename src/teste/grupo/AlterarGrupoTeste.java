package teste.grupo;

import java.util.ArrayList;
import java.util.List;

import moduloTabela.GrupoTabular;

import org.junit.Before;
import org.junit.Test;

import entidades.Grupo;
import exceptions.grupo.DescricaoGrupoInvalida;
import exceptions.grupo.GrupoInexistente;
import exceptions.grupo.LimiteMinimoInvalido;
import exceptions.grupo.NomeGrupoInvalido;

public class AlterarGrupoTeste {

	List<Grupo> grupos;
	GrupoTabular grupoTab;
	
	@Before
	public void initialize(){
		
		grupos = new ArrayList<Grupo>();
		grupos.add(new Grupo(0, "Nome", "Descricao", 3, "Regras do Grupo"));
		grupoTab = new GrupoTabular(grupos);
		
	}
	
	
	@Test (expected = NomeGrupoInvalido.class)
	public void alterarGrupoNomeInvalido() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, GrupoInexistente {
		
		grupoTab.alterarGrupo(grupos.get(0).obterIdGrupo(), "", grupos.get(0).obterDescricao(), grupos.get(0).obterLimiteMinimo());
	}
	
	@Test (expected = NomeGrupoInvalido.class)
	public void alterarGrupoNomeInvalido2() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, GrupoInexistente {
		
		grupoTab.alterarGrupo(grupos.get(0).obterIdGrupo(),  null, grupos.get(0).obterDescricao(), grupos.get(0).obterLimiteMinimo());
	}

	@Test (expected = DescricaoGrupoInvalida.class)
	public void alterarGrupoDescricaoInvalida() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, GrupoInexistente {
		
		grupoTab.alterarGrupo(grupos.get(0).obterIdGrupo(), grupos.get(0).obterNome(), "", grupos.get(0).obterLimiteMinimo());
	}
	
	@Test (expected = DescricaoGrupoInvalida.class)
	public void alterarGrupoDescricaoInvalida2() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, GrupoInexistente {
		
		grupoTab.alterarGrupo(grupos.get(0).obterIdGrupo(), grupos.get(0).obterNome(), null, grupos.get(0).obterLimiteMinimo());
	}
	
	@Test (expected = LimiteMinimoInvalido.class)
	public void alterarGrupoLimiteInvalido() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, GrupoInexistente {
		
		grupoTab.alterarGrupo(grupos.get(0).obterIdGrupo(),  grupos.get(0).obterNome(), grupos.get(0).obterDescricao(), -1);
	}
	
	@Test (expected = GrupoInexistente.class)
	public void alterarGrupoInexistente() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, GrupoInexistente {
		
		grupoTab.alterarGrupo(2,  "NovoNome", "NovaDescricao", 1);
	}
	
	@Test (expected = GrupoInexistente.class)
	public void procurarGrupoPorId() throws GrupoInexistente{
		
		grupoTab.findById(2);
	}
	
	@Test
	public void agoraVai() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, GrupoInexistente {
		
		grupoTab.alterarGrupo(grupos.get(0).obterIdGrupo(),  "NovoNome", "Nova Descricao", 1);
	}
	
}
