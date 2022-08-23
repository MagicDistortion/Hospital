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
<br><br><br><br><br><br><br><br><br><br><br><br><br>
<table align="center">
    <tr>
        <td>
           <h2  style="color:#0000ff">Get appointments of patient</h2>
            <form action ="../doctors_only/get_appointments" method ="get">
                <input type="hidden" name="id" value="${id_card}"/>
                <input type="submit" value="Get them!"/>
            </form>
           <table >
            <colgroup style="background-color:#87CEFA;">
             <col>
             <col>
             <col>
             <col>
             <col>
             <col>
            </colgroup>
               <th style="color:#0000ff"><h3/> Appointment &nbsp</th>
               <th style="color:#ffff00"><h3/> Appointment details &nbsp</th>
               <th style="color:#0000ff"><h3/> Current Doctor &nbsp</th>
               <th style="color:#ffff00"><h3 />Designated Nurse &nbsp</th>
               <th style="color:#0000ff"><h3 />Date &nbsp&nbsp&nbsp</th>
               <th style="color:#ffff00"><h3 />Status &nbsp&nbsp&nbsp</th>
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
              <form action ="../doctors_only/insert_appoint" method ="post">
                  <input type="hidden" name="id" value="${id_card}"/>
                <select name="appoint" required>
                    <option disabled>pick a appoint</option>
                   <c:forEach items="${appoints}" var="j">
                    <option value="${j.getId()}">${j.getName()}</option>
                   </c:forEach>
                </select>
                  <input name="text" placeholder="details" required/>
                <select name="nurse" >
                    <option disabled>pick a nurse</option>
                   <c:forEach items="${nurses}" var="i">
                    <option value="${i.getId()}">${i.getSurname()} ${i.getName()}</option>
                   </c:forEach>
                </select>

                <input type="date" name="date" value="${today}" required/>
    	        <input type="submit" value="insert new appoint"/><br>
              </form>
             <h2 style="color:#B22222">
                 <c:if  test="${not empty mes}" >${mes}</c:if>
                 <c:if  test="${not empty message}" >${message}</c:if>
             </h2>
        </td>
        <td>
            <h3>
                <table>
                  <colgroup style="background-color:#87CEFA;">
                    <col>
                    <col>
                  </colgroup>
                <br><br>
                     <tr style="color:#0000ff">
                         <td>Patient Surname:</td>
                         <td>${patient_surname}</td>
                     </tr>
                     <tr style="color:#ffff00">
                         <td>Patient Name:</td>
                         <td>${patient_name}</td>
                     </tr>
                     <tr style="color:#0000ff">
                        <td/>Date of registration:</td>
	                    <td>${create_time}</td>
                     </tr>
                     <tr style="color:#ffff00">
                        <td>Current doctor`s Surname:</td>
                        <td>${current_doctorSurname}</td>
                     </tr>
                     <tr style="color:#0000ff">
                        <td>Current doctor`s name:</td>
                        <td>${current_doctorName}</td>
                     </tr>
                     <tr style="color:#ffff00">
                        <td>Current diagnosis:</td>
                        <td width=400>${diagnosis}</td>
                     </tr>
                     <tr style="color:#0000ff">
                        <td>Status:</td>
                        <td width=400>${status_patient}</td>
                     </tr>
                     <tr>
                         <td><form action ="../doctors_only/update_diagnosis" method ="post">
                           <input type="hidden" name="id" value="${id_card}"/>
    	                   <input type="submit" value="change diagnosis"/><br>
    	                   <input name="diagnosis" placeholder="Enter new diagnosis"/><br>
                         </form></td>
                         <td><form action ="../doctors_only/update_status" method ="post">
                             <select name="status">
                             <option disabled>set status</option>
                             <option value="is being treated">is being treated</option>
                             <option value="cured">cured</option>
                             <option value="discharged">discharged</option>
                             <input type="submit" value="Set status"/>
                             </select></form></td>
                     </tr>
            </table>
             <h2 style="color:#B22222">
                 <c:if  test="${not empty messtatus}" >${messtatus}</c:if>
             </h2>
            </h3>
        </td>
    </tr>
</table>
<div align="center">
<input type="submit" value="back to Hospital Cards"  onclick="window.location='${back}.jsp';"/>
</div>

</body>
</html>