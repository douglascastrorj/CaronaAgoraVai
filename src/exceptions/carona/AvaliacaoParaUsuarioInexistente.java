package exceptions.carona;

@SuppressWarnings("serial")
public class AvaliacaoParaUsuarioInexistente extends Exception {

	@Override
	public String toString(){
		
		return "N�o Existe este Usu�rio";
	}
}
