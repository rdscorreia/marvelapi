<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Comics</title>

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

<style type="text/css">
        body{
            padding-top: 60px;
        }
    </style>

</head>
<body>

	<div class="container">
	
		<h1>Lista de Comics</h1>

		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Código Comics</th>
				<th>Título</th>
				<th>Descrição</th>
				<th>Outras Descriões</th>

			</tr>
			<c:forEach items="${ comics }" var="comic">
				<tr>
					<td><a href="${ s:mvcUrl('HC#getComicsId').arg(0, comic.id).build() }" >${ comic.id} </a></td>
					<td>${comic.title }</td>
					<td>${comic.description }</td>
					<td>${comic.variantDescription }</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>