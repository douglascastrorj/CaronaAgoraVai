package exceptions.veiculo;

@SuppressWarnings("serial")
public class PlacaVeiculoInvalido extends Exception {

	@Override
	public String toString(){
		
		return "O Campo Placa n�o pode ser Vazio";
	}
	
}
