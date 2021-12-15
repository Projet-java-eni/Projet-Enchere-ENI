<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate"%>

<!-- @author Lucie -->

<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/detail_vente.css"
	rel="stylesheet">
<title>Détail Vente</title>
<meta charset="UTF-8">
</head>

<%@ include file="../jsps/EnteteBootstrap.jspf" %>

<body>

<jsp:include page="blocs/erreurs.jsp">
	<jsp:param name="errors" value="${requestScope.errors}"/>
</jsp:include>


<!-- Bouton retour accueil connecté -->
	<section>
		<div class="titre">
			<h1>Détails de l'article</h1>
		</div>

<!-- Récupérer les infos de l'article -->

<% 	String nomArticle = (String)request.getAttribute("nomArticle");
	String categorie = (String)request.getAttribute("libelleCategorie");
	String pseudoVendeur = (String)request.getAttribute("pseudoVendeur");
	String description = (String)request.getAttribute("description");
	String rue = (String)request.getAttribute("rue");
	String codePostal = (String)request.getAttribute("codePostal");
	String ville = (String)request.getAttribute("ville");
	int miseAPrix = (int)request.getAttribute("miseAPrix");
	int meilleureOffre = (int)request.getAttribute("meilleureOffre");
	LocalDate dateDebutEnchere = (LocalDate)request.getAttribute("dateDebutEnchere");
	LocalDate dateFinEnchere = (LocalDate)request.getAttribute("dateFinEnchere");
	int offreMin = meilleureOffre -1;
	 %>
		<article>
			<div><strong><%=nomArticle%></strong></div>
			
			<div class="details">
				<img src="images/fauteuil.jpg" title="chaise" alt="chaise">
				<table class="infos">
					<tr>
						<th>Catégorie : <%=categorie%></th>
					</tr>
					<tr>
						<th> Vendu par <a href=""><%=pseudoVendeur%></a></th>
					</tr>
					<tr>
						<th><%=description%></th>
					</tr>
					<tr>
						<th>Adresse de retrait : <%=rue%><%=codePostal%><%=ville%></th>
					</tr>
				</table>
			</div>
		</article>


		<div class="encherir">
			<table>
				<tr>
					<th>Mise à prix : </th>
					<td><%=miseAPrix%></td>
				</tr>
				<tr>
					<th>Meilleure offre : </th>
					<td><%=meilleureOffre%></td>
				</tr>
				<tr>
					<th>Date de fin de l'enchère : </th>
					<td><%=dateFinEnchere%></td>
				</tr>	
			</table>
			<div class="offre">
				<form action="ValiderOffreServlet" method="post">	
					<p><label for="offre">FAIRE UNE OFFRE</label></p>
					<input type="number" id="offre" min="<%=offreMin%>" max="9999" step="1" name="nouvelleOffre"/>
					<input class="valider"	type="submit" value="Enchérir">
				</form>
			</div>
		</div>
	</section>
</body>

<%@ include file="../jsps/PiedBootstrap.jspf" %>

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
         	