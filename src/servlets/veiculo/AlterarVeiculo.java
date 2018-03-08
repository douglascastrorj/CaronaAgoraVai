package servlets.veiculo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Veiculo;
import exceptions.veiculo.CorVeiculoInvalida;
import moduloTabela.VeiculoTabular;
import persistencia.VeiculoMapeador;

/**
 * Servlet implementation class AlterarVeiculo
 */
@WebServlet("/AlterarVeiculo")
public class AlterarVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VeiculoMapeador mapeador = new VeiculoMapeador();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarVeiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String idUsuarioStr = String.valueOf(session.getAttribute("idUsuario"));
		
		if(idUsuarioStr != null && !idUsuarioStr.equals("")){
			//usuario esta logado
			
			long idVeiculo = Long.parseLong(request.getParameter("idVeiculo"));
			
			Veiculo veiculo = mapeador.buscarVeiculo(idVeiculo);
			
			request.setAttribute("veiculo",veiculo);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AlterarVeiculo.jsp");
			rd.forward(request, response);
			
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String idUsuarioStr = String.valueOf(session.getAttribute("idUsuario"));
		
		if(idUsuarioStr != null && !idUsuarioStr.equals("")){
			//usuario esta logado
		
			String idVeiculoStr = request.getParameter("idVeiculo");
			long idVeiculo = Long.parseLong(idVeiculoStr);
			
			Veiculo veiculo = mapeador.buscarVeiculo(idVeiculo);
			
			VeiculoTabular tabelaVeiculos = new VeiculoTabular();
			
			String cor = request.getParameter("cor");
			try {
				veiculo = tabelaVeiculos.alterarVeiculo(veiculo, cor);
			} catch (CorVeiculoInvalida e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				request.setAttribute("mensagemErro", e.toString());
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
				rd.forward(request, response);
			}
		
			mapeador.atualizar(veiculo);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListarVeiculos");
			rd.forward(request, response);
			
			
		}
		
		
	}

}
