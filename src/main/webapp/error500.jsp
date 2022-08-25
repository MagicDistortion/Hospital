<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
     <title>Error 500</title>
    </head>
    <body>
        <style>
            body {background:#000000 url(<c:url value="/images/Serze2.jpg"/>)}
        </style>
         <div align="center" style="color:#fff">
       <c:if test="${lang=='UA'}">
            <h2 align="center">Помилка 500</h2>
            <h2 align="center">Ууупс. Щось пішло не так. Поверніться на головну.Та спробуйте знову пізніше </h2>
        </c:if>
        <c:if test="${lang=='EN'}">
            <h2 align="center">Error 500</h2>
            <h2 align="center">Ooops. Something went wrong. Go to Login. And try again Later> </h2>
        </c:if>
        <br><br><br><br><br><br><br><br><br><br><br><br>
           <h1> <a style="color:#fff" href="/Hospital/">${langGoToLogin}</a> </h1>
         </div>
    </body>
</html>
