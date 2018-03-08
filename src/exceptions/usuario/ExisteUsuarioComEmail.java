package exceptions.usuario;

public class ExisteUsuarioComEmail extends Exception {
	@Override
	public String toString(){
		return "Já existe um usuário cadastrado com este email.";
	}
}
