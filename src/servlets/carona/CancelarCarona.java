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

import moduloTabela.CaronaTabular;
import persistencia.CaronaMapeador;
import persistencia.ParadaMapeador;
import persistencia.VeiculoMapeador;
import entidades.Carona;
import entidades.Parada;
import exceptions.carona.SomenteMotoristaPodeCancelarCaronas;

/**
 * Servlet implementation class CancelarCarona
 */
@WebServlet("/CancelarCarona")
public class CancelarCarona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarCarona() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );
		
		if(idUsuarioStr != null && !idUsuarioStr.equals("")){
			long idUsuario = Long.valueOf(idUsuarioStr);
			long idCarona = Long.parseLong(request.getParameter("idCarona"));
			
			
			VeiculoMapeador vMap = new VeiculoMapeador();
			CaronaMapeador cMap = new CaronaMapeador();
			
			CaronaTabular cTab = new CaronaTabular();
			
			try {
				Carona c = cTab.cancelarCarona(cMap.buscarCarona(idCarona), vMap.buscarVeiculosUsuario(idUsuario));
				
				cMap.finalizar(c.obterIdCarona());
//				ParadaMapeador pMap = new ParadaMapeador();
				
//				List<Parada> paradas = pMap.buscarParadaCarona(c.obterIdCarona());
				
				
			} catch (SomenteMotoristaPodeCancelarCaronas e) {
				request.setAttribute("mensagemErro", e.toString());
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
				rd.forward(request, response);
			}
		}
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/ListarCaronas");
		rd.forward(request, response);
		
	}


}
