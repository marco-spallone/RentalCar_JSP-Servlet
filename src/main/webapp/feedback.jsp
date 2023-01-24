<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 20/01/2023
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rental Car</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
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
  <div class="nav navbar-nav navbar-right">
    <a href="loginPage.jsp" class="navbar-brand"><button type="button" class="btn btn-danger"><i class="bi bi-box-arrow-right"></i> Esci</button></a>
  </div>
</nav>

<!--CONFERMA-->
<div class="container">
  <div class="row"></div>
  <div class="row"></div>
    <div class="row">
      <div class="mx-auto mt-5 text-center col-sm-6">
        <c:choose>
          <c:when test="${action=='modifica_utente'}">
            <h3>Utente modificato con successo!</h3>
            <a href="utenteServlet"><button type="button" class="btn btn-success">Torna alla home</button></a>
          </c:when>
          <c:when test="${action=='elimina_utente'}">
            <h3>Utente eliminato con successo!</h3>
            <a href="utenteServlet"><button type="button" class="btn btn-success">Torna alla home</button></a>
          </c:when>
          <c:when test="${action=='conferma_prenotazione'}">
            <h3>Prenotazione accettata!</h3>
            <a href="prenotazioneServlet"><button type="button" class="btn btn-success">Torna alle prenotazioni</button></a>
          </c:when>
          <c:when test="${action=='rifiuta_prenotazione'}">
            <h3>Prenotazione rifiutata!</h3>
            <a href="prenotazioneServlet"><button type="button" class="btn btn-success">Torna alle prenotazioni</button></a>
          </c:when>
          <c:when test="${action=='login_failed'}">
            <script type="text/javascript">
              window.alert("LOGIN ERRATO!");
              window.location.href='loginPage.jsp';
            </script>
          </c:when>
          <c:when test="${action=='modifica_auto'}">
            <h3>Auto modificata con successo!</h3>
            <a href="autoServlet"><button type="button" class="btn btn-success">Torna al parco auto</button></a>
          </c:when>
          <c:when test="${action=='elimina_auto'}">
            <h3>Auto eliminata con successo!</h3>
            <a href="autoServlet"><button type="button" class="btn btn-success">Torna al parco auto</button></a>
          </c:when>
          <c:when test="${action=='redirect'}">
            <h2>Benvenuto ${utente.nome}</h2>
            <c:choose>
              <c:when test="${tipo==1}">
                <a href="utenteServlet"><button type="button" class="btn btn-success">Vai alla home</button></a>
              </c:when>
              <c:when test="${tipo==0}">
                <a href="prenotazioneServlet?id=${utente.idUtente}"><button type="button" class="btn btn-success">Vai alla home</button></a>
              </c:when>
              <c:otherwise></c:otherwise>
            </c:choose>
          </c:when>
          <c:when test="${action=='prenotazione_inserita'}">
            <h2>Prenotazione inserita con successo!</h2>
            <h3>Attendere la conferma da parte dell'amministratore</h3>
            <a href="prenotazioneServlet?id=${id}"><button type="button" class="btn btn-success">Torna alla tua home</button></a>
          </c:when>
          <c:otherwise></c:otherwise>
        </c:choose>


      </div>
  </div>
</div>

</body>
</html>
