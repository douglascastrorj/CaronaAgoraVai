package teste.veiculo;

import java.util.ArrayList;
import java.util.List;

import moduloTabela.VeiculoTabular;

import org.junit.Before;
import org.junit.Test;

import entidades.Veiculo;
import exceptions.veiculo.CorVeiculoInvalida;
import exceptions.veiculo.ExisteVeiculoComPlaca;
import exceptions.veiculo.ModeloVeiculoInvalido;
import exceptions.veiculo.NumeroDeVagasInvalido;
import exceptions.veiculo.PlacaVeiculoInvalido;


public class AlterarVeiculoTeste {

	
	List<Veiculo> veiculos;
	VeiculoTabular veiculoTab;
	
	@Before
	public void initialize(){
		
		veiculos = new ArrayList<Veiculo>();
		veiculos.add(new Veiculo("Mercedes", "ABC-0000", "Vermelha", 3, 1));
		
		veiculoTab = new VeiculoTabular(veiculos);

	}
	
	@Test (expected = ExisteVeiculoComPlaca.class)
	public void veiculoComPlacaIgual() throws ExisteVeiculoComPlaca, NumeroDeVagasInvalido, CorVeiculoInvalida, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
		
		veiculoTab.inserir("Audi A3", "ABC-0000", "Vermelha", 3);
		
		
	}

	@Test (expected = CorVeiculoInvalida.class)
	public void alterarVeiculoCorInvalida() throws ExisteVeiculoComPlaca, NumeroDeVagasInvalido, CorVeiculoInvalida, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
		
		veiculoTab.alterarVeiculo(veiculos.get(0), "");
		
		
	}
	
	@Test (expected = CorVeiculoInvalida.class)
	public void alterarVeiculoCorInvalida2() throws ExisteVeiculoComPlaca, NumeroDeVagasInvalido, CorVeiculoInvalida, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
		
		veiculoTab.alterarVeiculo(veiculos.get(0), null);
		
		
	}
	
	@Test
	public void agoraVai() throws ExisteVeiculoComPlaca, NumeroDeVagasInvalido, CorVeiculoInvalida, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
		
		veiculoTab.alterarVeiculo(veiculos.get(0), "Vinho");
		
		
	}
}
