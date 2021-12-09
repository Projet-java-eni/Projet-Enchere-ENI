<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="EnteteBootstrap.html" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">ENI Enchères</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Accueil <span class="sr-only">(page actuelle)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Lien</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Liste
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Autre action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Etc</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Inactivé</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Recherche" aria-label="Recherche">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Recherche</button>
    </form>
  </div>
</nav>


Salut de Bootstrap
<%@ include file="PiedBootstrap.html" %>