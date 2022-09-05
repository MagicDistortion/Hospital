<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
     <meta charset="UTF-8">
     <title>Register</title>
    </head>

    <body>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <style>
                body {background:#000000 url(images/Serze2.jpg)}
            </style>

                <form action ="edit_profile" method ="post">
                    <div align="center" >
                     <table>
                       <tr>
                         <td style="color:#0000ff"/>${phrases['langSurname']}:</td>
                         <td><input name="surname"  pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{1,32}" value="${user.getSurname()}"/></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/>${phrases['langName']}:</td>
                         <td><input name="name"  pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{1,32}" value="${user.getName()}"/></td>
                       </tr>
                       <tr>
                         <td style="color:#ffff00"/>${phrases['langLogin']}:</td>
	                     <td><input name="login" value="${user.getLogin()}" ></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/>${phrases['langNewPassword']}:</td>
                         <td><input type="password" placeholder="${phrases['langNewPassword']}" name="password" /></td>
                       </tr>
	                   <tr>
	                     <td style="color:#ffff00"/>${phrases['langRePassword']}:</td>
	                     <td><input type="password" placeholder="${phrases['langRePassword']}" name="repassword" /></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/>${phrases['langTel']}:</td>
                         <td> <input  type="number" name="tel" value="${user.getTel()}" ></td>
                         <td style="color:#0000ff"/>Format: xxx xxx-xx-xx</td>
                       </tr>
                       <tr>
                         <td style="color:#ffff00"/>${phrases['langDateOfBirth']}</td>
                         <td><input type="date" name="date_of_birth" value="${user.getDateOfBirth()}" /></td>
                         <td style="color:#ffff00"/>Format: 1900 - ${phrases['langToday']}</td>
                       </tr>
                      </table>
                    	<input type="submit" value="${phrases['langEditNow']}"/><br>
                           <h2 style="color:#B22222">
                               <c:forEach items="${errors}" var="i">
                                  ${i}<br>
                                  </c:forEach>
                                <a style="color:#0000ff" href="index.jsp">${phrases['langBackToMain']}</a>
                           </h2>
                    </div>
                </form>
    </body>
</html>