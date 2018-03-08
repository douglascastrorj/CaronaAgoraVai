
<%@page import="entidades.Parada"%>
<%@page import="java.util.Date"%>
<%@page import="entidades.Logradouro"%>
<%@page import="entidades.Grupo"%>
<%@page import="entidades.Veiculo"%>
<%@page import="java.util.List"%>


<form action="CriarCarona" method="post">
	<div class="row">
		<input type="hidden" name="idMotorista"
			value="<%=session.getAttribute("idUsuario")%>"> 
		<input type="hidden" name="estiloRadio" id="estiloRadio">
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
			<!-- Grupo  -->
			<%
				List<Grupo> grupos = (List<Grupo>) request.getAttribute("grupos");
			%>
			<label for="exampleInputPassword1">Para qual Grupo deseja
				ofertar a Carona</label> <select class="form-control" name="grupo" required>
				<%
					for (Grupo grupo : grupos) {
				%>
				<option value="<%=grupo.obterIdGrupo()%>"><%=grupo.obterNome()%></option>
				<%
					}
				%>
			</select>
		</div>


		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Data</label> <input type="date"
				class="form-control datepicker" name="data"
				"
			data-date-format="mm/dd/yyyy" required> </input>
		</div>


		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Horario de Saida</label> <input
				type="time" class="form-control" name="horario"
				placeholder="Horario de Saida" required>
		</div>


		<div class="form-group col-md-12">
			<br>
			<h4>Parada</h4>
			<br>
			<div class="radio-inline">
				<label> <input type="radio" name="optionsRadios"
					onclick="paradaExistente()" id="optionsRadios1" value="option1"
					checked=""> Selecionar parada existente
				</label>
			</div>
			<div class="radio-inline">
				<label> <input type="radio" name="optionsRadios"
					onclick="criarParada()" id="optionsRadios2" value="option2">
					Criar uma nova parada
				</label>
			</div>
			<br>
		</div>


		<div id="paradaExistente">
			<%
				List<Parada> paradas = (List<Parada> ) request.getAttribute("paradas");
				
			%>
			<div class="form-group col-md-6">
				<!--  -->
				<label for="exampleInputPassword1">Paradas para Origem</label> <select
					class="form-control" name="paradaOrigem" >
					<% for(Parada parada: paradas){ %>
						<option value="<%=parada.obterIdParada() %>"><%= parada.obterNome() %></option>
					<% } %>
				</select>
			</div>
			<div class="form-group col-md-6">
				<!--  -->
				<label for="exampleInputPassword1">Parada Destino</label> <select
					class="form-control" name="paradaDestino" >
					<% for(Parada parada: paradas){ %>
						<option value="<%=parada.obterIdParada() %>"><%= parada.obterNome() %></option>
					<% } %>
				</select>
			</div>
		</div>


		<div class="row" id="criarParada">

			<div class="col-md-6" style="padding: 30px">
				<div class="box-body" style="background-color: #fff">
					<h3>Logradouro Origem</h3>
					<br>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Informe o CEP da
							Localidade</label> <input type="name" class="form-control"
							name="cepOrigem" id="cepOrigem" placeholder="CEP" >
					</div>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Alias Parada</label> <input type="name" class="form-control"
							name="nomeOrigem"  placeholder="Alias para Parada" >
					</div>
					<div class="form-group col-md-12">
						<button type="button" id="callService" class="btn btn-primary">
							Preencher Campos</button>
					</div>


					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">UF</label> <input
							type="nameOrigem" class="form-control" name="ufOrigem" id="ufOrigem"
							placeholder="UF" >
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Cidade</label> <input
							type="name" class="form-control" name="cidadeOrigem"
							id="cidadeOrigem" placeholder="Cidade" >
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Distrito</label> <input
							type="name" class="form-control" name="distritoOrigem"
							id="distritoOrigem" placeholder="Distrito" >
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Endereço</label> <input
							type="name" class="form-control" name="enderecoOrigem"
							id="enderecoOrigem" placeholder="Endereço" >
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Numero</label> <input
							type="name" class="form-control" name="numeroOrigem"
							id="numeroOrigem" placeholder="Numero" >
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
							name="cepDestino" id="cepDestino" placeholder="CEP" >
					</div>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Alias Parada</label> <input type="name" class="form-control"
							name="nomeDestino"  placeholder="Alias para Parada" >
					</div>
					<div class="form-group col-md-12">
						<button type="button" id="callService2" class="btn btn-primary">
							Preencher Campos</button>
					</div>


					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">UF</label> <input type="name"
							class="form-control" name="ufDestino" id="ufDestino"
							placeholder="UF" >
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Cidade</label> <input
							type="name" class="form-control" name="cidadeDestino"
							id="cidadeDestino" placeholder="Cidade" >
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Distrito</label> <input
							type="name" class="form-control" name="distritoDestino"
							id="distritoDestino" placeholder="Distrito" >
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Endereço</label> <input
							type="name" class="form-control" name="enderecoDestino"
							id="enderecoDestino" placeholder="Endereço" >
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Numero</label> <input
							type="name" class="form-control" name="numeroDestino"
							id="numeroDestino" placeholder="Numero" >
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

	function paradaExistente() {
		$("#criarParada").hide();
		$("#paradaExistente").show();
		console.log("paradaExistente");
		$("#estiloRadio").val("paradaExistente");
	}

	function criarParada() {
		$("#paradaExistente").hide();
		$("#criarParada").show();
		console.log("paradaNaoExistente");
		$("#estiloRadio").val("criarParada");

	}

	paradaExistente();
</script>
