package exceptions.carona;

@SuppressWarnings("serial")
public class HoraInvalida extends Exception {

	@Override
	public String toString(){
		
		return "Hora Inv�lida, deve ser Hora:Minutos";
	}
}

