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

import moduloTabela.CaronaTabular;
import persistencia.CaronaMapeador;
import persistencia.LogradouroMapeador;
import persistencia.ParadaMapeador;
import persistencia.VeiculoMapeador;
import entidades.Carona;
import entidades.Parada;
import entidades.Veiculo;

/**
 * Servlet implementation class ListarCaronasUsuario
 */
@WebServlet("/ListarCaronasPassageiro")
public class ListarCaronasPassageiro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarCaronasPassageiro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String idStr = String.valueOf( session.getAttribute("idUsuario") );

		if(idStr != null && !idStr.equals("")){
			long idUsuario = Long.valueOf(idStr);	
			
			VeiculoMapeador mapeadorVeiculo = new VeiculoMapeador();
			CaronaMapeador caronaMapeador = new CaronaMapeador();
			ParadaMapeador paradaMapeador = new ParadaMapeador();
			
			
			List<Carona> caronas = caronaMapeador.buscarCaronasUsuario(idUsuario);
			
			CaronaTabular carTab = new CaronaTabular();
			
			caronas = carTab.obterCaronasNaoFinalizadas(caronas);
			
			List<Veiculo> veiculos = new ArrayList<Veiculo>();
			List<Parada> origens = new ArrayList<Parada>();
			List<Parada> destinos = new ArrayList<Parada>();
			
			for(Carona c : caronas){
				veiculos.add(mapeadorVeiculo.buscarVeiculo(c.obterIdVeiculo()));
				origens.add(paradaMapeador.buscarParadaPorId(c.obterIdOrigem()));
				destinos.add(paradaMapeador.buscarParadaPorId(c.obterIdDestino()));
			}
			

			request.setAttribute("veiculos", veiculos);
			request.setAttribute("origens", origens);
			request.setAttribute("destinos", destinos);
			request.setAttribute("caronas", caronas);
			
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListarCaronasPassageiro.jsp");
			rd.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		String idStr = String.valueOf( session.getAttribute("idUsuario") );

		if(idStr != null && !idStr.equals("")){
			long idUsuario = Long.valueOf(idStr);	
			
			
			
			ParadaMapeador paradaMapeador = new ParadaMapeador();
			LogradouroMapeador logMap = new LogradouroMapeador();
			
			CaronaMapeador caronaMapeador = new CaronaMapeador();

		}
		
	
	}

}
