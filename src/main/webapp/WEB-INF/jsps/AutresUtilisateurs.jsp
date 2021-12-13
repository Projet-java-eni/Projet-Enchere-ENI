<%--
  Created by IntelliJ IDEA.
  User: hurin
  Date: 13/12/2021
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="EnteteBootstrap.html" %>

<jsp:useBean id="infos" type="fr.eni.encheres.beans.Infos" scope="request" />
<jsp:useBean id="errors" type="fr.eni.encheres.beans.Erreurs" scope="request" />
<jsp:useBean id="utilisateur" type="fr.eni.encheres.bo.Utilisateur" scope="request" />

<c:if test="${errors.hasErrors()}">
    <div class="alert alert-danger">
        <p>Certaines données du formulaire sont incorrectes !</p>
        <ol>
            <c:forEach var="erreur" items="${errors.liste}">
                <li>${erreur}</li>
            </c:forEach>

        </ol>
    </div>
</c:if>


<c:if test="${infos.hasInfos()}">

    <div class="alert alert-success">
        <c:choose>
            <c:when test="${infos.liste.size() > 1}">
                <ul>
                    <c:forEach var="info" items="${infos.liste}">
                        <li>
                                ${info}
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                ${infos.liste.get(0)}
            </c:otherwise>
        </c:choose>
    </div>
</c:if>

<h1>Profil de ${utilisateur.pseudo}</h1>

<div class="card" style="width: 18rem;">
    <img class="card-img-top" src="<%=request.getContextPath()%>/images/logo_retreci.png" alt="Avatar">
    <div class="card-body">
        <h5 class="card-title">${utilisateur.pseudo}</h5>
        <p class="card-text">${utilisateur.prenom} ${utilisateur.nom}. </p>
        <p class="card-text">${utilisateur.email}, ${utilisateur.telephone} </p>
        <p class="card-text">${utilisateur.rue} ${utilisateur.codePostal}  ${utilisateur.ville}</p>
        <p class="card-text">${utilisateur.credit} points</p>
        <a href="#" class="btn btn-primary">Revenir</a>
    </div>
</div>


<%@ include file="PiedBootstrap.html" %>