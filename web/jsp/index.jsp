<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Fond dégradé fondu */
    body {
      background: linear-gradient(135deg, rgba(176, 190, 197, 0.9), rgba(144, 202, 249, 0.9));
      background-size: cover;
      height: 100vh; /* Pleine hauteur de la fenêtre */
      display: flex;
      justify-content: center; /* Centrage horizontal */
      align-items: center; /* Centrage vertical */
      margin: 0;
    }

    /* Conteneur de boutons */
    .button-container {
      text-align: center;
      background: rgba(255, 255, 255, 0.7); /* Fond semi-transparent */
      padding: 40px;
      border-radius: 10px;
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
    }

    /* Boutons Bootstrap */
    .btn-custom {
      width: 200px;
      margin: 10px;
      font-size: 18px;
    }
  </style>
</head>
<body>
  <div class="button-container">
    <a href="login?type=candidat" class="btn btn-primary btn-custom">Login Candidat</a>
    <a href="login?type=admin" class="btn btn-secondary btn-custom">Login Admin</a>
  </div>

  <!-- Bootstrap JS Bundle -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
