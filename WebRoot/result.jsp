<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>友情提示</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css" media="all" />
		<style media="screen" type="text/css">
			table.errorsTable {
				border-left: 1px solid;
				border-top: 1px solid;
				width: 100%;
			}
			table.errorsTable th, table.errorsTable td {
				border-right: 1px solid;
				border-bottom: 1px solid;
				padding: 3px;
			}

		</style>
	</head>
	<body>
		<div id="left">
			<div class="innertube">
				<h1 class="head beright">提示信息</h1>
			</div>
		</div>
		<div id="page">
			<div id="main">
				<br />
				<font size="2" color='${result ? "green" : "red"}'>${msg}</font>
				<c:if test='${url eq "javascript:history.go(-1)"}'>
					<a href="${url}">${url_display}</a>
				</c:if>
				<c:if test='${url ne "javascript:history.go(-1)"}'>
					<a href="<%=request.getContextPath()%>/${url}">${url_display}</a>
				</c:if>
				<c:if test="${auto_redirect ne false}">
					( <span id="secondSpan" style="color: green;">3</span> 秒后自动跳转...)
					<script type="text/javascript">
						var secs = 3;
						//倒计时的秒数
						var URL;
						function Load(url) {
							URL = url;
							for(var i = secs; i >= 0; i--) {
								window.setTimeout('doUpdate(' + i + ')', (secs - i) * 1000);
							}
						}

						function doUpdate(num) {
							document.getElementById('secondSpan').innerHTML = num;
							if(num == 0) {
								window.location = URL;
							}
						}

						if("${url}" == "javascript:history.go(-1)") {
							Load("${url}");
						} else {
							Load("<%=request.getContextPath()%>/${url}");
						}
					</script>
				</c:if>
				<c:if test="${errors ne NULL}">
					<br/>
					<br/>
					<br/>
					<h3 style="margin-left:120px;">错误列表</h3>
					<table class="errorsTable">
						<tr>
							<th>字段名</th>
							<th>内容</th>
							<th>错误原因</th>
						</tr>
						<c:forEach var="e" items="${errors}">
							<tr>
								<td>${e.field}</td>
								<td>${e.rejectedValue}</td>
								<td>${e.defaultMessage}</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
