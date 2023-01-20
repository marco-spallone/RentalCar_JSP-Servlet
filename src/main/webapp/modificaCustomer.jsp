<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 20/01/2023
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica utente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>

<!-- HEADER -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Rental Car</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="homeServlet">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Parco Auto</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Profilo utente</a>
            </li>
        </ul>
    </div>
</nav>

<!--FORM DI MODIFICA-->
    <div class="container">
        <div class="row">
            <div class="mx-auto mt-5 col-sm-6">
                <h2>Modifica dati per: ${utente.cognome}  ${utente.nome}</h2>
                <form action="modifyServlet" method="post">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome: </label>
                        <input type="text" class="form-control" id="nome" name="nome">
                    </div>
                    <div class="mb-3">
                        <label for="cognome" class="form-label">Cognome: </label>
                        <input type="text" class="form-control" id="cognome" name="cognome">
                    </div>
                    <button type="submit" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                        <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                    </svg>Conferma</button>
                    <a href="homeServlet"><button type="button" class="btn btn-danger">Annulla</button></a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
