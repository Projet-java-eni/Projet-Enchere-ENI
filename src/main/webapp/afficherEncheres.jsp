<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- @Author Sego  -->  

<%@page import="fr.eni.encheres.bo.Enchere"%>
<%--  <%@page import="fr.eni.encheres.messages.LecteurMessage"%>--%>

    <%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des enchères</title>
</head>
<body>
<h1>ENCHERES</h1>


<!-- Ici je vais juste afficher toutes les enchères -->
<!-- faire soit sous forme de liste / soit avec image / pouvoir classer par dates -->
<div class="contenu">
	
		<%
		String dateFiltre="";
		if(request.getParameter("dateFiltre")!=null)
		{
			dateFiltre=request.getParameter("dateFiltre");
		}
		%>
		
		<form action="<%=request.getContextPath()%>/AfficherEncheresServlet" method="post">
			<input type="date" name="dateFiltre" value="<%=dateFiltre%>"/>
			<input type="submit" value="Filtrer"/>
			<a href="<%=request.getContextPath()%>/AfficherEncheresServlet"><input type="button" value="Réinitialiser"/></a>
		</form>
		
<!-- TP REPAS: renvoyait à une liste de message d'erreur dans la classe "lecteur message"
<%-- 		<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
		%>
				<p style="color:red;">Erreur :</p>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
					<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
		<%	
				}
			}
		%>
 
		<table align="center">
			<thead>
				<tr>
					<td>Date</td>
					<td>Heure</td>
					<td>Action</td>
				</tr>
			</thead>
				<%
					List<Enchere> listeEnchere = (List<Enchere>) request.getAttribute("listeEnchere");
					if(listeEnchere!=null && listeEnchere.size()>0)
					{
				%>
						<tbody>
							<%
							for(Enchere enchere : listeEnchere)
							{
							%>
								<tr>
									<td><%=enchere.getDateRepas()%></td>
									<td><%=enchere.getHeureRepas()%></td>
									
									<td><a href="<%=request.getContextPath()%>/repas?detail=<%=enchere.getId()%>&<%=dateFiltre%>">détail</a></td>
								</tr>
							<%
								if(String.valueOf(enchere.getId()).equals(request.getParameter("detail")))
								{
							%>
									<tr>
										<td colspan="3">
											<ul>
												<%
												for(Aliments aliment:repas.getListeAliments())
												{
												%>
													<li><%=aliment.getNom()%></li>
												<%
												}
												%>
											</ul>
										</td>
									</tr>
							<%
								}
							}
							%>
						</tbody>
				<%
					}
					else
					{
				%>
					<p>Il n'y a aucune enchères à afficher<P>
				<%
					}
				%>
	
	 --%>
			
		</table>
		
FIN DE LA LISTE MESSAGE D'ERREUR-->

<!-- Là je crée un bouton permettant d'acceder aux enchères selon leur statut -->

<label for="statut de l'enchère">
Choix du statut des enchères : 
</label>

<select name="statut" size="1">
<option value="en cours">En cours</option>
<option value="terminée">Terminée</option>
<option value="annulée">Annulée</option>
</select> 
<input type="submit" value="Ok"/>


	</div>


</body>
</html>