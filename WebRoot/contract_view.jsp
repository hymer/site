<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>合同：${contract.contractType eq "sales" ? contract.customerName : contract.supplierName}</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/contract.js"></script>
		<script type="text/javascript">
			function addWater() {
				$(".water").each(function(i, ele) {
					var contractType = "${contract.contractType}";
					var posi_w = (contractType == "sales" ? 360 : 820);
					var _this = $(ele), _position = _this.position(), _height = _this.height(), _width = _this.width(), _alt = _this.attr('alt') || '';
					/*$('<div />').css({
					 height : _height,
					 width : _width,
					 position : 'absolute',
					 zIndex : 9999,
					 top : _position.top,
					 left : _position.left,
					 backgroundImage : 'url(<%=request.getContextPath()%>/images/water.png)',
					 backgroundPosition : posi_w + ' 0',
					 backgroundRepeat : 'no-repeat'
					 }).appendTo('body');*/
					$('<div />').css({
						height : _height,
						width : _width,
						position : 'absolute',
						zIndex : 9999,
						top : _position.top,
						// left : _position.left
						left : posi_w
					}).append('<img width="113px" height="113px" src="<%=request.getContextPath()%>/images/water.png"/>').appendTo('body');
				});
			};

			function printHTML() {
				window.print();
			}

			function generateNO() {
				var date = "${contract.signDate}";
				var id = "${contract.id}";
				var year = date.substring(0, 4);
				var month = date.substring(5, 7);
				var day = date.substring(8, 10);
				var oo = parseInt(id) < 10 ? "0" : "";
				document.write(year + month + day + oo + id);
			}
		</script>
		<style type="text/css" media="all" >
			body {
				margin: 0;
				text-align: center;
				font-family: "微软雅黑";
				font-family: "Lucida Grande", verdana, arial, helvetica, sans-serif;
				color: #000000 !important;
				font-size: 12px;
				padding: 0;
			}
			body div {
				text-align: left;
			}
			#page {
				width: 1024px;
				margin: 0 auto;
				text-align: left;
				overflow: hidden;
				background-repeat: no-repeat;
			}
			#header_table {
				text-align: left;
			}
			#foot_table, #itemTable {
				width: 100%;
				border: 1px solid #666;
				border-collapse: collapse;
			}
			#itemTable th, #itemTable td {
				text-align: center;
				border: 1px solid #666;
			}
			#itemTable td.beleft {
				text-align: left;
			}
			#foot_table th, #foot_table td {
				font-weight: bold;
				border: 1px solid #666;
			}
			#content_div, #first_table td {
				font-family: "宋体";
				font-weight: bold;
				font-size: 11pt;
			}
			#content_div {
				padding: 5px 0px;
			}
			#content_div label {
				display: -moz-inline-box;
				display: inline-block;
			}
			#content_div label.label1 {
				width: 120px;
			}
			#content_div label.label11 {
				width: 320px;
			}
			#content_div label.label2 {
			}
			#content_div label.label3 {
				width: 120px;
				text-align: right;
			}
			#content_div label.label33 {
				text-align: left;
				width: 320px;
			}
			font.font1 {
				font-size: 24pt;
				font-weight: bolder;
			}
			font.font2 {
				font-size: 12pt;
				font-weight: bolder;
			}
			span.contract_title {
				font-size: 28pt;
				font-weight: bolder;
			}
			#printPage {
				width: 1002px;
				margin: 0 auto;
				padding: 0 0px;
				overflow: hidden;
				background: #fff;
				text-align: left;
			}
		</style>
		<style media="print">
			.noprint {
				display: none;
			}
		</style>
	</head>
	<body>
		<div id="printPage">
			<div id="body">
				<div id="head">
					<div style="padding: 10px 10px 10px 0px; width: 150px; float: left;">
						<img src="<%=request.getContextPath()%>/images/logo.jpg"/>
					</div>
<!--
					<div style="float: left;">
						<br />
						<font class="font1">深圳华瑞源科技有限公司</font>
						<br />
						<font class="font2">Shenzhen huaruiyuan technology co., LTD</font>
					</div>
-->
					<div style="clear:both; text-align: center">
						<c:if test='${contract.contractType eq "purchase"}'>
							<span class="contract_title">采&nbsp;&nbsp;购&nbsp;&nbsp;合&nbsp;&nbsp;同</span>
						</c:if>
						<c:if test='${contract.contractType eq "sales"}'>
							<span class="contract_title">销&nbsp;&nbsp;售&nbsp;&nbsp;合&nbsp;&nbsp;同</span>
						</c:if>
					</div>
				</div>
				<div style="clear:both;"></div>
				<div id="content_div">
					<div style="text-align: right; padding-right: 50px;">
						<label>修改次数：${contract.modifyTimes}</label>
					</div>
					<div id='first_table' style="padding:20px 0 0 0;">
						<table width="100%;">
							<tr>
								<td width="52%">合同编号：
								<script>
									generateNO();

								</script></td>
								<td width="48%">签订日期：${fn:substring(contract.signDate,0,10)}</td>
							</tr>
							<tr>
								<td>供方（甲方）：${contract.supplierName}</td>
								<td>需方（乙方）：${contract.customerName}</td>
							</tr>
							<tr>
								<td>联系人：${contract.supplierLinkman}</td>
								<td>联系人：${contract.customerLinkman}</td>
							</tr>
						</table>
					</div>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;甲、乙双方经过友好协商，本着平等互利的原则，乙方同意购进甲方下列货物，甲、乙双方应真诚合作、共同信守、遵照下列条款严格执行本合同：
					</p>
					<p>
						<label class="label2">1、产品名称、型号规格及价格</label>
						<br/>
						<table id="itemTable">
							<thead>
								<th style="width: 4%;">序号</th>
								<th style="width: 18%;">产品编码</th>
								<th style="width: 20%;">产品名称</th>
								<th style="width: 18%;">规格</th>
								<th style="width: 4%;">单位</th>
								<th style="width: 4%;">数量</th>
								<th style="width: 6%;">单价</th>
								<th style="width: 8%;">金额</th>
								<th style="width: 12%;">备注</th>
							</thead>
							<tbody>
								<c:forEach items="${contract.items}" var="item" varStatus="loop">
									<tr id='item_tr_${loop.index}'>
										<td> ${loop.index + 1} </td>
										<td> ${item.itemCode} </td>
										<td class="beleft"> ${item.itemName} </td>
										<td class="beleft">${item.itemStandard} </td>
										<td>${item.itemUnit} </td>
										<td>${item.itemCount} </td>
										<td>${item.itemPrice} </td>
										<td>${item.itemTotalPrice} </td>
										<td class="beleft">${item.itemRemark} </td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<c:if test='${contract.itemRemarks ne null and contract.itemRemarks ne ""}'>
									<tr>
										<td colspan="2">以下为空白</td>
										<td colspan="8" class="beleft"> ${contract.itemRemarks} </td>
									</tr>
								</c:if>
								<tr>
									<td colspan="2"><b>合计金额（人民币）：</b></td>
									<td style="text-align: left;"><font><b>
										<script type="text/javascript">
											document.write(transferChinese("${contract.amount}"));

										</script></b></font></td>
									<td style="text-align: left;"><label style="width: 100%; text-align: center;font-weight: bold;">小写：</label></td>
									<td colspan="5" style="text-align: left;"><font><b>￥${contract.amount}</b></font></td>
									</td>
								</tr>
							</tfoot>
						</table>
					</p>
					<p>
						${contract.content}
					</p>
					<br />
					<table id="foot_table" class="water">
						<tr>
							<td style="width: 50%;">供方： ${contract.supplierName}</td>
							<td style="width: 50%;">需方： ${contract.customerName}</td>
						</tr>
						<tr>
							<td>单位地址： ${contract.supplierAddress}</td>
							<td>单位地址： ${contract.customerAddress}</td>
						</tr>
						<tr>
							<td>代表： ${contract.supplierLinkman}</td>
							<td>代表： ${contract.customerLinkman}</td>
						</tr>
						<tr>
							<td>电话/传真： ${contract.supplierFax}</td>
							<td>电话/传真： ${contract.customerFax}</td>
						</tr>
						<tr>
							<td>开户行： ${contract.supplierBank}</td>
							<td>开户行： ${contract.customerBank}</td>
						</tr>
						<!--<tr>
							<td>开户名称： ${contract.supplierBankAccount}</td>
							<td>开户名称： ${contract.customerBankAccount}</td>
						</tr>-->
						<tr>
							<td>银行账号： ${contract.supplierTaxNo}</td>
							<td>银行账号： ${contract.customerTaxNo}</td>
						</tr>
					</table>
					<div class="noprint">
						<p style="text-align: center;">
							<br />
							<br />
							<a id="addWaterA" href="javascript:addWater();">加盖公司章</a>
							<label>&nbsp;</label>
							<a id="printP" href="javascript:printHTML();">打印本页</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
