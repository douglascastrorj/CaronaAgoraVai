package exceptions.usuario;

public class ExisteUsuarioComEmail extends Exception {
	@Override
	public String toString(){
		return "J� existe um usu�rio cadastrado com este email.";
	}
}
