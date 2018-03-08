package teste.carona;

import java.util.ArrayList;
import java.util.List;

import moduloTabela.CaronaTabular;

import org.junit.Before;
import org.junit.Test;

import entidades.Carona;
import entidades.Veiculo;
import exceptions.carona.DiaInvalido;
import exceptions.carona.HoraInvalida;
import exceptions.carona.NaoHaVagasDisponiveis;
import exceptions.carona.ParadaInexistente;
import exceptions.carona.UsuarioParticipaDeCaronaNoMesmoHorario;
import exceptions.carona.VagasInvalida;
import exceptions.carona.VeiculoInexistente;
import exceptions.carona.VeiculoUtilizadoNoMesmoHorario;
import exceptions.grupo.GrupoInexistente;

public class CriarCaronaTeste {

	CaronaTabular carTab;
	List<Carona> caronasParticipa;
	List<Carona> caronasOfertadas;
	
	Veiculo v;

	@Before
	public void initialize(){
		
		Carona c = new Carona(1, 1, "2017-02-20", "15:15", 1, 1, 1, 1);
		Carona c1 = new Carona(2, 2, "2017-02-21", "16:16", 2, 2, 2, 2);
		
		caronasParticipa = new ArrayList<Carona>();	
		caronasOfertadas = new ArrayList<Carona>();	
		
		caronasParticipa.add(c1);
		caronasOfertadas.add(c);
		
		v = new Veiculo("Audi", "AUD-0000", "Vermelha", 2, 1);


		carTab = new CaronaTabular(caronasOfertadas);
		

	}
	
	
	
	@Test (expected = DiaInvalido.class)
	public void erroDiaCarona() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "", "15:20", 1, 2, 5, 5, 3);
	}
	
	@Test (expected = DiaInvalido.class)
	public void erroDiaCarona2() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, null, "15:20", 1, 2, 5, 5, 3);
	}


	@Test (expected = HoraInvalida.class)
	public void erroGrupoCarona() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "2017-08-15", "", 1, 2, 5, 5, 3);
	}
	
	@Test (expected = HoraInvalida.class)
	public void erroHoraCarona2() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "2017-08-15", null, 1, 2, 5, 5, 3);
	}
	
	@Test (expected = GrupoInexistente.class)
	public void erroGrupoInexistenteCarona() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "2017-08-15", "15:20", -1, 2, 5, 5, 3);
	}
	
	@Test (expected = VeiculoInexistente.class)
	public void erroVeiculoInexistenteCarona() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "2017-08-15", "15:20", 1, -2, 5, 5, 3);
	}
	
	@Test (expected = ParadaInexistente.class)
	public void erroParadaOrigemCarona() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "2017-08-15", "15:20", 1, 2, -5, 5, 3);
	}
	
	@Test (expected = ParadaInexistente.class)
	public void erroParadaDestinoCarona() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "2017-08-15", "15:20", 1, 2, 5, -5, 3);
	}
	
	@Test (expected = VagasInvalida.class)
	public void erroVagasCarona() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "2017-08-15", "15:20", 1, 2, 5, 5, -3);
	}
	
	@Test (expected = VeiculoUtilizadoNoMesmoHorario.class)
	public void erroVeiculoUtilizado() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "2017-02-20", "15:15", 1, 1, 5, 5, 2);
	}

	@Test (expected = UsuarioParticipaDeCaronaNoMesmoHorario.class)
	public void erroUsuarioVinculado() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "2017-02-21", "16:16", 2, 1, 5, 5, 2);
	}
	
	@Test
	public void agoraVai() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida{
		
		carTab.criarCarona(caronasParticipa, caronasOfertadas, "2017-08-15", "15:20", 1, 2, 5, 5, 3);
	}
	
	@Test (expected = NaoHaVagasDisponiveis.class)
	public void erroSemVagas() throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida, NaoHaVagasDisponiveis{
		
		carTab.validarVagas(3, v);
	}
	
}
