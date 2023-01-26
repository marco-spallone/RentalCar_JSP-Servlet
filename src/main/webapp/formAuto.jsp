<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 23/01/2023
  Time: 16:53
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

<jsp:include page="navbar.jsp">
  <jsp:param name="url1" value="utenteServlet?id=${id}&action=home"/>
  <jsp:param name="url2" value="autoServlet?isAdmin=1&id=${id}&action=home"/>
  <jsp:param name="url3" value="utenteServlet?action=profilo&isAdmin=1&id=${id}"/>
</jsp:include>

<!--FORM DI MODIFICA-->
<div class="container">
  <div class="row">
    <div class="mx-auto mt-5 col-sm-6">
      <h2>Modifica auto</h2>
      <form action="autoServlet" method="post">
        <input type="hidden" name="action" value="formauto">
        <input type="hidden" name="id" value="${id}">
        <c:choose>
          <c:when test="${idAuto!=null}">
            <input type="hidden" name="idAuto" value="${idAuto}">
          </c:when>
          <c:otherwise></c:otherwise>
        </c:choose>
        <div class="mb-3">
          <label for="marca" class="form-label">Marca: </label>
          <input type="text" class="form-control" id="marca" name="marca" value="${auto.marca}">
        </div>
        <div class="mb-3">
          <label for="modello" class="form-label">Modello: </label>
          <input type="text" class="form-control" id="modello" name="modello" value="${auto.modello}">
        </div>
        <div class="mb-3">
          <label for="anno" class="form-label">Anno: </label>
          <input type="number" class="form-control" id="anno" name="anno" value="${auto.anno}">
        </div>
        <div class="mb-3">
          <label for="prezzo" class="form-label">Prezzo: </label>
          <input type="number" class="form-control" id="prezzo" name="prezzo" value="${auto.prezzo}">
        </div>
        <div class="mb-3">
          <label for="targa" class="form-label">Targa: </label>
          <input type="text" class="form-control" id="targa" name="targa" value="${auto.targa}">
        </div>
        <button type="submit" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
          <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
        </svg>Conferma</button>
        <a href="autoServlet?isAdmin=1&id=${id}&action=home"><button type="button" class="btn btn-danger">Annulla</button></a>
      </form>
    </div>
  </div>
</div>

</body>
</html>
