<%@page import="entidades.Veiculo"%>
<%
	Veiculo veiculo= (Veiculo) request.getAttribute("veiculo");
%>

<form action="AlterarVeiculo" method="post">

	<input name="idVeiculo" type="hidden" value="<%= veiculo.obterId()%>">
	<div class="form-group col-md-4">
		<label for="exampleInputPassword1">Cor</label> <input type="name" value="<%= veiculo.obterCor() %>"
			class="form-control" name="cor" placeholder="Cor" required>
	</div>
	
	<div class="form-group col-md-12">
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>
	
</form>

