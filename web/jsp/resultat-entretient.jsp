<%@page import="java.util.List"%>
<%@page import="model.Poste"%>
<%
    List<Poste> listePost = (List<Poste>) request.getAttribute("listePost");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des candidats</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <link rel="stylesheet" href="assets/css/table.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <script src="assets/js/app.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body ng-app="myApp" ng-controller="SelectionController">

    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="table-container">
            <h1>Liste des Candidats Sélectionnés</h1>

            <!-- Poste Select Filter -->
            <div class="select-container">
                <select id="post-select" class="form-select" ng-model="idPost" ng-change="loadCandidates()">
                    <% for (Poste post : listePost) { %>
                        <option value="<%= post.getId() %>"><%= post.getNom() %></option>
                    <% } %>
                </select>
            </div>

            <!-- Candidate List Table -->
            <table class="table table-dark table-bordered">
                <thead>
                    <tr>
                        <th>Rang</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Poste</th>
                        <th>Nb Soft Skills</th>
                        <th>Score</th>
                    </tr>
                </thead>
                <tbody ng-if="candidateList.length > 0">
                    <tr ng-repeat="candidate in candidateList">
                        <td>{{ candidate.rang }}</td>
                        <td>{{ candidate.nom }}</td>
                        <td>{{ candidate.prenom }}</td>
                        <td>{{ candidate.poste.nom }}</td>
                        <td>{{ candidate.nbSoftSkills }}</td>
                        <td>{{ candidate.score | number:2 }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
