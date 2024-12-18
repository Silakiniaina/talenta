<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Poste" %>
<%@ page import="model.Recrutement" %> 
<%@ page import="model.Competence" %> 
<%
    List<Recrutement> recrutements = (List<Recrutement>)request.getAttribute("recrutements");
    List<Competence> competences = (List<Competence>)request.getAttribute("competences");
%>
    <%@include file="../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../shared/navbarCandidat.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../shared/settingPanel.jsp" %>
                <%@include file="../shared/sidebarCandidat.jsp" %>
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
                                            <div class="col-4 col-sm-12 col-xl-4 text-center align-items-center justify-content-betweentext-xl-right">
                                                <h4 class="mb-0">#<%= r.getIdRecrutement() %></h4>  
                                                <p class="badge badge-<%= r.getStatus().getCorrespondingColor() %>"><%= r.getStatus().getLabel() %></p>
                                            </div>
                                            <div class="d-flex d-sm-block d-md-flex align-items-center justify-content-center col-md-12 p-0"style="gap:10px;">
                                                <a href="detailsRecrutement?idRecrutement=<%=r.getIdRecrutement() %>" type="button" class="btn btn-sm btn-outline-success ">Details</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                        </div>
                    </div>
                    <%@ include file="../shared/footer.jsp" %>
                </div>
            </div>
        </div>
        <%@ include file="../shared/script.jsp" %>
    </body>
</html>
