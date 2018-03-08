package entidades;

public class Parada {
	
	private long idParada;
	private long idCarona;
	private long idLogradouro;
	private long idUsuario;
	private String nome;
	
	public Parada(long idParada, long idCarona, long idLogradouro, long idUsuario , String nome){
		this.idCarona = idCarona;
		this.idParada = idParada;
		this.idLogradouro = idLogradouro;
		this.idUsuario = idUsuario;
		this.nome = nome;
	}
	
	public long obterIdParada(){
		return this.idParada;
	}
	public long obterIdUsuario(){
		return this.idUsuario;
	}
	public long obterIdCarona(){
		return this.idCarona;
	}
	public long obterIdLogradouro(){
		return this.idLogradouro;
	}
	
	public String obterNome(){
		return this.nome;
	}
}
