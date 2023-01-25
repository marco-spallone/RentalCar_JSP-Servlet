<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <script src="https://kit.fontawesome.com/6b1574191b.js" crossorigin="anonymous"></script>
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
                <a class="nav-link" href="utenteServlet?id=${id}&action=home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="autoServlet?tipo=1&id=${id}&action=home">Parco Auto</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="utenteServlet?action=profilo&tipo=1&id=${id}">Profilo utente</a>
            </li>
        </ul>
    </div>
    <div class="nav navbar-nav navbar-right">
        <a href="loginPage.jsp" class="navbar-brand"><button type="button" class="btn btn-danger"><i class="bi bi-box-arrow-right"></i> Esci</button></a>
    </div>
</nav>

<!--TABELLA UTENTI-->
<div class="container">
    <div class="row">
        <div class="mx-auto mt-5 col-sm-6">
            <div class="col-sm-3">
                <h2 class="mx-auto">Customers</h2>
            </div>
            <div class="row"><div class="mt-4 mb-4 col-sm-1">
                <a href="utenteServlet?action=aggiungi&id=${id}"><i class="fa-sharp fa-solid fa-user-plus fa-lg" style="color: dodgerblue"></i></a>
            </div></div>
            <div id="tabUtenti">
                <table class="table table-striped" id="tab">
                    <thead>
                    <tr>
                        <th scope="col">Nome</th>
                        <th scope="col">Cognome</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="utenti" items="${listaUt}">
                        <tr>
                            <td>${utenti.nome}</td>
                            <td>${utenti.cognome}</td>
                            <td>
                                <a href="utenteServlet?id=${id}&action=modifica&customer=${utenti.idUtente}">
                                    <button type="submit" class="btn"><i class="fa-solid fa-pencil fa-lg" style="color: green"></i></button>
                                </a>
                            </td>
                            <td>
                                <form action="prenotazioneServlet" method="get">
                                    <input type="hidden" name="id" value="${utenti.idUtente}">
                                    <input type="hidden" name="tipo" value="1">
                                    <input type="hidden" name="myid" value="${id}">
                                    <button type="submit" class="btn"><i class="fa-regular fa-calendar fa-lg" style="color: green"></i></button>
                                </form>
                            </td>
                            <td>
                                <form action="utenteServlet" method="post">
                                    <input type="hidden" name="action" value="elimina">
                                    <input type="hidden" name="id" value="${utenti.idUtente}">
                                    <button type="submit" class="btn"><i class="fa-solid fa-trash fa-lg" style="color: red"></i></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>