<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<h1>Lista de Comics</h1>

		<label>Código do comic: </label> <span>${ comic[0].id}</span>
		<p>
			<label>Título do comic: </label> <span>${ comic[0].title}</span>
		<p>
			<label>Descrição: </label> <span>${ comic[0].description}</span>
		<p>
			<label>Outras descrições: </label><span>${ comic[0].variantDescription}</span>
		<p>		
	</div>

</body>
</html>