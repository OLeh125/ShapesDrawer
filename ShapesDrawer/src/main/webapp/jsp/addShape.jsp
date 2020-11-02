
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>ADD SHAPE</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
ADD Shape
<div class="form-style-2">
    <form method="post" action="/addShape">
    <label for="name">Name
        <input type="text" id="name" name="name">
    </label>
        <label for="position">Positions
            <input type="text" id="position" name="position">
        </label>
        <input type="submit" value="Add Shape" >
</form>
    <form method="get" action="/shapes">
        <input type="submit" value="MAIN PAGE" >
    </form>
</div>

</body>
</html>
