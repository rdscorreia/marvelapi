<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Creator</title>

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

<style type="text/css">
body {
	padding-top: 60px 0px;
}
</style>

</head>
<body>

	<div>
		<h1>Creator</h1>

		<label>Código do creator: </label> <span>${ creator.id }</span>
		<p>
			<label>Nome completo: </label> <span>${ creator.fullName }</span>
		<p>
		<p>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Tipo</th>
				<th>Link</th>
			</tr>
			<c:forEach items="${ creator.urls }" var="url">
				<tr>
					<td>${url.type}</td>
					<td><a href=" ${ url.url }">Trabalhos</a></td>

				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>