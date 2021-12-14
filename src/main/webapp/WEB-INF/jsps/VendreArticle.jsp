<%--
  Created by IntelliJ IDEA.
  User: hurin
  Date: 14/12/2021
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="EnteteBootstrap.html" %>

<jsp:useBean id="infos" type="fr.eni.encheres.beans.Infos" scope="request" />
<jsp:useBean id="errors" type="fr.eni.encheres.beans.Erreurs" scope="request" />
<jsp:useBean id="article" type="fr.eni.encheres.bo.Article" scope="request" />
<jsp:useBean id="categories" type="java.util.List" scope="request" />

<%--<jsp:useBean id="utilisateur" type="fr.eni.encheres.bo.Utilisateur" scope="request" />--%>

<jsp:include page="blocs/erreurs.jsp">
    <jsp:param name="errors" value="${errors}"/>
</jsp:include>

<jsp:include page="blocs/infos.jsp">
    <jsp:param name="infos" value="${infos}"/>
</jsp:include>

<h1>Vendre un article</h1>

<form method="post">

    <div class="row mb-3">
        <label class="form-label" for="nom">Nom de votre article</label>
        <input class="form-control" type="text" maxlength="200" id="nom" name="nom" required value="${article.nomArticle}">
        <div class="form-text">Obligatoire.</div>
    </div>
    <div class="row mb-3">
        <label class="form-label" for="description">Description</label>
        <textarea class="form-control" id= "description" name="description" maxlength="300">${article.description}</textarea>
        <div class="form-text">300 caractères max. Facultatif.</div>

    </div>

    <div class="row mb-3">
        <label class="form-label" for="category">Catégorie</label>
        <select name="category" id="category" class="form-control">
            <c:forEach items="${categories}" var="category">
                <option value="${category.libelle}">${category.libelle}</option>
            </c:forEach>
        </select>
    </div>

    <div class="row mb-3">
    <label class="form-label" for="prix">Prix initial </label>
        <input class="form-control" id="prix" name="prix" type="number" value="${article.miseAPrix}">
    </div>
    <div class="row mb-3">
    <label class="form-label" for="date">Date de mise en vente</label>
        <input class="form-control" id="date" name="date" type="datetime-local" value="${article.dateDebutEnchere}">
    </div>

    <div class="row mb-3">
        <input class="btn btn-primary" type="submit" name="vendre" value="Programmer">
    </div>
</form>

<%@ include file="PiedBootstrap.html" %>