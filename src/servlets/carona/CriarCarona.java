package servlets.carona;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import persistencia.CaronaMapeador;
import persistencia.GrupoMapeador;
import persistencia.LogradouroMapeador;
import persistencia.ParadaMapeador;
import persistencia.VeiculoMapeador;
import entidades.Carona;
import entidades.Grupo;
import entidades.Logradouro;
import entidades.Parada;
import entidades.Veiculo;
import exceptions.carona.ComingBackToFuture;
import exceptions.carona.DiaInvalido;
import exceptions.carona.HoraInvalida;
import exceptions.carona.OrigemDestinoIguais;
import exceptions.carona.ParadaInexistente;
import exceptions.carona.UsuarioParticipaDeCaronaNoMesmoHorario;
import exceptions.carona.VagasInvalida;
import exceptions.carona.VeiculoInexistente;
import exceptions.carona.VeiculoUtilizadoNoMesmoHorario;
import exceptions.grupo.GrupoInexistente;
import exceptions.logradouro.LogradouroInvalido;

/**
 * Servlet implementation class CriarCarona
 */
@WebServlet("/CriarCarona")
public class CriarCarona extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CaronaMapeador mapeador = new CaronaMapeador();
	private VeiculoMapeador mapeadorVeiculo = new VeiculoMapeador();
	private GrupoMapeador mapeadorGrupo = new GrupoMapeador();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriarCarona() {
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
			
			List<Grupo> grupos = mapeadorGrupo.buscarGruposUsuario(idUsuario);
			List<Veiculo> veiculos = mapeadorVeiculo.buscarVeiculosUsuario(idUsuario);
			
			ParadaMapeador mapeadorParada = new ParadaMapeador();
			List<Parada> paradas = mapeadorParada.buscarParada(idUsuario);  
			
			LogradouroMapeador logMap = new LogradouroMapeador();
			
			List<Parada> paradasDistintas = new ArrayList<Parada>();
			if(paradas.size() > 0)
				paradasDistintas.add(paradas.get(0));
			
			
			for(Parada p1 : paradas){
				boolean estaContido = false;
				for(int i = 0; i < paradasDistintas.size(); i++){
					Parada p2 = paradasDistintas.get(i);
					Logradouro l1 = logMap.buscarLogradouro(p1.obterIdLogradouro());
					Logradouro l2 = logMap.buscarLogradouro(p2.obterIdLogradouro());
					
					String cepNum1 = l1.obterCep()+l1.obterNumero();
					String cepNum2 = l2.obterCep()+l2.obterNumero();
					if(cepNum1.equals(cepNum2)){
						estaContido = true;
					}
				}
				if(!estaContido){
					paradasDistintas.add(p1);
				}
			}
			
			
			request.setAttribute("paradas", paradasDistintas);
			request.setAttribute("grupos", grupos);
			request.setAttribute("veiculos", veiculos);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/CriarCarona.jsp");
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
			long idUsuario = Long.valueOf(idUsuarioStr);
			
			String estiloRadio = request.getParameter("estiloRadio");
			
			
			String dia = request.getParameter("data");
			String hora = request.getParameter("horario");
			

			String veiculoIdStr = request.getParameter("veiculo");
			long veiculoId = Long.parseLong(veiculoIdStr);
			
			VeiculoMapeador vMap = new VeiculoMapeador();
			int vagas = vMap.buscarVeiculo(veiculoId).obterNumVagas();
			
			String grupoIdStr = request.getParameter("grupo");
			long grupoId = Long.parseLong(grupoIdStr);
			
			List<Carona> caronas = mapeador.buscarCaronasUsuario(idUsuario);
			CaronaTabular tabelaCaronas = new CaronaTabular(caronas);
			
			
			if(estiloRadio.equals("paradaExistente")){
				//simplesmente linka parada com carona
				
				long paradaOrigem = Long.parseLong(request.getParameter("paradaOrigem"));
				long paradaDestino = Long.parseLong(request.getParameter("paradaDestino"));
				
				List<Carona> caronasOfertadasPeloUsuario = mapeador.caronasOfertadasPor(idUsuario);
				List<Carona> caronasQueUsuarioParticipa = mapeador.buscarCaronasUsuario(idUsuario);
				
				try {
					
					CaronaTabular tabelaCarona = new CaronaTabular();

					
					LogradouroMapeador logMap = new LogradouroMapeador();
					ParadaMapeador parMap = new ParadaMapeador();
					
					Logradouro origem = logMap.buscarLogradouro(parMap.buscarParadaPorId(paradaOrigem).obterIdLogradouro());					
					//o id da origem eh setado pelo mapeador
					origem = logMap.adicionar(origem);
					
					//criando destino
					Logradouro destino = logMap.buscarLogradouro(parMap.buscarParadaPorId(paradaDestino).obterIdLogradouro());
					destino = logMap.adicionar(destino);
					
					tabelaCarona.validarOrigemDestino(origem, destino);
					//associando logradouro a parada
										
					ParadaMapeador mapeadorParada = new ParadaMapeador();
					
					Parada parTemp = mapeadorParada.buscarParadaPorId(paradaOrigem);
					
					Parada paradaOrigem1  = mapeadorParada.adicionar(origem.obterIdLogradouro(), 0, idUsuario, parTemp.obterNome());
					
					parTemp = mapeadorParada.buscarParadaPorId(paradaDestino);
					Parada paradaDestino1 = mapeadorParada.adicionar(destino.obterIdLogradouro(), 0, idUsuario, parTemp.obterNome());
								
					List<Carona> caronasOfertadasPeloUsuario1 = mapeador.caronasOfertadasPor(idUsuario);
					List<Carona> caronasQueUsuarioParticipa1 = mapeador.buscarCaronasUsuario(idUsuario);
					
					Carona caronaCriada = tabelaCarona.criarCarona(caronasQueUsuarioParticipa1, caronasOfertadasPeloUsuario1, dia, hora, grupoId, veiculoId,paradaOrigem1.obterIdParada(),paradaDestino1.obterIdParada(), vagas);
					caronaCriada = mapeador.adicionar(caronaCriada);
					
					
					
					//setando id da carona na origem e destino					
					mapeadorParada.vincularParadaCarona(paradaOrigem1,caronaCriada.obterIdCarona());
					mapeadorParada.vincularParadaCarona(paradaDestino1,caronaCriada.obterIdCarona());
					
					
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
					
				} catch (VeiculoUtilizadoNoMesmoHorario e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				} catch (UsuarioParticipaDeCaronaNoMesmoHorario e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				} catch (DiaInvalido | HoraInvalida | GrupoInexistente | VeiculoInexistente | ParadaInexistente | VagasInvalida e) {
					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				} catch (OrigemDestinoIguais | ComingBackToFuture e) {
					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				}
				
				
			}else if(estiloRadio.equals("criarParada")){
				System.out.println("criando uma parada...\nObtendo dados...");
				//criar logradouro e associar a uma parada
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
				
				//ATRIBUTOS DO LOGRADOURO DESTINO
				String cepDestino = request.getParameter("cepDestino");
				String ufDestino = request.getParameter("ufDestino");
				String cidadeDestino = request.getParameter("cidadeDestino");
				String distritoDestino = request.getParameter("distritoDestino");
				String enderecoDestino = request.getParameter("enderecoDestino");
				String numeroDestino = request.getParameter("numeroDestino");
				String nomeDestino= request.getParameter("nomeDestino");
				
				
				try {
					System.out.println(cepOrigem);
					//criando origem
					Logradouro origem = tabelaLogradouros.adicionarLogradouro(cepOrigem, ufOrigem, cidadeOrigem, distritoOrigem, enderecoOrigem, numeroOrigem);					
					//o id da origem eh setado pelo mapeador
					origem = mapeadorLogradouro.adicionar(origem);
					
					//criando destino
					Logradouro destino = tabelaLogradouros.adicionarLogradouro(cepDestino, ufDestino, cidadeDestino, distritoDestino, enderecoDestino, numeroDestino);
					destino = mapeadorLogradouro.adicionar(destino);
					
					//associando logradouro a parada
					
					ParadaMapeador mapeadorParada = new ParadaMapeador();
					Parada paradaOrigem  = mapeadorParada.adicionar(origem.obterIdLogradouro(), 0, idUsuario, nomeOrigem);
					Parada paradaDestino = mapeadorParada.adicionar(destino.obterIdLogradouro(), 0, idUsuario, nomeDestino);
					
					List<Carona> caronasOfertadasPeloUsuario = mapeador.caronasOfertadasPor(idUsuario);
					List<Carona> caronasQueUsuarioParticipa = mapeador.buscarCaronasUsuario(idUsuario);
					
					CaronaTabular tabelaCarona = new CaronaTabular();
					Carona caronaCriada = tabelaCarona.criarCarona(caronasQueUsuarioParticipa, caronasOfertadasPeloUsuario, dia, hora, grupoId, veiculoId,paradaOrigem.obterIdParada(),paradaDestino.obterIdParada(), vagas);
					caronaCriada = mapeador.adicionar(caronaCriada);
					
					//setando id da carona na origem e destino					
					mapeadorParada.vincularParadaCarona(paradaOrigem,caronaCriada.obterIdCarona());
					mapeadorParada.vincularParadaCarona(paradaDestino,caronaCriada.obterIdCarona());
					
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
					
				} catch (LogradouroInvalido e) {

					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				} catch (VeiculoUtilizadoNoMesmoHorario e) {

					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				} catch (UsuarioParticipaDeCaronaNoMesmoHorario e) {

					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				} catch (DiaInvalido | HoraInvalida | GrupoInexistente | VeiculoInexistente | ParadaInexistente | VagasInvalida | ComingBackToFuture e) {
					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				} 
							
				
			}else{
				//usuario ta querendo trolar mudando o input oculto da jsp
				request.setAttribute("mensagemErro", "Quer trolar o trolador...");
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
				rd.forward(request, response);
			}
			
			
		}
		
	}

}
