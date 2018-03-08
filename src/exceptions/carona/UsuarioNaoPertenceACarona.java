package exceptions.carona;

@SuppressWarnings("serial")
public class UsuarioNaoPertenceACarona extends Exception {
	
	@Override
	public String toString(){
		
		return "Esta Parada não está relacionada a vc!!!!";
		
	}
}
