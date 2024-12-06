<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Résultats soumis</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f8f9fa;
            margin: 0;
        }
        .card {
            max-width: 500px;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="card shadow-sm text-center">
        <h3 class="card-title">Résultats envoyés</h3>
        <p class="card-text mt-3">
            Vos réponses ont été enregistrées et les résultats ont été transmis au responsable pour analyse.
            <br><br>
            Vous serez informé(e) prochainement concernant la suite de votre candidature.
        </p>
        <div class="mt-4">
            <a href="recrutement?role=candidat" class="btn btn-primary">OK</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
