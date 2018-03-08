package entidades;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Carona{
    
    //É criada por um motorista que, por sua vez, informa em qual dos seus veículos ele ofertará a
//carona
    private long idVeiculo;
    private String data;
    private String horaSaida;
    private long idOrigem;
    private long idDestino;
    private int vagas;
    private List<Usuario> usuarios;
	private long id;
	private long idGrupo;
	private boolean foiFinalizada;
    
    public Carona(long idCarona,long idVeiculo, String data, String horaSaida, long idOrigem, long idDestino, int vagas, long idGrupo){
        this.id = idCarona;
    	this.idVeiculo = idVeiculo;
        this.data = data;
        this.horaSaida = horaSaida;
        this.idOrigem = idOrigem;
        this.idDestino = idDestino;
        this.vagas = vagas;
        this.idGrupo = idGrupo;
        this.foiFinalizada = false;
    }
    
    public Carona(long idCarona,long idVeiculo, String data, String horaSaida, long idOrigem, long idDestino, int vagas, long idGrupo, boolean foiFinalizada){
        this.id = idCarona;
    	this.idVeiculo = idVeiculo;
        this.data = data;
        this.horaSaida = horaSaida;
        this.idOrigem = idOrigem;
        this.idDestino = idDestino;
        this.vagas = vagas;
        this.idGrupo = idGrupo;
        this.foiFinalizada = foiFinalizada;
    }
    

    public boolean foiFinalizada(){
    	
    	return this.foiFinalizada;
    }
    
    public long obterIdCarona(){
    	return this.id;
    }
    
    public long obterIdVeiculo(){
        return this.idVeiculo;
    }
    
    public String obterData (){
        return this.data;
    }
    
    public String obterHoraSaida(){
        return this.horaSaida;
    }
    
    public long obterIdOrigem(){
        return this.idOrigem;
    }
   
    public long obterIdDestino(){
        return this.idDestino;
    }
    
    public int obterVagas(){
        return this.vagas;
    }
    
    public List<Usuario> obterUsuarios(){
        return this.usuarios;
    }

    public long obterIdGrupo(){
    	return this.idGrupo;
    }
	
    
}