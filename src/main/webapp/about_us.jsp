<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>About Us
		</title>
             <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
          <style>
               body {background:#000000 url(images/Serze2.jpg)}
          </style>
    <body>

    <%@ include file="header.jspf" %>
        <div style="color:#fff"/>
        <c:if test="${lang=='UA'}">
            <h2 align="center">О Нас</h2>
            <h3 align="center">Ми найкраща лікарня у місті Одеса </h3>
        </c:if>
        <c:if test="${lang=='EN'}">
            <h2 align="center">About us</h2>
            <h3 align="center">We are the best hospital in Odesa city </h3
        </c:if>
        </div>
    </body>
</html>