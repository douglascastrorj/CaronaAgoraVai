package entidades;

import java.util.ArrayList;
import java.util.List;

public class Grupo{

	private List<Usuario> usuarios;
	private String regras;
	private String descricao;
	private String nome;
	private boolean ativo;
	private int limiteMinimo;

	private long id;

	//     deve apresentar um nome, uma descriÃ§Ã£o, uma lista de regras que devem ser aceitas pelos seus
	// usuÃ¡rios, no momento em que entram, e o limite mÃ­nimo de avaliaÃ§Ãµes â€œruinsâ€� para o usuÃ¡rio
	// ficar â€œinativoâ€�.

	//Tem que adicionar as regras ao criar o grupo

	public Grupo(long id, String nome, String descricao, int limiteMinimo, String regras){

		this.id = id;
		this.usuarios = new ArrayList<Usuario>();
		this.regras = regras;
		this.ativo = true;

		this.nome = nome;
		this.descricao = descricao;
		this.limiteMinimo = limiteMinimo;

	}


	public Grupo(long id, String nome, String descricao, int limiteMinimo, String regras, boolean estaAtivo){

		this.id = id;
		this.usuarios = new ArrayList<Usuario>();
		this.regras = regras;
		this.ativo = estaAtivo;

		this.nome = nome;
		this.descricao = descricao;
		this.limiteMinimo = limiteMinimo;

	}

	//Ao passar o limite de avaliaÃ§Ãµes ruins o usuÃ¡rio fica â€œinativoâ€� no grupo.
	// Por exemplo: Ao receber 3 avaliaÃ§Ãµes abaixo de 3 estrelas o usuÃ¡rio fica â€œinativoâ€�.
	//Usuario inativo nÃ£o deve ter acesso Ã  informaÃ§Ãµes do grupo.
	public List<Usuario> obterParticipantes(){
		return this.usuarios;
	}

	public void adicionarUsuario(Usuario usuario){
		this.usuarios.add(usuario);
	}

	public long obterIdGrupo(){
		return this.id;
	}

	public String obterRegras(){
		return this.regras;
	}


	public void alterarNome(String novoNome){
		this.nome = novoNome;
	}

	public String obterNome(){
		return this.nome;
	}

	public void alterarDescricao(String novaDescricao){
		this.descricao = novaDescricao;
	}

	public String obterDescricao(){
		return this.descricao;
	}

	public int obterLimiteMinimo(){
		return this.limiteMinimo;
	}

	public void alterarLimiteMinimo(int novoLimiteMinimo){
		if(novoLimiteMinimo >= 0){
			this.limiteMinimo = novoLimiteMinimo;
		}else{
			this.limiteMinimo = 0;
		}
	}

	public boolean estaAtivo(){
		return this.ativo;
	}
}