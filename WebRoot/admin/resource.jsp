<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>资源管理</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/lib-base.jsp"></script>
	</head>
	<body>
		<%@ include file="left.jsp"%>
		<div id="page">
			<%@ include file="../top.jsp"%>
			<div id="main">
				<script type="text/javascript" charset="utf-8">
					var t = null;
					$(function() {
						t = new SimpleTable("resourceQueryTable", {
							pageSize : 10,
							param : 'searchDiv',
							url : '<%=request.getContextPath()%>/admin/resource_query.html',
							sortables : ["name"],
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
									width : '3%'
								},
								name : {
									header : '资源名称',
									width : '15%'
								},
								type : {
									header : '资源类型',
									width : '10%'
								},
								priority : {
									header : '优先级',
									width : '8%'
								},
								url : {
									header : '资源路径',
									width : '32%'
								},
								description : {
									header : '资源描述',
									width : '25%'
								},
								ops : {
									header : '操作',
									width : '6%'
								}
							},
							formatters : {
								ops : function(v, obj) {
									var html = '';
									html += "<a href='javascript:doEdit(" + obj.id + ");'>修改</a>";
									return html;
								}
							},
							info : {
								//pageSizeSelect:'', //不显示此控件
								pageSizeSelect : '显示{0}条记录',
								pagingInfo : '目前显示{0}-{1}条记录，共计：{2}'
							}
						});

						$('#resource_add_form_div').dialog({
							draggable : true,
							title : '添加/修改资源',
							width : 620,
							modal : true,
							// position:'center', 'left', 'right', 'top', 'bottom'.  or [350,100]
							resizable : false,
							autoOpen : false,
							// show:'slide',
							height : 220,
							buttons : {
								"OK" : function() {
									ajaxSubmitForm("resource_add_form", editCallback);
								},
								"Cancel" : function() {
									$(this).dialog("close");
								}
							}
						});
					});
					function editCallback() {
						t.doSearch();
					}

					function doEdit(id) {
						doGetAjax("<%=request.getContextPath()%>/admin/resource/" + id + ".html", '', function(data, textStatus, XMLHttpRequest) {
							fillForm("resource_add_form", data);
							$("#resource_add_form_div").dialog("open");
						});
					}
				</script>
				<div id="searchDiv" style="width: 90%;">
					<table width="100%">
						<tr>
							<td width="20%">资源名称：</td><td width="30%">
							<input name="name" />
							</td>
							<td width="20%">资源路径：</td><td width="30%">
							<input name="url" />
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
						function showAddForm() {
							document.forms['resource_add_form'].reset();
							var dialog = $('#resource_add_form_div').dialog("open");
						}

						function removeResources() {
							var rids = t.getSelected();
							if(rids.length >= 1) {
								showConfirm("确认要删除选中的资源吗？", function() {
									doPostAjax("<%=request.getContextPath()%>/admin/resource/delete.html", "ids=" + rids, function(resp) {
										showMsg(resp);
										t.doSearch();
									});
								});
							} else {
								showMsg("<font color='red'>请至少选择一行记录!</font>");
							}
						}
					</script>
					<a href="javascript:showAddForm();">添加新资源</a>
					&nbsp;&nbsp; <a href="javascript:removeResources();">删除选中资源</a>
					<br />
					<br/>
				</div>
				<div id="tableDiv" style="width: 100%;">
					<table id="resourceQueryTable"></table>
				</div>
			</div>
			<div id="resource_add_form_div" style="display: none;">
				<form id="resource_add_form" action="<%=request.getContextPath()%>/admin/resource_edit.html"  method="post">
					<input type="text" style="display: none;" name="id" />
					<table>
						<tr>
							<td>资源名称：</td><td>
							<input type="text" name="name" />
							</td>
							<td>资源类型：</td><td>
							<select name="type">
								<option value="query">查询</option>
								<option value="create">创建</option>
								<option value="edit">编辑</option>
								<option value="delete">删除</option>
								<option value="get">获取</option>
								<option value="static">静态页面</option>
							</select></td>
						</tr>
						<tr>
							<td>资源路径：</td><td>
							<input type="text" name="url" />
							</td>
							<td>优先级：</td><td>
							<select name="priority" style="width: 90px;">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
							</select></td>
						</tr>
						<tr>
							<td>资源描述：</td>
							<td colspan="3">
							<input type="text" size="54" name="description"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<%@ include file="../foot.jsp"%>
		</div>
	</body>
</html>
