<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Doctors
		</title>
        		   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
                <style>
                    body {background:#000000 url(../images/Serze2.jpg)}
                </style>
    <body>
    <%@ include file="../header_admins.jspf" %>
<br><br><br><br><br><br><br><br><br><br><br>

             <form action ="../admins_only/add_category" method ="post">
             <div align="center" >
                    <input name="category" placeholder="add new category" required/>
                    <input type="submit" value="add category!"/><br>
                    <h2 style="color:#B22222">
                        <c:if  test="${not empty mes}" >${mes}</c:if>
                    </h2>
             </div>
             </form>
    </body>
</html>
