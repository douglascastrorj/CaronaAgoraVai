package exceptions.carona;

@SuppressWarnings("serial")
public class OrigemDestinoIguais extends Exception {

	@Override
	public String toString(){
		
		return "Local de Origem e Destino da Carona n�o podem ser o mesmo";
	}
}
