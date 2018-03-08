package exceptions.carona;

@SuppressWarnings("serial")
public class ParadaInexistente extends Exception {

	@Override
	public String toString(){
		
		return "Parada não Encontrada";
	}
}

