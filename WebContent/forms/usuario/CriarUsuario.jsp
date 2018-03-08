
<form action="CriarUsuario" method="post">

	<div class="form-group col-md-6">
		<label for="exampleInputPassword1">Nome</label> <input type="name"
			class="form-control" name="nome" placeholder="Nome">
	</div>
	
	<div class="form-group col-md-6">
		<label for="exampleInputPassword1">Telefone</label> <input id="phone" type="text"
			class="form-control" name="telefone" data-inputmask="'mask': '(999) 999-9999'" placeholder="Telefone" data-mask>
	</div>
	
	<div class="form-group col-md-6">
		<label for="exampleInputEmail1">Email address</label> <input
			type="email" class="form-control" id="exampleInputEmail1"
			aria-describedby="emailHelp" name="email" placeholder="Enter email"> <small
			 class="form-text text-muted">We'll never share
			your email with anyone else.</small>
	</div>
	<div class="form-group col-md-6">
		<label for="exampleInputPassword1">Senha</label> <input type="password"
			class="form-control" name="senha" placeholder="Digite sua Senha">
	</div>
	
	
	<div class="form-group col-md-12">
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>
</form>
<script>
$("#phone").mask("(99) 9999?9-9999");

$("#phone").on("blur", function() {
    var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

    if( last.length == 3 ) {
        var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
        var lastfour = move + last;
        var first = $(this).val().substr( 0, 9 );

        $(this).val( first + '-' + lastfour );
    }
});
</script>