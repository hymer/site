<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>销售合同管理</title>
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
							url : '<%=request.getContextPath()%>/contract/query.html',
							sortables : ["contractName", "id"],
							pagingOptions : {
								first : true,
								end : true,
								go : true,
								firstHtml : '<a href="#">首页</a>',
								lastHtml : '<a href="#">上一页</a>',
								nextHtml : '<a href="#">下一页</a>',
								endHtml : '<a href="#">尾页</a>'
							},
							//checkMode : 'single',
							// scrollX : true,
							columns : {
								checkbox : {
									width : '2%'
								},
								id : {
									header : '编号',
									width : '9%'
								},
								customerName : {
									header : '客户名称',
									width : '28%'
								},
								customerLinkman : {
									header : '联系人',
									width : '15%'
								},
								signDate : {
									header : '签订时间',
									width : '9%'
								},
								// status : {
								// header : '状态',
								// width : '5%'
								// },
								ops : {
									header : '操作',
									width : '8%'
								}
							},
							formatters : {
								id : function(v, obj) {
									var date = obj.signDate;
									var year = date.substring(0,4);
									var month = date.substring(5,7);
									var day = date.substring(8,10);
									var oo = v < 10 ? "0" : "";
									return year + month + day + oo + v;
								},
								ops : function(v, obj) {
									var html = '';
									html += "<a target='_blank' href='contract/view/" + obj.id + ".html'>查看</a>";
									html += "&nbsp;&nbsp;<a target='_blank' href='contract/edit/" + obj.id + ".html'>修改</a>";
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
					<input type="text" name="contractType" style="display: none;" value="sales" />
					<table width="100%">
						<tr>
							<td width="20%">客户名称：</td><td width="30%">
							<input name="customerName" />
							</td>
							<td width="20%">联系人：</td><td width="30%">
							<input name="customerLinkman" />
							</td>
						</tr>
					</table>
				</div>
				<div style="text-align: right;">
					<input type="button" id="searchButton" value="查询" />
					<script type='text/javascript'>
						$("#searchButton").click(function() {
							t.doSearch();
						});

					</script>
				</div>
				<div>
					<script type="text/javascript">
						function removeResources() {
							var rids = t.getSelected();
							if(rids.length >= 1) {
								showConfirm("确认要删除选中的合同吗？", function() {
									doPostAjax("<%=request.getContextPath()%>/contract/delete.html", "ids=" + rids, function(resp) {
										showMsg(resp);
										t.doSearch();
									});
								});
							} else {
								showMsg("<font color='red'>请至少选择一行记录!</font>");
							}
						}
					</script>
					<a href="contract_sales_add.jsp" target="_blank">添加新合同</a>
					&nbsp;&nbsp; <a href="javascript:removeResources();">删除选中合同</a>
					<br />
					<br/>
				</div>
				<div id="tableDiv" style="width: 100%;">
					<table id="contractQueryTable"></table>
				</div>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
