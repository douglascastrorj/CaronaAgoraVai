package entidades;

import java.util.ArrayList;
import java.util.List;

public class Motorista extends Usuario{
    
    private List<Veiculo> veiculos; 
    
    public Motorista(long id, String nome, String email,String senha, String telefone){
    	super(id, nome, email,senha, telefone);
        this.veiculos = new ArrayList<Veiculo>();
    }
}