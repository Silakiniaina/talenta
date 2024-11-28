<%@ page import="java.util.List" %>
<%@ page import="model.Conge" %>
<%
    List<Conge> planningConges=  (List<Conge>) request.getAttribute("planningConges");
%>

<%@include file="../shared/header.jsp" %>
    <h1>Planning des Congés</h1>
    
    <table>
        <thead>
            <tr>
                <th>ID Congé</th>
                <th>Employé</th>
                <th>Date de Début</th>
                <th>Date de Fin</th>
                <th>Type de Congé</th>
            </tr>
        </thead>
        <tbody>
            <% 
                for (Conge conge : planningConges) {
            %>
                <tr>
                    <td><%= conge.getIdConge() %></td>
                    <td><%= conge.getEmploye().getCandidat().getNom() %></td>
                    <td><%= conge.getDateDebut() %></td>
                    <td><%= conge.getDateFin() %></td>
                    <td><%= conge.getTypeConge().getNomTypeConge() %></td>
                </tr>
            <% 
                } 
            %>
        </tbody>
        
    </table>



