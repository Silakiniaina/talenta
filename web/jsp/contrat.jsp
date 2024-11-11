<%@page import="java.util.List"%>
<%@page import="model.Person"%>
<%@page import="model.Poste"%>
<%
    List<Person> listePerson = (List<Person>) request.getAttribute("listePerson");
    List<Poste> listePost = (List<Poste>) request.getAttribute("listePost");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire de Sélection</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Formulaire de Sélection</h1>
        <form action="yourFormActionURL" method="post">

            <!-- Select for Person -->
            <div class="mb-3">
                <label for="person-select" class="form-label">Personne</label>
                <select id="person-select" name="personId" class="form-select" required>
                    <% for (Person person : listePerson) { %>
                        <option value="<%= person.getId() %>"><%= person.getNom() %> <%= person.getPrenom() %></option>
                    <% } %>
                </select>
            </div>

            <!-- Select for Post -->
            <div class="mb-3">
                <label for="post-select" class="form-label">Poste</label>
                <select id="post-select" name="postId" class="form-select" required>
                    <% for (Poste post : listePost) { %>
                        <option value="<%= post.getId() %>"><%= post.getNom() %></option>
                    <% } %>
                </select>
            </div>

            <!-- Date Input for Debut -->
            <div class="mb-3">
                <label for="date-debut" class="form-label">Date de Début</label>
                <input type="date" id="date-debut" name="dateDebut" class="form-control" required>
            </div>

            <!-- Date Input for Fin -->
            <div class="mb-3">
                <label for="date-fin" class="form-label">Date de Fin</label>
                <input type="date" id="date-fin" name="dateFin" class="form-control" required>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary">Soumettre</button>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
