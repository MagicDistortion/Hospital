<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>My patients
		</title>
        		   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
                <style>
                    body {background:#000000 url(../images/Serze2.jpg)}
                </style>
    <body>
    <%@ include file="../header_doctors.jspf" %>
   <br><br>
    <div align="center" >
        <h2  style="color:#fff">Get my Patients</h2>
            <form action ="../doctors_only/my_patients" method ="get">
                         <input type="text" name="pagination" pattern="[1-9]\d*"
                         <c:choose>
                            <c:when test="${not empty pagination}">value="${pagination}"
                            </c:when>
                            <c:otherwise>value="5"
                            </c:otherwise>
                        </c:choose>
                          placeholder="pagination" required />
                <select name="sort">
                   <option disabled>sorted by</option>
                   <option value="name">by Name</option>
                   <option value="date">by Date of Birth</option>
                <input type="submit" value="Get them!"/><br>
            </form>
        <h2 style="color:#B22222">
        <table border="1">
            <colgroup style="background-color:#87CEFA;">
             <col>
             <col>
             <col>
             <col>
            </colgroup>
            <th style="color:#0000ff"><h4/> Patients Surname &nbsp</th>
            <th style="color:#ffff00"><h4/> Patients Name &nbsp</th>
            <th style="color:#0000ff"><h4 />Date of Birth &nbsp</th>
            <th style="color:#ffff00"><h4 />go to &nbsp</th>
                  <c:forEach items="${patientlist}" var="i">
                      <tr>
                         <td style="color:#fff"><h3/>${i.getSurname()}</td>
                         <td style="color:#fff"><h3/>${i.getName()}</td>
                         <td style="color:#fff" align="center"><h3/>${i.getDateOfBitrth()}</td>
                            <form action ="../doctors_only/edit_hospital_cards" method ="post">
                               <input type="hidden" name="id" value="${i.getId()}"/>
                               <input type="hidden" name="back" value="my_patients"/>
                         <td> <input type="submit" value="get detail"> </td>
                            </form>

                      </tr>
                  </c:forEach>
        </table>
                           <c:forEach items="${patients}" var="i" varStatus="j">
                           <c:if test="${j.count<=pages}">
                                <a href="../doctors_only/my_patients?pagination=${pagination}&sort=${sort}&page=${j.count}">${j.count}</a>
                           </c:if>
                           </c:forEach>
             <h2 style="color:#B22222">
                  <c:if  test="${not empty mes}" >${mes}</c:if>
             </h2>
        </h2>
      </div>
    </body>
</html>