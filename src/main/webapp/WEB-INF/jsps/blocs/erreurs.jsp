<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.eni.encheres.beans.Erreurs" %><%--
  Created by IntelliJ IDEA.
  User: hurin
  Date: 14/12/2021
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="errors" type="fr.eni.encheres.beans.Erreurs" scope="request" />

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
