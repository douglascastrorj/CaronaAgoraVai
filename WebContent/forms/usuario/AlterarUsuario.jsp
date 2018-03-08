<%@page import="entidades.Usuario"%>
<%
	Usuario usuario = (Usuario) request.getAttribute("usuario");
	String mensagemErro = (String) request.getAttribute("mensagemErro");
%>
<form action="AlterarUsuario" method="post">
	<input name="idUsuario" type="hidden" value="<%= usuario.obterIdUsuario() %>">
	
	<div class="form-group col-md-4">
		<label for="exampleInputPassword1">Nome</label> <input type="name" value="<%= usuario.obterNomeUsuario() %>"
			class="form-control" name="nome" placeholder="Nome">
	</div>
	<div class="form-group col-md-4">
		<label for="exampleInputPassword1">Telefone</label> <input type="tel" value="<%=usuario.obterTelefoneUsuario() %>"
			class="form-control" name="telefone" placeholder="Telefone">
	</div>
	
	<div class="form-group col-md-9">
		<% if(! (mensagemErro == null || mensagemErro.equals(""))){ %>
		<div class="form-group col-sm-offset-2 col-sm-10">
			<div class="bg-red disabled color-palette" ><p style="padding:5px;"> <%= mensagemErro %> </p></div>
		</div>
		<%} %>
	</div>
	
	<div class="col-md-12">
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>
	
</form>
