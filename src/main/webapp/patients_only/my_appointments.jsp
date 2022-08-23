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
   <br><br>
    <div align="center" >
        <h2  style="color:#fff">Get my appointments</h2>
            <form action ="../patients_only/my_appointments" method ="get">
                <input type="submit" value="Get them!"/><br>
            </form>
        <table border="1" >
            <colgroup style="background-color:#87CEFA;">
             <col>
             <col>
             <col>
             <col>
             <col>
            </colgroup>
            <th style="color:#0000ff"><h4/> Appointment &nbsp</th>
            <th style="color:#ffff00"><h4/> Appointment details &nbsp</th>
            <th style="color:#0000ff"><h4/> Current Doctor &nbsp</th>
            <th style="color:#ffff00"><h4 />Designated Nurse &nbsp</th>
            <th style="color:#0000ff"><h4 />Date &nbsp</th>
              <c:forEach items="${appointments}" var="i">
                <tr>
                  <td><h3 style="color:#fff"/>${i.getAppointment()}&nbsp</td>
                  <td><h3 style="color:#fff"/>${i.getText()}&nbsp</td>
                  <td><h3 style="color:#fff"/>${i.getDoctorFullName()}&nbsp</td>
                  <td><h3 style="color:#fff"/>${i.getNurseFullName()} &nbsp</td>
                  <td><h3 style="color:#fff"/>${i.getDate()} &nbsp</td>
                </tr>
              </c:forEach>
        </table>
             <h2 style="color:#B22222">
                  <c:if  test="${not empty mes}" >${mes}</c:if>
             </h2>
             <h2  style="color:#fff">Get my hospitalCard</h2>
            <form action ="../patients_only/my_hospitalcard" method ="get">
                <input type="submit" value="Get"/><br>
            </form>
    </form>
    <c:if test="${not empty diagnosis}">
    <h3>
        <table border="1" >
            <colgroup style="background-color:#87CEFA;">
             <col>
             <col>
             <col>
             <col>
             <col>
            </colgroup>
                     <tr style="color:#0000ff">
                        <td>Current doctor`s Surname: &nbsp</td>
                        <td>${current_doctorSurname}</td>
                     </tr>
                     <tr style="color:#ffff00">
                        <td>Current doctor`s name: &nbsp</td>
                        <td>${current_doctorName}</td>
                     </tr>
                     <tr style="color:#0000ff">
                        <td>Current diagnosis: &nbsp</td>
                        <td width=400>${diagnosis}</td>
                     </tr>
                     <tr style="color:#ffff00">
                        <td>Status:</td>
                        <td>${status_patient}</td>
                     </tr>
        </table>
    </h3>
    </c:if>
             <c:if test="${status_patient.equals('discharged')}">
                 <form action ="../patients_only/newbee_again" method ="post">
                    <input type="submit" value="need help again"/>
                 </form>
             </c:if>
              <c:if test="{not empty messtatus}">${messtatus}
              </c:if>
      </div>
    </body>
</html>
