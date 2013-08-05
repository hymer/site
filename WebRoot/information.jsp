<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>公司信息维护</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/lib-base.jsp"></script>
		<style type="text/css" media="all" >
			#change_password_form label {
				width: 100px;
				display: inline-block;
				text-align: right;
			}
		</style>
	</head>
	<body>
		<%@ include file="left.jsp"%>
		<div id="page">
			<%@ include file="top.jsp"%>
			<div id="main">
				<form id="change_password_form" action="<%=request.getContextPath()%>/information.html"  method="post">
					<input type="text" style="display: none;" name="id" value="${user.companyInfo.id}" />
					<p>
						<label>公司名称：</label>
						<input type="text" name="companyName" value="${user.companyInfo.companyName}" size="64" />
					</p>
					<p>
						<label>地址：</label>
						<input type="text" name="companyAddress" value="${user.companyInfo.companyAddress}" size="64" />
					</p>
					<p>
						<label>联系人：</label>
						<input type="text" value="${user.companyInfo.linkman}" name="linkman" />
						<label>手机：</label>
						<input type="text" value="${user.companyInfo.mobile}" name="mobile" />
					</p>
					<p>
						<label>电话/传真：</label>
						<input type="text" value="${user.companyInfo.fax}" name="fax" />
						<label>邮箱：</label>
						<input type="text" value="${user.companyInfo.email}" name="email" />
					</p>
					<p>
						<label>开户行：</label>
						<input type="text" value="${user.companyInfo.bank}" name="bank" size="64" />
					</p>
					<p>
						<label>开户名称：</label>
						<input type="text" value="${user.companyInfo.bankAccount}" name="bankAccount" size="64" />
					</p>
					<p>
						<label>银行账号：</label>
						<input type="text" value="${user.companyInfo.taxNo}" name="taxNo" size="64" />
					</p>
					<p>
						<label></label>
						<input type="submit" value="确认修改"/>
					</p>
				</form>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
