package exceptions.carona;

@SuppressWarnings("serial")
public class AvaliacaoParaCaronaInexistente extends Exception {

	@Override
	public String toString(){
		
		return "Não existe esta Carona";
	}
}
