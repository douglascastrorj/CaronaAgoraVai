<%
	String mensagemErro = (String) request.getAttribute("mensagemErro");
%>

<h3> <%= session.getAttribute("nomeUsuario") + " " +  session.getAttribute("idUsuario")  %></h3>
<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title">Login</h3>
	</div>
	<!-- /.box-header -->
	<!-- form start -->
	<form class="form-horizontal" action="Login" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" name="email" id="inputEmail3"
						placeholder="Email">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Senha</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="inputPassword3" name="senha"
						placeholder="Senha">
				</div>
			</div>
			
			
			
			
			<hr/>
			<div class="form-group col-md-3 ">
				
			</div>
			
			<div class="form-group col-md-9">
				<% if(! (mensagemErro == null || mensagemErro.equals(""))){ %>
				<div class="form-group col-sm-offset-2 col-sm-10">
					<div class="bg-red disabled color-palette" ><p style="padding:5px;"> <%= mensagemErro %> </p></div>
				</div>
				<%} %>
			</div>
			
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			
			<button type="submit" class="btn btn-info col-md-2 col-md-offset-4">Logar</button>
			<a class="btn btn-success col-md-2"  href="CriarUsuario.jsp"> Criar Usuario </a>

		</div>
		<!-- /.box-footer -->
	</form>
</div>
<!-- /.box -->