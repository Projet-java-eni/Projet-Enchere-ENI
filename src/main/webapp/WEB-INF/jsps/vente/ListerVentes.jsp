<%@ page import="fr.eni.encheres.bo.Article" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hurin
  Date: 14/12/2021
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../EnteteBootstrap.jspf" %>

<h1>Ventes en cours</h1>

<div style="display: flex; flex-direction: column; flex-wrap: wrap">
<c:forEach var="article" items="${requestScope.articles}">
    <div class="card" style="width: 18rem;">
        <img class="card-img-top" src="images/logo_retreci.png" alt="Image de la vente">
        <div class="card-body">
            <h5 class="card-title">${article.nomArticle}</h5>
            <p class="card-text">${article.description}</p>
            <a href="<%=request.getContextPath()%>/DetailArticle/${article.noArticle}" class="btn btn-primary">Voir</a>
        </div>
    </div>
</div>
</c:forEach>

<%@ include file="../PiedBootstrap.jspf" %>