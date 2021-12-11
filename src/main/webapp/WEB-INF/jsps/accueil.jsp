<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Liste des enchères</title>
</head>

<h1>Liste des enchères</h1>
<body>

<div class="contenu">
		<a href="<%=request.getContextPath()%>/encheresEnCours"><input type="button" value="Visualiser les enchères en cours"/></a>
	</div>
	<div class="contenu">
		<a href="<%=request.getContextPath()%>/encheresTerminees"><input type="button" value="Visualiser les enchères terminées"/></a>
	</div>


</body>
</html>