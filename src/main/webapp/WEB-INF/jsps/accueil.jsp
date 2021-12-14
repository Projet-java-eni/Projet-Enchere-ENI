<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- @Author Sego  -->

<%@page import="fr.eni.encheres.bo.Enchere"%>
<%--  <%@page import="fr.eni.encheres.messages.LecteurMessage"%>--%>

<%@page import="java.util.List"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/accueil.css"
	rel="stylesheet">
<meta charset="UTF-8">
<title>Liste des enchères</title>
</head>
<body>
	<header>
		<div id="header-logo">
			<!-- logo -->
			<img
				src="<%=request.getContextPath()%>/images/logoProjet_Taille_reduite.png"
				alt="logo Association" />
		</div>

		<div id="header-user">



			<!-- Création / connexion à un compte -->
			<a href="<%=request.getContextPath()%>/Login"><input
				type="button" value="se connecter" /></a> | <a
				href="<%=request.getContextPath()%>/Inscription"><input
				type="button" value="créer un compte" /></a> | (<a
				href="<%=request.getContextPath()%>/Profil">
				${sessionScope.user_pseudo}</a>) <a
				href="<%=request.getContextPath()%>/Deconnexion">Se déconnecter</a>
		</div>

		<jsp:useBean id="infos" type="fr.eni.encheres.beans.Infos"
			scope="request" />

		<c:if test="${infos.hasInfos()}">

			<div class="alert alert-success" id="infos">
				<c:choose>
					<c:when test="${infos.liste.size() > 1}">
						<ul>
							<c:forEach var="info" items="${infos.liste}">
								<li>${info}</li>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						${infos.liste.get(0)}
					</c:otherwise>
				</c:choose>
			</div>
		</c:if>



		<br>
		<h1>ENCHERES</h1>

		<h2>Site d'enchères de seconde main</h2>

		<div class="listes_deroulantes" id="listes_deroulantes">

			<!-- Création liste déroulante permettant d'acceder aux enchères selon leur statut -->

			<table>
				<tr>
					<th><label for="statut de l'enchère"> Statut des
							enchères : </label> <select name="statut" size="1">
							<option value="en cours">Non commencées</option>
							<option value="en cours">En cours</option>
							<option value="terminée">Terminée</option>
							<option value="annulée">Mes ventes</option>
					</select></th>
					<td>
						<form method="link"
							action="<%=request.getContextPath()%>/AfficherEncheresServlet">
							<input type="submit" value="Ok" />
						</form>
					</td>
				</tr>


				<!-- Création liste déroulante permettant d'acceder aux enchères selon la categorie -->
				<tr>
					<th><label for="Catégorie de l'enchère"> Catégories :</label> <select
						name="catégorie" size="1">
							<option value="Toutes catégories">Toutes</option>
							<option value="ameublement">Ameublement</option>
							<option value="informatique">Informatique</option>
							<option value="sports_loisirs">Sports / Loisirs</option>
							<option value="vêtements">Vêtements</option>
							<option value="divers">Divers</option>
					</select></th>
					<td>
						<form method="link"
							action="<%=request.getContextPath()%>/PageEnConstructionServlet">
							<input type="submit" value="Ok" />
						</form>
					</td>
				</tr>
				
				
				<!-- Création liste déroulante permettant d'acceder aux enchères selon date -->
				<tr>
					<th><label for="Trier par date"> Trier par date :</label> <select
						name="statut" size="1">
							<option value="Nouvelles">Nouvelles enchères</option>
							<option value="Bientôt_terminées">Enchères bientôt terminées</option>
							
							
					</select></th>
					<td>
						<form method="link"
							action="<%=request.getContextPath()%>/PageEnConstructionServlet">
							<input type="submit" value="Ok" />
						</form>
					</td>
				</tr>
				
				
				
				<!-- Là je crée un bouton permettant d'acceder aux enchères selon leur date -->
				<tr>
					<td>
						<%
						String dateFiltre = "";
						if (request.getParameter("dateFiltre") != null) {
							dateFiltre = request.getParameter("dateFiltre");
						}
						%>

				
					<form
						action="<%=request.getContextPath()%>/AfficherEncheresServlet"
						method="post">
					<input type="date" name="dateFiltre"
						value="<%=dateFiltre%>" /> <input type="submit" value="Filtrer" />
						<a href="<%=request.getContextPath()%>/AfficherEncheresServlet"><input
							type="button" value="Réinitialiser" /></a>
						</form></td>
				<tr>
			</table>
		</div>


	</header>
	<!-- faire soit sous forme de liste / soit avec image / pouvoir classer par dates -->
	<hr>


	<!-- Ici je vais juste afficher toutes les enchères en commençant par les plus récentes-->
	<article class="articles" id="articles">
		<table class="table_articles" id="table_articles">
			<tr>
				<td style="text-align: center"><img
				src="<%=request.getContextPath()%>/images/logoProjet_Taille_reduite.png"
				alt="logo Association" />
					<h3>enchere1</h3></td>
				<td style="text-align: center"><img
				src="<%=request.getContextPath()%>/images/fauteuil.jpg"
				alt="logo Association" />
					<h3>enchere2</h3>
				</td>
				<td style="text-align: center"><img
				src="<%=request.getContextPath()%>/images/logoProjet_Taille_reduite.png"
				alt="logo Association" />
					<h3>enchere3</h3>
				</td>
			</tr>
			<tr>
				<td style="text-align: center"><img
				src="<%=request.getContextPath()%>/images/fauteuil.jpg"
				alt="logo Association" />
					<h3>enchere4</h3>
				</td>
				<td style="text-align: center"><img
				src="<%=request.getContextPath()%>/images/logoProjet_Taille_reduite.png"
				alt="logo Association" />
					<h3>enchere5</h3>
				</td>
				<td style="text-align: center"><img
				src="<%=request.getContextPath()%>/images/fauteuil.jpg"
				alt="logo Association" />
					<h3>enchere6</h3>
				</td>
			</tr>
		</table>




	</article>
	<hr>





	<footer>


		<a href="<%=request.getContextPath()%>"><input type="button"
			value="Retour à l'accueil" /></a>

		<div id="footer-author">
			<!-- auteur -->
			<a href="mailto:troc.encheres@gmail.com"
				title="Si vous désirez nous écrire">Les objets sont nos amis</a>
		</div>
	</footer>

</body>
</html>