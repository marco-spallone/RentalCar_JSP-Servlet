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
<jsp:include page="navbar.jsp">
    <jsp:param name="url1" value="utenteServlet?id=${id}&action=home"/>
    <jsp:param name="url2" value="autoServlet?isAdmin=1&id=${id}&action=home"/>
    <jsp:param name="url3" value="utenteServlet?action=profilo&isAdmin=1&id=${id}"/>
</jsp:include>

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
            <div class="form-horizontal"><div class="form-group mt-2 mb-2">
                <form action="utenteServlet" method="post">
                    <input type="hidden" name="action" value="filtra" />
                    <input type="hidden" name="id" value="${id}" />
                    <div class="mb-3 d-inline-flex align-items-center col-sm-12">
                        <select class="form-select" id="filtraper" name="filtraper">
                            <option value="nome" selected>Nome</option>
                            <option value="cognome">Cognome</option>
                        </select>
                        <input class="form-control" type="text" name="filtra" id="filtra" placeholder="Filtra..."/>
                        <button class="btn btn-warning" type="submit">Filtra</button>
                    </div>
                </form>
                <a href="utenteServlet?id=${id}&action=home"><button class="btn btn-danger">Rimuovi filtro</button></a>
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
                                    <button type="submit" class="btn"><i class="fa-solid fa-pencil fa-lg" style="color: #f0ad4e"></i></button>
                                </a>
                            </td>
                            <td>
                                <form action="prenotazioneServlet" method="get">
                                    <input type="hidden" name="id" value="${utenti.idUtente}">
                                    <input type="hidden" name="isAdmin" value="1">
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