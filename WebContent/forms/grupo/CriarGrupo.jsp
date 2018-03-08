
<form action="CriarGrupo" method="post">
	<div class="form-group col-md-8">
		<label for="exampleInputPassword1">Nome</label> <input type="name"
			class="form-control" name="nome" placeholder="Nome" required>
	</div>
	<div class="form-group col-md-8">
		<label for="exampleInputPassword1">Descrição</label> <textarea
			type="textarea" class="form-control" name="descricao"
			placeholder="Descricao" required ></textarea>
	</div>
	<div class="form-group col-md-8">
		<label for="exampleInputPassword1">Regras</label> <textarea type="textarea"
			class="form-control" name="regras" placeholder="Regras"> </textarea>
	</div>

	<div class="form-group col-md-8">
		<label for="exampleInputPassword1">Limite minimo de
			Avaliações Ruins</label> <input type="name" class="form-control"
			name="limite" placeholder="Limite Minimo" required>
	</div>
	
	<div class="form-group col-md-12">
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>

</form>
