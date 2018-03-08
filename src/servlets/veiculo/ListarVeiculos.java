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

import persistencia.UsuarioMapeador;
import persistencia.VeiculoMapeador;
import entidades.Usuario;
import entidades.Veiculo;

/**
 * Servlet implementation class ListarVeiculos
 */
@WebServlet("/ListarVeiculos")
public class ListarVeiculos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioMapeador mapeadorUsuario;
	private VeiculoMapeador mapeadorVeiculo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarVeiculos() {
        super();
        // TODO Auto-generated constructor stub
        mapeadorUsuario = new UsuarioMapeador();
        mapeadorVeiculo = new VeiculoMapeador();
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
			
			List<Veiculo> veiculos = mapeadorVeiculo.buscarVeiculosUsuario(idUsuario);
			
			request.setAttribute("veiculos", veiculos);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/ListarVeiculos.jsp");
			rd.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
