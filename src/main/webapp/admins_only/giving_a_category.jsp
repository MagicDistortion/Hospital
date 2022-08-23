<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>Giving a category
		</title>
        		   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
                <style>
                    body {background:#000000 url(../images/Serze2.jpg)}
                </style>
    <body>
    <%@ include file="../header_admins.jspf" %>
   <br><br>
    <div align="center" >
              <h2 style="color:#fff">Give a category for doctor</h2>
                        <form action ="../admins_only/doctors_list" method ="get">
                            <input type="submit" value="Get all Doctors!"/><br>
                        </form>
        <h2 style="color:#B22222">
          <table border="1">
            <th style="color:#0000ff"><h4/> Doctor`s Surname</th>
            <th style="color:#ffff00"><h4/> Doctor`s name</th>
            <th style="color:#0000ff"><h4/> Pick a category</th>
                  <c:forEach items="${docs}" var="i">
                      <tr>
                         <td style="color:#fff"><h4/>${i.getSurname()}</td>
                         <td style="color:#fff"><h4/>${i.getName()}</td>
                         <td ><h4/>
                         <form action ="../admins_only/give_a_category" method ="post">
                            <input type="hidden" name="id" value="${i.getId()}"/>
                             <select name="category">
                                 <option disabled>select action</option>
                             <c:forEach items="${categories}" var="j">
                                <option value="${j.getId()}">${j.getName()}</option>
                             </c:forEach>
                             </select><br>
                             <input type="submit" value="pick"/>
                         </form>
                         </td>
                      </tr>
                  </c:forEach>
          </table>
             <h2 style="color:#B22222">
                  <c:if  test="${not empty mes}" >${mes}</c:if>
             </h2>
        </h2>
      </div>
    </body>
</html>
