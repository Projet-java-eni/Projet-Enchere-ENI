<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- @author Lucie -->

<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/detail_vente.css"
	rel="stylesheet">
<title>Détail Vente</title>
<meta charset="UTF-8">
</head>

<%@ include file="..//jsps/Entete.html" %>

<body>

<!-- Bouton retour accueil connecté -->
	<section>
		<div class="titre">
			<h1>Détails de l'article</h1>
		</div>

		<article>
			<div><strong>"Nom de l'article"</strong></div>
			
			<div class="details">
				<img src="images/chaise2.jpg" title="chaise" alt="chaise">
				<table class="infos">
					<tr>
						<th>Catégorie : "Nom de la catégorie"</th>
					</tr>
					<tr>
						<th> Vendu par <a href="">"pseudo du vendeur"</a></th>
					</tr>
					<tr>
						<th>"Description"</th>
					</tr>
					<tr>
						<th>Adresse de retrait : "adresse de retrait"</th>
					</tr>
				</table>
			</div>
		</article>


		<div class="encherir">
			<table>
				<tr>
					<th>Mise à prix : </th>
					<td>--</td>
				</tr>
				<tr>
					<th>Meilleure offre : </th>
					<td>--</td>
				</tr>
				<tr>
					<th>Date de fin de l'enchère : </th>
					<td>--</td>
				</tr>	
			</table>
			<div class="offre">
				<form action="ValiderOffreServlet" method="post">	
					<p><label for="offre">FAIRE UNE OFFRE</label></p>
					<input type="number" id="offre" min="5" max="9999" step="1" name="nouvelleOffre"/>
					<input class="valider"	type="submit" value="Enchérir">
				</form>
			</div>
		</div>
	</section>
</body>


</html>


<!-- If utilisateur acheteur -->
	<!-- Input montant nouvelle enchère -->
	<!-- Bouton valider -->
		

<!-- If utilisateur vendeur -->
	<!-- FACULTATIF 2ème Itération : Bouton modifier -->

<!-- If enchère terminée : afficher "vous avez remporté la vente" ou "Untel a remporté la vente" -->

<!-- Else (si enchère encore en cours) -->

			<!-- Récupérer le contenu de l'article -->
			
         	<!--choixJoueur = (int)request.getAttribute("choixJoueur"); --> 
         	