package exceptions.carona;

public class UsuarioJaAvaliadoParaEstaCarona extends Exception {
	@Override
	public String toString(){
		return "Usu�rio j� foi avaliado para esta carona";
	}
}
