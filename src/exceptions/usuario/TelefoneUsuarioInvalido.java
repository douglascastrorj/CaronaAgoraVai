package exceptions.usuario;

@SuppressWarnings("serial")
public class TelefoneUsuarioInvalido extends Exception {
	
	@Override
	public String toString(){
		
		return "Telefone do Usu�rio n�o deve ser Vazio";
	}
}
