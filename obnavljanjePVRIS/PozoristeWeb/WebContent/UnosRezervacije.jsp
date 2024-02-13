<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unos rezervacije</title>
</head>
<body>
	<form action="/PozoristeWeb/RezervacijaMesta" method="post">
		Id posetioca: <input type="text" name="idPosetilac">
		<input type="submit" value="Rezervisi">
	</form>
	${poruka }
</body>
</html>