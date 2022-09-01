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
               body {background:#000000 url(../images/Serze2.jpg)}
          </style>
        <body>

    <%@ include file="../header_nurses.jspf" %>
    <div align="center" >
        <h2  style="color:#fff">${langGetMyAppointments}</h2>
            <form action ="../nurses_only/nurses_appointments" method ="get">
                <input type="submit" value="${langGetThem}"/><br>
            </form>
        <h4 style="color:#B22222">

         <table class="table">
            <th style="color:#0000ff"><h4/> ${langAppointment} &nbsp</th>
            <th style="color:#ffff00"><h4/> ${langAppointmentDetails} &nbsp</th>
            <th style="color:#0000ff"><h4/> ${langCurrentDoctor} &nbsp</th>
            <th style="color:#ffff00"><h4/> ${langPatient} &nbsp</th>
            <th style="color:#0000ff"><h4 />${langDate} &nbsp</th>
            <th style="color:#ffff00"><h4 />${langStatus} &nbsp</th>
            <th style="color:#0000ff"><h4 />${langSetStatus} &nbsp</th>
              <c:forEach items="${appointments}" var="i">
                <tr>
                  <td><h4 style="color:#fff"/>${i.getAppointment()}&nbsp</td>
                  <td><h4 style="color:#fff"/>${i.getText()}&nbsp</td>
                  <td><h4 style="color:#fff"/>${i.getDoctorFullName()}&nbsp</td>
                  <td><h4 style="color:#fff"/>${i.getPatientFullName()}&nbsp</td>
                  <td><h4 style="color:#fff"/>${i.getDate()} &nbsp</td>
                  <td><h4 style="color:#fff"/>${i.getStatus()} &nbsp</td>
                <form action ="../nurses_only/update_status" method ="post">
                  <input type="hidden" name="id" value="${i.getId()}"/>
                  <td><select name="status">
                         <option disabled>${langSetStatus}</option>
                         <option value="waiting">waiting</option>
                         <option value="in process">In process</option>
                         <option value="done">Done</option> </select><br>
                       <input type="submit" value="${langSetStatus}"/>
                  </td>
                </form>
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
