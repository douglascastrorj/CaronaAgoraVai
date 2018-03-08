package exceptions.carona;

public class UsuarioParticipaDeCaronaNoMesmoHorario extends Exception {

	@Override
	public String toString(){
		return "O Usuário já participa de alguma carona neste horário.";
	}
}
