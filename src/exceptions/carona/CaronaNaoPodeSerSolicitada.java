package exceptions.carona;

@SuppressWarnings("serial")
public class CaronaNaoPodeSerSolicitada extends Exception {
	
	public String toString(){
		
		return "Carona n�o pode ser solicitada porque n�o Existe";
	}
}
