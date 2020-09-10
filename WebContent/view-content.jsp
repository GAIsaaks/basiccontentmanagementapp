<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Basic Content Management App</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: darkkhaki">
			<div>
				<a href="/BasicContentManagementApp/" class="navbar-brand"> Basic Content Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Content</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				


				<fieldset class="form-group">
				<caption>
					<h3>${content.title}</h3> 	
				</caption>
				</fieldset>

				<fieldset class="form-group">
					<h4>Content</h4> 
					${content.content}
				</fieldset>

				<fieldset class="form-group">
					<h4>Created Date</h4> 
					${content.created}
				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>
