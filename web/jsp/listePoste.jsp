<%@page import="java.util.List"%>
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
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/table.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="table-container">
            <h1>Liste des postes</h1>
            <table class="table table-dark table-bordered">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nom</th>
                        <th>Places totales</th>
                        <th>Places occupees</th>
                        <th>Etat</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Poste p: listePoste) { %>
                        <tr>
                            <td><%= p.getId() %></td>
                            <td><%= p.getNom() %></td>
                            <td><%= p.getTotalPlace() %></td>
                            <td><%= p.getOccupees() %></td>
                            <td><%= p.getEtat() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
