
<%@page import="entidades.Veiculo"%>
<%@page import="entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<%
		List<Veiculo> veiculos = (List<Veiculo>) request.getAttribute("veiculos");
	%>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>id</th>
        <th>Modelo</th>
        <th>Placa</th>
        <th>Cor</th>
        <th>Num. Vagas</th>
        <th>Ações</th>
      </tr>
    </thead>
    <tbody>
    
    <% for(Veiculo veiculo: veiculos){ %>
      <tr>
        <td><%= veiculo.obterId() %></td>
        <td><%= veiculo.obterModelo()%></td>
        <td><%= veiculo.obterPlaca() %></td>
        <td><%= veiculo.obterCor()%></td>
        <td><%= veiculo.obterNumVagas()%></td>
        				
        <td> <a href="AlterarVeiculo?idVeiculo=<%= veiculo.obterId() %>" class="btn btn-warning" > Alterar </a> </td>
        
      </tr>
    <%} %>
     
    </tbody>
  </table>

	
