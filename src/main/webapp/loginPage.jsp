<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 23/01/2023
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<html>
<head>
  <title>Aggiungi utente</title>
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
</nav>

<!--LOGIN-->
<div class="container">
  <div class="row">
    <div class="mx-auto mt-5 col-sm-3">
      <h2>Login</h2>
      <form action="utenteServlet" method="post">
        <input type="hidden" name="action" value="login">
        <label for="user">Username: </label><input type="text" name="user" id="user" placeholder="Username" class="form-control">
        <label for="pass">Password: </label><input type="password" name="pass" id="pass" placeholder="Password" class="form-control">
        <button type="submit" class="btn btn-warning mt-3">Entra</button>
      </form>
    </div>
  </div>
</div>

</body>
</html>
