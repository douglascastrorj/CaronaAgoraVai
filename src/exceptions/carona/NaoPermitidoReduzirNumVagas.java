package exceptions.carona;

@SuppressWarnings("serial")
public class NaoPermitidoReduzirNumVagas extends Exception {

	@Override
	public String toString(){
		
		return "Deve-se escolher um novo veiculo com capacidade de vagas igual ou superior ao antigo.";
	}
}
