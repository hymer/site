<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>客户管理</title>
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
						t = new SimpleTable("customerQueryTable", {
							param : 'searchDiv',
							url : '<%=request.getContextPath()%>/customer/query.html',
							sortables : ["companyName", "id"],
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
								id : {
									header : '编号',
									width : '6%'
								},
								companyName : {
									header : '客户名称',
									width : '34%'
								},
								linkman : {
									header : '联系人',
									width : '10%'
								},
								mobile : {
									header : '手机',
									width : '15%'
								},
								fax : {
									header : '电话/传真',
									width : '18%'
								},
								// email : {
								// header : '邮箱',
								// width : '12%'
								// },
								// address : {
								// header : '地址',
								// width : '15%'
								// },
								// description : {
								// header : '备注',
								// width : '10%'
								// },
								ops : {
									header : '备注',
									width : '14%'
								}
							},
							formatters : {
								ops : function(v, obj) {
									var html = '';
									html += "<a href='javascript:doEdit(" + obj.id + ");'>修改</a>";
									var detail = "<table>";
									detail += "<tr><td>Email：</td><td>" + obj.email + "</td></tr>";
									detail += "<tr><td>地址：</td><td>" + obj.address + "</td></tr>";
									detail += "<tr><td>备注：</td><td>" + obj.description + "</td></tr>";
									detail += "</table>";
									html += "&nbsp;&nbsp;<span class='tips' title='" + detail + "'>其他信息</span>";
									return html;
								}
							},
							listeners : {
								afterload : function(data) {
									$(".tips").tooltip({
										// place tooltip on the right edge
										position : "left center",
										// a little tweaking of the position
										// offset : [-2, 10],
										// use the built-in fadeIn/fadeOut effect
										effect : "fade",
										// custom opacity setting
										opacity : 0.9
									});
									return true;
								}
							},
							info : {
								//pageSizeSelect:'', //不显示此控件
								pageSizeSelect : '显示{0}条记录',
								pagingInfo : '目前显示{0}-{1}条记录，共计：{2}'
							}
						});

						$('#customer_add_form_div').dialog({
							draggable : true,
							title : '添加/修改',
							width : "auto",
							height : "auto",
							modal : true,
							// position:'center', 'left', 'right', 'top', 'bottom'.  or [350,100]
							resizable : false,
							autoOpen : false,
							// show:'slide',
							buttons : {
								"OK" : function() {
									ajaxSubmitForm("customer_add_form", editCallback);
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
						doGetAjax("<%=request.getContextPath()%>/customer/" + id + ".html", '', function(data, textStatus, XMLHttpRequest) {
							fillForm("customer_add_form", data);
							$("#customer_add_form_div").dialog("open");
						});
					}
				</script>
				<div id="searchDiv" style="width: 90%;">
					<table width="100%">
						<tr>
							<td width="20%">客户名称：</td><td width="30%">
							<input name="companyName" />
							</td>
							<td width="20%">联系人：</td><td width="30%">
							<input name="linkman" />
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
							document.forms['customer_add_form'].reset();
							var dialog = $('#customer_add_form_div').dialog("open");
						}

						function removeResources() {
							var rids = t.getSelected();
							if(rids.length >= 1) {
								showConfirm("确认要删除选中的信息吗？", function() {
									doPostAjax("<%=request.getContextPath()%>/customer/delete.html", "ids=" + rids, function(resp) {
										showMsg(resp);
										t.doSearch();
									});
								});
							} else {
								showMsg("<font color='red'>请至少选择一行记录!</font>");
							}
						}
					</script>
					<a href="javascript:showAddForm();">添加新客户</a>
					&nbsp;&nbsp; <a href="javascript:removeResources();">删除选中信息</a>
					<br />
					<br/>
				</div>
				<div id="tableDiv" style="width: 100%;">
					<table id="customerQueryTable"></table>
				</div>
			</div>
			<div id="customer_add_form_div" style="display: none;">
				<form id="customer_add_form" action="<%=request.getContextPath()%>/customer/edit.html"  method="post">
					<input type="text" style="display: none;" name="id" />
					<table>
						<tr>
							<td>客户名称：</td><td colspan="3">
							<input type="text" name="companyName" size="54"/>
							</td>
						</tr>
						<tr>
							<td>地址：</td>
							<td colspan="3">
							<input type="text" size="54" name="address"/>
							</td>
						</tr>
						<tr>
							<td>联系人：</td><td>
							<input type="text" name="linkman"/>
							</td>
							<td>手机：</td>
							<td>
							<input type="text" name="mobile"/>
							</td>
						</tr>
						<tr>
							<td>邮箱：</td><td>
							<input type="text" name="email"/>
							</td>
							<td>电话/传真：</td>
							<td>
							<input type="text" name="fax"/>
							</td>
						</tr>
						<tr>
							<td>备注：</td>
							<td colspan="3">							<textarea name="description" cols="55" rows="6"></textarea></td>
						</tr>
					</table>
				</form>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
