package exceptions.carona;

@SuppressWarnings("serial")
public class SolicitanteInexistente extends Exception {

	@Override
	public String toString(){
		
		return "Solicita��o Proveniente de um Usu�rio Inexistente";
	}
}
