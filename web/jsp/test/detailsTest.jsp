```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Test" %>
<%@ page import="model.QuestionTest" %>
<%@ page import="model.ReponseTestPossible" %>
<%@include file="../shared/head.jsp" %>
<body>
    <div class="container-scroller">
        <%@ include file="../shared/navbar.jsp" %>
        <div class="container-fluid page-body-wrapper">
            <%@ include file="../shared/sidebarAdmin.jsp" %>
            <div class="main-panel">
                <div class="content-wrapper">
                    <div class="row">
                        <div class="col-md-12 grid-margin">
                            <div class="card">
                                <div class="card-body">
                                    <% 
                                    Test test = (Test) request.getAttribute("test");
                                    if (test != null) { 
                                    %>
                                    <h4 class="card-title">Détails du Test</h4>
                                    
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <tbody>
                                                <tr>
                                                    <th>ID Test</th>
                                                    <td><%= test.getIdTest() %></td>
                                                </tr>
                                                <tr>
                                                    <th>Titre</th>
                                                    <td><%= test.getTitre() %></td>
                                                </tr>
                                                <tr>
                                                    <th>Description</th>
                                                    <td><%= test.getDescription() %></td>
                                                </tr>
                                                <tr>
                                                    <th>Date de Création</th>
                                                    <td><%= test.getDateCreation() %></td>
                                                </tr>
                                                <tr>
                                                    <th>Responsable</th>
                                                    <td><%= test.getResponsable().getNomAdmin() %></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    
                                    <div class="mt-4">
                                        <h4 class="card-title">Questions du Test</h4>
                                        <% 
                                        for(QuestionTest questionTest : test.getQuestions()) { 
                                        %>
                                            <div class="card mb-3">
                                                <div class="card-body">
                                                    <h5 class="card-title">Question <%= questionTest.getIdQuestionTest() %>: <%= questionTest.getTexteQuestion() %></h5>
                                                    
                                                    <div class="list-group mb-3">
                                                        <% 
                                                        if (questionTest.getReponsePossible() != null) {
                                                            for(ReponseTestPossible reponse : questionTest.getReponsePossible()) { 
                                                        %>
                                                            <a href="#" class="list-group-item list-group-item-action">
                                                                <%= reponse.getTexteReponse() %>
                                                            </a>
                                                        <% 
                                                            } 
                                                        }
                                                        %>
                                                    </div>
                                                    
                                                    <div class="d-flex justify-content-between">
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#reponseModal<%= questionTest.getIdQuestionTest() %>">
                                                            Ajouter Réponse
                                                        </button>
                                                    </div>

                                                    <!-- Modal pour Ajout de Réponse -->
                                                    <div class="modal fade" id="reponseModal<%= questionTest.getIdQuestionTest() %>" tabindex="-1" role="dialog" aria-labelledby="reponseModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="reponseModalLabel">Ajouter une Réponse</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form class="forms-sample" action="addReponseTest" method="POST">
                                                                        <input type="hidden" name="idQuestion" value="<%= questionTest.getIdQuestionTest() %>">
                                                                        <div class="form-group">
                                                                            <label for="reponse">Réponse</label>
                                                                            <input type="text" class="form-control" id="reponse" name="reponse" placeholder="Saisir la réponse">
                                                                        </div>
                                                                        <div class="form-check form-check-flat form-check-primary">
                                                                            <label class="form-check-label">
                                                                                <input type="checkbox" class="form-check-input" name="estAttendue">
                                                                                Est une réponse attendue
                                                                                <i class="input-helper"></i>
                                                                            </label>
                                                                        </div>
                                                                        <button type="submit" class="btn btn-primary mr-2">Ajouter</button>
                                                                        <button type="button" class="btn btn-light" data-dismiss="modal">Annuler</button>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        <% 
                                        } 
                                        %>
                                        
                                        <div class="d-flex justify-content-end mt-3">
                                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#questionModal">
                                                Ajouter Question
                                            </button>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Modal pour Ajout de Question -->
                <div class="modal fade" id="questionModal" tabindex="-1" role="dialog" aria-labelledby="questionModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="questionModalLabel">Ajouter une Question</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form class="forms-sample" action="addQuestionTest" method="POST">
                                    <input type="hidden" name="idTest" value="<%= test.getIdTest() %>">
                                    <div class="form-group">
                                        <label for="question">Question :</label>
                                        <input type="text" class="form-control" id="question" name="texte_question" placeholder="Saisir la question">
                                    </div>
                                    <button type="submit" class="btn btn-primary mr-2">Ajouter</button>
                                    <button type="button" class="btn btn-light" data-dismiss="modal">Annuler</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Pied de page partiel -->
                <%@ include file="../shared/footer.jsp" %>
            </div>
        </div>
    </div>

    <!-- Fichiers JavaScript requis pour Skydash -->
    <%@ include file="../shared/script.jsp" %>
</body>
</html>