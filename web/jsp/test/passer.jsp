```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Test" %>
<%@ page import="model.QuestionTest" %>
<%@ page import="model.ReponseTestPossible" %>
<%@ include file="../shared/head.jsp" %>
<body>
    <div class="container-scroller">
        <!-- Inclusion de la navbar -->
        <%@ include file="../shared/navbar.jsp" %>
        
        <div class="container-fluid page-body-wrapper">
            <!-- Inclusion de la sidebar -->
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
                                        <h2 class="card-title mb-4"><%= test.getTitre() %></h2>
                                        
                                        <form action="passerTest" method="post">
                                            <input type="hidden" name="idTest" value="<%= test.getIdTest() %>">
                                            <% 
                                            int questionNumero = 1;
                                            for (QuestionTest question : test.getQuestions()) { 
                                            %>
                                                <div class="card mb-3">
                                                    <div class="card-body">
                                                        <h4 class="card-title">Question <%= questionNumero %>: <%= question.getTexteQuestion() %></h4>
                                                        <div class="form-group">
                                                            <% 
                                                            for (ReponseTestPossible reponse : question.getReponsePossible()) { 
                                                            %>
                                                                <div class="form-check">
                                                                    <label class="form-check-label">
                                                                        <input type="radio" 
                                                                            class="form-check-input" 
                                                                            name="question_<%= question.getIdQuestionTest() %>" 
                                                                            value="<%= reponse.getIdReponseTestPossible() %>" 
                                                                            required>
                                                                        <%= reponse.getTexteReponse() %>
                                                                        <i class="input-helper"></i>
                                                                    </label>
                                                                </div>
                                                            <% 
                                                            } 
                                                            %>
                                                        </div>
                                                    </div>
                                                </div>
                                            <% 
                                                questionNumero++;
                                                } 
                                            %>
                                            
                                            <div class="text-right">
                                                <button type="submit" class="btn btn-primary">Soumettre le Test</button>
                                            </div>
                                        </form>
                                    <% 
                                    } else { 
                                    %>
                                        <div class="alert alert-warning">
                                            Aucun test n'est disponible pour le moment.
                                        </div>
                                    <% 
                                    } 
                                    %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Inclusion du footer -->
                <%@ include file="../shared/footer.jsp" %>
            </div>
        </div>
    </div>

    <%@ include file="../shared/script.jsp" %>
    <!-- Scripts JavaScript de Skydash -->
    
</body>
</html>
```