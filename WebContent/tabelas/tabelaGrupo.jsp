
<%@page import="entidades.Grupo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<%
		List<Grupo> grupos = (List<Grupo>) request.getAttribute("grupos");
	%>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>id</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Regras</th>
        <th>Limite Minimo</th>
        <th>Ações</th>
      </tr>
    </thead>
    <tbody>
    
    <% for(Grupo grupo: grupos){ %>
      <tr>
        <td><%= grupo.obterIdGrupo() %></td>
        <td><%= grupo.obterNome() %></td>
        <td><%= grupo.obterDescricao() %></td>
        <td style="max-width:300px"><%= grupo.obterRegras() %></td>
        <td><%= grupo.obterLimiteMinimo() %></td>
        				
        <td> <a href="AlterarGrupo?idGrupo=<%= grupo.obterIdGrupo() %>" class="btn btn-success" > Visualizar / Alterar</a> </td>
        
      </tr>
    <%} %>
     
    </tbody>
  </table>

	
