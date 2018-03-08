package moduloTabela;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidades.Carona;
import entidades.Logradouro;
import entidades.Veiculo;
import exceptions.carona.ComingBackToFuture;
import exceptions.carona.DiaInvalido;
import exceptions.carona.HoraInvalida;
import exceptions.carona.NaoHaVagasDisponiveis;
import exceptions.carona.NaoPermitidoReduzirNumVagas;
import exceptions.carona.NaoPodeAlterarLogradourosDaCarona;
import exceptions.carona.OrigemDestinoIguais;
import exceptions.carona.ParadaInexistente;
import exceptions.carona.SomenteMotoristaPodeCancelarCaronas;
import exceptions.carona.UsuarioParticipaDeCaronaNoMesmoHorario;
import exceptions.carona.VagasInvalida;
import exceptions.carona.VeiculoInexistente;
import exceptions.carona.VeiculoUtilizadoNoMesmoHorario;
import exceptions.grupo.GrupoInexistente;

public class CaronaTabular {

	private List<Carona> dataset; 
	public CaronaTabular(List<Carona> caronas) {
		// TODO Auto-generated constructor stub
		this.dataset = caronas;
	}

	public CaronaTabular(){

	}

	public Carona  	criarCarona(List<Carona> caronasQueUsuarioParticipa, List<Carona> caronasOfertadasPeloUsuario, String dia,
			String hora, long idGrupo, long veiculoId, long idParadaOrigem, long idParadaDestino, int vagas) 
					throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, ParadaInexistente, VagasInvalida, ComingBackToFuture {


		// REGRA DE NEGOCIO
		//usuario ou veiculo nao podem estar associado a caronas no mesmo dia/ horario
		
		long year = Long.parseLong(dia.split("-")[0]);
		long month = Long.parseLong(dia.split("-")[1]);
		long day = Long.parseLong(dia.split("-")[2]);

		Date data = new Date();
		long currentYear = data.getYear() + 1900;
		long currentMonth = data.getMonth() + 1;
		long currentDay = data.getDate();

		if(year*365 + month*30 + day < currentYear*365 + currentMonth*30 + currentDay){
			throw new ComingBackToFuture();
		}
		
		
		if(dia == null || dia.equals(""))
			throw new DiaInvalido();
		
		if(hora == null || hora.equals(""))
			throw new HoraInvalida();
		
		if(idGrupo < 0)
			throw new GrupoInexistente();
		
		if(veiculoId < 0)
			throw new VeiculoInexistente();
		
		if(idParadaOrigem < 0)
			throw new ParadaInexistente();

		if(idParadaDestino < 0)
			throw new ParadaInexistente();
		
		if(vagas < 0)
			throw new VagasInvalida();
		
		List<Carona> todasCaronasUsuario = new ArrayList<Carona>();
		todasCaronasUsuario.addAll(caronasQueUsuarioParticipa);
		todasCaronasUsuario.addAll(caronasOfertadasPeloUsuario);
		todasCaronasUsuario = this.obterCaronasNaoFinalizadas(todasCaronasUsuario);

		for(Carona c : todasCaronasUsuario){
			String diaHorario = dia+hora;
			if(c.obterIdVeiculo() == veiculoId){				
				if(diaHorario.equals( c.obterData()+c.obterHoraSaida())){
					//erro ja existe carona com esse veiculo no mesmo horario
					throw new VeiculoUtilizadoNoMesmoHorario();
				}
			}

			if(diaHorario.equals( c.obterData()+c.obterHoraSaida())){
				//erro usuario participa de carona no mesmo horario
				throw new UsuarioParticipaDeCaronaNoMesmoHorario();
			}
		}


		Carona carona = new Carona(0, veiculoId, dia, hora, idParadaOrigem, idParadaDestino, vagas, idGrupo);

		return carona;

	}

	public Carona adicionarPassageiro(Carona carona, List<Carona> caronasQueUsuarioParticipa,
			List<Carona> caronasOfertadasPeloUsuario, long idPassageiro) throws VeiculoUtilizadoNoMesmoHorario, UsuarioParticipaDeCaronaNoMesmoHorario {
		// TODO Auto-generated method stub

		// REGRA DE NEGOCIO
		//usuario ou veiculo nao podem estar associado a caronas no mesmo dia/ horario

		List<Carona> todasCaronasUsuario = new ArrayList<Carona>();
		todasCaronasUsuario.addAll(caronasQueUsuarioParticipa);
		todasCaronasUsuario.addAll(caronasOfertadasPeloUsuario);

		todasCaronasUsuario = this.obterCaronasNaoFinalizadas(todasCaronasUsuario);
		for(Carona c : todasCaronasUsuario){

			String diaHorario = carona.obterData()+carona.obterHoraSaida();
			if(c.obterIdVeiculo() == carona.obterIdVeiculo()){				
				if(diaHorario.equals( c.obterData()+c.obterHoraSaida())){
					//erro ja existe carona com esse veiculo no mesmo horario
					throw new VeiculoUtilizadoNoMesmoHorario();
				}
			}

			if(diaHorario.equals( c.obterData()+c.obterHoraSaida())){
				//erro usuario participa de carona no mesmo horario
				throw new UsuarioParticipaDeCaronaNoMesmoHorario();
			}
		}
		
		return carona;

	}

	public void validarVagas(int numPassageirosCarona, Veiculo v) throws NaoHaVagasDisponiveis {
		// TODO Auto-generated method stub
		if(numPassageirosCarona >= v.obterNumVagas())
			throw new NaoHaVagasDisponiveis();
	}
	
	public void validarOrigemDestino(Logradouro origem, Logradouro destino) throws OrigemDestinoIguais{
		
		String cepNum1 = origem.obterCep()+origem.obterNumero();
		String cepNum2 = destino.obterCep()+destino.obterNumero();
		if(cepNum1.equals(cepNum2)){
			throw new OrigemDestinoIguais();
		}

	}
	
	public List<Carona> obterCaronasNaoFinalizadas (List<Carona> caronas){
		
		List<Carona> caronasNaoFinalizadas = new ArrayList<Carona>();
		for (Carona carona : caronas) {
			if(carona.foiFinalizada() == false){
				caronasNaoFinalizadas.add(carona);
			}
		}
		
		return caronasNaoFinalizadas;
	}
	
	public Carona cancelarCarona(Carona carona, List<Veiculo> veiculos) throws SomenteMotoristaPodeCancelarCaronas{
		
		for (Veiculo veiculo : veiculos) {
			if(carona.obterIdVeiculo() == veiculo.obterId())
				return carona;
		}
		
		throw new SomenteMotoristaPodeCancelarCaronas();
		
	}
	
	
	public Carona alterarCarona(List<Carona> caronasQueUsuarioParticipa, List<Carona> caronasOfertadasPeloUsuario, Carona oldCarona, 
								Carona newCarona, int numPassageiros, Logradouro origem, Logradouro destino, Logradouro novaOrigem, 
								Logradouro novaDestino, Veiculo veiculoAntigo, Veiculo veiculoNovo, long idOrigem, long idDestino) 
									throws NaoPodeAlterarLogradourosDaCarona, NaoPermitidoReduzirNumVagas, VeiculoUtilizadoNoMesmoHorario, 
									UsuarioParticipaDeCaronaNoMesmoHorario, DiaInvalido, HoraInvalida, GrupoInexistente, VeiculoInexistente, 
									ParadaInexistente, VagasInvalida, ComingBackToFuture{
		
		if(numPassageiros > 0){
			
			//Nao pode alterar Origem e destino
			
			String antigoLogOrigem = origem.obterCep() + origem.obterEstado() + origem.obterCidade() + origem.obterDistrito() + origem.obterEndereco() + origem.obterNumero();
			String novoLogOrigem = novaOrigem.obterCep() + novaOrigem.obterEstado() + novaOrigem.obterCidade() + novaOrigem.obterDistrito() + novaOrigem.obterEndereco() + novaOrigem.obterNumero();
			String antigoLogDestino = destino.obterCep() + destino.obterEstado() + destino.obterCidade() + destino.obterDistrito() + destino.obterEndereco() + destino.obterNumero();
			String novoLogDestino = novaDestino.obterCep() + novaDestino.obterEstado() + novaDestino.obterCidade() + novaDestino.obterDistrito() + novaDestino.obterEndereco() + novaDestino.obterNumero();

			String velho = antigoLogOrigem + antigoLogDestino;
			
			String novo = novoLogOrigem + novoLogDestino;
			
			if(!velho.equals(novo))
				throw new NaoPodeAlterarLogradourosDaCarona();
		}
		
		if(veiculoNovo.obterNumVagas() < veiculoAntigo.obterNumVagas())
			throw new NaoPermitidoReduzirNumVagas();
		
		Carona c = this.criarCarona(caronasQueUsuarioParticipa, caronasOfertadasPeloUsuario, newCarona.obterData(), newCarona.obterHoraSaida(),
									oldCarona.obterIdGrupo(), newCarona.obterIdVeiculo(), idOrigem, idDestino, veiculoNovo.obterNumVagas());
		
		Carona agoraVai = new Carona(oldCarona.obterIdCarona(), c.obterIdVeiculo(), c.obterData(), c.obterHoraSaida(), 
										c.obterIdOrigem(), c.obterIdDestino(), c.obterVagas(), c.obterIdGrupo());
		
		
		return agoraVai;
	}

}
