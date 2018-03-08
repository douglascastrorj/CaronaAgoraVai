
<%@page import="entidades.Motorista"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<%
		List<Motorista> motoristas = (List<Motorista>) request.getAttribute("motoristas");
	%>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>id</th>
        <th>Nome</th>
        <th>E-mail</th>
        <th>Telefone</th>
        <th>Ações</th>
      </tr>
    </thead>
    <tbody>
    
    <% for(Motorista motorista: motoristas){ %>
      <tr>
        <td><%= motorista.obterIdUsuario() %></td>
        <td><%= motorista.obterNomeUsuario() %></td>
        <td><%= motorista.obterEmailUsuario() %></td>
        <td><%= motorista.obterTelefoneUsuario() %></td>
        				
        <td> <a href="AlterarMotorista?idMotorista=<%= motorista.obterIdUsuario() %>" class="btn btn-warning" > Alterar </a> </td>
        
      </tr>
    <%} %>
     
    </tbody>
  </table>

	
