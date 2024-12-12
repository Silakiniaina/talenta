<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.Poste" %>
<%@ page import="model.TypeContrat" %> 
<%
    List<TypeContrat> typeContrats = (List<TypeContrat>)request.getAttribute("typeContrats");
    String idCandidat = (String)request.getAttribute("idCandidat");
    String idRecrutement = (String)request.getAttribute("idRecrutement");
%>
    <%@include file="../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="row">
                            <div class="col-md-6 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Embauche</h4>
                                        <form class="forms-sample" action="contrat" method="post">
                                            <input type="hidden" name="idCandidat" value="<%= idCandidat %>">
                                            <input type="hidden" name="idRecrutement" value="<%= idRecrutement %>">
                                            <div class="form-group">
                                                <label for="date-debut">Date debut :</label>
                                                <input type="date" class="form-control" id="date-debut" name="debut" placeholder="Date debut">
                                            </div>
                                            <div class="form-group">
                                                <label for="salaire">Salaire de base :</label>
                                                <input type="number" min="0" class="form-control" id="salaire" name="salaire" placeholder="Salaire de base">
                                            </div>
                                            <div class="form-group">
                                                <label for="typecontrat">Type Contrat</label>
                                                <select class="form-control" id="typecontrat" name="type">
                                                        <%
                                                            for (TypeContrat typeContrat : typeContrats) {
                                                                out.println("<option value=\"" + typeContrat.getId_type_contrat() + "\">" + typeContrat.getLabel() + "</option>");
                                                            }
                                                        %>
                                                </select>
                                            </div>
                                            <p><b class="text-danger">*</b> A remplir seulement si le type de contrat est <b class="text-success">CDD</b></p>
                                            <div class="form-group">
                                                <label for="date-fin">Date Fin :</label>
                                                <input type="date" class="form-control" id="date-fin" name="fin" placeholder="Date debut">
                                            </div>
                                            <button type="submit" class="btn btn-primary mr-2">Ajouter</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@ include file="../shared/footer.jsp" %>
                </div>
            </div>
        </div>
        <%@ include file="../shared/script.jsp" %>
    </body>
</html>
