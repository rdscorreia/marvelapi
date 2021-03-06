<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comics com Creators</title>

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
		<h1>Comics com Creators</h1>

		<label>Código do comic: </label> <span>${ comicCreators.comic.id}</span>
		<p>
			<label>Título do comic: </label> <span>${ comicCreators.comic.title}</span>
		<p>
			<label>Descrição: </label> <span>${ comicCreators.comic.description}</span>
		<p>
			<label>Outras descrições: </label><span>${ comicCreators.comic.variantDescription}</span>
		<p>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Código Creator</th>
				<th>Nome Completo</th>
			</tr>
			<c:forEach items="${ comicCreators.creator }" var="creator">
				<tr>
					<td>${creator.id}</td>
					<td>${creator.fullName }</td>

				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>