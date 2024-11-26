<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Poste" %>
<%@ page import="model.Recrutement" %> 
<%@ page import="model.Competence" %> 
<%@ page import="model.CompetenceRequise" %> 
<%
    List<Recrutement> recrutements = (List<Recrutement>)request.getAttribute("recrutements");
    List<Competence> competences = (List<Competence>)request.getAttribute("competences");
%>
    <%@include file="../../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../../shared/sidebarAdmin.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../../shared/navbar.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <h2>Liste des Recrutements</h2>
                        <div class="row">
                            <%
                                for(Recrutement r : recrutements){
                            %>
                            <div class="col-sm-4 grid-margin">
                                <div class="card">
                                    <div class="card-body p-3">
                                        <div class="row">
                                            <div class="col-12 col-sm-12 col-xl-8">
                                                <div class="d-flex d-sm-block d-md-flex align-items-center" style="justify-content: space-between;">
                                                    <h4 class="mb-0"><%= r.getPoste().getNomPoste() %></h4>
                                                </div>
                                                <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                                                    <h3><%= r.getNombre() %> postes</h3>
                                                </div>
                                                <h6 class="text-muted font-weight-normal">Date Fin : <%= r.getDateFin() %></h6>
                                            </div>
                                            <div class="col-4 col-sm-12 col-xl-4 text-center text-xl-right">
                                                <h4 class="mb-0">#<%= r.getIdRecrutement() %></h4>
                                                <i class="icon-lg mdi mdi-codepen text-primary ml-auto"></i>
                                            </div>
                                            <div class="d-flex d-sm-block d-md-flex align-items-center"style="gap:10px;">
                                                <a href="listeCandidat?idRecrutement=<%=r.getIdRecrutement() %>" type="button" class="btn btn-inverse-primary ">Candidats</a>
                                                <a href="preselection?idRecrutement=<%=r.getIdRecrutement() %>" type="button" class="btn btn-inverse-secondary ">Selection</a>
                                                <a href="resultatTest?idRecrutement=<%=r.getIdRecrutement() %>" type="button" class="btn btn-inverse-success ">Resultat</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                        </div>
                    </div>
                    <%@ include file="../../shared/footer.jsp" %>
                </div>
            </div>
        </div>
        <%@ include file="../../shared/script.jsp" %>
    </body>
</html>
