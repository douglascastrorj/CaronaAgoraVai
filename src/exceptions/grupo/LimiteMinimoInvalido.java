package exceptions.grupo;

public class LimiteMinimoInvalido extends Exception {
	@Override
	public String toString(){
		return "Limite Minimo Para Cria��o do grupo inv�lido. (O limite deve ser maior que zero)";
	}
}
