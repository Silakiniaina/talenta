<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Candidats</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="assets/js/app.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700;900&display=swap');

        *, body {
            font-family: 'Poppins', sans-serif;
            font-weight: 400;
            -webkit-font-smoothing: antialiased;
        }

        html, body {
            height: 100%;
            background-color: #152733;
            overflow: hidden;
            color: #f8f9fa;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            flex-direction: column;
        }

        .btn-container {
            margin-top: 30px;
        }

        .btn {
            font-size: 16px;
            padding: 10px 20px;
            margin: 10px;
            background-color: #495057;
            color: #f8f9fa;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #6c757d;
        }

        h1 {
            color: #fff;
            text-align: center;
            margin-bottom: 30px;
            font-weight: 600;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Gestion de talents</h1>

        <div class="btn-container">
            <a href="./PosteServlet" class="btn">Liste des Postes</a>
            <a href="./ajout-cv" class="btn">Ajouter CV</a>
            <a href="./SelectionServlet" class="btn">Liste CV Selectionnes</a>
            <a href="listeCVApresEntretien.jsp" class="btn">Liste CV Apres Entretien</a>
            <a href="remplirContrat.jsp" class="btn">Remplir Contrat</a>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
