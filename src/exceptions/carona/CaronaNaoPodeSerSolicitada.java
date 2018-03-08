package exceptions.carona;

@SuppressWarnings("serial")
public class CaronaNaoPodeSerSolicitada extends Exception {
	
	public String toString(){
		
		return "Carona não pode ser solicitada porque não Existe";
	}
}
