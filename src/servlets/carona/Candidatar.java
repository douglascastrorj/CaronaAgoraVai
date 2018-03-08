package servlets.carona;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Solicitacao;
import exceptions.carona.CaronaNaoPodeSerSolicitada;
import exceptions.carona.SolicitanteInexistente;
import exceptions.carona.UsuarioJaSeCandidatouACarona;
import moduloTabela.SolicitacaoTabular;
import persistencia.SolicitacaoMapeador;

/**
 * Servlet implementation class Candidatar
 */
@WebServlet("/Candidatar")
public class Candidatar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Candidatar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );
		
		if(idUsuarioStr != null && !idUsuarioStr.equals("")){
			long idUsuario = Long.valueOf(idUsuarioStr);
			long idCarona = Long.parseLong(request.getParameter("idCarona"));
			
			
			SolicitacaoMapeador solicitacoesMap = new SolicitacaoMapeador();
			SolicitacaoTabular tabelaSolicitacoes = new SolicitacaoTabular();
			List<Solicitacao> solicitacoesCarona= solicitacoesMap.buscarSolicitacoesCarona(idCarona);
			
			try {
				Solicitacao solicitacao = tabelaSolicitacoes.incluirSolicitacao(idUsuario,idCarona,solicitacoesCarona);
				solicitacoesMap.adicionar(solicitacao);
				
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ListarGrupo");
				rd.forward(request, response);
				
			} catch (UsuarioJaSeCandidatouACarona | SolicitanteInexistente | CaronaNaoPodeSerSolicitada e) {
				// TODO Auto-generated catch block
				request.setAttribute("mensagemErro", e.toString());
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
				rd.forward(request, response);
			}
		}
		
	}

}
