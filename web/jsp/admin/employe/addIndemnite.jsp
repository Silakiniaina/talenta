<%@ page import="java.util.List" %>
<%@ page import="model.Competence" %>
<%@ page import="model.classification.Hierarchie" %>
<%@ page import="model.Employe" %> 
<%@ page import="model.employe.TypeIndemnite" %> 

<%
    List<Employe> employes = (List<Employe>)request.getAttribute("employes");
    List<TypeIndemnite> types = (List<TypeIndemnite>)request.getAttribute("types");
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
                                        <h4 class="card-title">Ajout Indemnite</h4>
                                        <form class="forms-sample" action="addIndemnite" method="post">
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
                                                <label for="type">Type Indemnite</label>
                                                <select class="form-control" id="type" name="type">
                                                    <%
                                                        for (TypeIndemnite type : types) {
                                                            out.println("<option value=\"" + type.getIdTypeIndemnite() + "\">" + type.getNomTypeIndemnite()+ "</option>");
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="entree">Montant :</label>
                                                <input type="number" class="form-control" id="entree" name="montant" placeholder="Date debut">
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
