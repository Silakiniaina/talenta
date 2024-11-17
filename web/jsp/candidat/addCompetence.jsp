<%@ page import="java.util.List" %>
<%@ page import="model.Competence" %>
<%@ page import="com.google.gson.Gson" %>
<%
    List<Competence> competences = (List<Competence>)request.getAttribute("allCompetence");
    List<Competence> candidatCompetences = (List<Competence>)request.getAttribute("candidat_competence");
%>
<form action="add" method="post">
    <label for="competence">Competence</label>
    <select id="comp" name="competence" required>
        <%
            for (Competence competence : competences) {
                out.println("<option value=\"" + competence.getIdCompetence() + "\">" + competence.getNomCompetence() + "</option>");
            }
        %>
    </select>
    <br><br>
    <input type="submit" value="Ajouter">
</form>
<%
    out.println(new Gson().toJson(candidatCompetences)); 
%>