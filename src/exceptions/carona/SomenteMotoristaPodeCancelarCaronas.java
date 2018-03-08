package exceptions.carona;

@SuppressWarnings("serial")
public class SomenteMotoristaPodeCancelarCaronas extends Exception {

	@Override
	public String toString(){
		
		return "Somente o Motorista pode Cancelar Caronas ofertadas por ele";
	}
}
