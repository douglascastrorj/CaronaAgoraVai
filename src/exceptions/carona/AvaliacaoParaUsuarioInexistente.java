package exceptions.carona;

@SuppressWarnings("serial")
public class AvaliacaoParaUsuarioInexistente extends Exception {

	@Override
	public String toString(){
		
		return "Não Existe este Usuário";
	}
}
