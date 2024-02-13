<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unos glumca</title>
</head>
<body>
		<form action="/PozoristeWeb/UnosGlumcaServlet" method="post">
		<table>
			<tr><td>Ime:</td><td><input type="text" name="ime"></td></tr>
			<tr><td>Prezime:</td><td><input type="text" name="prezime"></td></tr>
			<tr><td>Jmbg:</td><td><input type="text" name="jmbg"></td></tr>
			<tr><td><input type="submit" value="Sacuvaj"></td></tr>
		</table>
	</form>
</body>
</html>