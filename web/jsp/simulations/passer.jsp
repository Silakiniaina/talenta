<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Passer une Simulation</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <%
    Simulation simulation = (Simulation) request.getAttribute("simulation");
    List<QuestionSimulation> questions = (List<QuestionSimulation>) request.getAttribute("questions");
    %>
    
    <h1><%=simulation.getTitre()%></h1>
    <p class="description"><%=simulation.getDescription()%></p>
    
    <form action="<%=request.getContextPath()%>/passer-simulation" method="post">
        <input type="hidden" name="id" value="<%=simulation.getIdSimulation()%>">
        
        <%
        if(questions != null) {
            for(QuestionSimulation question : questions) {
                List<ReponseSimulationPossible> reponses = question.getReponsesPossibles();
        %>
            <div class="question-block">
                <h3><%=question.getTexteQuestion()%></h3>
                <div class="reponses">
                    <%
                    if(reponses != null) {
                        for(ReponseSimulationPossible reponse : reponses) {
                    %>
                        <div class="reponse-option">
                            <input type="radio" 
                                   name="question_<%=question.getIdQuestionSimulation()%>" 
                                   value="<%=reponse.getIdReponseSimulationPossibles()%>" 
                                   id="reponse_<%=reponse.getIdReponseSimulationPossibles()%>">
                            <label for="reponse_<%=reponse.getIdReponseSimulationPossibles()%>">
                                <%=reponse.getTexteReponse()%>
                            </label>
                        </div>
                    <%
                        }
                    }
                    %>
                </div>
            </div>
        <%
            }
        }
        %>
        
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Soumettre mes réponses</button>
        </div>
    </form>
    
    <script>
        // Validation côté client
        document.querySelector('form').onsubmit = function(e) {
            const questions = document.querySelectorAll('.question-block');
            for(let question of questions) {
                const reponses = question.querySelectorAll('input[type="radio"]');
                let checked = false;
                for(let reponse of reponses) {
                    if(reponse.checked) {
                        checked = true;
                        break;
                    }
                }
                if(!checked) {
                    alert('Veuillez répondre à toutes les questions.');
                    e.preventDefault();
                    return false;
                }
            }
            return true;
        };
    </script>
</body>
</html>