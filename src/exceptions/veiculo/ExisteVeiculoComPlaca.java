package exceptions.veiculo;

@SuppressWarnings("serial")
public class ExisteVeiculoComPlaca extends Exception {
	@Override
	public String toString(){
		return "Ja existe algum ve�culo com esta placa cadastrado (ve�culo clonado aconselhamos voc� a ir a pol�cia)";
	}
}
