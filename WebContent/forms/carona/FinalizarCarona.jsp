<%@page import="entidades.Usuario"%>
<%@page import="java.util.List"%>
<%
	List<Usuario> passageiros = (List<Usuario>) request.getAttribute("passageiros");
	long idCarona = (Long) request.getAttribute("idCarona");
%>



<form action="FinalizarCarona" method="post">

<table class="table table-striped">
	<thead>
		<tr>

			<th>Nome do Passageiro</th>
			<th>Email</th>
			<th>Telefone</th>
			<th>Avaliação</th>
		</tr>
	</thead>
	<tbody>

		<%
			int id = 0;
			for (Usuario passageiro : passageiros) {
				id += 1;
		%>
		<tr id="<%=  "passageiro"+id %>">
			<td><%=passageiro.obterNomeUsuario()%></td>
			<td><%=passageiro.obterEmailUsuario()%></td>
			<td><%=passageiro.obterTelefoneUsuario()%></td>


			<td >
				<!-- <form action="AvaliarUsuario" method="post"> -->
					<input type="hidden" value="<%=passageiro.obterIdUsuario()%>"
						id="idUsuario<%= id %>"> <input type="hidden"
						value="<%=idCarona%>" name="idCarona"> <select
						data-type="range" name="estrelas" required id="<%= "estrelas"+id %>">
						<option value="1" selected>1 Estrela</option>
						<option value="2">2 Estrelas</option>
						<option value="3">3 Estrelas</option>
						<option value="4">4 Estrelas</option>
						<option value="5">5 Estrelas</option>
					</select>
					<a href="#" class="btn btn-link" id="<%=  "avaliar"+id %>"> Avaliar </a>

				<!-- </form> -->
			</td>

		</tr>
		<%
			}
		%>

	</tbody>
</table>

	<input type="hidden" value="" name="avaliacoes" id="avaliacoes">
	<input type="hidden" value="<%=idCarona%>" name="idCarona">

	<div class="form-group col-md-12">
		<button type="submit" class="btn btn-success">Finalizar
			Carona</button>
	</div>
</form>

<script>

	<% 
		int idUsuario = 0;
		for(Usuario u : passageiros){ 
			idUsuario++;
	%>
		$("#avaliar<%= idUsuario %>").click(function(){
			$("#passageiro<%= idUsuario %>").hide();
			
			var avaliacoes = $("#avaliacoes");
			
			avaliacoes.val( avaliacoes.val() +  $("#idUsuario<%= idUsuario %>").val()  + "-" + $("#estrelas<%= idUsuario %>").val() + "/");
			
			console.log(avaliacoes.val())
		
		})
	<% } %>
		
	
	


</script>
