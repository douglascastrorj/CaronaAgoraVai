package servlets.carona;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Carona;
import entidades.Logradouro;
import entidades.Parada;
import entidades.Usuario;
import entidades.Veiculo;
import persistencia.CaronaMapeador;
import persistencia.LogradouroMapeador;
import persistencia.ParadaMapeador;
import persistencia.UsuarioMapeador;
import persistencia.VeiculoMapeador;

/**
 * Servlet implementation class SolicitarDados
 */
@WebServlet("/SolicitarDados")
public class SolicitarDados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolicitarDados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioMapeador usuarioMapeador = new UsuarioMapeador();
		CaronaMapeador caronaMapeador = new CaronaMapeador();
		VeiculoMapeador veiculoMapeador = new VeiculoMapeador();
		ParadaMapeador paradaMapeador = new ParadaMapeador();
		LogradouroMapeador logradouroMapeador = new LogradouroMapeador();
		
		long idCarona = Long.parseLong(request.getParameter("idCarona"));
		
		Carona carona   = caronaMapeador.buscarCarona(idCarona);
		
		Parada pOrigem  = paradaMapeador.buscarParadaPorId(carona.obterIdOrigem());
		Parada pDestino = paradaMapeador.buscarParadaPorId(carona.obterIdDestino());
		
		Logradouro lOrigem = logradouroMapeador.buscarLogradouro(pOrigem.obterIdLogradouro());
		Logradouro lDestino = logradouroMapeador.buscarLogradouro(pDestino.obterIdLogradouro());
		
		Veiculo veiculo = veiculoMapeador.buscarVeiculo(carona.obterIdVeiculo());
		
		Usuario usuario = usuarioMapeador.buscarUsuario(veiculo.obterIdMotorista());

		request.setAttribute("vagasDisponiveis", veiculo.obterNumVagas() - caronaMapeador.obterNumPassageirosCarona(idCarona));
		request.setAttribute("carona", carona);
		
		request.setAttribute("pOrigem", pOrigem);
		request.setAttribute("pDestino", pDestino);
		
		request.setAttribute("lOrigem", lOrigem);
		request.setAttribute("lDestino", lDestino);
		
		request.setAttribute("veiculo", veiculo);
		
		request.setAttribute("motorista", usuario);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/SolicitarDados.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
