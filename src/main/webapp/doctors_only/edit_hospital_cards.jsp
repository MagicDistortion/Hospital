<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="mytaglib" prefix="mt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>

<body>

<style>
body {background:#000000 url(../images/Serze2.jpg) no-repeat;}
</style>
<br><br><br>
  <table class="table table-borderless">
    <tr>
        <td>
           <h2  style="color:#0000ff">${phrases['langGetAppointmentsOfPatient']}</h2>
            <form action ="../doctors_only/get_appointments" method ="get">
                <input type="hidden" name="id" value="${id_card}"/>
                <input type="submit" class="btn btn-primary" value="${phrases['langGetThem']}"/>
            </form>
             <table class="table table-dark table-striped table-bordered">

               <th style="color:#0000ff"><h5/> ${phrases['langAppointment']} &nbsp</th>
               <th style="color:#ffff00"><h5/> ${phrases['langAppointmentDetails']}</th>
               <th style="color:#0000ff"><h5/> ${phrases['langCurrentDoctor']}</th>
               <th style="color:#ffff00"><h5/> ${phrases['langDesignatedNurse']}</th>
               <th style="color:#0000ff"><h5/> ${phrases['langDate']} &nbsp&nbsp&nbsp</th>
               <th style="color:#ffff00"><h5/> ${phrases['langStatus']} &nbsp</th>
           <c:forEach items="${appointments}" var="i">
                  <tr>
                    <td><h5 style="color:#fff"/>${i.getAppointment()}&nbsp</td>
                    <td><h5 style="color:#fff"/>${i.getText()}&nbsp</td>
                    <td><h5 style="color:#fff"/>${i.getDoctorFullName()}&nbsp</td>
                    <td><h5 style="color:#fff"/>${i.getNurseFullName()} &nbsp</td>
                    <td><h5 style="color:#fff"/>${i.getDate()} &nbsp</td>
                    <td><h5 style="color:#fff"/>${i.getStatus()} &nbsp</td>
                  </tr>
           </c:forEach>
           </table>
                        <h2 style="color:#B22222">
                            <c:if  test="${not empty mes}" >${mes}</c:if>
                        </h2>
             <h2  style="color:#ffff00">${phrases['langAddAppointment']}</h2>
              <form action ="../doctors_only/insert_appoint" method ="post">
                  <input type="hidden" name="id" value="${id_card}"/>
                <select class="btn btn-primary dropdown-toggle" name="appoint" required>
                    <option disabled>${phrases['langPickAPoint']}</option>
                   <c:forEach items="${appoints}" var="j">
                    <option value="${j.getId()}">${j.getName()}</option>
                   </c:forEach>
                </select>
                  <input class="btn btn-warning" name="text" placeholder="${phrases['langDetails']}" required/>
                <select class="btn btn-primary dropdown-toggle" name="nurse" >
                    <option disabled>${phrases['langPickANurse']}</option>
                   <c:forEach items="${nurses}" var="i">
                    <option value="${i.getId()}">${i.getSurname()} ${i.getName()}</option>
                   </c:forEach>
                </select>

                <input class="btn btn-warning dropdown-toggle" type="date" name="date" value=<mt:myTag/> required/>
    	        <input type="submit" class="btn btn-primary" value="${phrases['langInsertNewAppointment']}"/><br>
              </form>
             <h2 style="color:#B22222">
                 <c:if  test="${not empty message}" >${message}</c:if>
             </h2>
        </td>
        <td>
            <h5>
                  <table class="table table-borderless">

                <br><br>
                     <tr style="color:#0000ff">
                         <td>${phrases['langPatientSurname']}:&nbsp</td>
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
    	                   <input type="submit" class="btn btn-primary" value="${phrases['langChangeDiagnosis']}"/><br>
    	                   <input name="diagnosis"  class="form-control" placeholder="${phrases['langEnterNewDiagnosis']}"/><br>
                         </form></td>
                         <td><form action ="../doctors_only/update_status" method ="post">
                             <select  class="form-control" name="status">
                             <option disabled>${phrases['langSetStatus']}</option>
                             <option value="is being treated">is being treated</option>
                             <option value="cured">cured</option>
                             <option value="discharged">discharged</option>
                             <input type="submit" class="btn btn-warning" value="${phrases['langSetStatus']}"/>
                             </select></form></td>
                     </tr>
            </table>
             <h2 style="color:#B22222">
                 <c:if  test="${not empty messtatus}" >${messtatus}</c:if>
             </h2>
            </h5>
        </td>
    </tr>
</table>
<div align="center">
<input type="submit" class="btn btn-dark" value="${phrases['langGoBack']}"  onclick="window.location='${back}.jsp';"/>
</div>

</body>
</html>