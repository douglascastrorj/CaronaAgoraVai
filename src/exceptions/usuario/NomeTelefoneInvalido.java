package exceptions.usuario;

public class NomeTelefoneInvalido extends Exception {
	@Override
	public String toString(){
		return "Nome ou Telefone Inválidos";
	}
}
