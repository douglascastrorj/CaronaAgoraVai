package exceptions.veiculo;

@SuppressWarnings("serial")
public class ExisteVeiculoComPlaca extends Exception {
	@Override
	public String toString(){
		return "Ja existe algum veículo com esta placa cadastrado (veículo clonado aconselhamos você a ir a polícia)";
	}
}
