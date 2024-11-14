<%@ page import="java.util.List" %>
<%@ page import="model.Poste" %>
<%
    List<Poste> postes = (List<Poste>)request.getAttribute("postes");
%>

<%@include file="header.jsp" %>
    <h1>Liste des postes</h1>
    <a href="poste?mode=i">Ajouter Poste</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Departement</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Poste poste : postes) {
            %>
            <tr>
                <td><%= poste.getIdPoste() %></td>
                <td><%= poste.getNomPoste() %></td>
                <td><%= poste.getDepartement().getNomDepartement() %></td>
                <td><a href="recrutement?mode=i&idPoste=<%= poste.getIdPoste() %>">Recruter</a></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
