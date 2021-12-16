<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.eni.encheres.bo.Categorie" %>
<%@ page import="fr.eni.encheres.bo.Article" %>

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

<jsp:include page="blocs/infos.jsp">
	<jsp:param name="errors" value="${requestScope.infos}"/>
</jsp:include>


<!-- Bouton retour accueil connecté -->
	<section>
		<div class="titre">
			<h1>Détails de l'article</h1>
		</div>

<!-- Récupérer les infos de l'article -->

<%

	Integer noArticle = (Integer)request.getAttribute("noArticle");
	String nomArticle = (String)request.getAttribute("nomArticle");
	Article article = (Article) request.getAttribute("article");
	String categorie = (String)request.getAttribute("libelleCategorie");
	String etiquetteCategorie = (String)request.getAttribute("etiquette_categorie");
	List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
	Integer idVendeur = (Integer) request.getAttribute("idVendeur");
	String pseudoVendeur = (String)request.getAttribute("pseudoVendeur");
	String description = (String)request.getAttribute("description");
	String rue = (String)request.getAttribute("rue");
	String codePostal = (String)request.getAttribute("codePostal");
	String ville = (String)request.getAttribute("ville");
	int miseAPrix = (int)request.getAttribute("miseAPrix");
	int meilleureOffre = (int)request.getAttribute("meilleureOffre");
	LocalDate dateDebutEnchere = (LocalDate)request.getAttribute("dateDebutEnchere");
	LocalDate dateFinEnchere = (LocalDate)request.getAttribute("dateFinEnchere");
	LocalTime heureDebutEnchere = (LocalTime)request.getAttribute("heureDebutEnchere");
	LocalTime heureFinEnchere = (LocalTime)request.getAttribute("heureFinEnchere");
	Boolean aDebute = (Boolean) request.getAttribute("aDebute");
	Boolean estFinie = (Boolean) request.getAttribute("estFinie");
	int offreMin =(int)request.getAttribute("prixMin");
	Boolean connecte = (Boolean) request.getAttribute("connecte");
	Boolean estProprietaire = (Boolean) request.getAttribute("est_proprietaire");
	Boolean peutEncherir = (Boolean) request.getAttribute("peut_encherir");
	Integer credit = (Integer) request.getAttribute("credit");
	 %>
		<article>
			<div><strong><%=nomArticle%></strong></div>
			
			<div class="details">
				<img src="images/fauteuil.jpg" title="chaise" alt="chaise">
				<% if(!connecte || !estProprietaire) { %>
					<table class="infos">
						<tr>
							<th>Catégorie : <%=categorie%></th>
						</tr>
						<tr>
							<th> Vendu par <a href="<%=request.getContextPath()%>/Utilisateur/<%=idVendeur%>"><%=pseudoVendeur%></a></th>
						</tr>
						<tr>
							<th><%=description%></th>
						</tr>
						<tr>
							<th>Adresse de retrait : <%=rue%><%=codePostal%><%=ville%></th>
						</tr>
					</table>
				<% } %>
				<% if(estProprietaire) {%>
					<form method="post">
					<table class="infos">
						<tr>
							<th>Nom
								<input type="text" name="nom" value="<%=nomArticle%>">
							</th>
						</tr>

						<tr>
							<th>Catégorie :
								<select name="categorie">
									<% for (Categorie cat: categories) {%>
									<option value="<%=cat.getEtiquette()%>"
											<%= cat.getEtiquette().equals(etiquetteCategorie) ? "selected" : ""%>>
										<%=cat.getLibelle()%>
									</option>
									<% } %>
								</select>
							</th>
						</tr>
						<tr>
							<th><textarea name="description"><%=description%></textarea></th>
						</tr>
						<tr>
							<th>Adresse de retrait : <input name="rue" value="<%=rue%>"><br>
								<input name="code_postal" value="<%=codePostal%>"><br>
								<input name="ville" value="<%=ville%>"></th>
						</tr>
						<tr>
							<th>Debut enchere 
							<input type="date" name="date_debut_enchere" value="<%=dateDebutEnchere%>">
							<input type="time" name="heure_debut_enchere" value="<%=heureDebutEnchere%>">
							</th>						
						</tr>
						<tr>
							<th>Fin enchere 
							<input type="date" name="date_fin_enchere" value="<%=dateFinEnchere%>">
							<input type="time" name="heure_fin_enchere" value="<%=heureFinEnchere%>">
							</th>						
						</tr>
						<tr>
							<th>Mise a prix
							<input type="number" name="prix" value="<%=miseAPrix%>">
							</th>
						</tr>
						<tr>
							<td>
						<input type="submit" name="mettre_a_jour" value="Mettre à jour" />
							</td>
						</tr>
					</table>
					</form>
				<% } %>
			</div>
		</article>

		<% if (connecte ) { %>
			<% if(peutEncherir)  {%>
			<div class="encherir">
				<table>
					<tr>
						<th>Mise à prix : </th>
						<td><%=miseAPrix%></td>
					</tr>
					<tr>
						<th>Meilleure offre : </th>
						<td><%=meilleureOffre%> par ${requestScope.enchere.utilisateur.pseudo}</td>
					</tr>
					<tr>
						<th>Date de début de l'enchère : </th>
						<td><%=dateDebutEnchere%> à <%=heureDebutEnchere%>
						A débuté ? <%=aDebute ? "oui" : "non"%>
						</td>
					</tr>
					<tr>
						<th>Date de fin de l'enchère : </th>
						<td><%=dateFinEnchere%> à <%=heureFinEnchere%>
						Est finie ? <%=estFinie? "oui" : "non"%></td>
					</tr>
				</table>
				<div class="offre">
					<form method="post">
						<input type="hidden" name="noArticle" value="<%=noArticle%>">
						<p><label for="offre">FAIRE UNE OFFRE (solde: <%=credit%>)</label></p>
						<input type="number"
							   id="offre"  max="9999" step="1"
							   name="nouvelleOffre" value="<%=offreMin %>"/>
						<input class="valider" name="encherir" type="submit" value="Enchérir">
					</form>
				</div>
			</div>
			<% } %>
			<% if(estProprietaire) { %>
				<% if (!aDebute && !article.isAnnuleParVendeur()) { %>
				<form method="post">
				<div class="annuler_vente">
					<input type="submit" name="annuler" value="Annuler la vente">
				</div>
				</form>
					La vente commence à <%=dateDebutEnchere%> à <%=heureDebutEnchere%>
				<% } else if(!aDebute && article.isAnnuleParVendeur()) { %>
					<form method="post">
						<div class="remettre_vente">
							<input type="submit" name="restaurer" value="Restaurer la vente">
						</div>
					</form>

					La vente débute à <%=dateDebutEnchere%> à <%=heureDebutEnchere%>
				<% } %>
			<% } %>
		<% } %>
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
         	