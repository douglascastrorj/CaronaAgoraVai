package entidades;

// Um logradouro:
// ï‚· Apresenta um cep, estado, cidade, distrito, endereÃ§o e nÃºmero.
// ï‚· Para se criar um logradouro um usuÃ¡rio deve informar o CEP.
// ï‚· O sistema deverÃ¡ encontrar o endereÃ§o usando a api https://viacep.com.br/ e armazenar o cep, o
// estado, a cidade, o distrito e o endereÃ§o. Contudo, antes do sistema armazenar, o usuÃ¡rio deverÃ¡
// informar o nÃºmero.
// ï‚· ApÃ³s criado, um logradouro nÃ£o pode ser alterado ou removido.

public class Logradouro{
    
	private long id;
    private String cep;
    private String estado;
    private String cidade;
    private String distrito;
    private String endereco;
    private String numero;
    
    
    public Logradouro(long id, String cep, String estado, String cidade, String distrito,String endereco, String numero){
    	
    	this.id = id;
    	this.cep = cep;
    	this.estado = estado;
    	this.cidade = cidade;
    	this.distrito = distrito;
    	this.endereco = endereco;
    	this.numero = numero;
    }
    
    
    public Logradouro(String cep, String numero){
        
        this.cep = cep;
        this.numero = numero;
        
        //buscar dados usando esta API (retorna JSON)
        // {
        //   "cep": "26030-420",
        //   "logradouro": "Rua ParanaguÃ¡",
        //   "complemento": "",
        //   "bairro": "Posse",
        //   "localidade": "Nova IguaÃ§u",
        //   "uf": "RJ",
        //   "unidade": "",
        //   "ibge": "3303500",
        //   "gia": ""
        // }
        String url ="https://viacep.com.br/ws/"+cep+"/json/";
        
        //setar dados obtidos
        this.estado = "RJ";
        this.cidade = "Nova IguaÃ§u";
        this.distrito = "Posse";
        this.endereco = "Rua Paranagua";
        
    }
    
    public String obterCep(){
        return this.cep;
    }
    
    public String obterNumero(){
        return this.numero;
    }
    
    public String obterEstado(){
        return this.estado;
    }
    
    public String obterCidade(){
        return this.cidade;
    }
    
    public String obterDistrito(){
        return this.distrito;
    }
    
    public String obterEndereco(){
        return this.endereco;
    }
    
    public long obterIdLogradouro(){
    	return this.id;
    }
    
    
}