<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../EnteteBootstrap.html"%>

<h1>Bienvenue sur trocenchères</h1>

<form>

	<div class="row">
	<div class="mb-3 col">
		<label for="exampleInputPseudo" class="form-label">Pseudo</label> <input
			type="text" class="form-control" id="exampleInputPseudo"
			aria-describedby="emailHelp" required name="pseudo">
		<div id="emailHelp" class="form-text">Comment vous apparaitrez
			sur le site.</div>
	</div>

	<div class="mb-3 col">
		<label for="exampleInputName" class="form-label">Nom de
			famille</label> <input type="text" class="form-control" id="exampleInputName"
			aria-describedby="nomHelp" required name="nom">
		<!--     <div id="nomHelp" class="form-text">Votre nom.</div> -->
	</div>
	</div>

	<div class="row">
	<div class="mb-3 col">
		<label for="exampleInputFirstname" class="form-label">Prénom</label> <input
			type="text" class="form-control" id="exampleInputFirstname"
			aria-describedby="prenomHelp" required name="prenom">
		<!--     <div id="prenomHelp" class="form-text">Votre prénom.</div> -->
	</div>

	<div class="mb-3 col">
		<label for="exampleInputMail" class="form-label">Email</label> <input
			type="email" class="form-control" id="exampleInputMail"
			aria-describedby="prenomHelp" required name="email">
		<!--     <div id="prenomHelp" class="form-text">Votre adresse mail.</div> -->
	</div>
	</div>

	<div class="row">
	<div class="mb-3 col">
		<label for="exampleInputPhone" class="form-label">Telephone</label> <input
			type="tel" class="form-control" id="exampleInputPhone"
			aria-describedby="telephoneHelp" required name="telephone">
		<!--     <div id="telephoneHelp" class="form-text">Votre téléphone.</div> -->
	</div>

	<div class="mb-3 col">
		<label for="exampleInputRue" class="form-label">Rue</label> <input
			type="text" class="form-control" id="exampleInputRue"
			aria-describedby="rueHelp" required name="rue">
		<div id="rueHelp" class="form-text">Votre numéro et nom de rue.</div>
	</div>
	</div>

	<div class="row">
	<div class="mb-3 col">
		<label for="exampleInputRue" class="form-label">Code postal</label> <input
			type="text" class="form-control" id="exampleInputRue"
			aria-describedby="cpHelp" minlength="5" maxlength="5" required
			name="cp">
		<!--     <div id="cpHelp" class="form-text">Votre code postal.</div> -->
	</div>

	<div class="mb-3 col">
		<label for="exampleInputVille" class="form-label">Ville</label> <input
			type="text" class="form-control" id="exampleInputVille"
			aria-describedby="villeHelp" required name="ville">
		<!--     <div id="villeHelp" class="form-text">Votre ville.</div> -->
	</div>
	</div>

	<div class="row">
	<div class="mb-3 col">
		<label for="exampleInputPassword" class="form-label">Mot de
			passe</label> <input type="password" class="form-control"
			id="exampleInputPassword" aria-describedby="passwordHelp" required
			name="mdp">
		<div id="passwordHelp" class="form-text">6 lettres minimum.</div>
	</div>

	<div class="mb-3 col">
		<label for="exampleInputPasswordRep" class="form-label">Répétez
			le mot de passe</label> <input type="password" class="form-control"
			id="exampleInputPasswordRep" aria-describedby="passwordHelpRep"
			required name="mdp_rep">
		<!--     <div id="passwordHelpRep" class="form-text">Répétez le mot de passe.</div> -->
	</div>
	</div>


	<div class="mb-3 form-check">
		<input type="checkbox" class="form-check-input" id="exampleCheck1">
		<label class="form-check-label" for="exampleCheck1">Se
			souvenir de moi ?</label>
	</div>
	<button type="submit" class="btn btn-primary">S'inscrire</button>
</form>

<%@ include file="../PiedBootstrap.html"%>