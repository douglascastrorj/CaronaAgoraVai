package exceptions.carona;

@SuppressWarnings("serial")
public class AvaliacaoParaCaronaInexistente extends Exception {

	@Override
	public String toString(){
		
		return "N�o existe esta Carona";
	}
}
