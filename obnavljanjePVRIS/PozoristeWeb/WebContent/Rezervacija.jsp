<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prikaz slobodnih mesta</title>
</head>
<body>
	<table>
	<tr><th>Broj</th><th>Red</th><th>Sekcija</th><th>Rezervacija</th></tr>
		<c:forEach items="${mesta }" var="m">
			<tr>
				<td>${m.broj }</td>
				<td>${m.red }</td>
				<td>${m.sekcija }</td>
				<td><a href="/PozoristeWeb/RezervacijaMesta?idMesto=${m.idMesto }">Rezervisi</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>