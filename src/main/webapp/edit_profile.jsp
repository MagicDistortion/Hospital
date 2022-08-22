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
                         <td style="color:#0000ff"/> Surname:</td>
                         <td><input name="surname"  pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{1,32}" value="${surname}"/></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/> Name:</td>
                         <td><input name="name"  pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{1,32}" value="${name}"/></td>
                       </tr>
                       <tr>
                         <td style="color:#ffff00"/> Login:</td>
	                     <td><input name="login" value="${login}" ></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/>New Password:</td>
                         <td><input type="password" placeholder="enter new Password" name="password" /></td>
                       </tr>
	                   <tr>
	                     <td style="color:#ffff00"/> Re-enter Password:</td>
	                     <td><input type="password" placeholder="re enter Password" name="repassword" /></td>
                       </tr>
                       <tr>
                         <td style="color:#0000ff"/> Tel:</td>
                         <td> <input  type="number" name="tel" value="${tel}" ></td>
                         <td style="color:#0000ff"/>Format: xxx xxx-xx-xx</td>
                       </tr>
                       <tr>
                         <td style="color:#ffff00"/> Date of Birth</td>
                         <td><input type="date" name="date_of_birth" value="${date_of_birth}" /></td>
                         <td style="color:#ffff00"/>Format: 1900 - today:</td>
                       </tr>
                      </table>
                    	<input type="submit" value="edit now!"/><br>
                           <h2 style="color:#B22222">
                             <c:if  test="${not empty mes}" >${mes}</c:if><br>
                             <c:if  test="${not empty meserror}" >${meserror}</c:if>
                                <a style="color:#0000ff" href="index.jsp">back to Main</a>
                           </h2>
                    </div>
                </form>
    </body>
</html>