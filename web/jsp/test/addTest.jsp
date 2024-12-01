    <%-- <%@include file="../shared/head.jsp" %>
    <body>
        <div class="container-scroller">
            <%@include file="../shared/navbar.jsp" %>
            <div class="container-fluid page-body-wrapper">
                <%@include file="../shared/sidebarAdmin.jsp" %>
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="row">
                            <div class="col-md-6 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Creation Test</h4>
                                        <form class="forms-sample" action="addTest" method="post">
                                            <div class="form-group">
                                                <label for="titre">Titre :</label>
                                                <input type="text" class="form-control" id="titre" name="titre" placeholder="Nom">
                                            </div>
                                            <div class="form-group">
                                                <label for="dateCreation">Date creation :</label>
                                                <input type="datetime-local" class="form-control" id="dateCreation" name="dateCreation" placeholder="Date Creation">
                                            </div>
                                            <div class="form-group">
                                                <label for="desc">Description :</label>
                                                <textarea name="desc" id="desc" placeholder="Description"  class="form-control"></textarea>
                                            </div>
                                            <button type="submit" class="btn btn-primary mr-2">Ajouter</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@ include file="../shared/footer.jsp" %>
                </div>
            </div>
        </div>
        <%@ include file="../shared/script.jsp" %>
    </body>
</html> --%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Création de Test QCM</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .section {
            margin-bottom: 20px;
            border: 1px solid #ddd;
            padding: 15px;
        }
        .reponse-item {
            display: flex;
            align-items: center;
            margin-bottom: 5px;
        }
        .btn {
            margin-left: 10px;
            padding: 5px 10px;
            cursor: pointer;
        }
        .reponse-attendue {
            background-color: #e0ffe0;
        }
    </style>
</head>
<body>
    <h1>Création de Test QCM</h1>

    <!-- Étape 1 : Création du Test -->
    <div id="creation-test" class="section">
        <h2>Informations du Test</h2>
        <form id="form-creation-test">
            <div>
                <label for="titre-test">Titre du Test :</label>
                <input type="text" id="titre-test" required>
            </div>
            <div>
                <label for="description-test">Description :</label>
                <textarea id="description-test"></textarea>
            </div>
            <div>
                <label for="responsable-test">Responsable :</label>
                <input type="text" id="responsable-test">
            </div>
            <button type="submit">Créer le Test</button>
        </form>
    </div>

    <!-- Étape 2 : Gestion des Questions -->
    <div id="gestion-questions" class="section" style="display:none;">
        <h2>Gestion des Questions du Test</h2>
        <div id="form-ajout-question">
            <h3>Ajouter une Question</h3>
            <div>
                <label for="libelle-question">Libellé de la Question :</label>
                <input type="text" id="libelle-question" required>
            </div>
            <button id="btn-ajouter-question">Ajouter Question</button>
        </div>

        <div id="liste-questions">
            <!-- Les questions seront listées ici dynamiquement -->
        </div>
    </div>

    <script>
        let testId = null;

        // Étape 1 : Création du Test
        document.getElementById('form-creation-test').addEventListener('submit', function(e) {
            e.preventDefault();

            const titre = document.getElementById('titre-test').value.trim();
            const description = document.getElementById('description-test').value.trim();
            const responsable = document.getElementById('responsable-test').value.trim();

            fetch('creerTest', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `titre=${encodeURIComponent(titre)}&description=${encodeURIComponent(description)}&responsable=${encodeURIComponent(responsable)}`
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    testId = data.testId;
                    // Masquer la section de création de test
                    document.getElementById('creation-test').style.display = 'none';
                    // Afficher la section de gestion des questions
                    document.getElementById('gestion-questions').style.display = 'block';
                    alert('Test créé avec succès !');
                } else {
                    alert('Erreur lors de la création du test');
                }
            })
            .catch(error => {
                console.error('Erreur :', error);
            });
        });

        // Étape 2 : Ajout de Questions
        document.getElementById('btn-ajouter-question').addEventListener('click', function() {
            const libelleQuestion = document.getElementById('libelle-question').value.trim();

            if (!libelleQuestion) {
                alert('Veuillez saisir un libellé pour la question');
                return;
            }

            fetch('ajouterQuestion', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `testId=${testId}&libelle=${encodeURIComponent(libelleQuestion)}`
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // Créer la structure pour la nouvelle question
                    const questionContainer = document.createElement('div');
                    questionContainer.classList.add('question-item');
                    questionContainer.dataset.questionId = data.questionId;
                    questionContainer.innerHTML = `
                        <h3>${libelleQuestion}</h3>
                        <div class="ajout-reponse">
                            <input type="text" class="nouvelle-reponse" placeholder="Nouvelle réponse">
                            <button class="btn btn-ajouter-reponse" data-question-id="${data.questionId}">
                                Ajouter Réponse
                            </button>
                        </div>
                        <div class="liste-reponses"></div>
                    `;

                    document.getElementById('liste-questions').appendChild(questionContainer);

                    // Réinitialiser le champ de saisie
                    document.getElementById('libelle-question').value = '';

                    // Attacher l'événement pour ajouter des réponses
                    attacherEvenementAjoutReponse(questionContainer);
                } else {
                    alert('Erreur lors de l\'ajout de la question');
                }
            })
            .catch(error => {
                console.error('Erreur :', error);
            });
        });

        // Fonction pour attacher l'événement d'ajout de réponse
        function attacherEvenementAjoutReponse(questionContainer) {
            const btnAjouterReponse = questionContainer.querySelector('.btn-ajouter-reponse');
            const inputReponse = questionContainer.querySelector('.nouvelle-reponse');
            const listeReponses = questionContainer.querySelector('.liste-reponses');
            const questionId = btnAjouterReponse.dataset.questionId;

            btnAjouterReponse.addEventListener('click', function() {
                const libelleReponse = inputReponse.value.trim();

                if (!libelleReponse) {
                    alert('Veuillez saisir un libellé pour la réponse');
                    return;
                }

                fetch('ajouterReponse', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: `questionId=${questionId}&libelle=${encodeURIComponent(libelleReponse)}`
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        // Créer l'élément de réponse
                        const reponseDiv = document.createElement('div');
                        reponseDiv.classList.add('reponse-item');
                        reponseDiv.dataset.reponseId = data.reponseId;
                        reponseDiv.innerHTML = `
                            <span>${libelleReponse}</span>
                            <button class="btn btn-definir-attendue" 
                                    data-question-id="${questionId}" 
                                    data-reponse-id="${data.reponseId}">
                                Définir comme réponse attendue
                            </button>
                        `;

                        listeReponses.appendChild(reponseDiv);

                        // Réinitialiser l'input
                        inputReponse.value = '';

                        // Attacher l'événement pour définir la réponse attendue
                        attacherEvenementReponseAttendue(reponseDiv);
                    } else {
                        alert('Erreur lors de l\'ajout de la réponse');
                    }
                })
                .catch(error => {
                    console.error('Erreur :', error);
                });
            });
        }

        // Fonction pour attacher l'événement de définition de réponse attendue
        function attacherEvenementReponseAttendue(reponseDiv) {
            const btnDefinirAttendue = reponseDiv.querySelector('.btn-definir-attendue');

            btnDefinirAttendue.addEventListener('click', function() {
                const questionId = this.dataset.questionId;
                const reponseId = this.dataset.reponseId;

                fetch('definirReponseAttendue', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: `questionId=${questionId}&reponseId=${reponseId}`
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        // Retirer la classe de toutes les réponses de cette question
                        const questionContainer = reponseDiv.closest('.question-item');
                        questionContainer.querySelectorAll('.reponse-item').forEach(item => {
                            item.classList.remove('reponse-attendue');
                        });

                        // Ajouter la classe à la réponse sélectionnée
                        reponseDiv.classList.add('reponse-attendue');

                        alert('Réponse attendue mise à jour');
                    } else {
                        alert('Erreur lors de la définition de la réponse attendue');
                    }
                })
                .catch(error => {
                    console.error('Erreur :', error);
                });
            });
        }
    </script>
</body>
</html>
