<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>产品/物料管理</title>
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
						t = new SimpleTable("itemQueryTable", {
							param : 'searchDiv',
							url : '<%=request.getContextPath()%>/item/query.html',
							sortables : ["itemName", "itemCode"],
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
								checkbox : {
									width : '2%'
								},
								itemCode : {
									header : '产品编码',
									width : '18%'
								},
								itemName : {
									header : '产品名称',
									width : '28%'
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
									width : '18%'
								},
								ops : {
									header : '操作',
									width : '12%'
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
						$('#item_add_form_div').dialog({
							draggable : true,
							title : '添加/修改产品/物料',
							width : 620,
							modal : true,
							// position:'center', 'left', 'right', 'top', 'bottom'.  or [350,100]
							resizable : false,
							autoOpen : false,
							buttons : {
								"OK" : function() {
									ajaxSubmitForm("item_add_form", editCallback);
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
						doGetAjax("<%=request.getContextPath()%>/item/" + id + ".html", '', function(data, textStatus, XMLHttpRequest) {
							fillForm("item_add_form", data);
							$("#item_add_form_div").dialog("open");
						});
					}
				</script>
				<div id="searchDiv" style="width: 90%;">
					<table width="100%">
						<tr>
							<td width="20%">产品编码：</td><td width="30%">
							<input name="itemCode" />
							</td>
							<td width="20%">产品名称：</td><td width="30%">
							<input name="itemName" />
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
				<div>
					<script type="text/javascript">
						function showAddForm() {
							document.forms['item_add_form'].reset();
							var dialog = $('#item_add_form_div').dialog("open");
						}

						function removeItems() {
							var rids = t.getSelected();
							if(rids.length >= 1) {
								showConfirm("确认要删除选中的产品/物料吗？", function() {
									doPostAjax("<%=request.getContextPath()%>/item/delete.html", "ids=" + rids, function(resp) {
										showMsg(resp);
										t.doSearch();
									});
								});
							} else {
								showMsg("<font color='red'>请至少选择一行记录!</font>");
							}
						}
					</script>
					<a href="javascript:showAddForm();">添加新产品/物料</a>
					&nbsp;&nbsp; <a href="javascript:removeItems();">删除选中产品/物料</a>
					<br />
					<br/>
				</div>
				<div id="tableDiv" style="width: 100%;">
					<table id="itemQueryTable"></table>
				</div>
			</div>
			<div id="item_add_form_div" style="display: none;">
				<form id="item_add_form" action="<%=request.getContextPath()%>/item/edit.html"  method="post">
					<input type="text" style="display: none;" name="id" />
					<table>
						<tr>
							<td>产品编码：</td>
							<td>
							<input type="text" name="itemCode" />
							</td>
							<td>产品名称：</td><td>
							<input type="text" name="itemName" />
							</td>
						</tr>
						<tr>
							<td>规格：</td>
							<td>
							<input type="text" name="itemStandard" />
							</td>
							<td>单位：</td><td>
							<input type="text" name="itemUnit" />
							</td>
						</tr>
						<tr>
							<td>单价：</td>
							<td>
							<input type="text" name="itemPrice" />
							</td>
							<td>备注：</td><td>
							<input type="text" name="itemRemark" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
