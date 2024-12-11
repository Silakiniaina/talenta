<%@ page import="java.util.List" %>
<%@ page import="model.Competence" %>
<%@ page import="model.classification.Hierarchie" %>
<%@ page import="model.Departement" %> 

<%
    List<Departement> departements = (List<Departement>)request.getAttribute("departements");
    List<Hierarchie> hierarchies = (List<Hierarchie>)request.getAttribute("hierarchies");
    List<Competence> competences = (List<Competence>)request.getAttribute("competences");
%>
    <%@include file="../../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="row">
                            <div class="col-md-6 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Default form</h4>
                                        <p class="card-description"> Basic form layout </p>
                                        <form class="forms-sample" action="poste" method="post">
                                            <div class="form-group">
                                                <label for="nom">Nom :</label>
                                                <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom">
                                            </div>
                                            <div class="form-group">
                                                <label for="departement">Departement</label>
                                                <select class="form-control" id="departement" name="dept">
                                                    <%
                                                        for (Departement dept : departements) {
                                                            out.println("<option value=\"" + dept.getIdDepartement() + "\">" + dept.getNomDepartement() + "</option>");
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="departement">Hierarchie</label>
                                                <select class="form-control" id="departement" name="hierarchie">
                                                    <%
                                                        for (Hierarchie hierarchie : hierarchies) {
                                                            out.println("<option value=\"" + hierarchie.getIdHierarchie() + "\">" + hierarchie.getNomHierarchie() + "</option>");
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="annee-xp">Annee xp requise :</label>
                                                <input type="number" min=0 class="form-control" id="annee-xp" name="annee-xp" placeholder="Annee xp">
                                            </div>
                                            <button type="submit" class="btn btn-primary mr-2">Ajouter</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@ include file="../../shared/footer.jsp" %>
                </div>
            </div>
        </div>
        <%@ include file="../../shared/script.jsp" %>
    </body>
</html>
