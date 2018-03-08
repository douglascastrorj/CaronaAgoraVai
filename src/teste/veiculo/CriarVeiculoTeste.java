package teste.veiculo;

import moduloTabela.VeiculoTabular;

import org.junit.Before;
import org.junit.Test;

import exceptions.veiculo.CorVeiculoInvalida;
import exceptions.veiculo.ExisteVeiculoComPlaca;
import exceptions.veiculo.ModeloVeiculoInvalido;
import exceptions.veiculo.NumeroDeVagasInvalido;
import exceptions.veiculo.PlacaVeiculoInvalido;

public class CriarVeiculoTeste {
	
	VeiculoTabular veiculoTab;
	
	@Before
	public void initialize(){
		veiculoTab = new VeiculoTabular();

	}

	@Test(expected = NumeroDeVagasInvalido.class)
	public void campoVagasInvalido() throws CorVeiculoInvalida, ExisteVeiculoComPlaca, NumeroDeVagasInvalido, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
				
		veiculoTab.inserir("Mercedes C200", "KKK-6666", "Vermelho", -1);
		
	}
	
	@Test(expected = ModeloVeiculoInvalido.class)
	public void campoModeloInvalido() throws CorVeiculoInvalida, ExisteVeiculoComPlaca, NumeroDeVagasInvalido, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
			
		veiculoTab.inserir("", "KKK-6666", "Vermelho", 5);
		
	}
	
	@Test(expected = ModeloVeiculoInvalido.class)
	public void campoModeloInvalido2() throws CorVeiculoInvalida, ExisteVeiculoComPlaca, NumeroDeVagasInvalido, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
			
		veiculoTab.inserir(null, "KKK-6666", "Vermelho", 5);
		
	}
	
	@Test(expected = PlacaVeiculoInvalido.class)
	public void campoPlacaInvalido() throws CorVeiculoInvalida, ExisteVeiculoComPlaca, NumeroDeVagasInvalido, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
		
		
		veiculoTab.inserir("Mercedes C200", "", "Vermelho", 2);
		
	}
	
	@Test(expected = PlacaVeiculoInvalido.class)
	public void campoPlacaInvalido2() throws CorVeiculoInvalida, ExisteVeiculoComPlaca, NumeroDeVagasInvalido, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
		
		
		veiculoTab.inserir("Mercedes C200", null, "Vermelho", 2);
		
	}
	
	@Test(expected = CorVeiculoInvalida.class)
	public void campoCorInvalido() throws CorVeiculoInvalida, ExisteVeiculoComPlaca, NumeroDeVagasInvalido, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
		
		
		veiculoTab.inserir("Mercedes C200", "KKK-6666", "", 2);
		
	}
	
	@Test(expected = CorVeiculoInvalida.class)
	public void campoCorInvalido2() throws CorVeiculoInvalida, ExisteVeiculoComPlaca, NumeroDeVagasInvalido, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
		
		
		veiculoTab.inserir("Mercedes C200", "KKK-6666", null, 2);
		
	}
	
	@Test
	public void agoraVai() throws CorVeiculoInvalida, ExisteVeiculoComPlaca, NumeroDeVagasInvalido, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
		
		
		veiculoTab.inserir("Mercedes C200", "KKK-6666", "Prata", 2);
		
	}
	
	
}
