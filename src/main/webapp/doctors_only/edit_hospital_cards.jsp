<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>

<body>

<style>
body {background:#000000 url(../images/Serze2.jpg)}
</style>
<br><br><br><br><br><br>
  <table class="table">
    <tr>
        <td>
           <h2  style="color:#0000ff">${phrases['langGetAppointmentsOfPatient']}</h2>
            <form action ="../doctors_only/get_appointments" method ="get">
                <input type="hidden" name="id" value="${id_card}"/>
                <input type="submit" value="${phrases['langGetThem']}"/>
            </form>
             <table border=1 class="table">

               <th style="color:#0000ff"><h3/> ${phrases['langAppointment']} &nbsp</th>
               <th style="color:#ffff00"><h3/> ${phrases['langAppointmentDetails']}</th>
               <th style="color:#0000ff"><h3/> ${phrases['langCurrentDoctor']}</th>
               <th style="color:#ffff00"><h3/> ${phrases['langDesignatedNurse']}</th>
               <th style="color:#0000ff"><h3/> ${phrases['langDate']} &nbsp&nbsp&nbsp</th>
               <th style="color:#ffff00"><h3/> ${phrases['langStatus']} &nbsp</th>
           <c:forEach items="${appointments}" var="i">
                  <tr>
                    <td><h3 style="color:#fff"/>${i.getAppointment()}&nbsp</td>
                    <td><h3 style="color:#fff"/>${i.getText()}&nbsp</td>
                    <td><h3 style="color:#fff"/>${i.getDoctorFullName()}&nbsp</td>
                    <td><h3 style="color:#fff"/>${i.getNurseFullName()} &nbsp</td>
                    <td><h3 style="color:#fff"/>${i.getDate()} &nbsp</td>
                    <td><h3 style="color:#fff"/>${i.getStatus()} &nbsp</td>
                  </tr>
           </c:forEach>
           </table>
                        <h2 style="color:#B22222">
                            <c:if  test="${not empty mes}" >${mes}</c:if>
                        </h2>
             <h2  style="color:#ffff00">${phrases['langAddAppointment']}</h2>
              <form action ="../doctors_only/insert_appoint" method ="post">
                  <input type="hidden" name="id" value="${id_card}"/>
                <select name="appoint" required>
                    <option disabled>${phrases['langPickAPoint']}</option>
                   <c:forEach items="${appoints}" var="j">
                    <option value="${j.getId()}">${j.getName()}</option>
                   </c:forEach>
                </select>
                  <input name="text" placeholder="${phrases['langDetails']}" required/>
                <select name="nurse" >
                    <option disabled>${phrases['langPickANurse']}</option>
                   <c:forEach items="${nurses}" var="i">
                    <option value="${i.getId()}">${i.getSurname()} ${i.getName()}</option>
                   </c:forEach>
                </select>

                <input type="date" name="date" value="${today}" required/>
    	        <input type="submit" value="${phrases['langInsertNewAppointment']}"/><br>
              </form>
             <h2 style="color:#B22222">
                 <c:if  test="${not empty message}" >${message}</c:if>
             </h2>
        </td>
        <td>
            <h2>
                  <table class="table">

                <br><br>
                     <tr style="color:#0000ff">
                         <td>${phrases['langPatientSurname']}:</td>
                         <td>${patient_surname}</td>
                     </tr>
                     <tr style="color:#ffff00">
                         <td>${phrases['langPatientName']}:</td>
                         <td>${patient_name}</td>
                     </tr>
                     <tr style="color:#0000ff">
                        <td/>${phrases['langDateOfRegistration']}:</td>
	                    <td>${create_time}</td>
                     </tr>
                     <tr style="color:#ffff00">
                        <td>${phrases['langDoctorSurname']}:</td>
                        <td>${current_doctorSurname}</td>
                     </tr>
                     <tr style="color:#0000ff">
                        <td>${phrases['langDoctorName']}:</td>
                        <td>${current_doctorName}</td>
                     </tr>
                     <tr style="color:#ffff00">
                        <td>${phrases['langCurrentDiagnosis']}:</td>
                        <td>${diagnosis}</td>
                     </tr>
                     <tr style="color:#0000ff">
                        <td>${phrases['langStatus']}:</td>
                        <td>${status_patient}</td>
                     </tr>
                     <tr>
                         <td><form action ="../doctors_only/update_diagnosis" method ="post">
                           <input type="hidden" name="id" value="${id_card}"/>
    	                   <input type="submit" value="${phrases['langChangeDiagnosis']}"/><br>
    	                   <input name="diagnosis" placeholder="${phrases['langEnterNewDiagnosis']}"/><br>
                         </form></td>
                         <td><form action ="../doctors_only/update_status" method ="post">
                             <select name="status">
                             <option disabled>${phrases['langSetStatus']}</option>
                             <option value="is being treated">is being treated</option>
                             <option value="cured">cured</option>
                             <option value="discharged">discharged</option>
                             <input type="submit" value="${phrases['langSetStatus']}"/>
                             </select></form></td>
                     </tr>
            </table>
             <h2 style="color:#B22222">
                 <c:if  test="${not empty messtatus}" >${messtatus}</c:if>
             </h2>
            </h2>
        </td>
    </tr>
</table>
<div align="center">
<input type="submit" value="${phrases['langGoBack']}"  onclick="window.location='${back}.jsp';"/>
</div>

</body>
</html>