<%@ page import="java.util.List" %>
<%@ page import="model.Competence" %>
<%@ page import="model.classification.Hierarchie" %>
<%@ page import="model.Employe" %> 

<%
    List<Employe> employes = (List<Employe>)request.getAttribute("employes");
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
                                        <h4 class="card-title">Ajout presence</h4>
                                        <form class="forms-sample" action="addPresence" method="post">
                                            <div class="form-group">
                                                <label for="departement">Employe</label>
                                                <select class="form-control" id="departement" name="emp">
                                                    <%
                                                        for (Employe emp : employes) {
                                                            out.println("<option value=\"" + emp.getIdEmploye() + "\">" + emp.getNomComplet() +"</option>");
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="entree">Date Heure entree :</label>
                                                <input type="datetime-local" class="form-control" id="entree" name="entree" placeholder="Date Heure entree">
                                            </div>
                                            <div class="form-group">
                                                <label for="sortie">Date Heure sortie :</label>
                                                <input type="datetime-local" class="form-control" id="sortie" name="sortie" placeholder="Date Heure Sortie">
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
