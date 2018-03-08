package servlets.usuario;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistencia.UsuarioMapeador;
import entidades.Usuario;
import exceptions.usuario.EmailUsuarioInvalido;
import exceptions.usuario.ExisteUsuarioComEmail;
import exceptions.usuario.NomeUsuarioInvalido;
import exceptions.usuario.SenhaUsuarioInvalido;
import exceptions.usuario.TelefoneUsuarioInvalido;
import moduloTabela.UsuarioTabular;

/**
 * Servlet implementation class CriarUsuario
 */
@WebServlet("/CriarUsuario")
public class CriarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioMapeador mapeador;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriarUsuario() {
        super();
        
		mapeador  = new UsuarioMapeador();
		

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
	
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		
		List<Usuario> usuarios = mapeador.buscarTodosUsuarios();
		
		UsuarioTabular tabela = new UsuarioTabular(usuarios);
		
		try {
			Usuario usuario = tabela.insert( nome, email, senha, telefone);
			mapeador.adicionar(usuario);
		} catch (ExisteUsuarioComEmail | NomeUsuarioInvalido | TelefoneUsuarioInvalido | SenhaUsuarioInvalido | EmailUsuarioInvalido e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			request.setAttribute("mensagemErro", e.toString());
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
			rd.forward(request, response);
		}
		
		
		
		//redirecionar para listar
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
		
		
	}

}
