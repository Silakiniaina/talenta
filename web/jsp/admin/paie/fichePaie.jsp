<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.paie.FichePaie" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Fiche de Paie</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .fiche-paie {
            border: 1px solid #000;
            padding: 20px;
        }
        .section {
            margin-bottom: 20px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }
        .row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
        }
        .label {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <%
        // Récupérer les informations de la fiche de paie
        FichePaie fichePaie = (FichePaie) request.getAttribute("fichePaie");
        
        // Formateur de date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
    %>

    <div class="fiche-paie">
        <h1>Fiche de Paie</h1>
        
        <!-- Section Informations Personnelles -->
        <div class="section">
            <h2>Informations Personnelles</h2>
            <div class="row">
                <span class="label">Date :</span>
                <span><%= LocalDate.now() %></span>
            </div>
            <div class="row">
                <span class="label">Nom :</span>
                <span><%= fichePaie.getInformation().getNom() %> <%= fichePaie.getInformation().getPrenom() %></span>
            </div>
            <div class="row">
                <span class="label">Date de Naissance :</span>
                <span><%= dateFormat.format(fichePaie.getInformation().getDateNaissance()) %></span>
            </div>
            <div class="row">
                <span class="label">Âge :</span>
                <span><%= fichePaie.getInformation().getAge() %> ans</span>
            </div>
            <div class="row">
                <span class="label">Ancienneté :</span>
                <span><%= fichePaie.getInformation().getAncienete() %></span>
            </div>
            <div class="row">
                <span class="label">Email :</span>
                <span><%= fichePaie.getInformation().getEmail() %></span>
            </div>
            <div class="row">
                <span class="label">Numéro CNAPS :</span>
                <span><%= fichePaie.getInformation().getNumeroCnaps() %></span>
            </div>
            <div class="row">
                <span class="label">Adresse :</span>
                <span><%= fichePaie.getInformation().getAdresse() %></span>
            </div>
            <div class="row">
                <span class="label">Date d'Embauche :</span>
                <span><%= dateFormat.format(fichePaie.getInformation().getDateEmbauche()) %></span>
            </div>
            <div class="row">
                <span class="label">Poste :</span>
                <span><%= fichePaie.getInformation().getPoste() %></span>
            </div>
            <div class="row">
                <span class="label">Département :</span>
                <span><%= fichePaie.getInformation().getDepartement() %></span>
            </div>
            <div class="row">
                <span class="label">Taux Journalier :</span>
                <span><%= fichePaie.getTauxEmploye().getTauxJournalier() %></span>
            </div>
            <div class="row">
                <span class="label">Taux horaire :</span>
                <span><%= fichePaie.getTauxEmploye().getTauxHoraire() %></span>
            </div>
            <div class="row">
                <span class="label">Classification :</span>
                <span><%= fichePaie.getEmploye().getCsp().getCodeCsp() %></span>
            </div>
        </div>

        <!-- Section Salaire -->
        <div class="section">
            <h2>Rémunération</h2>
            <div class="row">
                <span class="label">Salaire Base :</span>
                <span><%= String.format("%.2f", fichePaie.getEmploye().getSalaireBase()) %> </span>
            </div>
            <div class="row">
                <span class="label">Prime de Rendement :</span>
                <span><%= String.format("%.2f", fichePaie.getSalaireEmploye().getPrimeRendement()) %> </span>
            </div>
            <div class="row">
                <span class="label">Prime d'Ancienneté :</span>
                <span><%= String.format("%.2f", fichePaie.getSalaireEmploye().getPrimeAncienete()) %> </span>
            </div>
            <div class="row">
                <span class="label">Congés Payés :</span>
                <span><%= fichePaie.getSalaireEmploye().getCongePaye() %> jours</span>
            </div>
            <div class="row">
                <span class="label">Montant Congés :</span>
                <span><%= String.format("%.2f", fichePaie.getSalaireEmploye().getMontantConge()) %> </span>
            </div>
        </div>

        <!-- Section Heures Supplémentaires -->
        <div class="section">
            <h2>Heures Supplémentaires</h2>
            <div class="row">
                <span class="label">Sup 30% :</span>
                <span><%= String.format("%.2f", fichePaie.getHeureSupplementaire().getSup30()) %></span>
            </div>
            <div class="row">
                <span class="label">Sup 40% :</span>
                <span><%= String.format("%.2f", fichePaie.getHeureSupplementaire().getSup40()) %></span>
            </div>
            <div class="row">
                <span class="label">Sup 50% :</span>
                <span><%= String.format("%.2f", fichePaie.getHeureSupplementaire().getSup50()) %></span>
            </div>
            <div class="row">
                <span class="label">Sup 100% :</span>
                <span><%= String.format("%.2f", fichePaie.getHeureSupplementaire().getSup100()) %></span>
            </div>
            <div class="row">
                <span class="label">Total Heures Supplémentaires :</span>
                <span><%= String.format("%.2f", fichePaie.getHeureSupplementaire().getTotal()) %></span>
            </div>
        </div>

        <!-- Section Absences Avec Solde -->
        <div class="section">
            <h2>Absences Avec Solde</h2>
            <div class="row">
                <span class="label">Repos Médical :</span>
                <span><%= fichePaie.getAbsenceAvecSolde().getReposMedical() %> jours</span>
            </div>
            <div class="row">
                <span class="label">Assistance Maternité :</span>
                <span><%= fichePaie.getAbsenceAvecSolde().getAssistanceMat() %> jours</span>
            </div>
            <div class="row">
                <span class="label">Hospitalisation Conventionnée :</span>
                <span><%= fichePaie.getAbsenceAvecSolde().getHospitalisationConv() %> jours</span>
            </div>
            <div class="row">
                <span class="label">Événement Familial :</span>
                <span><%= fichePaie.getAbsenceAvecSolde().getEvenementFamilial() %> jours</span>
            </div>
            <div class="row">
                <span class="label">Total Absences Avec Solde :</span>
                <span><%= fichePaie.getAbsenceAvecSolde().getTotal() %> jours</span>
            </div>
        </div>

        <!-- Section Absences Sans Solde -->
        <div class="section">
            <h2>Absences Sans Solde</h2>
            <div class="row">
                <span class="label">Retards :</span>
                <span><%= fichePaie.getAbsenceSansSolde().getRetard() %> heures</span>
            </div>
            <div class="row">
                <span class="label">Absences Sans Solde :</span>
                <span><%= fichePaie.getAbsenceSansSolde().getAbsenceSansSolde() %> jours</span>
            </div>
            <div class="row">
                <span class="label">Absences Non Autorisées :</span>
                <span><%= fichePaie.getAbsenceSansSolde().getAbsenceNonAutorise() %> jours</span>
            </div>
            <div class="row">
                <span class="label">Mise à Pied :</span>
                <span><%= fichePaie.getAbsenceSansSolde().getMiseAPied() %> jours</span>
            </div>
            <div class="row">
                <span class="label">Total Absences Sans Solde :</span>
                <span><%= fichePaie.getAbsenceSansSolde().getTotal() %> jours</span>
            </div>
        </div>

        <!-- Section Déductions Sociales et Fiscales -->
        <div class="section">
            <h2>Déductions Sociales et Fiscales</h2>
            <div class="row">
                <span class="label">Salaire Brut :</span>
                <span><%= String.format("%.2f", fichePaie.getDeductionFiscalEtSocial().getSalaireBrut()) %> </span>
            </div>
            <div class="row">
                <span class="label">Cotisation CNAPS :</span>
                <span><%= String.format("%.2f", fichePaie.getDeductionFiscalEtSocial().getCotisationCnaps()) %> </span>
            </div>
            <div class="row">
                <span class="label">Cotisation OSTIE :</span>
                <span><%= String.format("%.2f", fichePaie.getDeductionFiscalEtSocial().getCotisationOstie()) %> </span>
            </div>
            <div class="row">
                <span class="label">IRSA :</span>
                <span><%= String.format("%.2f", fichePaie.getDeductionFiscalEtSocial().getIrsa()) %> </span>
            </div>
            <div class="row">
                <span class="label">Salaire Net :</span>
                <span><%= String.format("%.2f", fichePaie.getDeductionFiscalEtSocial().getSalaireNet()) %> </span>
            </div>
        </div>
    </div>
</body>
</html>