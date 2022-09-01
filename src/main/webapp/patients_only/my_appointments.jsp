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

    <%@ include file="../header_patients.jspf" %>
    <div align="center" >
        <h2  style="color:#fff">${langGetMyAppointments}</h2>
            <form action ="../patients_only/my_appointments" method ="get">
                <input type="submit" value="${langGetThem}"/><br>
            </form>
 <table class="table">

            <th style="color:#0000ff"><h4/> ${langAppointment} &nbsp</th>
            <th style="color:#ffff00"><h4/> ${langAppointmentDetails} &nbsp</th>
            <th style="color:#0000ff"><h4/> ${langCurrentDoctor} &nbsp</th>
            <th style="color:#ffff00"><h4 />${langDesignatedNurse} &nbsp</th>
            <th style="color:#0000ff"><h4 />${langDate} &nbsp</th>
              <c:forEach items="${appointments}" var="i">
                <tr>
                  <td><h3 style="color:#fff"/>${i.getAppointment()}&nbsp&nbsp</td>
                  <td><h3 style="color:#fff"/>${i.getText()}&nbsp&nbsp</td>
                  <td><h3 style="color:#fff"/>${i.getDoctorFullName()}&nbsp&nbsp</td>
                  <td><h3 style="color:#fff"/>${i.getNurseFullName()} &nbsp&nbsp</td>
                  <td><h3 style="color:#fff"/>${i.getDate()} &nbsp&nbsp</td>
                </tr>
              </c:forEach>
        </table>
             <h2 style="color:#B22222">
                  <c:if  test="${not empty mes}" >${mes}</c:if>
             </h2>
             <h2  style="color:#fff">${langGetMyHospitalCard}</h2>
            <form action ="../patients_only/my_hospitalcard" method ="get">
                <input type="submit" value="${langGet}"/><br>
            </form>
    </form>
    <c:if test="${not empty myhospitalcard}">
    <h3>
        <table border="1" >
            <colgroup style="background-color:#87CEFA;">
             <col>
             <col>
            </colgroup>
                     <tr style="color:#0000ff">
                        <td>${langDoctorSurname}: &nbsp</td>
                        <td>${myhospitalcard.getCurrentDoctorSurname()}</td>
                     </tr>
                     <tr style="color:#ffff00">
                        <td>${langDoctorName}: &nbsp</td>
                        <td>${myhospitalcard.getCurrentDoctorName()}</td>
                     </tr>
                     <tr style="color:#0000ff">
                        <td>${langCurrentDiagnosis}: &nbsp</td>
                        <td>${myhospitalcard.getDiagnosis()}</td>
                     </tr>
                     <tr style="color:#ffff00">
                        <td>${langStatus}:</td>
                        <td>${myhospitalcard.getStatus()}</td>
                     </tr>
        </table>
    </h3>
    </c:if>
             <c:if test="${status_patient.equals('discharged')}">
                 <form action ="../patients_only/newbee_again" method ="post">
                    <input type="submit" value="${langNeedHelpAgain}"/>
                 </form>
             </c:if>
              <c:if test="{not empty messtatus}">${messtatus}
              </c:if>
      </div>
    </body>
</html>
