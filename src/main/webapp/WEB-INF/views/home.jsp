<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Marvel API</title>
</head>
<body>
	Deu certo

	<p>Entrando na home da Marvel API
	<p>
	<ul>
		<li><a href="${s:mvcUrl('HC#getComics').build()}">Clique aqui</a> para acessar a lista de Comics</li>

	</ul>

</body>
</html>