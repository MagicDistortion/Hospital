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

<form action ="register" method ="post">
<div align="center" >
    <table>
         <tr>
            <td style="color:#0000ff" />${langSurname}:</td>
            <td><input name="surname" placeholder="${langEnterSurname}" pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{1,32}" required/></td>
        </tr>
        <tr>
            <td style="color:#0000ff" />${langName}:</td>
            <td><input name="name" placeholder="${langEnterName}" pattern="^[A-Za-zА-Яа-яІіЇїєЄ]{1,32}" required/></td>
        </tr>
        <tr>
            <td style="color:#ffff00"/>${langLogin}:</td>
	        <td><input name="login" placeholder="${langEnterLogin}" required/></td>

        </tr>
        <tr>
            <td style="color:#0000ff"/>${langPassword}:</td>
            <td><input type="password" name="password" placeholder="${langEnterPassword}" required/></td>
        </tr>
	    <tr>
	        <td style="color:#ffff00"/>${langRePassword}:</td>
	        <td><input type="password" name="repassword"  placeholder="${langRePassword}"required/></td>

        </tr>
        <tr>
            <td style="color:#0000ff"/>${langTel}:</td>
            <td> <input  type="number" name="tel" placeholder="${langEnterTel}" required></td>
            <td style="color:#0000ff"/>Format: xxx xxx-xx-xx</td>

        </tr>
        <tr>
            <td style="color:#ffff00"/> ${langDateOfBirth}:</td>
            <td><input type="date" name="date_of_birth" value="1900-01-01" required/></td>
            <td style="color:#ffff00"/>Format: 1900 - ${langToday}:</td>

        </tr>
    </table>
    	<input type="submit" value="${langRegister}"/><br>

    <h2 style="color:#B22222">
        <c:if  test="${not empty mes}" >${mes}</c:if>
    </h2>

</div>
</form>
</body>
</html>