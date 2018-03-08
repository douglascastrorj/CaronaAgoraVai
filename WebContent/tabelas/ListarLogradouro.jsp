<%@page import="entidades.Logradouro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<%
		List<Logradouro> logradouros = (List<Logradouro>) request.getAttribute("logradouros");
	%>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>CEP</th>
        <th>Estado</th>
        <th>Cidade</th>
        <th>Bairro</th>
        <th>Endereco</th>
        <th>Numero</th>
      </tr>
    </thead>
    <tbody>
    
    <% for(Logradouro logradouro: logradouros){ %>
      <tr>
        <td><%= logradouro.obterCep() %></td>
        <td><%= logradouro.obterEstado() %></td>
        <td><%= logradouro.obterCidade() %></td>
        <td><%= logradouro.obterDistrito() %></td>
        <td><%= logradouro.obterEndereco() %></td>
        <td><%= logradouro.obterNumero() %></td>
      </tr>
    <%} %>
     
    </tbody>
  </table>

	
