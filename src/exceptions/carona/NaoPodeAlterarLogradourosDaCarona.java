package exceptions.carona;

@SuppressWarnings("serial")
public class NaoPodeAlterarLogradourosDaCarona extends Exception {

	@Override
	public String toString(){
		
		return "Carona já tem participantes, logo não pode alterar Origem e Destino";
	}
}
