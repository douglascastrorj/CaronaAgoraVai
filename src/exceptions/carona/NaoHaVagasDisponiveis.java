package exceptions.carona;

public class NaoHaVagasDisponiveis extends Exception {
	@Override
	public String toString(){
		return "Não é possivel adicionar passageiros a esta carona. LIMITE DE VAGAS EXCEDIDO!";
	}
}
