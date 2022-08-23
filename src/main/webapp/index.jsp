<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Hospital
		</title>
             <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
          <style>
               body {background:#000000 url(images/Serze2.jpg)}
          </style>
    <body>
    <%@ include file="header.jspf" %>
     <c:choose>
        <c:when test="${empty user}">
           <br><br><br><br><br><br><br><br><br><br><br>
               <form action ="login" method ="post">
                   <div align="center" >
                        <table >
                            <tr>
	                          <td style="color:#0000ff">Login:</td>
	                          <td><input  name="login" placeholder="enter Login" required/></td>
                            </tr>
                            <tr>
    	                      <td style="color:#ffff00" />Password:</td>
	                          <td><input type="password" name="password" placeholder="enter Password" required/></td>
	                        </tr>

                        </table>
    	                <input type="submit" value="Go!"/><br>
                           <h2 style="color:#B22222">
                             <c:if  test="${not empty mes}" >${mes}</c:if>
                           </h2>
                   </div>
               </form>
        </c:when>
     </c:choose>
  </body>
</html>