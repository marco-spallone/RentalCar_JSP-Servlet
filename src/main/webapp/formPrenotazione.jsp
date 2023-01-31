<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica prenotazione</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
  <script src="https://kit.fontawesome.com/6b1574191b.js" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="navbar.jsp">
  <jsp:param name="url4" value="prenotazioneServlet?id=${id}"/>
  <jsp:param name="url2" value="autoServlet?isAdmin=0&id=${id}&action=home"/>
  <jsp:param name="url3" value="utenteServlet?action=profilo&isAdmin=0&id=${id}"/>
</jsp:include>

<!--MODIFICA PRENOTAZIONE-->
<div class="container">
  <div class="row">
    <div class="mx-auto mt-5 col-sm-6">
      <h2>Modifica prenotazione</h2>
      <form action="prenotazioneServlet" method="post">
        <input type="hidden" name="action" value="formpren">
        <input type="hidden" name="autoSel" value="no">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="idPren" value=${idPren} />
        <div class="mb-3">
          <label for="inizio" class="form-label">Data inizio: </label>
          <input type="date" class="form-control" id="inizio" name="inizio" value="${prenotazione.dataInizio}">
        </div>
        <div class="mb-3">
          <label for="fine" class="form-label">Data fine: </label>
          <input type="date" class="form-control" id="fine" name="fine" value="${prenotazione.dataFine}">
        </div>
        <c:choose>
          <c:when test="${dateSelezionate=='1'}">
            <div class="mb-3">
              <label for="auto" class="form-label">Auto: </label>
              <select class="form-select" id="auto" name="auto">
                <c:forEach var="auto" items='${auto}'>
                  <option value="${auto.targa}">${auto.marca} ${auto.modello}</option>
                </c:forEach>
              </select>
            </div>
          </c:when>
          <c:otherwise></c:otherwise>
        </c:choose>
        <button type="submit" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
          <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
        </svg>Conferma</button>
        <a href="prenotazioneServlet?id=${id}"><button type="button" class="btn btn-danger">Annulla</button></a>
      </form>
    </div>
  </div>
</div>

</body>
</html>
