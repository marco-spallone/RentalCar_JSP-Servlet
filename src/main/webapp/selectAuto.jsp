<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 30/01/2023
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Seleziona auto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <script src="https://kit.fontawesome.com/6b1574191b.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="url4" value="prenotazioneServlet?id=${id}"/>
    <jsp:param name="url2" value="autoServlet?id=${id}&isAdmin=0&action=home"/>
    <jsp:param name="url3" value="utenteServlet?action=profilo&id=${id}"/>
</jsp:include>
<div class="container">
    <div class="row">
        <div class="mx-auto mt-5 col-sm-6">
            <form action="prenotazioneServlet" method="post">
                <input type="hidden" name="action" value="formpren">
                <input type="hidden" name="id" value="${id}">
                <c:choose>
                    <c:when test="${idPren!=null}">
                        <input type="hidden" name="idPren" value=${idPren}>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="autoSel" value="si">
                <input type="hidden" name="inizio" value="${inizio}">
                <input type="hidden" name="fine" value="${fine}">
                <div class="mb-3">
                    <label for="auto" class="form-label">Auto: </label>
                    <select class="form-select" id="auto" name="auto">
                        <c:forEach var="auto" items='${auto}'>
                            <option value="${auto.targa}">${auto.marca} ${auto.modello}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-success">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-check" viewBox="0 0 16 16">
                        <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                    </svg>
                    Conferma
                </button>
                <a href="prenotazioneServlet?id=${id}">
                    <button type="button" class="btn btn-danger">Annulla</button>
                </a>
            </form>
        </div>
    </div>
</div>


</body>
</html>
