<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 23/01/2023
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Parco auto</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<script src="https://kit.fontawesome.com/6b1574191b.js" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="navbar.jsp">
  <jsp:param name="url1" value="utenteServlet?id=${id}&action=home"/>
  <jsp:param name="url2" value="autoServlet?isAdmin=${isAdmin}&id=${id}&action=home"/>
  <jsp:param name="url3" value="utenteServlet?action=profilo&isAdmin=${isAdmin}&id=${id}"/>
  <jsp:param name="url4" value="prenotazioneServlet?id=${id}"/>
</jsp:include>

<div class="container">
  <div class="row">
    <div class="mx-auto mt-5 col-sm-6">
      <div class="col-sm-3">
        <h2 class="mx-auto">Auto</h2>
      </div>
      <c:choose>
        <c:when test="${isAdmin=='1'}">
          <div class="row"><div class="mt-4 mb-4 col-sm-4">
            <a href="autoServlet?id=${id}&action=aggiungi"><i class="fa-solid fa-car fa-lg" style="color: dodgerblue"></i></a>
          </div></div>
        </c:when>
        <c:otherwise></c:otherwise>
      </c:choose>
      <div id="tabAuto">
        <table class="table table-striped" id="tab">
          <thead>
          <tr>
            <th scope="col">Marca</th>
            <th scope="col">Modello</th>
            <th scope="col">Anno</th>
            <th scope="col">Prezzo</th>
            <th scope="col">Targa</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="auto" items="${auto}">
            <tr>
              <td>${auto.marca}</td>
              <td>${auto.modello}</td>
              <td>${auto.anno}</td>
              <td>€${auto.prezzo}</td>
              <td>${auto.targa}</td>
              <c:choose>
                <c:when test="${isAdmin=='1'}">
                  <td>
                    <a href="autoServlet?id=${id}&action=modifica&idAuto=${auto.idAuto}">
                      <button type="submit" class="btn"><i class="fa-solid fa-pencil fa-lg" style="color: #f0ad4e"></i></button>
                    </a>
                  </td>
                  <td>
                    <form action="autoServlet?isAdmin=1" method="post">
                      <input type="hidden" name="action" value="elimina">
                      <input type="hidden" name="idAuto" value="${auto.idAuto}">
                      <input type="hidden" name="id" value="${id}">
                      <button type="submit" class="btn"><i class="fa-solid fa-trash fa-lg" style="color: red"></i></button>
                    </form>
                  </td>
                </c:when>
                <c:otherwise></c:otherwise>
              </c:choose>
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
