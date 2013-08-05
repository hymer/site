<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>销售财务管理</title>
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
						t = new SimpleTable("finacingQueryTable", {
							param : 'searchDiv',
							url : '<%=request.getContextPath()%>/finacing/query.html',
							sortables : ["id"],
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
								// checkbox : {
									// width : '3%'
								// },
								id : {
									header : '编号',
									width : '6%'
								},
								companyName : {
									header : '客户名称',
									width : '39%'
								},
								contractAmount : {
									header : '总金额',
									width : '15%'
								},
								paidAmount : {
									header : '已收款',
									width : '15%'
								},
								notPaidAmount : {
									header : '未收款',
									width : '15%'
								},
								ops : {
									header : '操作',
									width : '10%'
								}
							},
							formatters : {
								paidAmount : function(v, obj) {
									return '<font color="green">' + v + '</font>';
								},
								notPaidAmount : function(v, obj) {
									return '<font color="red">' + (obj.contractAmount - obj.paidAmount) + '</font>';
									;
								},
								ops : function(v, obj) {
									var html = '';
									html += "<a target='_blank' href='finacing/manager/" + obj.id + ".html'>管理</a>";
									html += "&nbsp;&nbsp;<a href='javascript:removeFinancing(" + obj.id + ");'>删除</a>";
									return html;
								}
							},
							listeners : {
								afterload : function(data) {
									return true;
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
					<input type="text" style="display: none;" name="finacingType" value="sales" />
					<table width="100%">
						<tr>
							<td>客户名称：
							<input name="companyName" />
							</td>
							<td>联系人：
							<input name="linkman" />
							</td>
							<!--
							<td>是否收款已完成：
							<select name="paidAll">
								<option value="">全部</option>
								<option value="Y">已完成</option>
								<option value="N" selected="selected">未收完</option>
							</select>
							</td>
							-->
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
						function removeFinancing(id) {
							showConfirm("确认要删除选中的财务信息吗？", function() {
								doPostAjax("<%=request.getContextPath()%>/finacing/delete.html", "id=" + id, function(resp) {
									showMsg(resp);
									t.doSearch();
								});
							});
						}
					</script>
				</div>
				<div id="tableDiv" style="width: 100%;">
					<table id="finacingQueryTable"></table>
				</div>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
