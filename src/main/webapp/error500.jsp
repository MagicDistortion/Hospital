<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
     <title>Error 500</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    </head>
    <body>
        <style>
            body {background:#000000 url(<c:url value="/images/Serze2.jpg"/>)}
        </style>
         <div align="center" style="color:#fff">
       <c:if test="${lang=='UA'}">
            <h2 align="center">Помилка 500</h2>
            <h2 align="center">Упс. Щось пішло не так. Поверніться на головну.Та спробуйте знову пізніше </h2>
            <br><br><br><br><br><br><br><br><br><br><br><br>
                       <h1> <a class="btn btn-light" href="/Hospital/index.jsp">Повернутись на головну</a> </h1>
        </c:if>
        <c:if test="${lang=='EN'}">
            <h2 align="center">Error 500</h2>
            <h2 align="center">Oops. Something went wrong. Go to Login. And try again Later> </h2>
            <br><br><br><br><br><br><br><br><br><br><br><br>
                       <h1> <a class="btn btn-light" href="/Hospital/index.jsp">Go to Login</a> </h1>
        </c:if>
         </div>
    </body>
</html>
