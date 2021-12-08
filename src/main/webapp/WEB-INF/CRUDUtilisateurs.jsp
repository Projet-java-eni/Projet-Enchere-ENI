<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="entete.html" %>
<%@ page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page import="java.util.List" %>
<style>
	form {
		display: grid;
		grid-gap: 1em;
		justify-items: end;
		justify-content: space-between;
	}

	button, input:not([type="radio"]):not([type="checkbox"]), select, textarea {
		border: 1px solid black;
		border-radius: 5px;	
	}
	
	label {
		margin-left: 1ex;	
	}
</style>
<h1>CRUD Pour les utilisateurs</h1>

<h2>Ajouter un utilisateur</h2>
<% if(request.getAttribute("nouvel_utilisateur") != null) { %>
	<div class="succes">
		L'utilissateur <%= ((Utilisateur)request.getAttribute("nouvel_utilisateur")).getPseudo() %> a été ajouté!
	</div>

<% } %>

<% if(request.getAttribute("erreurs") != null && ((List<String>)request.getAttribute("erreurs")).size() > 0) { %>
	<div class="erreur">
		Des ereurs sont survenues !
		<ol>
		<% for(String erreur: (List<String>)request.getAttribute("erreurs"))  { %>
			<li><%= erreur %></li>
		<% } %>
		</ol>
	</div>

<% } %>



<form method="post" class="form">
	<label>Pseudo : <input type="text" name="pseudo" /></label>
	<label>Mot de passe : <input type="text" name="mot_de_passe" /></label>
	<label>Nom : <input type="text" name="nom" /></label>
	<label>Prenom : <input type="text" name="prenom" /></label>
	<label>Email : <input type="text" name="email" /></label>
	<label>Telephone : <input type="text" name="telephone" /></label>
	<label>Rue : <input type="text" name="rue" /></label>
	<label>Code postal : <input type="text" name="codePostal" /></label>
	<label>Ville : <input type="text" name="ville" /></label>
	<label>Credit : <input type="text" step="1" name="credit"/></label>
	<label>Est administrateur ? <input type="checkbox" name="administrateur" /></label>

	<input type="submit" />

</form>


<h2>Liste des utilisateurs</h2>

<table>
<thead>
<tr>
	<th>Numéro</th><th>Pseudo</th><th>Prenom</th><th>Nom</th><th>Email</th><th>Telephone</th><th>Rue</th><th>Code postal</th>
	<th>Ville</th><th>Crédit</th><th>Administrateur</th>
</tr>
</thead>
	<%
	List<Utilisateur> allUsers = (List<Utilisateur>)request.getAttribute("all_users");
	for(Utilisateur user: allUsers) {
	%>
	<tr>
		<td><%= user.getNoUtilisateur() %></td>
		<td><%= user.getPseudo() %></td>
		<td><%= user.getPrenom() %></td>
		<td><%= user.getNom() %></td>
		<td><%= user.getEmail() %></td>
		<td><%= user.getTelephone() %></td>
		<td><%= user.getRue() %></td>
		<td><%= user.getCodePostal() %></td>
		<td><%= user.getVille() %></td>
		<td><%= user.getCredit() %></td>
		<td><%= user.isAdministrateur() %></td>
	</tr>
	<% } %>
	
</table>


<%@include file="pied.html" %>