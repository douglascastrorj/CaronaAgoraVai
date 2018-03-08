
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Carona"%>
<%@page import="entidades.Parada"%>
<%@page import="java.util.Date"%>
<%@page import="entidades.Logradouro"%>
<%@page import="entidades.Grupo"%>
<%@page import="entidades.Veiculo"%>
<%@page import="java.util.List"%>


<%

	Carona carona = (Carona) request.getAttribute("carona");
	
	Parada pOrigem = (Parada) request.getAttribute("pOrigem");
	Parada pDestino = (Parada) request.getAttribute("pDestino");
	
	Logradouro lOrigem = (Logradouro)request.getAttribute("lOrigem");
	Logradouro lDestino = (Logradouro) request.getAttribute("lDestino");
	
	Veiculo veiculo = (Veiculo) request.getAttribute("veiculo");
	
	Usuario motorista = (Usuario) request.getAttribute("motorista");
	
	Integer vagasDisponiveis = (Integer) request.getAttribute("vagasDisponiveis");

%>

<form action=Candidatar method="post">
	<div class="row">
		<input type="hidden" name="idCarona"
			value="<%= carona.obterIdCarona() %>" > 

		<div class="form-group col-md-2">
			<label for="exampleInputPassword1">Quantidade de Vagas</label> <input
				 class="form-control"  
				 value="<%= vagasDisponiveis  %> " disabled> </input>
		</div>
		
		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Veículo</label> <input
				 class="form-control" 
				disabled value="<%= veiculo.obterModelo() + " :  " + veiculo.obterCor() + " : " + veiculo.obterPlaca() %>">  </input>
		</div>


		<div class="form-group col-md-2">
			<label for="exampleInputPassword1">Data</label> <input type="date"
				class="form-control datepicker" 
			data-date-format="mm/dd/yyyy" disabled value="<%= carona.obterData() %>">  </input>
		</div>


		<div class="form-group col-md-2">
			<label for="exampleInputPassword1">Horario de Saida</label> <input
				type="time" class="form-control" 
				placeholder="Horario de Saida" disabled value="<%= carona.obterHoraSaida() %>">  </input>
		</div>
		
		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Nome Motorista</label> <input
				 class="form-control" 
				placeholder="Horario de Saida" disabled value="<%= motorista.obterNomeUsuario() %>">  </input>
		</div>
		
		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Telefone Motorista</label> <input
				 class="form-control" 
				placeholder="Horario de Saida" disabled value="<%= motorista.obterTelefoneUsuario() %>"></input>
		</div>
		
		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Email Motorista</label> <input
				 class="form-control" 
				placeholder="Horario de Saida" disabled  value= "<%= motorista.obterEmailUsuario() %>"></input>
		</div>


	
		<div class="row" id="criarParada">

			<div class="col-md-6" style="padding: 30px">
				<div class="box-body" style="background-color: #fff">
					<h3>Logradouro Origem</h3>
					<br>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Informe o CEP da
							Localidade</label> <input type="name" class="form-control"
							 id="cepOrigem" placeholder="CEP"  disabled value="<%= lOrigem.obterCep() %>"></input>
					</div>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Alias Parada</label> <input type="name" class="form-control"
							  placeholder="Alias para Parada" disabled value="<%= pOrigem.obterNome() %>"> </input>
					</div>
					

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">UF</label> <input
							type="nameOrigem" class="form-control"  id="ufOrigem"
							placeholder="UF" disabled value="<%= lOrigem.obterEstado() %>"></input>
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Cidade</label> <input
							type="name" class="form-control"
							id="cidadeOrigem" placeholder="Cidade" disabled value="<%= lOrigem.obterCidade() %>"></input>
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Distrito</label> <input
							type="name" class="form-control"
							id="distritoOrigem" placeholder="Distrito" disabled value="<%= lOrigem.obterDistrito() %>"></input>
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Endereço</label> <input
							type="name" class="form-control" 
							id="enderecoOrigem" placeholder="Endereço" disabled value="<%= lOrigem.obterEndereco() %>"></input>
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Numero</label> <input
							type="name" class="form-control" 
							id="numeroOrigem" placeholder="Numero" disabled value="<%= lOrigem.obterNumero() %>"></input>
					</div>
				</div>
				<!-- /.box-body -->

			</div>

			<div class="col-md-6" style="padding: 30px">

				<div class="box-body" style="background-color: #fff">
					<h3>Logradouro Destino</h3>
					<br>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Informe o CEP da
							Localidade</label> <input type="name" class="form-control"
							 id="cepDestino" placeholder="CEP" disabled value="<%= lDestino.obterCep() %>"> </input>
					</div>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Alias Parada</label> <input type="name" class="form-control"
							  placeholder="Alias para Parada" disabled value="<%= pDestino.obterNome() %>"></input>
					</div>
					


					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">UF</label> <input type="name"
							class="form-control"  id="ufDestino"
							placeholder="UF" disabled value="<%= lDestino.obterEstado() %>"> </input>
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Cidade</label> <input
							type="name" class="form-control" 
							id="cidadeDestino" placeholder="Cidade" disabled value="<%=lDestino.obterCidade() %>"></input>
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Distrito</label> <input
							type="name" class="form-control" 
							id="distritoDestino" placeholder="Distrito" disabled value="<%= lDestino.obterDistrito()%>"></input>
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Endereço</label> <input
							type="name" class="form-control"
							id="enderecoDestino" placeholder="Endereço" disabled value="<%=lDestino.obterEndereco() %>"></input>
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Numero</label> <input
							type="name" class="form-control"
							id="numeroDestino" placeholder="Numero" disabled value="<%= lDestino.obterNumero()%>"></input>
					</div>
				</div>
				<!-- /.box-body -->

			</div>
		</div>

		<hr>

		<% if(vagasDisponiveis > 0){ %>
		<div class="form-group col-md-12">
			<button type="submit" class="btn btn-primary">Quero participar!</button>
		</div>
		<% }else{ %>
		<div class="form-group col-md-12">
			<a href="ListarGrupo" class="btn btn-warning" >Não há vagas disponíveis</a>
		</div>
		<% } %>
	</div>
</form>


