package exceptions.carona;

@SuppressWarnings("serial")
public class UsuarioNaoPertenceACarona extends Exception {
	
	@Override
	public String toString(){
		
		return "Esta Parada n�o est� relacionada a vc!!!!";
		
	}
}
