package servlets.veiculo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moduloTabela.UsuarioTabular;
import moduloTabela.VeiculoTabular;
import persistencia.UsuarioMapeador;
import persistencia.VeiculoMapeador;
import entidades.Usuario;
import entidades.Veiculo;
import exceptions.veiculo.CorVeiculoInvalida;
import exceptions.veiculo.ExisteVeiculoComPlaca;
import exceptions.veiculo.ModeloVeiculoInvalido;
import exceptions.veiculo.NumeroDeVagasInvalido;
import exceptions.veiculo.PlacaVeiculoInvalido;

/**
 * Servlet implementation class IncluirVeiculo
 */
@WebServlet("/IncluirVeiculo")
public class IncluirVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private VeiculoMapeador mapeador;
	private UsuarioMapeador mapeadorUsuario;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncluirVeiculo() {
        super();
        // TODO Auto-generated constructor stub
        mapeador = new VeiculoMapeador();
        mapeadorUsuario = new UsuarioMapeador();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String modelo = request.getParameter("modelo");
		String placa = request.getParameter("placa");
		String cor = request.getParameter("cor");
		String vagasStr = request.getParameter("vagas");
		List<Veiculo> veiculos = mapeador.buscarTodosVeiculos();
		
		VeiculoTabular tabelaVeiculos = new VeiculoTabular(veiculos);
		
		try {
			int vagas = Integer.valueOf(vagasStr);
			Veiculo veiculo = tabelaVeiculos.inserir(modelo, placa, cor, vagas);
			
			HttpSession session = request.getSession();
			String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );
			
			if(idUsuarioStr != null && !idUsuarioStr.equals("")){
				long idUsuario = Long.valueOf(idUsuarioStr);
				
				Usuario usuario = mapeadorUsuario.buscarUsuario(idUsuario);
				
				Veiculo adicionarVeiculo = new Veiculo(modelo, placa, cor,vagas,idUsuario);
				mapeador.adicionar(adicionarVeiculo, usuario);
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListarVeiculos");
				rd.forward(request, response);
				
			}
		} catch (ExisteVeiculoComPlaca e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			request.setAttribute("mensagemErro", e.toString());
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
			rd.forward(request, response);
		} catch (NumeroDeVagasInvalido e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			request.setAttribute("mensagemErro", e.toString());
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
			rd.forward(request, response);
		} catch (CorVeiculoInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModeloVeiculoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlacaVeiculoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
