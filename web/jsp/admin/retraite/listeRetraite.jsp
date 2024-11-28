<%@ page import="java.util.List" %>
<%@ page import="model.Employe" %>
<%@ page import="model.Candidat" %>
<%@ page import="java.sql.Date" %>

<%
    List<Employe> employesRetraite = (List<Employe>) request.getAttribute("employesRetraite");
%>

<%@ include file="../shared/header.jsp" %>

<h1>Liste des employés prêts pour la retraite</h1>

<table border="1">
    <thead>
        <tr>
            <th>ID Employé</th>
            <th>Nom</th>
            <th>Date de Naissance</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <% 
            for (Employe employe : employesRetraite) {
        %>
            <tr>
                <td><%= employe.getIdEmploye() %></td>
                <td><%= employe.getCandidat().getNom() + " " + employe.getCandidat().getPrenom() %></td>
                <td><%= employe.getCandidat().getDateNaissance() %></td>
                <td>
                    <form action="notifier-retraite" method="post">
                        <input type="hidden" name="idEmploye" value="<%= employe.getIdEmploye() %>">
                        <button type="submit">Notifier</button>
                    </form>
                </td>
            </tr>
        <% 
            }
        %>
    </tbody>
</table>

