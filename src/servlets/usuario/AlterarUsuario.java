package servlets.usuario;

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
import persistencia.UsuarioMapeador;
import entidades.Usuario;
import exceptions.usuario.NomeTelefoneInvalido;
import exceptions.usuario.NomeUsuarioInvalido;
import exceptions.usuario.TelefoneUsuarioInvalido;
import exceptions.usuario.UsuarioInexistente;

/**
 * Servlet implementation class AlterarUsuario
 */
@WebServlet("/AlterarUsuario")
public class AlterarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UsuarioMapeador mapeador;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarUsuario() {
        super();
        // TODO Auto-generated constructor stub
        mapeador = new UsuarioMapeador();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );
		
		if(idUsuarioStr != null && !idUsuarioStr.equals("")){
			long idUsuario = Long.valueOf(idUsuarioStr);
			
			List<Usuario> usuarios = mapeador.buscarTodosUsuarios();
			UsuarioTabular tabelaUsuarios = new UsuarioTabular(usuarios);
			
			Usuario usuario;
			try {
				usuario = tabelaUsuarios.findById(idUsuario);
				
				request.setAttribute("usuario", usuario);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/AlterarUsuario.jsp");
				rd.forward(request, response);
				
			} catch (UsuarioInexistente e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("Usuario inexistente id: "+ idUsuarioStr);
				request.setAttribute("mensagemErro", e.toString());
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
				rd.forward(request, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		
		HttpSession session = request.getSession();
		String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );
		
		long idUsuario = Long.valueOf(idUsuarioStr);
		
		Usuario usuario = mapeador.buscarUsuario(idUsuario);
		
		UsuarioTabular tabelaUsuario = new UsuarioTabular();
		
		Usuario usuarioAlterado;
		try {
			usuarioAlterado = tabelaUsuario.alterarUsuario(usuario,nome,telefone);
			mapeador.atualizar(usuarioAlterado);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			
		} catch (NomeUsuarioInvalido | TelefoneUsuarioInvalido | UsuarioInexistente e) {
			// TODO Auto-generated catch block
			request.setAttribute("mensagemErro", e.toString());
			request.setAttribute("usuario", usuario);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AlterarUsuario.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
	}

}
