package exceptions.carona;

public class UsuarioJaAvaliadoParaEstaCarona extends Exception {
	@Override
	public String toString(){
		return "Usuário já foi avaliado para esta carona";
	}
}
