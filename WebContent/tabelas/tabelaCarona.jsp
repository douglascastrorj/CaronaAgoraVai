<%@page import="entidades.Parada"%>
<%@page import="entidades.Veiculo"%>
<%@page import="entidades.Motorista"%>
<%@page import="entidades.Carona"%>
<%@page import="entidades.Logradouro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<%
	
		List<Carona> caronas = (List<Carona>) request.getAttribute("caronas");
		List<Veiculo> veiculos = (List<Veiculo>) request.getAttribute("veiculos");
		List<Parada> origens = (List<Parada>) request.getAttribute("origens");
		List<Parada> destinos = (List<Parada>) request.getAttribute("destinos");
		
		
		
	%>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Veiculo</th>
        <th>Origem</th>
        <th>Destino</th>
        <th>Data</th>
        <th>Hora</th>
        <th>Ações</th>
      </tr>
    </thead>
    <tbody>
    
    <% for(int i = 0; i < caronas.size(); i++){ %>
      <tr>
        <td><%= veiculos.get(i).obterModelo() %></td>
        <td><%= origens.get(i).obterNome() %></td>
        <td><%= destinos.get(i).obterNome() %></td>
        <td><%= caronas.get(i).obterData() %></td>
        <td><%= caronas.get(i).obterHoraSaida()%></td>
        
        <td>
        	<a href="VisualizarSolicitacao?idCarona=<%= caronas.get(i).obterIdCarona() %>" class="btn  btn-primary"> Solicitações</a> 
        	<a href="FinalizarCarona?idCarona=<%= caronas.get(i).obterIdCarona() %>" class="btn btn-success"> Avaliar </a>
        	<a href="AlterarCarona?idCarona=<%= caronas.get(i).obterIdCarona() %>" class="btn btn-warning"> Alterar </a>
        	
            <a href="CancelarCarona?idCarona=<%= caronas.get(i).obterIdCarona() %>" class="btn btn-danger"> Cancelar </a>
        
        </td>
      </tr>
    <%} %>
     
    </tbody>
  </table>

	
