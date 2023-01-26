<%@ page import="entities.Prenotazione" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.UtenteDaoImpl" %>
<%@ page import="entities.Utente" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%!
    private Object Date;
%>
<jsp:useBean id="now" class="java.util.Date"/>

<html>
<head>
    <title>Prenotazioni</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <script src="https://kit.fontawesome.com/1c742923ac.js" crossorigin="anonymous"></script>
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
                <c:choose>
                    <c:when test="${tipo=='1'}">
                        <a class="nav-link" href="utenteServlet?id=${myid}&action=home">Home</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="prenotazioneServlet?id=${myid}">Home</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="autoServlet?tipo=${tipo}&id=${myid}&action=home">Parco Auto</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="utenteServlet?action=profilo&tipo=${tipo}&id=${myid}">Profilo utente</a>
            </li>
        </ul>
    </div>
    <div class="nav navbar-nav navbar-right">
        <a href="loginPage.jsp" class="navbar-brand"><button type="button" class="btn btn-danger"><i class="bi bi-box-arrow-right"></i> Esci</button></a>
    </div>
</nav>

<!--VISUALIZZAZIONE PRENOTAZIONI-->
<div class="container">
    <div class="row">
        <div class="mx-auto mt-5 col-sm-8">
            <h3>Prenotazioni effettuate</h3>
            <div class="row"><div class="mt-4 mb-4 col-sm-1">
                <c:choose>
                    <c:when test="${tipo=='1'}"></c:when>
                    <c:otherwise>
                        <form action="prenotazioneServlet" method="post">
                            <input type="hidden" name="action" value="aggiunta_prenotazione">
                            <input type="hidden" name="id" value="${myid}">
                            <button type="submit" class="btn"><i class="fa-regular fa-calendar-plus fa-lg" style="color: dodgerblue"></i></button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div></div>
            <div class="row"><div class="mt-2 mb-2">
                <form action="prenotazioneServlet" method="post">
                    <input type="hidden" name="action" value="filtra" />
                    <input type="hidden" name="id" value="${myid}" />
                    <input type="hidden" name="tipo" value="${tipo}" />
                    <div class="mb-3">
                        <select class="form-select" id="filtraper" name="filtraper">
                            <option value="inizio" selected>Data inizio</option>
                            <option value="fine">Data fine</option>
                            <option value="targa">Targa auto</option>
                            <option value="confermata">Confermata</option>
                        </select>
                    </div><div class="mb-3">
                    <input class="form-control" type="text" name="filtra" id="filtra" placeholder="Filtra..."/>
                </div>
                    <button class="btn btn-warning" type="submit">Filtra</button>
                </form>
                <c:choose>
                    <c:when test="${tipo=='1'}">
                        <a href="utenteServlet?id=${myid}&action=home"><button class="btn btn-danger" type="submit">Annulla</button></a>
                    </c:when>
                    <c:otherwise>
                        <a href="prenotazioneServlet?id=${myid}"><button class="btn btn-danger" type="submit">Annulla</button></a>
                    </c:otherwise>
                </c:choose>
            </div></div>
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
                        <fmt:parseNumber var="giorni" value="${( prenotazioni.dataInizio.time - now.time ) / (1000*60*60*24) }"
                                         integerOnly="true" scope="page"/>
                        <tr>
                            <td>${prenotazioni.dataInizio}</td>
                            <td>${prenotazioni.dataFine}</td>
                            <td>${prenotazioni.auto.marca} ${prenotazioni.auto.modello}</td>
                            <td><c:choose>
                                    <c:when test="${prenotazioni.confermata==true}">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                            <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                        </svg>
                                        <c:choose>
                                            <c:when test="${tipo==1}"></c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${giorni>=2}">
                                                        </td>
                                                        <td>
                                                            <form action="prenotazioneServlet" method="post">
                                                                <input type="hidden" name="action" value="modifica_prenotazione">
                                                                <input type="hidden" name="id" value="${myid}">
                                                                <input type="hidden" name="idPren" value="${prenotazioni.idPrenotazione}">
                                                                <button type="submit" class="btn btn-outline-success">
                                                                <i class="fa-solid fa-pencil fa-lg" style="color: green"></i>Modifica</button>
                                                            </form>
                                                        </td><td>
                                                            <form action="prenotazioneServlet" method="post">
                                                                <input type="hidden" name="action" value="elimina_prenotazione">
                                                                <input type="hidden" name="id" value="${myid}">
                                                                <input type="hidden" name="idPren" value="${prenotazioni.idPrenotazione}">
                                                                <button type="submit" class="btn btn-outline-danger">
                                                                    <i class="fa-solid fa-trash fa-lg" style="color: red"></i>
                                                                    Elimina</button>
                                                            </form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>Prenotazione non modificabile, mancano 2 giorni o meno alla data di inizio</td><td></td>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${tipo=='1'}">
                                                <strong>Da confermare:</strong> <br>
                                                <form action="prenotazioneServlet" method="post">
                                                    <input type="hidden" name="id" value=${prenotazioni.idPrenotazione} />
                                                    <input type="hidden" name="action" value="conferma">
                                                    <button type="submit" class="mt-2 btn btn-outline-success"><i class="fa-solid fa-check" style="color: green">
                                                    </i> Accetta</button>
                                                </form>
                                                <form action="prenotazioneServlet" method="post">
                                                    <input type="hidden" name="id" value=${prenotazioni.idPrenotazione} />
                                                    <input type="hidden" name="action" value="rifiuta">
                                                    <button type="submit" class="btn btn-outline-danger"><i class="fa-solid fa-x" style="color: red">
                                                    </i> Rifiuta</button>
                                                </form>
                                                <c:choose>
                                                    <c:when test="${tipo=='1'}"></c:when>
                                                    <c:otherwise>
                                                        <c:when test="${giorni>=2}">
                                                            </td>
                                                            <td>
                                                                <form action="prenotazioneServlet" method="post">
                                                                    <input type="hidden" name="action" value="modifica_prenotazione">
                                                                    <input type="hidden" name="id" value="${myid}">
                                                                    <input type="hidden" name="tipo" value="${tipo}">
                                                                    <input type="hidden" name="idPren" value=${prenotazioni.idPrenotazione} />
                                                                    <button type="submit" class="btn btn-outline-success">
                                                                        <i class="fa-solid fa-pencil fa-lg" style="color: green"></i>Modifica</button>
                                                                </form>
                                                            </td><td>
                                                            <form action="prenotazioneServlet" method="post">
                                                                <input type="hidden" name="action" value="elimina_prenotazione">
                                                                <input type="hidden" name="id" value="${myid}">
                                                                <input type="hidden" name="tipo" value="${tipo}">
                                                                <input type="hidden" name="idPren" value="${prenotazioni.idPrenotazione}">
                                                                <button type="submit" class="btn btn-outline-danger">
                                                                    <i class="fa-solid fa-trash fa-lg" style="color: red"></i>
                                                                    Elimina</button>
                                                            </form>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>Prenotazione non modificabile, mancano 2 giorni o meno alla data di inizio</td><td></td>
                                                        </c:otherwise>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${giorni>=2}">
                                                        </td>
                                                        <td>
                                                        <form action="prenotazioneServlet" method="post">
                                                            <input type="hidden" name="action" value="modifica_prenotazione">
                                                            <input type="hidden" name="id" value="${myid}">
                                                            <input type="hidden" name="tipo" value="${tipo}">
                                                            <input type="hidden" name="idPren" value="${prenotazioni.idPrenotazione}">
                                                            <button type="submit" class="btn btn-outline-success">
                                                                <i class="fa-solid fa-pencil fa-lg" style="color: green"></i>Modifica</button>
                                                        </form>
                                                        </td><td>
                                                        <form action="prenotazioneServlet" method="post">
                                                            <input type="hidden" name="action" value="elimina_prenotazione">
                                                            <input type="hidden" name="id" value="${myid}">
                                                            <input type="hidden" name="tipo" value="${tipo}">
                                                            <input type="hidden" name="idPren" value="${prenotazioni.idPrenotazione}">
                                                            <button type="submit" class="btn btn-outline-danger">
                                                                <i class="fa-solid fa-trash fa-lg" style="color: red"></i>
                                                                Elimina</button>
                                                        </form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>Prenotazione non modificabile, mancano 2 giorni o meno alla data di inizio</td><td></td>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                </c:otherwise>
                            </c:choose></td>
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
