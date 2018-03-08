package exceptions.grupo;

public class LimiteMinimoInvalido extends Exception {
	@Override
	public String toString(){
		return "Limite Minimo Para Criação do grupo inválido. (O limite deve ser maior que zero)";
	}
}
