<%@ page import="entities.Prenotazione" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.UtenteDaoImpl" %>
<%@ page import="entities.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Prenotazioni</title>
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
                <a class="nav-link" href="utenteServlet">Home</a>
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

<!--VISUALIZZAZIONE PRENOTAZIONI-->

<div class="container">
    <div class="row">
        <div class="mx-auto mt-5 col-sm-6">
            <h3>Prenotazioni effettuate dall'utente: ${cognomeUt} ${nomeUt}</h3>
            <div id="tabPrenotazioni">
                <table class="table table-striped" id="tab">
                    <thead>
                    <tr>
                        <th scope="col">Data inizio</th>
                        <th scope="col">Data fine</th>
                        <th scope="col">Auto</th>
                        <th scope="col">Confermata</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="prenotazioni" items="${prenotazioni}">
                        <tr>
                            <td>${prenotazioni.dataInizio}</td>
                            <td>${prenotazioni.dataFine}</td>
                            <td>${prenotazioni.auto}</td>
                            <td><c:choose>
                                <c:when test="${prenotazioni.confermata==true}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                        <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                    </svg>
                                </c:when>
                                <c:otherwise>
                                    Da confermare <br>
                                    <form action="prenotazioneServlet" method="post">
                                    <input type="hidden" name="id" value=${prenotazioni.idPrenotazione} />
                                    <input type="hidden" name="action" value="conferma">
                                        <button type="submit"><svg style="color: green" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
                                        <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"/>
                                    </svg></button></form>
                                    <form action="prenotazioneServlet" method="post">
                                        <input type="hidden" name="id" value=${prenotazioni.idPrenotazione} />
                                        <input type="hidden" name="action" value="rifiuta">
                                        <button type="submit"><svg style="color: red" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                                        <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                                    </svg></button></form>
                                </c:otherwise>
                            </c:choose></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="utenteServlet"><button type="button" class="btn btn-black">Indietro</button></a>
        </div>
    </div>
</div>
</body>
</html>
