package entidades;

public class Veiculo{
    
	private long id;
    private String modelo;
    private String placa;
    private String cor;
    private int vagas;    
    private long idUsuario;
    
    public Veiculo(String modelo, String placa, String cor,int vagas, long idUsuario){
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
        this.vagas = vagas;
        this.idUsuario = idUsuario;
    }
    

    public Veiculo(){
    	
    }
    
    public Veiculo(long id, String modelo,String placa, String cor,int vagas, long idUsuario){
    	this.id = id;
    	this.modelo = modelo;
    	this.placa = placa;
    	this.cor = cor;
    	this.idUsuario = idUsuario;
    	this.vagas = vagas;
    }


	public long obterId(){
    	return this.id;
    }
    
    public long obterIdMotorista(){
    	return this.idUsuario;
    }
    
    public String obterCor() {
		return this.cor;
	}
	
    
    public String obterPlaca() {
		return this.placa;
	}
	
	public String obterModelo() {
		return this.modelo;
	}
	
	public int obterNumVagas(){
		return this.vagas;
	}
}