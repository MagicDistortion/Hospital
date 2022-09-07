<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
     <meta charset="UTF-8">
     <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    </head>

    <body>
        <br><br><br><br><br><br>
            <style>
                body {background:#000000 url(images/Serze2.jpg) no-repeat;}
            </style>

                <form action ="edit_profile" method ="post">
                    <div align="center" >
                     <table>
                       <tr>
                         <td style="color:#0000ff"/><h3>${phrases['langSurname']}:</h3></td>
                         <td><input class="form-control" name="surname"  pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{1,32}" value="${user.getSurname()}"/></td>
                       </tr>
                       <tr>
                         <td style="color:#ffff00"/><h3>${phrases['langName']}:</h3></td>
                         <td><input class="form-control" name="name"  pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{1,32}" value="${user.getName()}"/></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/><h3>${phrases['langLogin']}:</h3></td>
	                     <td><input class="form-control" name="login" value="${user.getLogin()}" ></td>
                       </tr>
                       <tr>
                         <td style="color:#ffff00"/><h3>${phrases['langNewPassword']}:</h3></td>
                         <td><input class="form-control" type="password" placeholder="${phrases['langNewPassword']}" name="password" /></td>
                       </tr>
	                   <tr>
	                     <td style="color:#0000ff"/><h3>${phrases['langRePassword']}:</h3></td>
	                     <td><input class="form-control" type="password" placeholder="${phrases['langRePassword']}" name="repassword" /></td>
                       </tr>
                       <tr>
                         <td style="color:#ffff00"/><h3>${phrases['langTel']}:</h3></td>
                         <td> <input class="form-control" type="number" name="tel" value="${user.getTel()}" ></td>
                         <td style="color:#0000ff"/><h5/>Format: xxx xxx-xx-xx</td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/><h3>${phrases['langDateOfBirth']}</h3></td>
                         <td><input class="form-control" type="date" name="date_of_birth" value="${user.getDateOfBirth()}" /></td>
                         <td style="color:#ffff00"/><h5/>Format: 1900 - ${phrases['langToday']}</td>
                       </tr>
                      </table>
                    	<input type="submit" class="btn btn-info" value="${phrases['langEditNow']}"/><br>
                           <h2 style="color:#B22222">
                               <c:forEach items="${errors}" var="i">
                                  ${i}<br>
                                  </c:forEach>
                                <a class=" btn btn-warning" href="index.jsp">${phrases['langBackToMain']}</a>
                           </h2>
                    </div>
                </form>
    </body>
</html>