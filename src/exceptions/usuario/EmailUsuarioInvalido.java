package exceptions.usuario;

@SuppressWarnings("serial")
public class EmailUsuarioInvalido extends Exception {

	@Override
	public String toString(){
		
		return "Email do usuário não pode ser Vazio";
	}
}
