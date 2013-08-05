<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改采购合同</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/lib-base.jsp"></script>
		<script type="text/javascript">
			var editor, t2;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : ['fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist', 'insertunorderedlist', '|', 'emoticons', 'image', 'link']
				});
			});
			$(function() {
				$("input.dateField").datepicker({
					dateFormat : 'yy-mm-dd'
				});
				setAmount();
				
				t2 = new SimpleTable("itemSelectTable", {
					pageSize : 5,
					param : 'searchDiv2',
					url : '<%=request.getContextPath()%>/item/query.html',
					sortables : [],
					autoLoad : false,
					pagingOptions : {
						first : true,
						end : true,
						go : true,
						firstHtml : '<a href="#">首页</a>',
						lastHtml : '<a href="#">上一页</a>',
						nextHtml : '<a href="#">下一页</a>',
						endHtml : '<a href="#">尾页</a>'
					},
					checkMode : 'single',
					columns : {
						checkbox : {
							width : '4%'
						},
						itemCode : {
							header : '产品编码',
							width : '20%'
						},
						itemName : {
							header : '产品名称',
							width : '25%'
						},
						itemStandard : {
							header : '规格',
							width : '20%'
						},
						itemUnit : {
							header : '单位',
							width : '6%'
						},
						itemRemark : {
							header : '备注',
							width : '25%'
						}
					},
					formatters : {
					},
					info : {
						//pageSizeSelect:'', //不显示此控件
						pageSizeSelect : '显示{0}条记录',
						pagingInfo : '目前显示{0}-{1}条记录，共计：{2}'
					}
				});
				$('#itemSelectTableDiv').dialog({
					title : '选择产品/物料',
					draggable : true,
					width : 720,
					height : "auto",
					modal : true,
					resizable : false,
					autoOpen : false,
					// show:'slide',
					buttons : {
						"OK" : function() {
							var selecteds = t2.getSelectedObj();
							if(selecteds.length == 0) {
								showMsg({
									result : false,
									msg : "请选择一条记录！"
								});
							} else {
								var obj = selecteds[0];
								var tr = $("#item_tr_" + current_item_tr);
								$('input[name="item_code' + current_item_tr + '"]', tr).val(obj.itemCode);
								$('input[name="item_name' + current_item_tr + '"]', tr).val(obj.itemName);
								$('input[name="item_standard' + current_item_tr + '"]', tr).val(obj.itemStandard);
								$('input[name="item_unit' + current_item_tr + '"]', tr).val(obj.itemUnit);
								$('input[name="item_price' + current_item_tr + '"]', tr).val(obj.itemPrice);
								$('input[name="item_remark' + current_item_tr + '"]', tr).val(obj.itemRemark);
								current_item_tr = null;
								$(this).dialog("close");
							}
						},
						"Cancel" : function() {
							$(this).dialog("close");
						}
					}
				});
				$("#searchButton2").click(function() {
					t2.doSearch();
				});
			});

		</script>
		<style type="text/css" media="screen" >
			#contract_add_form_div {
				padding: 10px 20px;
			}
			#contract_add_form_div label {
				width: 100px;
				display: inline-block;
				text-align: right;
			}
			#itemTable {
				width: 100%;
				border: 1px solid #666;
				border-collapse: collapse;
			}
			#itemTable th, #itemTable td {
				text-align: center;
				border: 1px solid #666;
			}
		</style>
	</head>
	<body>
		<div id="left">
			<div class="innertube">
				<h1 class="head">修改采购合同</h1>
			</div>
		</div>
		<div id="page">
			<%@ include file="top.jsp"%>
			<div id="main">
				<div id="contract_add_form_div">
					<form id="contract_add_form" action="<%=request.getContextPath()%>/contract/add.html"  method="post">
						<input type="text" style="display: none;" name="id" value="${contract.id}" />
						<input type="text" style="display: none;" name="contractType" value="purchase" />
						<fieldset>
							<legend>
								甲方（供方）信息
							</legend>
							<p>
								<label>甲方：</label>
								<input type="hidden" id="f_supplierId" value="${contract.supplier.id}" name="supplierId"/>
								<input type="text" id="f_supplierName" value="${contract.supplierName}" name="supplierName" readonly="readonly" size="48"/>
							</p>
							<p>
								<label>地址：</label>
								<input type="text" id="f_supplierAddress" value="${contract.supplierAddress}" name="supplierAddress" size="64" />
							</p>
							<p>
								<label>联系人：</label>
								<input type="text" id="f_supplierLinkman" value="${contract.supplierLinkman}" name="supplierLinkman" />
								<label>手机：</label>
								<input type="text" id="f_supplierMobile" value="${contract.supplierMobile}" name="supplierMobile" />
							</p>
							<p>
								<label>电话/传真：</label>
								<input type="text" id="f_supplierFax" value="${contract.supplierFax}" name="supplierFax" />
								<label>邮箱：</label>
								<input type="text" id="f_supplierEmail" value="${contract.supplierEmail}" name="supplierEmail" />
							</p>
							<p>
								<label>开户行：</label>
								<input type="text" id="f_supplierBank" name="supplierBank" value="${contract.supplierBank}" size="64" />
							</p>
							<p>
								<label>开户名称：</label>
								<input type="text" id="f_supplierBankAccount" name="supplierBankAccount" value="${contract.supplierBankAccount}" size="64" />
							</p>
							<p>
								<label>银行账号：</label>
								<input type="text" id="f_supplierTaxNo" name="supplierTaxNo" value="${contract.supplierTaxNo}" size="64" />
							</p>
						</fieldset>
						<fieldset>
							<legend>
								乙方（需方）信息
							</legend>
							<p>
								<label>乙方：</label>
								<input type="hidden" id="f_customerId" value="${contract.customer.id}" name="customerId"/>
								<input type="text" id="f_customerName" value="${contract.customerName}" name="customerName" readonly="readonly" size="48"/>
							</p>
							<p>
								<label>地址：</label>
								<input type="text" id="f_customerAddress" value="${contract.customerAddress}" name="customerAddress" size="64" />
							</p>
							<p>
								<label>联系人：</label>
								<input type="text" id="f_customerLinkman" value="${contract.customerLinkman}" name="customerLinkman" />
								<label>手机：</label>
								<input type="text" id="f_customerMobile" value="${contract.customerMobile}" name="customerMobile" />
							</p>
							<p>
								<label>电话/传真：</label>
								<input type="text" id="f_customerFax" value="${contract.customerFax}"  name="customerFax" />
								<label>邮箱：</label>
								<input type="text" id="f_customerEmail" value="${contract.customerEmail}" name="customerEmail" />
							</p>
							<p>
								<label>开户行：</label>
								<input type="text" id="f_customerBank" name="customerBank" value="${contract.customerBank}" size="64" />
							</p>
							<p>
								<label>开户名称：</label>
								<input type="text" id="f_customerBankAccount" name="customerBankAccount" value="${contract.customerBankAccount}" size="64" />
							</p>
							<p>
								<label>银行账号：</label>
								<input type="text" id="f_customerTaxNo" name="customerTaxNo" value="${contract.customerTaxNo}" size="64" />
							</p>
						</fieldset>
						<fieldset>
							<legend>
								产品名称、型号规格及价格
							</legend>
							<p>
								<a href="javascript:addItem();">添加产品</a>
								<br />
								<table id="itemTable">
									<thead>
										<th style="width: 4%;">序号</th>
										<th style="width: 18%;">产品编码</th>
										<th style="width: 20%;">产品名称</th>
										<th style="width: 18%;">规格</th>
										<th style="width: 4%;">单位</th>
										<th style="width: 4%;">数量</th>
										<th style="width: 5%;">单价</th>
										<th style="width: 7%;">金额</th>
										<th style="width: 10%;">备注</th>
										<th style="width: 10%;">操作</th>
									</thead>
									<tbody>
										<c:forEach items="${contract.items}" var="item" varStatus="loop">
											<tr id='item_tr_${loop.index}'>
												<td>
													${loop.index + 1}
												</td>
												<td>
													<input type='hidden' name='item_id${loop.index}' value="${item.id}" />
													<input type='text' name='item_code${loop.index}' value="${item.itemCode}" style="width: 100%;"/>
												</td>
												<td>
												<input type='text' name='item_name${loop.index}' value="${item.itemName}" style="width: 100%;"/>
												</td>
												<td>
												<input type='text' name='item_standard${loop.index}' value="${item.itemStandard}" style="width: 100%;"/>
												</td>
												<td>
												<input type='text' name='item_unit${loop.index}' value="${item.itemUnit}" style="width: 100%;"/>
												</td>
												<td>
												<input type='text' name='item_count${loop.index}' onblur="setTotalPrice(this);" value="${item.itemCount}" style="width: 100%;"/>
												</td>
												<td>
												<input type='text' name='item_price${loop.index}' onblur="setTotalPrice(this);" value="${item.itemPrice}" style="width: 100%;"/>
												</td>
												<td>
												<input type='text' id='f_totalPrice' name="item_total_price${loop.index}" readonly='readonly' value="${item.itemTotalPrice}" style="width: 100%;"/>
												</td>
												<td>
												<input type='text' name='item_remark${loop.index}' value="${item.itemRemark}" style="width: 100%;"/>
												</td>
												<td><a href='javascript:selectItem(${loop.index});'>选择</a>&nbsp;&nbsp;<a href='javascript:removeItem("item_tr_${loop.index}");'>删除</a></td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="2">以下为空白</td>
											<td colspan="8">
												<input type="text" name="itemRemarks" value="${contract.itemRemarks}" style="width: 100%;" />
											</td>
										</tr>
										<tr>
											<td colspan="2"><b>合计金额（人民币）：</b></td>
											<td style="text-align: left;">
											<input type="hidden" id="f_amount" name="amount" value="" />
											<label style="width: 100%; text-align: center; font-weight: bold;"><font id="amountMaxLabel"></font></label></td>
											<td style="text-align: left;">
												<label style="width: 100%; text-align: center;font-weight: bold;">小写：</label>
											</td>
											<td colspan="6" style="text-align: left;">
												<label><font id="amountMinLabel"></font></label>
											</td>
										</tr>
									</tfoot>
								</table>
							</p>
						</fieldset>
						<fieldset>
							<legend>
								其他内容
							</legend>
							<p>
								<textarea name="content" style="width:100%;height:320px;visibility:hidden;">${contract.content}</textarea>
							</p>
						</fieldset>
						<p>
							<label>签订时间：</label>
							<input type="text" class="dateField" value="${fn:substring(contract.signDate,0,10)}" name="signDate" />
							<label>修改次数：</label>
							<input type="text" value="${contract.modifyTimes}" name="modifyTimes" />
						</p>
						<p>
							<label style="width: 320px;"> </label>
							<input type="submit" value="提交" />
							<label></label>
							<input type="reset" value="重置" />
						</p>
					</form>
				</div>
			</div>
			<div id="itemSelectTableDiv" style="display: none;">
				<div id="searchDiv2" style="width: 90%;">
					<table width="100%">
						<tr>
							<td>产品编码：</td><td>
							<input name="itemCode" />
							</td>
							<td>产品名称：</td><td>
							<input name="itemName" />
							</td>
							<td>
							<input type="button" id="searchButton2" value="查询" />
							</td>
						</tr>
					</table>
				</div>
				<div style="width:100%;">
					<table id="itemSelectTable"></table>
				</div>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
