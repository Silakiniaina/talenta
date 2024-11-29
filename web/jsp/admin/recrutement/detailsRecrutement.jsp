<%@ page import="java.util.List" %>
<%@ page import="model.Recrutement" %>
<%@ page import="model.Poste" %>
<%@ page import="model.Candidat" %>
<%@ page import="model.Competence" %>
<%@ page import="model.Education" %>
<%@ page import="model.Experience" %>
<%
    Recrutement recrutement = (Recrutement)request.getAttribute("recrutement");
    int nbCandidature = (int)request.getAttribute("nbCandidature");
    Candidat candidat = (Candidat)request.getSession().getAttribute("candidat");
%>
    <%@include file="../../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="col-lg-12 grid-margin stretch-card" style="flex-direction:column; gap:10px;">
                            <div class="card">
                                <div class="card-body">
                                    <h2><%= recrutement.getPoste().getNomPoste() %></h2>
                                    <div class="row">
                                        <div class="col-md-4 grid-margin stretch-card">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h5 class="card-title">Candidature recu</h5>
                                                    <div class="media">
                                                        <i class="ti-world icon-md text-info d-flex align-self-start mr-3"></i>
                                                        <div class="media-body">
                                                        <h3><%= nbCandidature %></h3>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4 grid-margin stretch-card">
                                            <div class="card">
                                                <div class="card-body">
                                                    <h5 class="card-title">Nombre de poste</h5>
                                                    <div class="media">
                                                        <i class="ti-world icon-md text-info d-flex align-self-start mr-3"></i>
                                                        <div class="media-body">
                                                        <h3><%= recrutement.getNombre() %></h3>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <p>
                                        <b>Date debut : </b> <%= recrutement.getDateDebut() %>
                                    </p>
                                    <p>
                                        <b>Date fin : </b> <%= recrutement.getDateFin() %>
                                    </p>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="card-title">Description</h3>
                                    <p>
                                       <%= recrutement.getDescriptionRecrutement() != null ? recrutement.getDescriptionRecrutement() : "" %>
                                    </p>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="card-title">Compétences requises</h3>
                                    <ul class="list-star">
                                        <%
                                            List<Competence> competences = recrutement.getPoste().getListCompetence();
                                            if (competences == null || competences.isEmpty()) {
                                        %>
                                            <li>Aucune compétence requise pour ce poste</li>
                                        <% 
                                            } else {
                                                for (Competence competence : competences) {
                                        %>
                                            <li><%= competence.getNomCompetence() %></li>
                                        <% 
                                                }
                                            }
                                        %>
                                    </ul>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="card-title">Éducation requise</h3>
                                    <ul class="list-arrow">
                                        <%
                                            List<Education> educations = recrutement.getPoste().getListEducation();
                                            if (educations == null || educations.isEmpty()) {
                                        %>
                                            <li>Aucune éducation requise pour ce poste</li>
                                        <% 
                                            } else {
                                                for (Education education : educations) {
                                        %>
                                            <li><%= education.getTypeDiplome().getLabel() %> en <%= education.getBrancheEducation().getNomBranche() %></li>
                                        <% 
                                                }
                                            }
                                        %>
                                    </ul>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Expériences requises</h4>
                                    <ul class="list-ticked">
                                        <%
                                            List<Experience> experiences = recrutement.getPoste().getListExperience();
                                            if (experiences == null || experiences.isEmpty()) {
                                        %>
                                            <li>Aucune expérience requise pour ce poste</li>
                                        <% 
                                            } else {
                                                for (Experience experience : experiences) {
                                        %>
                                            <li><%= experience.getDureeMois() %> mois d'expérience en <%= experience.getSpecialite().getNomSpecialite() %> (<%= experience.isObligatoire() ? "Obligatoire" : "Facultatif" %>)</li>
                                        <% 
                                                }
                                            }
                                        %>
                                    </ul>
                                </div>
                            </div>
                            <% 
                                if(candidat != null){
                            %>
                                <a href="#" type="button" class="btn btn-success col-md-4">Postuler Maintenant</a>
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
