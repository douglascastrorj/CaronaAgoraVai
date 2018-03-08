package entidades;

public class Avaliacao{
    
	private long id;
    private int estrelas;
    private long idUsuario;
    private long idCarona;
    
    public Avaliacao(){
    	
    }
    
    public Avaliacao(long id, int estrelas, long idUsuario,long idCarona){
    	this.estrelas = estrelas;
    	this.id = id;
    	this.idUsuario = idUsuario;
    	this.idCarona = idCarona;
    }
    
    public int obterAvaliacao(){
        return this.estrelas;
    }
    
    public long obterIdUsuario(){
        return this.idUsuario;
    }
    
    public long obterIdAvaliacao(){
    	return this.id;
    }
    
    public long obterIdCarona(){
    	return this.idCarona;
    }
}
