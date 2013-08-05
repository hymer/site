<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>采购产品查询</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/lib-base.jsp"></script>
	</head>
	<body>
		<%@ include file="left.jsp"%>
		<div id="page">
			<%@ include file="top.jsp"%>
			<div id="main">
				<script type="text/javascript" charset="utf-8">
					var t = null;
					$(function() {
						t = new SimpleTable("contractQueryTable", {
							param : 'searchDiv',
							url : '<%=request.getContextPath()%>/item/queryInContract.html',
							sortables : ["itemName", "itemCode"],
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
							columns : {
								itemCode : {
									header : '产品编码',
									width : '18%'
								},
								itemName : {
									header : '产品名称',
									width : '18%'
								},
								itemStandard: {
									header : '规格',
									width : '13%'
								},
								itemUnit: {
									header : '单位',
									width : '3%'
								},
								itemCount: {
									header : '数量',
									width : '3%'
								},
								itemPrice: {
									header : '单价',
									width : '5%'
								},
								contractType: {
									header : '类型',
									width : '4%'
								},
								itemRemark: {
									header : '备注',
									width : '8%'
								},
								supplierName : {
									header : '供应商名称',
									width : '22%'
								},
								ops : {
									header : '操作',
									width : '6%'
								}
							},
							formatters : {
								contractType:function(v, obj) {
									if (v == 'sales') {
										return "销售";
									} else {
										return "采购";
									}
								},
								ops : function(v, obj) {
									var html = '';
									html += "<a target='_blank' href='contract/view/" + obj.contractId + ".html'>查看合同</a>";
									return html;
								}
							},
							info : {
								//pageSizeSelect:'', //不显示此控件
								pageSizeSelect : '显示{0}条记录',
								pagingInfo : '目前显示{0}-{1}条记录，共计：{2}'
							}
						});

					});

				</script>
				<div id="searchDiv" style="width: 90%;">
					<table width="100%">
						<tr>
							<td>产品编码：</td><td>
							<input name="itemCode" />
							</td>
							<td>产品名称：</td><td >
							<input name="itemName" />
							</td>
							<td>类型：</td><td>
								<select name="contractType">
									<option value="purchase" selected="selected">采购</option>
									<option value="sales">销售</option>
								</select>
							</td>
							<td>
							<input type="button" id="searchButton" value="查询" />
							</td>
						</tr>
					</table>
				</div>
				<div style="text-align: right;">
					<script type='text/javascript'>
						$("#searchButton").click(function() {
							t.doSearch();
						});

					</script>
				</div>
				<div id="tableDiv" style="width: 100%;">
					<table id="contractQueryTable"></table>
				</div>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
