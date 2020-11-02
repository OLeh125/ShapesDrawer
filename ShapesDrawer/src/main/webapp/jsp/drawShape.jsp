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
            <tr>
                <td>${shape.name}</td>
            </tr>
    </table>

    <form method="get" action="/shapes">
        <input type="submit" value="MAIN PAGE" >
    </form>
</div>

<canvas id="canvas" style="display: block;">Your browser is not supported</canvas>
<script>
    var
        canvas = document.getElementById('canvas'),
        ctx = canvas.getContext('2d');

    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    ctx.beginPath();
    ctx.moveTo(${shape.positions.get(0).x},${shape.positions.get(0).y});
    <c:forEach items="${shape.positions}" var="positions">
    ctx.lineTo(${positions.x},${positions.y});
    </c:forEach>
    ctx.lineTo(${shape.positions.get(0).x},${shape.positions.get(0).y});
    ctx.stroke();

</script>

</body>
</html>

