<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Comics com Creators</title>

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

<style type="text/css">
        body{
            padding-top: 60px 0px;
        }
    </style>
    
</head>
<body>

	<div class="container" >
		<h1>Lista de Comics com Creators</h1>

		<label>Código do comic: </label> <span>${ comicCreators[0].id}</span>
		<p>
			<label>Título do comic: </label> <span>${ comicCreators[0].title}</span>
		<p>
			<label>Descrição: </label> <span>${ comicCreators[0].description}</span>
		<p>
			<label>Outras descrições: </label><span>${ comicCreators[0].variantDescription}</span>
		<p>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Código Creator</th>
				<th>Nome Completo</th>
			</tr>
			<c:forEach items="${ comicCreators[0].creators }" var="creator">
				<tr>
					<td>${creator.id}</td>
					<td>${creator.fullName }</td>

				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>