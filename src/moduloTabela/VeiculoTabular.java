package moduloTabela;

import java.util.List;

import entidades.Veiculo;
import exceptions.veiculo.CorVeiculoInvalida;
import exceptions.veiculo.ExisteVeiculoComPlaca;
import exceptions.veiculo.ModeloVeiculoInvalido;
import exceptions.veiculo.NumeroDeVagasInvalido;
import exceptions.veiculo.PlacaVeiculoInvalido;

public class VeiculoTabular {

	private List<Veiculo> dataset;
	
	
	public VeiculoTabular(List<Veiculo> veiculos) {
		// TODO Auto-generated constructor stub
		this.dataset = veiculos;
	}
	
	
	public VeiculoTabular() {
		// TODO Auto-generated constructor stub
	}
	
	public Veiculo inserir(String modelo, String placa, String cor, int vagas) throws ExisteVeiculoComPlaca, NumeroDeVagasInvalido, CorVeiculoInvalida, ModeloVeiculoInvalido, PlacaVeiculoInvalido{
		
		if(vagas <= 0)
			throw new NumeroDeVagasInvalido();
		
		if(modelo == null || modelo.equals(""))
			throw new ModeloVeiculoInvalido();
		
		if(placa == null || placa.equals(""))
			throw new PlacaVeiculoInvalido();	
		
		if(cor == null || cor.equals(""))
				throw new CorVeiculoInvalida();
		
//		boolean existeVeiculoComPlaca = false;
		if(dataset == null || dataset.size() == 0 ){
			System.out.println("dataset eh nulo");
			return new Veiculo(0, modelo, placa, cor,vagas,0);
		}
		
		System.out.println("dataset com dados");
		for(Veiculo v : dataset){
			if(v.obterPlaca().equals(placa))
				throw new ExisteVeiculoComPlaca();
		}
		
		return new Veiculo(0, modelo, placa, cor,vagas,0);
		
	}


	public Veiculo alterarVeiculo(Veiculo veiculo, String cor) throws CorVeiculoInvalida {
		// TODO Auto-generated method stub
		
		if(cor == null || cor.equals(""))
			throw new CorVeiculoInvalida();
		
		Veiculo v = new Veiculo(veiculo.obterId(), veiculo.obterModelo(), veiculo.obterPlaca(), cor, veiculo.obterNumVagas() ,veiculo.obterIdMotorista());
		
		return v;
	}


}
