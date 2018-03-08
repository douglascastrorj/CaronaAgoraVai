package exceptions.veiculo;

@SuppressWarnings("serial")
public class PlacaVeiculoInvalido extends Exception {

	@Override
	public String toString(){
		
		return "O Campo Placa não pode ser Vazio";
	}
	
}
