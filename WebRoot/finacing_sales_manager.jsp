<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.hymer.contract.entity.Contract" %>
<%@ page import="com.hymer.finacing.entity.*" %>
<%@ page import="com.hymer.core.util.*" %>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>销售财务管理</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/lib-base.jsp"></script>
		<script type="text/javascript">
			$(function() {
				$("input.dateField").datepicker({
					dateFormat : "yy-mm-dd"
				});
				$('#payment_edit_form_div').dialog({
					title : '添加/修改',
					draggable : true,
					width : 420,
					height : "auto",
					modal : true,
					// position:'center', 'left', 'right', 'top', 'bottom'.  or [350,100]
					resizable : false,
					autoOpen : false,
					// show:'slide',
					buttons : {
						"OK" : function() {
							ajaxSubmitForm("payment_edit_form", function() {
								window.location.reload();
							});
						},
						"Cancel" : function() {
							$(this).dialog("close");
						}
					}
				});
			});
			function addPayment(isRefund) {
				document.forms['payment_edit_form'].reset();
				var refund = false;
				if (isRefund == "Y") {
					refund = true;
				}
				fillForm("payment_edit_form", {
					finacingId : "${finacing.id}",
					refund : refund
				});
				$("#payment_edit_form_div").dialog("open");
			}

			function editPayment(id, amount, dealDate, remarks, refund) {
				document.forms['payment_edit_form'].reset();
				fillForm("payment_edit_form", {
					id : id,
					amount : amount,
					dealDate : dealDate,
					refund : refund,
					remarks : remarks
				});
				$("#payment_edit_form_div").dialog("open");
			}
			function removePayment(id) {
				if(id) {
					showConfirm("确认要删除吗？", function() {
						doPostAjax("<%=request.getContextPath()%>/finacing/payment/delete.html", "id=" + id, function(resp) {
							showMsg(resp);
							window.location.reload();
						});
					});
				}
			}
		</script>
	</head>
	<body>
		<div id="left">
			<div class="innertube">
				<h1 class="head">销售财务管理</h1>
			</div>
		</div>
		<div id="page">
			<%@ include file="top.jsp"%>
			<div id="main">
				<div>
					<fieldset>
						<legend>
							客户信息：
						</legend>
						<p>
							<label>客户名称：</label>
							${finacing.customer.companyName}
						</p>
						<p>
							<label>地址：</label>
							${finacing.customer.address}
						</p>
						<p>
							<label>联系人：</label>
							${finacing.customer.linkman}
						</p>
						<p>
							<label>手机：</label>
							${finacing.customer.mobile}
						</p>
						<p>
							<label>电话/传真：</label>
							${finacing.customer.fax}
						</p>
						<p>
							<label>邮箱：</label>
							${finacing.customer.email}
						</p>
					</fieldset>
				</div>
				<div>
					<br />
					<fieldset>
						<legend>
							合同列表：
						</legend>
						<div id="contract_div">
							<table width="100%" class="borders">
								<thead>
									<th>合同编号</th>
									<th>签订时间</th>
									<th>合同金额</th>
									<th>已收款金额</th>
									<th>未收款金额</th>
									<th>退款金额</th>
								</thead>
								<tbody>
									<%
										Finacing finacing = (Finacing) request.getAttribute("finacing");
										List<Contract> contracts = (List<Contract>)request.getAttribute("contracts");
										Double contractAmount = 0.0;
										for (int i=0; i<contracts.size(); i++) {
											Contract c = contracts.get(i);
											contractAmount += c.getAmount();
											Date signDate = c.getSignDate();
											String NO = Formatters.formatDate(signDate, "yyyyMMdd");
											if (c.getId() < 10) {
												NO += ("0" + c.getId());
											} else {
												NO += c.getId();
											}
											List<Payment> payments = c.getPayments();
											Double paymentAmount = 0.0;
											Double backAmount = 0.0;
											for (Payment pp : payments) {
												if (pp.isRefund()) {
													backAmount += pp.getAmount();
												} else {
													paymentAmount += pp.getAmount();
												}
											}
											%>
												<tr>
													<td><%=NO%></td>
													<td><%=Formatters.formatDate(signDate)%></td>
													<td><%=c.getAmount()%></td>
													<td><font color="green"><%=paymentAmount%></font></td>
													<td><%=(c.getAmount()-backAmount-paymentAmount)%></td>
													<td><font color="red"><%=backAmount%></font></td>
												</tr>
												<%
												}
												%>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" style="text-align: right;"><b>合同总金额：<%=contractAmount%> 元（人民币）</b></td>
									</tr>
								</tfoot>
							</table>
						</div>
					</fieldset>
					<div>
						<br />
						<fieldset>
							<legend>
								已收款列表：
							</legend>
							<span><a href="javascript:addPayment();">添加收款信息</a></span>
							<div id="payment_div">
								<table width="100%" class="borders">
									<thead>
										<th>序号</th>
										<th>合同号</th>
										<th>金额</th>
										<th>收款时间</th>
										<th>备注</th>
										<th>操作</th>
									</thead>
									<tbody>
										<tr></tr>
										<%
											List<Payment>payments = finacing.getPayments();
											Double paymentAmount = 0.0;
											for (int i=0; i<payments.size(); i++) {
												Payment p = payments.get(i);
												if (p.isRefund()) {
													continue;
												}
												paymentAmount += p.getAmount();
												
												Contract c = p.getContract();
												Date signDate = c.getSignDate();
												String NO = Formatters.formatDate(signDate, "yyyyMMdd");
												if (c.getId() < 10) {
													NO += ("0" + c.getId());
												} else {
													NO += c.getId();
												}
											
											%>
												<tr>
													<td><%=i+1%></td>
													<td><%=NO%></td>
													<td><font color="green"><%=p.getAmount()%></font></td>
													<td><%=Formatters.formatDate(p.getDealDate())%></td>
													<td><%=p.getRemarks()%></td>
													<td><a href='javascript:editPayment(<%=p.getId()%>, <%=p.getAmount()%>, "<%=Formatters.formatDate(p.getDealDate())%>", "<%=p.getRemarks()%>");'>修改</a>&nbsp;&nbsp;<a href='javascript:removePayment(<%=p.getId()%>);'>删除</a></td>
												</tr>
												<%
												}
												%>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="6" style="text-align: right;"><b>已收款总金额：<%=paymentAmount%> 元（人民币）</b></td>
										</tr>
									</tfoot>
								</table>
							</div>
						</fieldset>
					</div>
					
					<div>
						<br />
						<fieldset>
							<legend>
								退款列表：
							</legend>
							<span><a href="javascript:addPayment('Y');">添加退款信息</a></span>
							<div id="back_payment_div">
								<table width="100%" class="borders">
									<thead>
										<th>序号</th>
										<th>合同号</th>
										<th>金额</th>
										<th>退款时间</th>
										<th>备注</th>
										<th>操作</th>
									</thead>
									<tbody>
										<tr></tr>
										<%
											Double backPaymentAmount = 0.0;
											for (int i=0; i<payments.size(); i++) {
												Payment p = payments.get(i);
												if (p.isRefund()) {
													backPaymentAmount += p.getAmount();
													
													Contract c = p.getContract();
													Date signDate = c.getSignDate();
													String NO = Formatters.formatDate(signDate, "yyyyMMdd");
													if (c.getId() < 10) {
														NO += ("0" + c.getId());
													} else {
														NO += c.getId();
													}
											%>
												<tr>
													<td><%=i+1%></td>
													<td><%=NO%></td>
													<td><font color="red"><%=p.getAmount()%></font></td>
													<td><%=Formatters.formatDate(p.getDealDate())%></td>
													<td><%=p.getRemarks()%></td>
													<td><a href='javascript:editPayment(<%=p.getId()%>, <%=p.getAmount()%>, "<%=Formatters.formatDate(p.getDealDate())%>", "<%=p.getRemarks()%>", <%=p.isRefund()%>);'>修改</a>&nbsp;&nbsp;<a href='javascript:removePayment(<%=p.getId()%>);'>删除</a></td>
												</tr>
											<%
													}
												}
											%>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="6" style="text-align: right;"><b>已退款总金额：<%=backPaymentAmount%> 元（人民币）</b></td>
										</tr>
									</tfoot>
								</table>
							</div>
						</fieldset>
					</div>
				</div>
				<div style="text-align: center;">
					<br />
					<br />
					<a href="javascript:window.close();">关闭本页</a>
				</div>
				<div id="payment_edit_form_div" style="display: none;">
					<form id="payment_edit_form" name="payment_edit_form" action="<%=request.getContextPath()%>/finacing/payment/edit.html"  method="post">
						<input type="text" style="display: none;" name="finacingId" value="${finacing.id}" />
						<input type="text" style="display: none;" name="paymentType" value="${finacing.finacingType}" />
						<input type="text" style="display: none;" name="refund" value="false" />
						<input type="text" style="display: none;" name="id" />
						<table>
							<tr>
								<td width="25%">合同：</td>
								<td width="75%">
									<select name="contractId">
										<%
										for (int i=0; i<contracts.size(); i++) {
											Contract c = contracts.get(i);
											Date signDate = c.getSignDate();
											String NO = Formatters.formatDate(signDate, "yyyyMMdd");
											if (c.getId() < 10) {
												NO += ("0" + c.getId());
											} else {
												NO += c.getId();
											}
										%>
											<option value="<%=c.getId()%>"><%=NO%></option>
										<%
										}
										%>
									</select>
								</td>
							</tr>
							<tr>
								<td width="25%">金额：</td>
								<td width="75%">
								<input type="text" name="amount"/>
								</td>
							</tr>
							<tr>
								<td>收款时间：</td>
								<td>
								<input type="text" class="dateField" name="dealDate"/>
								</td>
							</tr>
							<tr>
								<td>备注：</td>
								<td>								<textarea name="remarks" cols="30" rows="4"></textarea></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
