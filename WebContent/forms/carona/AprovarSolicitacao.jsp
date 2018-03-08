
<%@page import="entidades.Parada"%>
<%@page import="entidades.Carona"%>
<%@page import="entidades.Usuario"%>

<%@page import="entidades.Veiculo"%>
<%@page import="java.util.List"%>

<%
	Carona carona = (Carona) request.getAttribute("carona");
	Usuario solicitante = (Usuario) request.getAttribute("solicitante");
	
	List<Parada> paradas = (List<Parada>) request.getAttribute("paradas");

%>


<form action="AprovarSolicitacao" method="post">
	<div class="row">
		<input type="hidden" name="idSolicitante"
			value="<%=solicitante.obterIdUsuario()%>"> 
		<input	type="hidden" name="estiloRadio" id="estiloRadio">
		<input	type="hidden" name="idCarona" value="<%= carona.obterIdCarona() %>">


		<div class="form-group col-md-6">
			<label for="exampleInputPassword1">Nome</label> <input type="name"
				class="form-control" name="nome" placeholder="Nome" disabled value="<%= solicitante.obterNomeUsuario() %>">
		</div>

		<div class="form-group col-md-6">
			<label for="exampleInputPassword1">Telefone</label> <input id="phone" value="<%= solicitante.obterTelefoneUsuario() %>"
				type="text" class="form-control" name="telefone"
				data-inputmask="'mask': '(999) 999-9999'" placeholder="Telefone"
				data-mask disabled>
		</div>

		<div class="form-group col-md-6">
			<label for="exampleInputEmail1">Email address</label> <input disabled value="<%=solicitante.obterEmailUsuario() %>"
				type="email" class="form-control" id="exampleInputEmail1"
				aria-describedby="emailHelp" name="email" placeholder="Enter email">
			<small class="form-text text-muted">We'll never share your
				email with anyone else.</small>
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
				
			%>
			<div class="form-group col-md-6">
				<!--  -->
				<label for="exampleInputPassword1">Paradas para Origem</label> <select
					class="form-control" name="paradaOrigem">
					<% for(Parada p : paradas){ %>
						<option value="<%= p.obterIdParada()  %>"><%= p.obterNome() %></option>
					<% } %>
				</select>
			</div>

		</div>


		<div class="row" id="criarParada">

			<div class="col-md-6" style="padding: 30px">
				<div class="box-body" style="background-color: #fff">
					<h3>Logradouro</h3>
					<br>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Informe o CEP da
							Localidade</label> <input type="name" class="form-control"
							name="cepOrigem" id="cepOrigem" placeholder="CEP">
					</div>
					<div class="form-group col-md-6">
						<label for="exampleInputPassword1">Alias Parada</label> <input
							type="name" class="form-control" name="nomeOrigem"
							placeholder="Alias para Parada">
					</div>
					<div class="form-group col-md-12">
						<button type="button" id="callService" class="btn btn-primary">
							Preencher Campos</button>
					</div>


					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">UF</label> <input
							type="nameOrigem" class="form-control" name="ufOrigem"
							id="ufOrigem" placeholder="UF">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Cidade</label> <input
							type="name" class="form-control" name="cidadeOrigem"
							id="cidadeOrigem" placeholder="Cidade">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Distrito</label> <input
							type="name" class="form-control" name="distritoOrigem"
							id="distritoOrigem" placeholder="Distrito">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Endereço</label> <input
							type="name" class="form-control" name="enderecoOrigem"
							id="enderecoOrigem" placeholder="Endereço">
					</div>

					<div class="form-group col-md-4">
						<label for="exampleInputPassword1">Numero</label> <input
							type="name" class="form-control" name="numeroOrigem"
							id="numeroOrigem" placeholder="Numero">
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
