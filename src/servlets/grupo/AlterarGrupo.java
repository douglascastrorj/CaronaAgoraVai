package servlets.grupo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Carona;
import entidades.Grupo;
import entidades.Parada;
import entidades.Usuario;
import entidades.Veiculo;
import exceptions.grupo.DescricaoGrupoInvalida;
import exceptions.grupo.GrupoInexistente;
import exceptions.grupo.LimiteMinimoInvalido;
import exceptions.grupo.NomeGrupoInvalido;
import moduloTabela.CaronaTabular;
import moduloTabela.GrupoTabular;
import moduloTabela.UsuarioTabular;
import persistencia.CaronaMapeador;
import persistencia.GrupoMapeador;
import persistencia.ParadaMapeador;
import persistencia.UsuarioMapeador;
import persistencia.VeiculoMapeador;

/**
 * Servlet implementation class AlterarGrupo
 */
@WebServlet("/AlterarGrupo")
public class AlterarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GrupoMapeador mapeador = new GrupoMapeador();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarGrupo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );
		
		if(idUsuarioStr != null && !idUsuarioStr.equals("")){
			long idUsuario = Long.valueOf(idUsuarioStr);
			
			
			String idGrupoStr = request.getParameter("idGrupo");
			long idGrupo = Long.valueOf(idGrupoStr);
				
		
			Grupo grupo = mapeador.buscarGrupo(idGrupo);
			
			request.setAttribute("grupo", grupo);
			request.setAttribute("usuarios", mapeador.buscarUsuariosGrupo(idGrupo));
			
			
			
			//CARONAS OFERTADAS NESTE GRUPO 
			
			//buscar caronas do grupo
			// exibe ate as caronas que o proprio usuario ofertou
			// o usuario nao podera participar da carona q ele ofertou por causa da regra de
			// negocio em q ele nao pode estar em mais de uma carona ao msm tempo
			CaronaMapeador mapeadorCarona = new CaronaMapeador();
			List<Carona> caronasDoGrupo = mapeadorCarona.buscarCaronasGrupo(idGrupo);
			
			CaronaTabular carTab = new CaronaTabular();
			
			caronasDoGrupo = carTab.obterCaronasNaoFinalizadas(caronasDoGrupo);
			ParadaMapeador mapeadorParada = new ParadaMapeador();
			List<Parada> origemCarona = new ArrayList<Parada>();
			List<Parada> destinoCarona = new ArrayList<Parada>();
			
			
			
			//nao exibir as caronas ofertadas pelo usuario
			VeiculoMapeador vm = new VeiculoMapeador();
			List<Veiculo> veiculosUsuario = vm.buscarVeiculosUsuario(idUsuario);
			
			
			//remover caronas em q usuario participa
			List<Carona> caronasDoGrupoMenosEu = new ArrayList<Carona>();
			for(int i = 0; i < caronasDoGrupo.size(); i++){
				//se o usuario eh motorista remova-a
				Veiculo v = vm.buscarVeiculo(caronasDoGrupo.get(i).obterIdVeiculo());
				if(idUsuario != v.obterIdMotorista()){
					caronasDoGrupoMenosEu.add(caronasDoGrupo.get(i));
				}
			}
			
		
			for(Carona c : caronasDoGrupoMenosEu){
				
				origemCarona.add(mapeadorParada.buscarParadaPorId(c.obterIdOrigem()));
				destinoCarona.add(mapeadorParada.buscarParadaPorId(c.obterIdDestino()));
			}
			
			request.setAttribute("caronas", caronasDoGrupoMenosEu);
			request.setAttribute("origemCarona", origemCarona);
			request.setAttribute("destinoCarona", destinoCarona);

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AlterarGrupo.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession();
		String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );
		
		if(idUsuarioStr != null && !idUsuarioStr.equals("")){
			long idUsuario = Long.valueOf(idUsuarioStr);
			
			long idGrupo = Long.parseLong(request.getParameter("idGrupo"));
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			int limite = Integer.parseInt(request.getParameter("limite"));
			
			String email = request.getParameter("email");
			
			GrupoTabular tabelaGrupo = new GrupoTabular(mapeador.buscarGruposUsuario(idUsuario));
			
			try {
				
				Grupo grupo = tabelaGrupo.alterarGrupo(idGrupo, nome, descricao, limite);
				
				mapeador.atualizar(grupo);

				if(email != null && !email.equals("") ){
					
					UsuarioMapeador mapeadorUsuario = new UsuarioMapeador();
					UsuarioTabular tabelaUsuarios = new UsuarioTabular(mapeadorUsuario.buscarTodosUsuarios());
					Usuario u = tabelaUsuarios.findByEmail(email);

					mapeador.adicionarUsuarioGrupo(grupo, u);
				}
				
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListarGrupo");
				rd.forward(request, response);
				
			} catch (LimiteMinimoInvalido | NomeGrupoInvalido
					| DescricaoGrupoInvalida | GrupoInexistente e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				request.setAttribute("mensagemErro", e.toString());
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
				rd.forward(request, response);
			}
	
		}
	}

}
