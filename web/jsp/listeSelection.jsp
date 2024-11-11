<%@page import="java.util.List"%>
<%@page import="model.Personne"%>
<%@page import="model.Poste"%>
<%
    List<Poste> listePoste= (List<Poste>) request.getAttribute("listePoste");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des postes</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <link rel="stylesheet" href="assets/css/table.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <script src="assets/js/app.js"> </script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body ng-app="myApp" ng-controller="SelectionController">

    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="table-container">
            <h1>Liste des CV selectionnes</h1>

            <div class="select-container">
                <select id="poste-select" class="form-select" ng-model="idPoste" ng-change="chargerSelection()">
                    <% for (Poste p : listePoste) { %>
                        <option value="<%= p.getId() %>"><%= p.getNom() %></option>
                    <% } %>
                </select>
            </div>

            <table class="table table-dark table-bordered">
                <thead>
                    <tr>
                        <th>Rang</th>
                        <th>Nom</th>
                        <th>Prenom</th>
                        <th>Poste</th>
                        <th>Pourcentage</th>
                    </tr>
                </thead>
                <tbody ng-if="listeSelection.length > 0">
                    <tr ng-repeat="sel in listeSelection">
                        <td>{{ sel.rang }}</td>
                        <td>{{ sel.nom }}</td>
                        <td>{{ sel.prenom }}</td>
                        <td>{{ sel.poste.nom }}</td>
                        <td>{{ sel.pourcentage | number:2 }}</td>
                    </tr>
                </tbody>                
            </table>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
