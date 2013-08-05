<%@ page language="java" pageEncoding="utf-8"%>
<div id="head">
	<div class="innertube">
		<c:if test="${sessionScope.user ne NULL}">
			<span><a href="<%=request.getContextPath()%>/">首页</a></span>
			<span style="float: right;"> 欢迎您:<font color="green">${sessionScope.user.userName}</font>， 
				<a href="<%=request.getContextPath()%>/j_security_logout.html">退出系统</a> </span>
		</c:if>
		<c:if test="${sessionScope.user eq NULL}">
			<span> &nbsp; </span>
			<span style="float: right;"> <a href="<%=request.getContextPath()%>/login.html">登录</a></span>
		</c:if>
		<hr style="clear: both;" />
	</div>
</div>
