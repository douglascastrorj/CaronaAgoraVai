package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import moduloTabela.UsuarioTabular;
import persistencia.UsuarioMapeador;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioMapeador mapeador;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
		
		mapeador = new UsuarioMapeador();
		

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("mensagemErro","");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		System.out.println( email + " " + senha);
		
		List<Usuario> usuarios = mapeador.buscarTodosUsuarios();
		UsuarioTabular tabelaUsuarios = new UsuarioTabular(usuarios);
		Usuario usuario = tabelaUsuarios.findByEmail(email);
		
		if(usuario != null){	
			System.out.println("Usuario existe");
			
			if(tabelaUsuarios.verificarSenha(usuario, senha)){
				
				HttpSession session=request.getSession();  
				session.setAttribute("idUsuario",usuario.obterIdUsuario());
				session.setAttribute("nomeUsuario",usuario.obterNomeUsuario());

				//				    response.sendRedirect("ListarLogradouro");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}else{
				request.setAttribute("mensagemErro","Usuario ou senha incorretos");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			}
		}else{
			request.setAttribute("mensagemErro","Usuario ou senha incorretos");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
		
		
		


	}

}
