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
               body {background:#000000 url(images/Serze2.jpg) no-repeat;}
          </style>
    <body>
    <%@ include file="header.jspf" %>
    <br><br>
        <div align="center" >
        <h2  style="color:#fff">${phrases['langDoctors']}</h2>
            <form action ="doctors_sortlist" method ="get">
             <input class="btn btn-dark" type="text" name="pagination" pattern="[1-9]\d*"
             <c:choose>
                <c:when test="${not empty pagination}">value="${pagination}"
                </c:when>
                <c:otherwise>value="5"
                </c:otherwise>
            </c:choose>
              placeholder="pagination" required />
                <select type="button" class="btn btn-dark dropdown-toggle" name="sort">
                   <option disabled>${phrases['langSorted']} &nbsp</option>
                   <option value="Surname">${phrases['langBySurname']} &nbsp</option>
                   <option value="category">${phrases['langByCategory']} &nbsp</option>
                   <option value="patients">${phrases['langByNOP']} &nbsp</option>
                <input type="submit" class="btn btn-dark" value="${phrases['langGetThem']}"/><br>
            </form>
        <h2 style="color:#B22222">
        <table class="table table-dark table-striped">
            <th style="color:#0000ff"><h4/> ${phrases['langSurname']} &nbsp</th>
            <th style="color:#ffff00"><h4/> ${phrases['langName']} &nbsp</th>
            <th style="color:#0000ff"><h4 align="center"/>${phrases['langCategory']} &nbsp</th>
            <th style="color:#ffff00"><h4/>${phrases['langNOP']} &nbsp</th>
                  <c:forEach items="${docList}" var="i">
                      <tr>
                         <td style="color:#fff"><h3/>${i.getSurname()} &nbsp</td>
                         <td style="color:#fff"><h3/>${i.getName()} &nbsp</td>
                         <td style="color:#fff" align="center"><h3/>${i.getCategory()} &nbsp</td>
                         <td style="color:#fff" align="center"><h3/>${i.getNumberOfPatients()} &nbsp</td>
                      </tr>
                  </c:forEach>
        </table>
                   <c:if test="${page>1}">
                   <a class="btn btn-outline-primary"  href="doctors_sortlist?pagination=${pagination}&sort=${sort}&page=${page-1}"><<</a>
                   </c:if>
                   <c:forEach items="${allDoctors}" var="i" varStatus="j">
                   <c:if test="${j.count<=pages}">
                        <a class="btn btn-outline-primary" href="doctors_sortlist?pagination=${pagination}&sort=${sort}&page=${j.count}">${j.count}</a>
                   </c:if>
                   </c:forEach>
                   <c:if test="${page<pages}">
                   <a class="btn btn-outline-primary"  href="doctors_sortlist?pagination=${pagination}&sort=${sort}&page=${page+1}">>></a>
                   </c:if>
        </div>
     </h2>
    </body>
</html>
