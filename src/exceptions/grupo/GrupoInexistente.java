package exceptions.grupo;

public class GrupoInexistente extends Exception {
	@Override
	public String toString(){
		return "Falha ao encontrar o grupo.";
	}
}
