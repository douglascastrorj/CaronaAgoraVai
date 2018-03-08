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

import moduloTabela.ParadaTabular;
import persistencia.CaronaMapeador;
import persistencia.LogradouroMapeador;
import persistencia.ParadaMapeador;
import entidades.Parada;
import exceptions.carona.UsuarioNaoPertenceACarona;

/**
 * Servlet implementation class DeixarCarona
 */
@WebServlet("/DeixarCarona")
public class DeixarCarona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ParadaTabular pTab;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeixarCarona() {
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
			
			String idCaronaStr = request.getParameter("idCarona");
			
			long idCarona = Long.parseLong(idCaronaStr);
			
			ParadaMapeador paradaMapeador = new ParadaMapeador();
			LogradouroMapeador logMap = new LogradouroMapeador();
			
			CaronaMapeador caronaMapeador = new CaronaMapeador();
			
//			List<Carona> caronasUsuario = caronaMapeador.buscarCaronasUsuario(idUsuario);
			List<Parada> paradas = paradaMapeador.buscarParadaCaronaUsuario(idCarona, idUsuario);
			pTab = new ParadaTabular(paradas);
			
			try {
				Parada p = pTab.buscarUsuarioParada(idUsuario);
				
				logMap.deletarLogradouro(p.obterIdLogradouro());
				paradaMapeador.deletarParada(p.obterIdParada());
				
				
			} catch (UsuarioNaoPertenceACarona e) {
				request.setAttribute("mensagemErro", e.toString());
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
				rd.forward(request, response);
			}
			
			
		}
//		request.setAttribute("oi", "");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ListarCaronasPassageiro");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
