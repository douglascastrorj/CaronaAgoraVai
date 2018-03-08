package exceptions.carona;

public class VeiculoUtilizadoNoMesmoHorario extends Exception {
	@Override
	public String toString(){
		return "O Veículo já encontra-se sendo utilizado para alguma carona neste horário";
	}
}
