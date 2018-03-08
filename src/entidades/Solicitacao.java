package entidades;

import java.util.Date;

public class Solicitacao {
	private long idUsuario;
	private long idCarona;
	private String data;
	
	
	public Solicitacao(long idUsuario, long idCarona){
		this.idCarona = idCarona;
		this.idUsuario = idUsuario;
		Date data = new Date();
		
		String ano = String.valueOf(data.getYear() - 100 + 2000);
		String mes = String.valueOf(data.getMonth());
		if(data.getMonth() < 10){
			mes = "0"+mes;
		}
		
		String dia = String.valueOf(data.getDate());
		if(data.getDate() < 10){
			mes = "0"+dia;
		}
		
		this.data = ano+"-"+mes+"-"+dia;
		
		System.out.println(this.data.toString());
	}
	
	public long obterIdUsuario(){
		return this.idUsuario;
	}
	
	public long obterIdCarona(){
		return this.idCarona;
	}
	
	public String obterData(){
		return this.data;
	}
}
