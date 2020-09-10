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
				<c:if test="${content != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${content == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${content != null}">
            			Edit Content
            		</c:if>
						<c:if test="${content == null}">
            			Add New Content
            		</c:if>
					</h2>
				</caption>

				<c:if test="${content != null}">
					<input type="hidden" name="id" value="<c:out value='${content.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Title</label> <input type="text"
						value="<c:out value='${content.title}' />" class="form-control"
						name="title" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Content</label> <input type="text"
						value="<c:out value='${content.content}' />" class="form-control"
						name="content">
				</fieldset>

				<!-- 
				<fieldset class="form-group">
					<label>Created Date</label> <input type="text"
						value="<c:out value='${content.created}' />" class="form-control"
						name="created">
				</fieldset>
				-->

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
