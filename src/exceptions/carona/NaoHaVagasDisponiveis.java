package exceptions.carona;

public class NaoHaVagasDisponiveis extends Exception {
	@Override
	public String toString(){
		return "N�o � possivel adicionar passageiros a esta carona. LIMITE DE VAGAS EXCEDIDO!";
	}
}
