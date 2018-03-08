package exceptions.usuario;

public class UsuarioInexistente extends Exception {
	@Override
	public String toString(){
		return "Usuário Inexistente.";
	}
}
