package exceptions.carona;

@SuppressWarnings("serial")
public class VagasInvalida extends Exception {

	@Override
	public String toString(){
		
		return "Vagas disponíveis deve ser um Número Não-Negativo";
	}
}
