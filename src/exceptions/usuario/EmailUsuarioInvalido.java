package exceptions.usuario;

@SuppressWarnings("serial")
public class EmailUsuarioInvalido extends Exception {

	@Override
	public String toString(){
		
		return "Email do usu�rio n�o pode ser Vazio";
	}
}
