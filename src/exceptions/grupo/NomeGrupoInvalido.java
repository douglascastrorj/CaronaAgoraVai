package exceptions.grupo;

@SuppressWarnings("serial")
public class NomeGrupoInvalido extends Exception {
	@Override
	public String toString(){
		return "Nome de Grupo Inválido.";
	}
}
