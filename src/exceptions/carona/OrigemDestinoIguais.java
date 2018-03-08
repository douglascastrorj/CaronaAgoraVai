package exceptions.carona;

@SuppressWarnings("serial")
public class OrigemDestinoIguais extends Exception {

	@Override
	public String toString(){
		
		return "Local de Origem e Destino da Carona não podem ser o mesmo";
	}
}
