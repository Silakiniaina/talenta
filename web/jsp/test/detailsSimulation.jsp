<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Simulation" %>
<%@ page import="model.QuestionSimulation" %>
<%@ page import="model.ReponseSimulationPossible" %>>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails de la Simulation</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 800px; margin: 0 auto; padding: 20px; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .question { margin-bottom: 15px; border: 1px solid #ddd; padding: 10px; }
        .reponses { margin-left: 20px; }
        .form-group { margin-bottom: 10px; }
        .reponse-form { background-color: #f9f9f9; padding: 10px; margin-top: 10px; }
    </style>
</head>
<body>
    <% 
    Simulation simulation = (Simulation) request.getAttribute("simulation");
    if (simulation != null) { 
    %>
    <h1>Détails de la Simulation</h1>
    
    <table>
        <tr>
            <th>ID Simulation</th>
            <td><%= simulation.getIdSimulation() %></td>
        </tr>
        <tr>
            <th>Titre</th>
            <td><%= simulation.getTitre() %></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><%= simulation.getDescription() %></td>
        </tr>
        <tr>
            <th>Date de Création</th>
            <td><%= simulation.getDateCreation() %></td>
        </tr>
        <tr>
            <th>Responsable</th>
            <td><%= simulation.getResponsable().getNomAdmin() %></td>
        </tr>
    </table>
    
    <h2>Questions de Simulation</h2>
    <% 
        if (simulation.getQuestions() != null) {
            for(QuestionSimulation question : simulation.getQuestions()) { 
    %>
        <div class="question">
            <h3>Question <%= question.getIdQuestionSimulation() %>: <%= question.getTexteQuestion() %></h3>
            
            <!-- Liste des réponses existantes -->
            <ul class="reponses">
                <% 
                if (question.getReponsePossible() != null) {
                    for(ReponseSimulationPossible reponse : question.getReponsePossible()) { 
                %>
                    <li><%= reponse.getTexteReponse() %></li>
                <% 
                    } 
                }
                %>
            </ul>
            
            <!-- Formulaire pour ajouter une réponse possible -->
            <div class="reponse-form">
                <h4>Ajouter une Réponse Possible</h4>
                <form action="AjouterReponsePossibleServlet" method="post">
                    <input type="hidden" name="idSimulation" value="<%= simulation.getIdSimulation() %>">
                    <input type="hidden" name="idQuestionSimulation" value="<%= question.getIdQuestionSimulation() %>">
                    
                    <div class="form-group">
                        <label for="texteReponse<%= question.getIdQuestionSimulation() %>">Texte de la Réponse :</label>
                        <input type="text" 
                               id="texteReponse<%= question.getIdQuestionSimulation() %>" 
                               name="texteReponse" 
                               required 
                               placeholder="Saisir une réponse possible">
                    </div>
                    
                    <button type="submit">Ajouter Réponse</button>
                </form>
            </div>
        </div>
    <% 
            } 
        }
    } else { 
    %>
        <p>Aucune simulation trouvée.</p>
    <% } %>

    <h2>Ajouter une Question</h2>
    <form action="AjouterQuestionServlet" method="post">
        <input type="hidden" name="idSimulation" value="<%= simulation != null ? simulation.getIdSimulation() : ""  %>">
        
        <div class="form-group">
            <label for="texteQuestion">Texte de la Question :</label>
            <input type="text" id="texteQuestion" name="texteQuestion" required>
        </div>
        
        <div class="form-group">
            <label>Réponses Possibles :</label>
            <div id="reponsesContainer">
                <input type="text" name="reponses" placeholder="Première réponse possible">
            </div>
            <button type="button" onclick="ajouterReponse()">+ Ajouter Réponse</button>
        </div>
        
        <button type="submit">Enregistrer la Question</button>
    </form>

    <script>
    function ajouterReponse() {
        const container = document.getElementById('reponsesContainer');
        const nouvelleReponse = document.createElement('input');
        nouvelleReponse.type = 'text';
        nouvelleReponse.name = 'reponses';
        nouvelleReponse.placeholder = 'Réponse possible supplémentaire';
        container.appendChild(nouvelleReponse);
    }
    </script>
</body>
</html>