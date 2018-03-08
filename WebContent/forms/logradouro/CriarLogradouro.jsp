<form role="form" action="CriarLogradouro" method="post">
	<div class="box-body">
		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">cep</label> <input type="name"
				class="form-control" name="cep" id="cep" placeholder="CEP" required>
		</div>
		<div class="form-group col-md-12">
			<a href="#" id="callService" class="btn btn-primary"> Preencher
				Campos</a>
		</div>
		

		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">UF</label> <input type="name"
				class="form-control" name="uf" id="uf" placeholder="UF" required
				>
		</div>

		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Cidade</label> <input type="name"
				class="form-control" name="cidade" id="cidade" placeholder="Cidade"
				required >
		</div>

		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Distrito</label> <input
				type="name" class="form-control" name="distrito" id="distrito"
				placeholder="Distrito" required >
		</div>

		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Endereço</label> <input
				type="name" class="form-control" name="endereco" id="endereco"
				placeholder="Endereço" required >
		</div>

		<div class="form-group col-md-4">
			<label for="exampleInputPassword1">Numero</label> <input type="name"
				class="form-control" name="numero" id="numero" placeholder="Numero"
				required>
		</div>
	</div>
	<!-- /.box-body -->

	<div class="box-footer">
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>
</form>


<script> 		
	
	$(function() {
	    $('#callService').click(function() {
	    	
	    	var cep = $('#cep').val();
	        $.ajax({
	            type: 'GET',
	            url: 'https://viacep.com.br/ws/'+cep+'/json/',
	            dataType: 'json',
	            contentType: 'application/json; charset=utf-8',
	            success: function(response) {
	            		
	                	$('#cidade').val(response.localidade);
	                	$('#uf').val(response.uf);
	                	$('#distrito').val(response.bairro);
	                	$('#endereco').val(response.logradouro);
	                	

	            },
	            error: function(error) {
	                console.log(error);
	            }
	        });
	    });
	});
</script>
