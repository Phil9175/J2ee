<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<div class="container">
		<div>
			<tiles:insertAttribute name="menu" />
		</div>
		<div>
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
	<div></div>
</body>
</html>