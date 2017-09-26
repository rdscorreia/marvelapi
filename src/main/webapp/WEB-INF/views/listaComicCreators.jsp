<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<h1>Lista de Comics com Creators</h1>
		
		<label>Código do comic: </label> <span>${ comicCreators.id}</span> <p>
		<label>Título do comic: </label> <span>${ comicCreators.title}</span> <p>
		<label>Descrição: </label>	<span>${ comicCreators.description}</span> <p>
		<label>Outras descrições: </label><span>${ comicCreators.variantDescription}</span> <p>
	
		<table border=1>
			<tr>
				<th>Código Creator</th>
				<th>Nome Completo</th>
			</tr>
			<c:forEach items="${ comicCreators.creators }" var="creator">
				<tr>
					<td>${creator.id}</td>
					<td>${creator.fullName }</td>
					
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>