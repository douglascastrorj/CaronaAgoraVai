package servlets.grupo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Grupo;
import exceptions.grupo.DescricaoGrupoInvalida;
import exceptions.grupo.LimiteMinimoInvalido;
import exceptions.grupo.NomeGrupoInvalido;
import exceptions.grupo.RegrasGrupoInvalida;
import moduloTabela.GrupoTabular;
import persistencia.GrupoMapeador;
import persistencia.UsuarioMapeador;

/**
 * Servlet implementation class CriarGrupo
 */
@WebServlet("/CriarGrupo")
public class CriarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GrupoMapeador mapeador  = new GrupoMapeador();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriarGrupo() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session=request.getSession();
		String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );
		
		if(idUsuarioStr != null && !idUsuarioStr.equals("")){
			long idUsuario = Long.valueOf(idUsuarioStr);
			
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			
			//cast para int
			String limiteMinimoStr = request.getParameter("limite");
			System.out.println(limiteMinimoStr);
			int limiteMinimo = Integer.parseInt(limiteMinimoStr);
			
			String regras = request.getParameter("regras");

			GrupoTabular tabelaGrupo = new GrupoTabular();
			
			Grupo grupo;
			try {
				grupo = tabelaGrupo.adicionarGrupo(nome, descricao, limiteMinimo, regras);
				grupo = mapeador.adicionar(grupo);
				
				UsuarioMapeador mapeadorUsuario = new UsuarioMapeador();
				mapeador.adicionarUsuarioGrupo(grupo, mapeadorUsuario.buscarUsuario(idUsuario));
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				
			} catch (LimiteMinimoInvalido | NomeGrupoInvalido | DescricaoGrupoInvalida | RegrasGrupoInvalida e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				request.setAttribute("mensagemErro", e.toString());
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
				rd.forward(request, response);
			}
			
			
			
		}
	}

}
