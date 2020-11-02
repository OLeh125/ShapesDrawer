<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Users</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="form-style-2">
    <table>
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach items="${shapesList}" var="shapes">
            <tr>
                <td>${shapes.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="form-style-2">
<form method="post" action="/deleteShape">
    <label for="name">Shape Name
        <input type="text" id="name" name="name">
    </label>
    <input type="submit" value="DELETE SHAPE" >
</form>
    <form method="get" action="/shapes">
        <input type="submit" value="MAIN PAGE" >
    </form>
</div>

</body>
</html>
