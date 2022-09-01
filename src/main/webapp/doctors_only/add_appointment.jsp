<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Add appoinment
		</title>
        		   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
                <style>
                    body {background:#000000 url(../images/Serze2.jpg)}
                </style>
    <body>
    <%@ include file="../header_doctors.jspf" %>
            <br><br><br><br><br><br>
    <div align="center" >
        <form action="../doctors_only/add_appointment" method="post">
        <table>
            <tr>
                <td><input name="name" placeholder="enter a name" required/></td>
                  <td><input type="submit" value="${langAddAppointment}"/></td>
            </tr>
        </form>
        </table>
          <h2 style="color:#B22222">
             <c:if  test="${not empty mes}" >${mes}</c:if>
          </h2>
        </div>
    </body>
</html>