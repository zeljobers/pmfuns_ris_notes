<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unos uloge</title>
</head>
<body>
${poruka }
<jsp:useBean id="predstaveBean" class="beans.PredstavaBeans" scope="session"></jsp:useBean>
<c:if test="${!empty glumac }">
	<form action="/PozoristeWeb/UnosUlogeServlet" method="post">
		Uloga:<input type="text" name="uloga"><br>
		Predstava:<select name="predstava">
						<c:forEach var="pr" items="${predstaveBean.svePredstave}">
							<option value="${pr.idPredstava}">${pr.naziv}</option>
						</c:forEach>
				</select><br/>
		<input type="submit" value="Sacuvaj">
	</form>
</c:if>

${porukaUloga }<br/>
<c:if test="${!empty uloga }">
	naziv: ${uloga.naziv }<br/>
	predstava: ${uloga.predstava.naziv }<br/>
	id: ${uloga.idUloga }<br/>
</c:if>

</body>
</html>