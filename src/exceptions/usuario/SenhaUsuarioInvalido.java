package exceptions.usuario;

@SuppressWarnings("serial")
public class SenhaUsuarioInvalido extends Exception {
	
	@Override
	public String toString(){
		
		return "Senha do Usu�rio n�o deve ser Vazia";
	}
}
