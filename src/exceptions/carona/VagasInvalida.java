package exceptions.carona;

@SuppressWarnings("serial")
public class VagasInvalida extends Exception {

	@Override
	public String toString(){
		
		return "Vagas dispon�veis deve ser um N�mero N�o-Negativo";
	}
}
