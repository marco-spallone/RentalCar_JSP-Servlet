<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>

<c:forEach var="ut" items="${list}">
    ${ut.nome}
</c:forEach>

</body>
</html>