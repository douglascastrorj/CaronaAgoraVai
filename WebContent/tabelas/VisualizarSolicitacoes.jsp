
<%@page import="entidades.Solicitacao"%>
<%@page import="entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<%
	
		List<Solicitacao> solicitacoes = (List<Solicitacao>) request.getAttribute("solicitacoes");
		List<Usuario> solicitantes= (List<Usuario>) request.getAttribute("solicitantes");
		
	%>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Solicitante</th>
        <th>Email do solicitante</th>
        <th>Telefone do solicitante</th>
        <th>Data da solicitacao</th>
        <th>Ações</th>
      </tr>
    </thead>
    <tbody>
    
    <% for(int i = 0; i < solicitacoes.size(); i++){ %>
      <tr>
        <td><%= solicitantes.get(i).obterNomeUsuario() %></td>
        <td><%= solicitantes.get(i).obterEmailUsuario() %></td>
        <td><%= solicitantes.get(i).obterTelefoneUsuario() %></td>
        <td><%= solicitacoes.get(i).obterData()%></td>
        
        <td>
        	<a href="AprovarSolicitacao?idCarona=<%= solicitacoes.get(i).obterIdCarona() %>&idUsuario=<%= solicitacoes.get(i).obterIdUsuario() %>" class="btn btn-success"> 
        		Aprovar Solicitação
        	</a>
        	
        </td>
      </tr>
    <%} %>
     
    </tbody>
  </table>

	
