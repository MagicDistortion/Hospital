<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	 <head>
		<title>My appointments
		</title>
        	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
     </head>
          <style>
               body {background:#000000 url(../images/Serze2.jpg) no-repeat;}
          </style>
        <body>

    <%@ include file="../header_nurses.jspf" %>
    <br><br>
    <div align="center" >
        <h2  style="color:#fff">${phrases['langGetMyAppointments']}</h2>
            <form action ="../nurses_only/nurses_appointments" method ="get">
                <input type="submit" class="btn btn-dark" value="${phrases['langGetThem']}"/><br>
            </form>
        <h4 style="color:#B22222">

         <table class="table table-dark table-striped table-bordered">
            <th style="color:#0000ff"><h4/> ${phrases['langAppointment']} &nbsp</th>
            <th style="color:#ffff00"><h4/> ${phrases['langAppointmentDetails']} &nbsp</th>
            <th style="color:#0000ff"><h4/> ${phrases['langCurrentDoctor']} &nbsp</th>
            <th style="color:#ffff00"><h4/> ${phrases['langPatient']} &nbsp</th>
            <th style="color:#0000ff"><h4 />${phrases['langDate']} &nbsp</th>
            <th style="color:#ffff00"><h4 />${phrases['langStatus']} &nbsp</th>
            <th style="color:#0000ff"><h4 />${phrases['langSetStatus']} &nbsp</th>
              <c:forEach items="${appointments}" var="i">
                <tr>
                  <td><h4 style="color:#fff"/>${i.getAppointment()}&nbsp</td>
                  <td><h4 style="color:#fff"/>${i.getText()}&nbsp</td>
                  <td><h4 style="color:#fff"/>${i.getDoctorFullName()}&nbsp</td>
                  <td><h4 style="color:#fff"/>${i.getPatientFullName()}&nbsp</td>
                  <td><h4 style="color:#fff"/>${i.getDate()} &nbsp</td>
                  <td><h4 style="color:#fff"/>${i.getStatus()} &nbsp</td>
            <c:choose>
              <c:when test="${!i.getAppointment().equals('Операція')}">
                <form action ="../nurses_only/update_status" method ="post">
                  <input type="hidden" name="id" value="${i.getId()}"/>
                  <td><select class="btn btn-primary dropdown-toggle" name="status">
                         <option disabled>${phrases['langSetStatus']}</option>
                         <option value="delete">delete</option>
                         <option value="waiting">waiting</option>
                         <option value="in process" selected>In process</option>
                         <option value="done">Done</option> </select><br>
                       <input type="submit" class="btn btn-warning" value="${phrases['langSetStatus']}"/>
                  </td>
                </form>
              </c:when>
              <c:otherwise>
                 <td><h4 style="color:#fff"/>${phrases['langOnlyForDoctor']}</td>
              </c:otherwise>
            </c:choose>
                </tr>
              </c:forEach>
        </table>
             <h2 style="color:#B22222">
                  <c:if  test="${not empty mes}" >${mes}</c:if>
                  <c:if  test="${not empty messtatus}" >${messtatus}</c:if>
             </h2>
        </h4>
      </div>
    </body>
</html>
