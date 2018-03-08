package exceptions.veiculo;

@SuppressWarnings("serial")
public class ModeloVeiculoInvalido extends Exception {

	@Override
	public String toString(){
		
		return "O Campo Modelo não pode ser Vazio";
	}
}
