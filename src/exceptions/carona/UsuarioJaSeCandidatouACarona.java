package exceptions.carona;

public class UsuarioJaSeCandidatouACarona extends Exception {
	@Override
	public String toString(){
		return "Usuário já se candidatou a esta carona";
	}
}
