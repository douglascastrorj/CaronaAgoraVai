package exceptions.carona;

@SuppressWarnings("serial")
public class NaoPodeAlterarLogradourosDaCarona extends Exception {

	@Override
	public String toString(){
		
		return "Carona j� tem participantes, logo n�o pode alterar Origem e Destino";
	}
}
