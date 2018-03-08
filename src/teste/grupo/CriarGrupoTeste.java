package teste.grupo;

import moduloTabela.GrupoTabular;

import org.junit.Before;
import org.junit.Test;

import exceptions.grupo.DescricaoGrupoInvalida;
import exceptions.grupo.LimiteMinimoInvalido;
import exceptions.grupo.NomeGrupoInvalido;
import exceptions.grupo.RegrasGrupoInvalida;

public class CriarGrupoTeste {

	GrupoTabular gTab;
	
	@Before
	public void initialize(){
		
		gTab = new GrupoTabular();
	}

	@Test (expected = NomeGrupoInvalido.class)
	public void campoNomeInvalido() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, RegrasGrupoInvalida{
		
		gTab.adicionarGrupo("", "Descricao Bolada", 3, "My Body My Rules");
		
	}
	
	@Test (expected = NomeGrupoInvalido.class)
	public void campoNomeInvalido2() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, RegrasGrupoInvalida{
		
		gTab.adicionarGrupo(null, "Descricao Bolada", 3, "My Body My Rules");
		
	}
	
	@Test (expected = DescricaoGrupoInvalida.class)
	public void campoDescricaoInvalido() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, RegrasGrupoInvalida{
		
		gTab.adicionarGrupo("Bool", "", 3, "My Body My Rules");
		
	}
	
	@Test (expected = DescricaoGrupoInvalida.class)
	public void campoDescricaoInvalido2() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, RegrasGrupoInvalida{
		
		gTab.adicionarGrupo("Bool", null, 3, "My Body My Rules");
		
	}
	
	@Test (expected = LimiteMinimoInvalido.class)
	public void campoLimiteMinimoInvalido() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, RegrasGrupoInvalida{
		
		gTab.adicionarGrupo("Bool", "Descricao Bolada", -1, "My Body My Rules");
		
	}
	
	@Test (expected = RegrasGrupoInvalida.class)
	public void campoRegrasInvalido() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, RegrasGrupoInvalida{
		
		gTab.adicionarGrupo("Bool", "Descricao Bolada", 3, "");
		
	}
	
	@Test (expected = RegrasGrupoInvalida.class)
	public void campoRegrasInvalido2() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, RegrasGrupoInvalida{
		
		gTab.adicionarGrupo("Bool", "Descricao Bolada", 3, null);
		
	}
	
	@Test 
	public void agoraVai() throws LimiteMinimoInvalido, NomeGrupoInvalido, DescricaoGrupoInvalida, RegrasGrupoInvalida{
		
		gTab.adicionarGrupo("Bool", "Descricao Bolada", 3, "No Rules");
		
	}
}
