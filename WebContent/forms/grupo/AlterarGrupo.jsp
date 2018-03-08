
<%@page import="entidades.Parada"%>
<%@page import="entidades.Carona"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Grupo"%>
<%
	Grupo grupo = (Grupo) request.getAttribute("grupo");
%>
<form class="row" action="AlterarGrupo" method="post">
	<input name="idGrupo" type="hidden" value="<%=grupo.obterIdGrupo()%>">

	<div class="col-md-4">
		<div class="form-group col-md-12">
			<label for="exampleInputPassword1">Nome</label> <input type="name"
				value="<%=grupo.obterNome()%>" class="form-control" name="nome"
				placeholder="Nome" required>
		</div>
		<div class="form-group col-md-12">
			<label for="exampleInputPassword1">Descrição</label>
			<textarea type="textarea" class="form-control" name="descricao"
				placeholder="Descricao" required><%=grupo.obterDescricao()%></textarea>
		</div>

		<div class="form-group col-md-12">
			<label for="exampleInputPassword1">Limite minimo de
				Avaliações Ruins</label> <input type="name"
				value="<%=grupo.obterLimiteMinimo()%>" class="form-control"
				name="limite" placeholder="Limite Minimo" required>
		</div>

		<div class="form-group col-md-12">
			<label for="exampleInputPassword1">Convide um amigo</label> <input
				type="email" class="form-control" name="email"
				placeholder="exemplo@exemplo.com">
		</div>

		<div class="form-group col-md-12">
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	</div>

	<div class="col-md-8" >
		<%
		
			List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
			List<Carona> caronasGrupo = (List<Carona>) request.getAttribute("caronas");
			List<Parada> origemCarona = (List<Parada>) request.getAttribute("origemCarona");
			List<Parada> destinoCarona = (List<Parada>) request.getAttribute("destinoCarona");
		%>
		<h4> Caronas do Grupo</h4>
		<div  style="height:400px; overflow-y:scroll;">
		<table class="table table-striped">
		
			<thead>
				<tr>
					<th>Origem</th>
					<th>Destino</th>
					<th>Data</th>
					<th>Hora</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody >

				<%  for(int i = 0; i < caronasGrupo.size(); i++){ 
					Carona carona = caronasGrupo.get(i);
				%>
				<tr>
					<td><%= origemCarona.get(i).obterNome() %></td>
					<td><%= destinoCarona.get(i).obterNome() %></td>
					<td><%= carona.obterData() %></td>
					<td><%= carona.obterHoraSaida() %></td>	
					<td><a href="SolicitarDados?idCarona=<%=carona.obterIdCarona() %>" class="btn btn-success" > Visualizar </a></td>					

				</tr>
				<%} %>

			</tbody>
		</table>
		</div>
	</div>




</form>
