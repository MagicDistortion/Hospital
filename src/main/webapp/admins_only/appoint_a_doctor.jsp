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
                    body {background:#000000 url(../images/Serze2.jpg) no-repeat;}
                </style>
    <body>
    <%@ include file="../header_admins.jspf" %>
<br><br>
    <div align="center" >
              <h2 style="color:#fff">${phrases['langAppointADoctorForPatient']}</h2>
                        <form action ="../admins_only/patients_sortlist" method ="post">
                        <select class="btn btn-dark dropdown-toggle" name="sort">
                             <option disabled>${phrases['langSorted']}</option>
                             <option value="surname">${phrases['langBySurname']}</option>
                             <option value="date">${phrases['langByDateOfBirth']}</option>
                            <input type="submit" class="btn btn-dark" value="${phrases['langGetPatientsWithOutADoctor']}"/><br>
                        </form>
<h2 style="color:#B22222">
         <table class="table table-dark table-striped table-bordered">
            <th style="color:#0000ff"><h4/> ${phrases['langSurname']}&nbsp</th>
            <th style="color:#ffff00"><h4/> ${phrases['langName']}&nbsp</th>
            <th style="color:#0000ff"><h4/> ${phrases['langPickADoctor']}&nbsp</th>
                  <c:forEach items="${patients}" var="i">
                      <tr>
                         <td style="color:#fff"><h4/>${i.getSurname()} &nbsp</td>
                         <td style="color:#fff"><h4/>${i.getName()} &nbsp</td>
                         <td style="color:#fff"><h4/>
                         <form action ="../admins_only/give_a_doctor" method ="post">
                             <input type="hidden" name="id" value="${i.getId()}"/>
                             <select class="btn btn-info dropdown-toggle"  name="doctor">
                                     <option disabled>${phrases['langPickADoctor']}&nbsp</option>
                                 <c:forEach items="${doctors}" var="j">
                                     <option value="${j.getId()}">${j.getSurname()} ${j.getName()} ${j.getCategory()}</option>
                                 </c:forEach>
                             </select>
                             <input type="submit" class="btn btn-warning" value="${phrases['langPick']}"/>
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
