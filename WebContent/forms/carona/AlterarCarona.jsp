
<%@page import="entidades.Parada"%>
<%@page import="java.util.Date"%>
<%@page import="entidades.Logradouro"%>
<%@page import="entidades.Carona"%>
<%@page import="entidades.Veiculo"%>
<%@page import="java.util.List"%>

<%
	Parada pOrigem = (Parada) request.getAttribute("pOrigem");
	Parada pDestino = (Parada) request.getAttribute("pDestino");

	Logradouro lOrigem = (Logradouro) request.getAttribute("lOrigem");
	Logradouro lDestino = (Logradouro) request.getAttribute("lDestino");

	Carona carona = (Carona) request.getAttribute("carona");
%>

<form action="AlterarCarona" method="post">
	<div class="row">
		<input type="hidden" name="idMotorista" value="<%=session.getAttribute("idUsuario")%>"> 
		<input type="hidden" name="idCarona" value="<%=carona.obterIdCarona()%>">
		<input type="hidden" name="pOrigem" value="<%=pOrigem.obterIdParada()%>">
		<input type="hidden" name="pDestino" value="<%=pDestino.obterIdParada()%>">
		
		<div class="form-group col-md-4">
			<!--  -->
			<%
				List<Veiculo> veiculos = (List<Veiculo>) request.getAttribute("veiculos");
			%>
			<label for="exampleInputPassword1">Meus Veiculos</label> <select
				class="form-control" name="veiculo" required>
				<%
					for (Veiculo veiculo : veiculos) {
				%>
				<option value="<%=veiculo.obterId()%>"><%=veiculo.obterModelo() + " :: " + veiculo.obterCor()%></option>
				<%
					}
				%>
			</select>
		</div>

		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Data</label> <input type="date"
				class="form-control datepicker" name="data"
				value="<%=carona.obterData()%>" data-date-format="mm/dd/yyyy"
				required> </input>
		</div>


		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Horario de Saida</label> <input
				type="time" class="form-control" name="horario"
				value="<%=carona.obterHoraSaida()%>" placeholder="Horario de Saida"
				required>
		</div>


		<div class="row" id="criarParada">

			<div class="col-md-6" style="padding: 30px">
				<div class="box-body" style="background-color: #fff">
					<h3>Logradouro Origem</h3>
					<br>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Informe o CEP da
							Localidade</label> <input type="name" class="form-control"
							name="cepOrigem" id="cepOrigem" placeholder="CEP"
							value="<%=lOrigem.obterCep()%>">
					</div>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Alias Parada</label> <input
							type="name" class="form-control" name="nomeOrigem"
							placeholder="Alias para Parada" value="<%=pOrigem.obterNome()%>">
						</input>
					</div>
					<div class="form-group col-md-12">
						<button type="button" id="callService" class="btn btn-primary">
							Preencher Campos</button>
					</div>


					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">UF</label> <input
							type="nameOrigem" class="form-control" name="ufOrigem"
							id="ufOrigem" placeholder="UF" value="<%=lOrigem.obterEstado()%>">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Cidade</label> <input
							type="name" class="form-control" name="cidadeOrigem"
							id="cidadeOrigem" placeholder="Cidade"
							value="<%=lOrigem.obterCidade()%>">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Distrito</label> <input
							type="name" class="form-control" name="distritoOrigem"
							id="distritoOrigem" placeholder="Distrito"
							value="<%=lOrigem.obterDistrito()%>">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Endereço</label> <input
							type="name" class="form-control" name="enderecoOrigem"
							id="enderecoOrigem" placeholder="Endereço"
							value="<%=lOrigem.obterEndereco()%>">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Numero</label> <input
							type="name" class="form-control" name="numeroOrigem"
							id="numeroOrigem" placeholder="Numero"
							value="<%=lOrigem.obterNumero()%>">
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
							name="cepDestino" id="cepDestino" placeholder="CEP"
							value="<%=lDestino.obterCep()%>">
					</div>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Alias Parada</label> <input
							type="name" class="form-control" name="nomeDestino"
							placeholder="Alias para Parada" value="<%=pDestino.obterNome()%>">
					</div>
					<div class="form-group col-md-12">
						<button type="button" id="callService2" class="btn btn-primary">
							Preencher Campos</button>
					</div>


					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">UF</label> <input type="name"
							class="form-control" name="ufDestino" id="ufDestino"
							placeholder="UF" value="<%=lDestino.obterEstado()%>">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Cidade</label> <input
							type="name" class="form-control" name="cidadeDestino"
							id="cidadeDestino" placeholder="Cidade"
							value="<%=lDestino.obterCidade()%>">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Distrito</label> <input
							type="name" class="form-control" name="distritoDestino"
							id="distritoDestino" placeholder="Distrito"
							value="<%=lDestino.obterDistrito()%>">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Endereço</label> <input
							type="name" class="form-control" name="enderecoDestino"
							id="enderecoDestino" placeholder="Endereço"
							value="<%=lDestino.obterEndereco()%>">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Numero</label> <input
							type="name" class="form-control" name="numeroDestino"
							id="numeroDestino" placeholder="Numero"
							value="<%=lDestino.obterNumero()%>">
					</div>
				</div>
				<!-- /.box-body -->

			</div>
		</div>

		<hr>

		<div class="form-group col-md-12">
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	</div>
</form>

<script>
	$(function() {
		$('#callService').click(function() {

			var cep = $('#cepOrigem').val();
			$.ajax({
				type : 'GET',
				url : 'https://viacep.com.br/ws/' + cep + '/json/',
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				success : function(response) {

					$('#cidadeOrigem').val(response.localidade);
					$('#ufOrigem').val(response.uf);
					$('#distritoOrigem').val(response.bairro);
					$('#enderecoOrigem').val(response.logradouro);

				},
				error : function(error) {
					console.log(error);
				}
			});
		});
	});

	$(function() {
		$('#callService2').click(function() {

			var cep = $('#cepDestino').val();
			$.ajax({
				type : 'GET',
				url : 'https://viacep.com.br/ws/' + cep + '/json/',
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				success : function(response) {

					$('#cidadeDestino').val(response.localidade);
					$('#ufDestino').val(response.uf);
					$('#distritoDestino').val(response.bairro);
					$('#enderecoDestino').val(response.logradouro);

				},
				error : function(error) {
					console.log(error);
				}
			});
		});
	});
</script>
