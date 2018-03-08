package servlets.carona;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moduloTabela.CaronaTabular;
import moduloTabela.LogradouroTabular;
import moduloTabela.ParadaTabular;
import persistencia.CaronaMapeador;
import persistencia.LogradouroMapeador;
import persistencia.ParadaMapeador;
import persistencia.VeiculoMapeador;
import entidades.Carona;
import entidades.Logradouro;
import entidades.Parada;
import entidades.Veiculo;
import exceptions.carona.ComingBackToFuture;
import exceptions.carona.DiaInvalido;
import exceptions.carona.HoraInvalida;
import exceptions.carona.NaoPermitidoReduzirNumVagas;
import exceptions.carona.NaoPodeAlterarLogradourosDaCarona;
import exceptions.carona.NomeParadaInvalido;
import exceptions.carona.ParadaInexistente;
import exceptions.carona.UsuarioParticipaDeCaronaNoMesmoHorario;
import exceptions.carona.VagasInvalida;
import exceptions.carona.VeiculoInexistente;
import exceptions.carona.VeiculoUtilizadoNoMesmoHorario;
import exceptions.grupo.GrupoInexistente;
import exceptions.logradouro.LogradouroInvalido;

/**
 * Servlet implementation class AlterarCarona
 */
@WebServlet("/AlterarCarona")
public class AlterarCarona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarCarona() {
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
			long idUsuario = Long.valueOf(idUsuarioStr);
			long idCarona = Long.parseLong(request.getParameter("idCarona"));
			
			CaronaMapeador cMap = new CaronaMapeador();
			ParadaMapeador pMap = new ParadaMapeador();
			LogradouroMapeador lMap = new LogradouroMapeador();
			VeiculoMapeador vMap = new VeiculoMapeador();
			
			
			Carona c = cMap.buscarCarona(idCarona);
			
			Parada pOrigem = pMap.buscarParadaPorId(c.obterIdOrigem());
			Parada pDestino = pMap.buscarParadaPorId(c.obterIdDestino());
			List<Veiculo> veiculos = vMap.buscarVeiculosUsuario(idUsuario);
			Logradouro lOrigem = lMap.buscarLogradouro(pOrigem.obterIdLogradouro());
			Logradouro lDestino = lMap.buscarLogradouro(pDestino.obterIdLogradouro());
			
			request.setAttribute("pOrigem", pOrigem);
			request.setAttribute("pDestino", pDestino);
			request.setAttribute("veiculos", veiculos);
			request.setAttribute("lOrigem", lOrigem);
			request.setAttribute("lDestino", lDestino);
			request.setAttribute("carona", c);
			
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/AlterarCarona.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );
		
		if(idUsuarioStr != null && !idUsuarioStr.equals("")){

			
			try {
				
				
				long idUsuario = Long.valueOf(idUsuarioStr);
				
				String dia = request.getParameter("data");
				String hora = request.getParameter("horario");
				

				String veiculoIdStr = request.getParameter("veiculo");
				long idVeiculo = Long.parseLong(veiculoIdStr);
				
				String pOrigem = request.getParameter("pOrigem");
				long idOrigem = Long.parseLong(pOrigem);
				
				String pDestino = request.getParameter("pDestino");
				long idDestino = Long.parseLong(pDestino);
				
				String caronaStr = request.getParameter("idCarona");
				long idCarona = Long.parseLong(caronaStr);
				
				VeiculoMapeador vMap = new VeiculoMapeador();
				
				Veiculo v = vMap.buscarVeiculo(idVeiculo);
				CaronaMapeador cMap = new CaronaMapeador();
				
				
				List<Carona> caronas = cMap.buscarCaronasUsuario(idUsuario);
				CaronaTabular tabelaCaronas = new CaronaTabular(caronas);
				
				LogradouroMapeador mapeadorLogradouro = new LogradouroMapeador();
				LogradouroTabular tabelaLogradouros = new LogradouroTabular();
				
				//ATRIBUTOS DO LOGRADOURO ORIGEM
				String cepOrigem = request.getParameter("cepOrigem");
				String ufOrigem = request.getParameter("ufOrigem");
				String cidadeOrigem = request.getParameter("cidadeOrigem");
				String distritoOrigem = request.getParameter("distritoOrigem");
				String enderecoOrigem = request.getParameter("enderecoOrigem");
				String numeroOrigem = request.getParameter("numeroOrigem");
				String nomeOrigem = request.getParameter("nomeOrigem");
				
				Logradouro novaOrigem = tabelaLogradouros.adicionarLogradouro(cepOrigem, ufOrigem, cidadeOrigem, distritoOrigem, enderecoOrigem, numeroOrigem);
				
				//ATRIBUTOS DO LOGRADOURO DESTINO
				String cepDestino = request.getParameter("cepDestino");
				String ufDestino = request.getParameter("ufDestino");
				String cidadeDestino = request.getParameter("cidadeDestino");
				String distritoDestino = request.getParameter("distritoDestino");
				String enderecoDestino = request.getParameter("enderecoDestino");
				String numeroDestino = request.getParameter("numeroDestino");
				String nomeDestino= request.getParameter("nomeDestino");
				
				Logradouro novaDestino = tabelaLogradouros.adicionarLogradouro(cepDestino, ufDestino, cidadeDestino, distritoDestino, enderecoDestino, numeroDestino);
				
				
				ParadaMapeador pMap = new ParadaMapeador();
				Parada origemAntiga = pMap.buscarParadaPorId(idOrigem);
				Parada destinoAntiga = pMap.buscarParadaPorId(idDestino);
								
				Logradouro origem = mapeadorLogradouro.buscarLogradouro(origemAntiga.obterIdLogradouro());
				Logradouro destino = mapeadorLogradouro.buscarLogradouro(destinoAntiga.obterIdLogradouro());
				
				
				Carona oldCarona = cMap.buscarCarona(idCarona);
				
				Veiculo veiculoAntigo = vMap.buscarVeiculo(oldCarona.obterIdVeiculo());

				Carona c = new Carona(idCarona, idVeiculo, dia, hora, idOrigem, idDestino, v.obterNumVagas(), oldCarona.obterIdGrupo());
				
				List<Carona> caronasOfertadasPeloUsuario = cMap.caronasOfertadasPor(idUsuario);
				List<Carona> caronasQueUsuarioParticipa = cMap.buscarCaronasUsuario(idUsuario);
				
				Carona novaCarona = tabelaCaronas.alterarCarona(caronasQueUsuarioParticipa, caronasOfertadasPeloUsuario, oldCarona, c, cMap.obterNumPassageirosCarona(idCarona), origem, destino, novaOrigem, novaDestino, veiculoAntigo, v, idOrigem, idDestino );
				
				ParadaTabular pTab = new ParadaTabular();
				
				Parada novaParadaOrigem = pTab.criarParada(nomeOrigem, idUsuario, idCarona, novaOrigem.obterIdLogradouro(), idOrigem);
				Parada novaParadaDestino = pTab.criarParada(nomeDestino, idUsuario, idCarona, novaOrigem.obterIdLogradouro(), idDestino);

				
				//Mappers
				mapeadorLogradouro.atualizar(novaOrigem);
				mapeadorLogradouro.atualizar(novaDestino);
				pMap.atualizar(origemAntiga);
				pMap.atualizar(destinoAntiga);
				
				cMap.atualizar(novaCarona);
				
				
				
			} catch (NaoPodeAlterarLogradourosDaCarona
					| NaoPermitidoReduzirNumVagas | LogradouroInvalido | VeiculoUtilizadoNoMesmoHorario | UsuarioParticipaDeCaronaNoMesmoHorario | DiaInvalido | HoraInvalida | GrupoInexistente | VeiculoInexistente | ParadaInexistente | VagasInvalida | ComingBackToFuture | NomeParadaInvalido  e) {
				request.setAttribute("mensagemErro", e.toString());
				e.printStackTrace();
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
				rd.forward(request, response);
				
			}
			
		}	
		

		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/index.jsp");
//		rd.forward(request, response);
		
	}
	
	

}
