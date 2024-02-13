<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pretraga izvodjenja</title>
</head>
<body>
	<form action="/PozoristeWeb/PretragaPredstojecihIzvodjenja" method="get">
		Naziv predstave: <input type="text" name="naziv">
		<input type="submit" value="Prikazi">
	</form>
	<c:if test="${!empty izvodjenja }">
	<table border="1">
		<tr><th>Datum izvodjenja</th><th>Naziv</th><th>Trajanje</th><th>Scena</th><th>Slobodna mesta</th></tr>
		<c:forEach items="${izvodjenja }" var="i">
			<tr>
				<td>${i.datum }</td>
				<td>${i.predstava.naziv }</td>
				<td>${i.predstava.trajanje }</td>
				<td>${i.scena.naziv}</td>
				<td><a href="/PozoristeWeb/PrikazSlobodnihMesta?idIzvodjenje=${i.idIzvodjenje}">Prikazi</a></td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>