<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>500</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css" media="all" />
	</head>
	<body>
		<div id="left">
			<div class="innertube">
				<h1 class="head beright">500</h1>
			</div>
		</div>
		<div id="page">
			<div id="main">
				<font color="red">服务器内部错误，请稍后再试！</font>
				<a href="#" onclick="top.location='index.jsp';">返回首页</a>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
