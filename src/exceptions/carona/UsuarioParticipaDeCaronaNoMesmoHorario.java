package exceptions.carona;

public class UsuarioParticipaDeCaronaNoMesmoHorario extends Exception {

	@Override
	public String toString(){
		return "O Usu�rio j� participa de alguma carona neste hor�rio.";
	}
}
