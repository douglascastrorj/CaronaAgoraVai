package exceptions.carona;

public class VeiculoUtilizadoNoMesmoHorario extends Exception {
	@Override
	public String toString(){
		return "O Ve�culo j� encontra-se sendo utilizado para alguma carona neste hor�rio";
	}
}
