<%@ page import="java.util.List" %>
<%@ page import="model.Competence" %>
<%@ page import="model.classification.Hierarchie" %>
<%@ page import="model.Employe" %> 
<%@ page import="model.employe.TypeAbsence" %> 

<%
    List<Employe> employes = (List<Employe>)request.getAttribute("employes");
    List<TypeAbsence> types = (List<TypeAbsence>)request.getAttribute("types");
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
                                        <h4 class="card-title">Ajout Absence</h4>
                                        <form class="forms-sample" action="addAbsence" method="post">
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
                                                <label for="entree">Date debut :</label>
                                                <input type="date" class="form-control" id="entree" name="debut" placeholder="Date debut">
                                            </div>
                                            <div class="form-group">
                                                <label for="sortie">Date fin :</label>
                                                <input type="date" class="form-control" id="sortie" name="fin" placeholder="Date Fin">
                                            </div>
                                            <div class="form-group">
                                                <label for="type">Employe</label>
                                                <select class="form-control" id="type" name="type">
                                                    <%
                                                        for (TypeAbsence type : types) {
                                                            out.println("<option value=\"" + type.getIdTypeAbsence() + "\">" + type.getNomTypeAbsence()+ "</option>");
                                                        }
                                                    %>
                                                </select>
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
