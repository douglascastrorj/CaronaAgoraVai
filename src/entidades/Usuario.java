package entidades;

import java.util.List;

public class Usuario{
    
	private long id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    
    private List<Grupo> grupos;
    
    public Usuario (long id, String nome, String email, String telefone){
        
    	this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
    
    public Usuario (long id, String nome, String email,String senha, String telefone){
        
    	this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }
    
    public long obterIdUsuario(){
    	return this.id;
    }
        
    public String obterNomeUsuario(){
    	return this.nome;
    }
    
    public String obterEmailUsuario(){
    	return this.email;
    }
    
    public String obterSenhaUsuario(){
    	return this.senha;
    }
    
    public void alterarSenha(String novaSenha){
    	this.senha = novaSenha;
    }
    
    public String obterTelefoneUsuario(){
    	return this.telefone;
    }
    
    public void alterarNome(String novoNome){
        this.nome = novoNome;
    }
    
    public void alterarTelefone(String novoTelefone){
        this.telefone = novoTelefone;
    }
    
    public void adicionarUsuarioGrupo(String email, Grupo grupos){
        //Precisa saber qm Ã© o usuÃ¡rio
        //Pode convidar pessoas a participar do grupo, informando qual o email deseja convidar. 
    }
    
    public void avaliarUsuario(Usuario usuario, int estrelas){
        //Avaliar â€œanonimamenteâ€� outro usuÃ¡rio (1 a 5 estrelas).
        // Logica da classe AvaliaÃ§Ã£o
        //Uma avaliaÃ§Ã£o ruim Ã© uma avaliaÃ§Ã£o com menos de 3 estrelas.
    }
    
    public void solicitarDadosMotorista(Motorista motorista){
        
    }
    
}