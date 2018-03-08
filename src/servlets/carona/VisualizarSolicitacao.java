package servlets.carona;

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

import entidades.Solicitacao;
import entidades.Usuario;
import persistencia.SolicitacaoMapeador;
import persistencia.UsuarioMapeador;

/**
 * Servlet implementation class VisualizarSolicitacao
 */
@WebServlet("/VisualizarSolicitacao")
public class VisualizarSolicitacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizarSolicitacao() {
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
			long idCarona = Long.parseLong(request.getParameter("idCarona"));
			
			SolicitacaoMapeador solMap = new SolicitacaoMapeador();
			
			List<Solicitacao> solCaronas = solMap.buscarSolicitacoesCarona(idCarona);
			
			List<Usuario> solicitantes = new ArrayList<Usuario>();
			UsuarioMapeador uMap = new UsuarioMapeador();
			for(Solicitacao s : solCaronas){
				solicitantes.add(uMap.buscarUsuario(s.obterIdUsuario()));
			}
			
			
			request.setAttribute("solicitantes", solicitantes);
			request.setAttribute("solicitacoes", solCaronas);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/VisualizarSolicitacoes.jsp");
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
