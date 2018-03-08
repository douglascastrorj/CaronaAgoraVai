package servlets.carona;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Carona;
import entidades.Grupo;
import entidades.Logradouro;
import entidades.Parada;
import entidades.Usuario;
import entidades.Veiculo;
import exceptions.carona.NaoHaVagasDisponiveis;
import exceptions.carona.UsuarioParticipaDeCaronaNoMesmoHorario;
import exceptions.carona.VeiculoUtilizadoNoMesmoHorario;
import exceptions.logradouro.LogradouroInvalido;
import moduloTabela.CaronaTabular;
import moduloTabela.LogradouroTabular;
import moduloTabela.SolicitacaoTabular;
import persistencia.CaronaMapeador;
import persistencia.GrupoMapeador;
import persistencia.LogradouroMapeador;
import persistencia.ParadaMapeador;
import persistencia.SolicitacaoMapeador;
import persistencia.UsuarioMapeador;
import persistencia.VeiculoMapeador;

/**
 * Servlet implementation class AprovarSolicitacao
 */
@WebServlet("/AprovarSolicitacao")
public class AprovarSolicitacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AprovarSolicitacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session=request.getSession();
		String idUsuarioStr = String.valueOf( session.getAttribute("idUsuario") );

		if(idUsuarioStr != null && !idUsuarioStr.equals("")){
			long idMotorista = Long.valueOf(idUsuarioStr);


			ParadaMapeador mapeadorParada = new ParadaMapeador();
			List<Parada> paradas = mapeadorParada.buscarParada(idMotorista);  

			LogradouroMapeador logMap = new LogradouroMapeador();

			List<Parada> paradasDistintas = new ArrayList<Parada>();
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

			long idCarona = Long.parseLong(request.getParameter("idCarona"));
			long idUsuario = Long.parseLong(request.getParameter("idUsuario"));

			UsuarioMapeador uMap = new UsuarioMapeador();
			CaronaMapeador cMap = new CaronaMapeador();

			Usuario usuario = uMap.buscarUsuario(idUsuario);
			Carona carona = cMap.buscarCarona(idCarona);
			


			request.setAttribute("paradas", paradasDistintas);
			request.setAttribute("solicitante", usuario);
			request.setAttribute("carona", carona);

			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/AprovarSolicitacao.jsp");
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
			long idMotorista = Long.valueOf(idUsuarioStr);

			String estiloRadio = request.getParameter("estiloRadio");


			CaronaMapeador mapeador = new CaronaMapeador();

			//id do passageiro
			long idPassageiro= Long.valueOf(request.getParameter("idSolicitante"));
			long idCarona = Long.valueOf(request.getParameter("idCarona"));
			long paradaOrigem = Long.parseLong(request.getParameter("paradaOrigem"));
			
			List<Carona> caronas = mapeador.buscarCaronasUsuario(idPassageiro);
			CaronaTabular tabelaCaronas = new CaronaTabular(caronas);
			
			CaronaMapeador carMap = new CaronaMapeador();
			int numPassageirosCarona = carMap.obterNumPassageirosCarona(idCarona);
			VeiculoMapeador vMap = new VeiculoMapeador();
			Veiculo v = vMap.buscarVeiculo(carMap.buscarCarona(idCarona).obterIdVeiculo());
			
			


			if(estiloRadio.equals("paradaExistente")){
				//simplesmente linka parada com carona
				
			
				List<Carona> caronasOfertadasPeloUsuario = mapeador.caronasOfertadasPor(idPassageiro);
				List<Carona> caronasQueUsuarioParticipa = mapeador.buscarCaronasUsuario(idPassageiro);

				try {
					tabelaCaronas.validarVagas(numPassageirosCarona, v);

					List<Carona> caronasOfertadasPeloUsuario1 = mapeador.caronasOfertadasPor(idPassageiro);
					List<Carona> caronasQueUsuarioParticipa1 = mapeador.buscarCaronasUsuario(idPassageiro);

					CaronaTabular tabelaCarona = new CaronaTabular();
					
					// se nao for possivel adicionar passageiro vai dar erro (algum catch)
					Carona carona = tabelaCarona.adicionarPassageiro(mapeador.buscarCarona(idCarona),  caronasQueUsuarioParticipa1, caronasOfertadasPeloUsuario1,idPassageiro);

					LogradouroMapeador logMap = new LogradouroMapeador();
					ParadaMapeador parMap = new ParadaMapeador();

					Logradouro origem = logMap.buscarLogradouro(parMap.buscarParadaPorId(paradaOrigem).obterIdLogradouro());					
					//o id da origem eh setado pelo mapeador
					origem = logMap.adicionar(origem);
					ParadaMapeador mapeadorParada = new ParadaMapeador();

					Parada parTemp = mapeadorParada.buscarParadaPorId(paradaOrigem);

					Parada paradaOrigem1  = mapeadorParada.adicionar(origem.obterIdLogradouro(), carona.obterIdCarona(), idPassageiro, parTemp.obterNome());
					
					//aprovar solicitacao
					SolicitacaoMapeador solMap = new SolicitacaoMapeador();
					solMap.aprovar(idCarona, idPassageiro);
					
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
				}catch(NaoHaVagasDisponiveis e){
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

			

				try {
					System.out.println(cepOrigem);
					tabelaCaronas.validarVagas(numPassageirosCarona, v);

					List<Carona> caronasOfertadasPeloUsuario = mapeador.caronasOfertadasPor(idPassageiro);
					List<Carona> caronasQueUsuarioParticipa = mapeador.buscarCaronasUsuario(idPassageiro);

					CaronaTabular tabelaCarona = new CaronaTabular();
					Carona carona = tabelaCarona.adicionarPassageiro(mapeador.buscarCarona(idCarona),  caronasQueUsuarioParticipa, caronasOfertadasPeloUsuario,idPassageiro);
					
					//criando origem
					Logradouro origem = tabelaLogradouros.adicionarLogradouro(cepOrigem, ufOrigem, cidadeOrigem, distritoOrigem, enderecoOrigem, numeroOrigem);					
					//o id da origem eh setado pelo mapeador
					origem = mapeadorLogradouro.adicionar(origem);


					//associando logradouro a parada

					ParadaMapeador mapeadorParada = new ParadaMapeador();
					Parada paradaOrigem3  = mapeadorParada.adicionar(origem.obterIdLogradouro(), idCarona, idPassageiro, nomeOrigem);

					//aprovar solicitacao
					SolicitacaoMapeador solMap = new SolicitacaoMapeador();
					solMap.aprovar(idCarona, idPassageiro);
					
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/index.jsp");
					rd.forward(request, response);

				} catch (LogradouroInvalido e) {
					// TODO Auto-generated catch block
					//					e.printStackTrace();
					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				} catch (VeiculoUtilizadoNoMesmoHorario e) {
					// TODO Auto-generated catch block

					//					e.printStackTrace();
					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				} catch (UsuarioParticipaDeCaronaNoMesmoHorario e) {
					// TODO Auto-generated catch block
					//					e.printStackTrace();
					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
					rd.forward(request, response);
				} catch (NaoHaVagasDisponiveis e) {
					request.setAttribute("mensagemErro", e.toString());
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/Erro.jsp");
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
