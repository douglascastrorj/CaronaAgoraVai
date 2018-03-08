
<%@page import="entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<%
		List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
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
    
    <% for(Usuario usuario: usuarios){ %>
      <tr>
        <td><%= usuario.obterIdUsuario() %></td>
        <td><%= usuario.obterNomeUsuario() %></td>
        <td><%= usuario.obterEmailUsuario() %></td>
        <td><%= usuario.obterTelefoneUsuario() %></td>
        				
        <td> <a href="AlterarUsuario?idUsuario=<%= usuario.obterIdUsuario() %>" class="btn btn-warning" > Alterar </a> </td>
        
      </tr>
    <%} %>
     
    </tbody>
  </table>

	
