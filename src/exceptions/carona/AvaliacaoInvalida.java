package exceptions.carona;

public class AvaliacaoInvalida extends Exception {
	@Override
	public String toString(){
		return "N�mero de estrelas deve estar entre 1 e 5";
	}
}
