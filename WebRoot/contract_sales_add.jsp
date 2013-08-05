<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加合同</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/lib-base.jsp"></script>
		<script type="text/javascript">
			var editor, t2, t3;
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
				t3 = new SimpleTable("itemSelectTable", {
					pageSize : 5,
					param : 'searchDiv3',
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
							var selecteds = t3.getSelectedObj();
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
				t2 = new SimpleTable("customerSelectTable", {
					pageSize : 5,
					param : 'searchDiv2',
					url : '<%=request.getContextPath()%>/customer/query.html',
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
					// scrollX : true,
					columns : {
						checkbox : {
							width : '5%'
						},
						companyName : {
							header : '客户名称',
							width : '30%'
						},
						address : {
							header : '地址',
							width : '50%'
						},
						linkman : {
							header : '联系人',
							width : '15%'
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
				$('#customerSelectTableDiv').dialog({
					title : '选择客户',
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
								clearCustomerInfo();
								$("#f_customerId").val(obj.id);
								$("#f_customerName").val(obj.companyName);
								$("#f_customerAddress").val(obj.address);
								$("#f_customerLinkman").val(obj.linkman);
								$("#f_customerFax").val(obj.fax);
								$("#f_customerMobile").val(obj.mobile);
								$("#f_customerEmail").val(obj.email);

								$(this).dialog("close");
							}
						},
						"Cancel" : function() {
							$(this).dialog("close");
						}
					}
				});

				$("#selectCustomerBtn").click(function() {
					$('#customerSelectTableDiv').dialog("open");
				});
				function clearCustomerInfo() {
					$("#f_customerId").val("");
					$("#f_customerName").val("");
					$("#f_customerAddress").val("");
					$("#f_customerLinkman").val("");
					$("#f_customerFax").val("");
					$("#f_customerMobile").val("");
					$("#f_customerEmail").val("");
				}


				$("#clearCustomerBtn").click(function() {
					clearCustomerInfo();
				});
				$("#searchButton2").click(function() {
					t2.doSearch();
				});
				$("#searchButton3").click(function() {
					t3.doSearch();
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
				<h1 class="head">添加销售合同</h1>
			</div>
		</div>
		<div id="page">
			<%@ include file="top.jsp"%>
			<div id="main">
				<div id="contract_add_form_div">
					<form id="contract_add_form" action="<%=request.getContextPath()%>/contract/add.html"  method="post">
						<input type="text" style="display: none;" name="id" />
						<input type="text" style="display: none;" name="contractType" value="sales" />
						<fieldset>
							<legend>
								甲方（供方）信息
							</legend>
							<p>
								<label>甲方：</label>
								<input type="text" id="f_supplierName" value="${sessionScope.user.companyInfo.companyName}" name="supplierName" size="48"/>
							</p>
							<p>
								<label>地址：</label>
								<input type="text" id="f_supplierAddress" value="${sessionScope.user.companyInfo.companyAddress}" name="supplierAddress" size="64" />
							</p>
							<p>
								<label>联系人：</label>
								<input type="text" id="f_supplierLinkman" value="${sessionScope.user.companyInfo.linkman}" name="supplierLinkman" />
								<label>手机：</label>
								<input type="text" id="f_supplierMobile" value="${sessionScope.user.companyInfo.mobile}" name="supplierMobile" />
							</p>
							<p>
								<label>电话/传真：</label>
								<input type="text" id="f_supplierFax" value="${sessionScope.user.companyInfo.fax}" name="supplierFax" />
								<label>邮箱：</label>
								<input type="text" id="f_supplierEmail" value="${sessionScope.user.companyInfo.email}" name="supplierEmail" />
							</p>
							<p>
								<label>开户行：</label>
								<input type="text" id="f_supplierBank" value="${sessionScope.user.companyInfo.bank}" name="supplierBank" size="64" />
							</p>
							<p>
								<label>开户名称：</label>
								<input type="text" id="f_supplierBankAccount" value="${sessionScope.user.companyInfo.bankAccount}" name="supplierBankAccount" size="64" />
							</p>
							<p>
								<label>银行账号：</label>
								<input type="text" id="f_supplierTaxNo" value="${sessionScope.user.companyInfo.taxNo}" name="supplierTaxNo" size="64" />
							</p>
						</fieldset>
						<fieldset>
							<legend>
								乙方（需方）信息
							</legend>
							<p>
								<label>乙方：</label>
								<input type="hidden" id="f_customerId" name="customerId"/>
								<input type="text" id="f_customerName" name="customerName" readonly="readonly" size="48"/>
								<input type="button" id="selectCustomerBtn" value="选择" />
								<input type="button" id="clearCustomerBtn" value="清除" />
							</p>
							<p>
								<label>地址：</label>
								<input type="text" id="f_customerAddress" name="customerAddress" size="64" />
							</p>
							<p>
								<label>联系人：</label>
								<input type="text" id="f_customerLinkman" name="customerLinkman" />
								<label>手机：</label>
								<input type="text" id="f_customerMobile" name="customerMobile" />
							</p>
							<p>
								<label>电话/传真：</label>
								<input type="text" id="f_customerFax" name="customerFax" />
								<label>邮箱：</label>
								<input type="text" id="f_customerEmail" name="customerEmail" />
							</p>
							<p>
								<label>开户行：</label>
								<input type="text" id="f_customerBank" name="customerBank" size="64" />
							</p>
							<p>
								<label>开户名称：</label>
								<input type="text" id="f_customerBankAccount" name="customerBankAccount" size="64" />
							</p>
							<p>
								<label>银行账号：</label>
								<input type="text" id="f_customerTaxNo" name="customerTaxNo" size="64" />
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
										<tr></tr>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="2">以下为空白</td>
											<td colspan="8">
												<input type="text" name="itemRemarks" style="width:100%;" />
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
								<textarea name="content" style="width:100%;height:320px;visibility:hidden;">
									&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;2&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、原产地及制造商：深圳&lt;span&gt;&nbsp;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;3&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、包装方式：适合长途运输，具有防振，防碰撞能力&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;4&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、交货日期、交货地址及收货单位：在合同签订后
&#12288;天或在&lt;span&gt; 2012 &lt;/span&gt;年&lt;span&gt; 11 &lt;/span&gt;月&lt;span&gt; 15 &lt;/span&gt;日发货至需方所在地 。&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;5&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、运输方式：&#12288;□
汽运 □ 运费由需方承担&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;6&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、付款方式： 款到发货&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;7&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、开据税票的种类：&#12288;&#12288;无&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;8&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、验收标准：&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;乙方收到货物后按企业标准（产品技术资料、说明书及合同清单）进行验收&lt;span&gt;:&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;&nbsp;a&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、如产品品种、数量、规格、包装等存在问题&lt;span&gt;,&lt;/span&gt;应在收货后及时以书面形式通知甲方&lt;span&gt;,&lt;/span&gt;并予以处理&lt;span&gt;;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;&nbsp;b&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、如产品功能缺陷等质量问题应在收货后一周内向甲方提出书面异议和处理意见，（人为或不可抗力等因素导致的质量问题不在此列），若情况属实，甲方无条件更换不合格产品；如乙方未按规定期限提出书面异议的，视为所交产品符合合同规定。&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;&nbsp;C&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、乙方中途退货，应向甲方偿付退货部分货款&lt;span&gt;30&lt;/span&gt;％的违约金；乙方违反合同约定拒绝收货的，应当承担由此造成甲方的损失。&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;&nbsp;9&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、甲方承诺向乙方提供的所有产品，三年质保。因产品本身质量原因，壹年免费保修，终身维护，不上门售后服务。乙方因人为或使用不当及私自拆装设备造成损坏不在保修范围之内，若维修将收取主要原器件的成本费。在一年的免费保修期结束后，有关设备的升级维修，乙方只收取相应的硬件成本费用，免收人员的服务费用，并将收费硬件的保修期延长&lt;span&gt;1&lt;/span&gt;年。返修产品要求配有返修清单及详细地址电话，本公司售后服务接口为商务部&lt;span&gt;.&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;10&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、不可抗力&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;任何一方由于不可抗力的原因不能履行合同时，应及时向对方通报不能履行的理由，在取得有关机构证明后，允许延期履行、部分履行或者终止履行，并根据情况免予承担部分或者全部违约责任。&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;11&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、解决争议方式：&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;本合同在履行过程中如发生争议，应友好协商解决，双方不能协商时，任何一方均可向甲方所在地人民法院提起诉讼。&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;12&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;、其他约定：&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;1&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;）任何一方违反合同条款，均按《合同法》和有关法规规定承担经济责任&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;p style="background:white;"&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;2&lt;/span&gt;&lt;/b&gt;&lt;b&gt;&lt;span style="font-size:11.5pt;color:black;"&gt;）本合同自双方签字盖章之日起生效，涂改无效&lt;span&gt;,&lt;/span&gt;本合同一式两份，甲乙双方各执一份，合同的传真件、与合同原件具有同等法律效力。&lt;span&gt;&lt;/span&gt;&lt;/span&gt;&lt;/b&gt; &lt;/p&gt;&lt;br&gt;
								</textarea>
							</p>
						</fieldset>
						<p>
							<label>签订时间：</label>
							<input type="text" class="dateField" name="signDate" />
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
			<div id="customerSelectTableDiv" style="display: none;">
				<div id="searchDiv2" style="width: 90%;">
					<table width="100%">
						<tr>
							<td>客户名称：</td><td>
							<input name="companyName" />
							</td>
							<td>联系人：</td><td>
							<input name="linkman" />
							</td>
							<td>
							<input type="button" id="searchButton2" value="查询" />
							</td>
						</tr>
					</table>
				</div>
				<div style="width:100%;">
					<table id="customerSelectTable"></table>
				</div>
			</div>
			<div id="itemSelectTableDiv" style="display: none;">
				<div id="searchDiv3" style="width: 90%;">
					<table width="100%">
						<tr>
							<td>产品编码：</td><td>
							<input name="itemCode" />
							</td>
							<td>产品名称：</td><td>
							<input name="itemName" />
							</td>
							<td>
							<input type="button" id="searchButton3" value="查询" />
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
