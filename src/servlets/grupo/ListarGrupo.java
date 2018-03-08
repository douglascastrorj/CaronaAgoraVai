package servlets.grupo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moduloTabela.AvaliacaoTabular;
import moduloTabela.GrupoTabular;
import entidades.Grupo;
import entidades.Veiculo;
import persistencia.AvaliacaoMapeador;
import persistencia.GrupoMapeador;

/**
 * Servlet implementation class ListarGrupo
 */
@WebServlet("/ListarGrupo")
public class ListarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GrupoMapeador mapeador = new GrupoMapeador();
	private GrupoTabular grupoTabular = new GrupoTabular();
	private AvaliacaoMapeador aMap = new AvaliacaoMapeador();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarGrupo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String idStr = String.valueOf( session.getAttribute("idUsuario") );

		if(idStr != null && !idStr.equals("")){
			long idUsuario = Long.valueOf(idStr);

			List<Grupo> grupos = mapeador.buscarGruposUsuario(idUsuario);
			
			grupos = grupoTabular.obterGruposUsuarioAtivos(aMap.buscarTodasAvaliacoesUsuario(idUsuario), grupos);
			request.setAttribute("grupos", grupos);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListarGrupos.jsp");
			rd.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
