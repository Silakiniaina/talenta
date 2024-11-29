<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Résultats de la Simulation</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <%
    Simulation simulation = (Simulation) request.getAttribute("simulation");
    List<QuestionSimulation> questions = (List<QuestionSimulation>) request.getAttribute("questions");
    List<ReponseSimulationCandidat> reponses = (List<ReponseSimulationCandidat>) request.getAttribute("reponses");
    Integer score = (Integer) request.getAttribute("score");
    %>
    
    <h1>Résultats : <%=simulation.getTitre()%></h1>
    
    <div class="score-section">
        <h2>Votre score : <%=score%>%</h2>
    </div>
    
    <div class="results-details">
        <%
        if(questions != null) {
            for(int i = 0; i < questions.size(); i++) {
                QuestionSimulation question = questions.get(i);
                ReponseSimulationCandidat reponse = reponses.get(i);
        %>
            <div class="question-result <%=reponse.isCorrect() ? "correct" : "incorrect"%>">
                <h3><%=question.getTexteQuestion()%></h3>
                <p>Votre réponse : <%=reponse.getTexteReponse()%></p>
                <% if(!reponse.isCorrect()) { %>
                    <p class="correct-answer">La bonne réponse était : <%=reponse.getReponseAttendue()%></p>
                <% } %>
            </div>
        <%
            }
        }
        %>
    </div>
    
    <div class="actions">
        <a href="<%=request.getContextPath()%>/simulations" class="btn btn-primary">
            Retour à la liste des simulations
        </a>
    </div>
</body>
</html>