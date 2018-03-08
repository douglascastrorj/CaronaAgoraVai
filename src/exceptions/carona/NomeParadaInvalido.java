package exceptions.carona;

@SuppressWarnings("serial")
public class NomeParadaInvalido extends Exception {

	@Override
	public String toString(){
		
		return "Nome deve conter algum caracter";
	}
}
