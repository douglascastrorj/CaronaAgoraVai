package exceptions.veiculo;

public class NumeroDeVagasInvalido extends Exception {
	@Override
	public String toString(){
		return "Numero de vagas inválido (Campo deve ser maior que zero ).";
	}
}
