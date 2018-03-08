package servlets.carona;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import entidades.Avaliacao;
import entidades.Parada;
import entidades.Usuario;
import exceptions.carona.AvaliacaoInvalida;
import exceptions.carona.AvaliacaoParaCaronaInexistente;
import exceptions.carona.AvaliacaoParaUsuarioInexistente;
import moduloTabela.AvaliacaoTabular;
import moduloTabela.CaronaTabular;
import persistencia.AvaliacaoMapeador;
import persistencia.CaronaMapeador;
import persistencia.ParadaMapeador;
import persistencia.UsuarioMapeador;

/**
 * Servlet implementation class FinalizarCarona
 */
@WebServlet("/FinalizarCarona")
public class FinalizarCarona extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CaronaTabular tabelaCaronas = new CaronaTabular();

	ParadaMapeador pMap = new ParadaMapeador();
	UsuarioMapeador uMap = new UsuarioMapeador();
	CaronaMapeador cMap = new CaronaMapeador ();
	AvaliacaoMapeador aMap = new AvaliacaoMapeador();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarCarona() {
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
			
			long idMotorista = Long.valueOf(idUsuarioStr);			
			long idCarona = Long.parseLong(request.getParameter("idCarona"));
			
			
			
			List<Parada> paradas = pMap.buscarParadaCarona(idCarona);
			
			//adiciona ao array de passageiros todos os usuarios atrelados a parada
			// exceto o motorista
			List<Usuario> passageiros = new ArrayList<Usuario>();
			for(Parada p : paradas){
				Usuario u = uMap.buscarUsuario(p.obterIdUsuario());
				if(u.obterIdUsuario() != idMotorista)
					passageiros.add(u);
			}
			
			request.setAttribute("idCarona", idCarona);
			request.setAttribute("passageiros", passageiros);
			
			
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/FinalizarCarona.jsp");
			rd.forward(request, response);
			
			
		}else{
			//precisa estar logado
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );
		
		if(idUsuarioStr != null && !idUsuarioStr.equals("")){
						
			long idCarona = Long.parseLong(request.getParameter("idCarona"));
			String avaliacoes = request.getParameter("avaliacoes");
			
			String[] usuarioEstrelas = avaliacoes.split("/");
			
			System.out.println("carona " + idCarona);
			for(String str : usuarioEstrelas){
				System.out.println(str);
				long idUsuario = Long.parseLong(str.split("-")[0]);
				int estrelas = Integer.parseInt(str.split("-")[1]);
				
				AvaliacaoTabular aTab = new AvaliacaoTabular();
				try {
					
					//avaliar passageiros
					Avaliacao a = aTab.avaliar(idUsuario, estrelas, idCarona);
					aMap.adicionar(a);
					
//					response.sendRedirect("/index.jsp");
					cMap.finalizar(idCarona);
					
					
					
				} catch (AvaliacaoInvalida | AvaliacaoParaUsuarioInexistente | AvaliacaoParaCaronaInexistente e) {
					// TODO Auto-generated catch block
					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				}
				
				
			}
			
		}else{
			//precisa estar logado
		}
		

		//finalizar carojna
		request.setAttribute("oi", "");
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

}
