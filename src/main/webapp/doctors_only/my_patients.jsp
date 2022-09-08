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
                    body {background:#000000 url(../images/Serze2.jpg) no-repeat;}
                </style>
    <body>
    <%@ include file="../header_doctors.jspf" %>
    <br><br>
    <div align="center" >
        <h2  style="color:#fff">${phrases['langGetMyPatients']}</h2>
            <form action ="../doctors_only/my_patients" method ="get">
                         <input class="btn btn-dark" type="text" name="pagination" pattern="[1-9]\d*"
                         <c:choose>
                            <c:when test="${not empty pagination}">value="${pagination}"
                            </c:when>
                            <c:otherwise>value="5"
                            </c:otherwise>
                        </c:choose>
                          placeholder="pagination" required />
                <select  class="btn btn-dark dropdown-toggle" name="sort">
                   <option disabled>${phrases['langSorted']}</option>
                   <option value="surname">${phrases['langBySurname']}</option>
                   <option value="date">${phrases['langByDateOfBirth']}</option>
                <input type="submit" class="btn btn-dark" value="${phrases['langGetThem']}"/><br>
            </form>
        <h2 style="color:#B22222">
        <table class="table table-dark table-striped table-bordered">

            <th style="color:#0000ff"><h4/> ${phrases['langSurname']} &nbsp</th>
            <th style="color:#ffff00"><h4/> ${phrases['langName']} &nbsp</th>
            <th style="color:#0000ff"><h4 /> ${phrases['langDateOfBirth']} &nbsp</th>
            <th style="color:#ffff00"><h4 /> ${phrases['langGoTo']} &nbsp</th>
                  <c:forEach items="${patientlist}" var="i">
                      <tr>
                         <td style="color:#fff"><h3/>${i.getSurname()} &nbsp</td>
                         <td style="color:#fff"><h3/>${i.getName()} &nbsp</td>
                         <td style="color:#fff" align="center"><h3/>${i.getDateOfBirth()} &nbsp</td>
                            <form action ="../doctors_only/edit_hospital_cards" method ="post">
                               <input type="hidden" name="id" value="${i.getId()}"/>
                               <input type="hidden" name="back" value="my_patients"/>
                         <td> <input type="submit" class="btn btn-light" value="${phrases['langGetDetail']}"> </td>
                            </form>

                      </tr>
                  </c:forEach>
        </table>
                   <c:if test="${page>1}">
                   <a class="btn btn-outline-primary"  href="../doctors_only/my_patients?pagination=${pagination}&sort=${sort}&page=${page-1}"><<</a>
                   </c:if>
                           <c:forEach begin="1" end="${patientsCount}" var="i" step="1">
                           <c:if test="${i<=pages}">
                                <a class="btn btn-outline-primary"  href="../doctors_only/my_patients?pagination=${pagination}&sort=${sort}&page=${i}">${i}</a>
                           </c:if>
                           </c:forEach>
                   <c:if test="${page<pages}">
                   <a class="btn btn-outline-primary"  href="../doctors_only/my_patients?pagination=${pagination}&sort=${sort}&page=${page+1}">>></a>
                   </c:if>
             <h2 style="color:#B22222">
                  <c:if  test="${not empty mes}" >${mes}</c:if>
             </h2>
        </h2>
      </div>
    </body>
</html>
