package exceptions.carona;

@SuppressWarnings("serial")
public class SolicitanteInexistente extends Exception {

	@Override
	public String toString(){
		
		return "Solicitação Proveniente de um Usuário Inexistente";
	}
}
