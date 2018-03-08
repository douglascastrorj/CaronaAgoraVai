package exceptions.usuario;

@SuppressWarnings("serial")
public class NomeUsuarioInvalido extends Exception {

	@Override
	public String toString(){
		
		return "Nome do Usuário não deve ser Vazio";
	}
}
