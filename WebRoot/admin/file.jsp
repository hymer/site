<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>文件管理</title>
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
						t = new SimpleTable("fileQueryTable", {
							pageSize : 10,
							param : 'searchDiv',
							url : '<%=request.getContextPath()%>/file/query.ajax',
							sortables : ["fileName", "fileType", "createTime", "size"],
							pagingOptions : {
								first : true,
								end : true,
								go : true,
								firstHtml : '<a href="#">首页</a>',
								lastHtml : '<a href="#">上一页</a>',
								nextHtml : '<a href="#">下一页</a>',
								endHtml : '<a href="#">尾页</a>'
							},
							// checkMode : 'single',
							// scrollX : true,
							columns : {
								checkbox : {
									width : '3%'
								},
								fileName : {
									header : '文件名',
									width : '22%'
								},
								size : {
									header : '大小',
									width : '7%'
								},
								attachmentType : {
									header : '类型',
									width : '7%'
								},
								realPath : {
									header : '真实路径',
									width : '30%'
								},
								userName : {
									header : "上传者",
									width : '12%'
								},
								createTime : {
									header : "上传时间",
									width : '13%'
								},
								ops : {
									header : '操作',
									width : '6%'
								}
							},
							formatters : {
								size : function(v, obj) {
									if(v > 1024000) {
										return parseInt(v / 1024000) + "M";
									} else if(v > 1023) {
										return parseInt(v / 1024) + "K";
									} else {
										return v;
									}
								},
								ops : function(v, obj) {
									var html = '';
									html += "<a href='javascript:doDel(" + obj.id + ");'>删除</a>";
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
					function doDel(id) {
						showMsg("待实现！id=" + id);
					}
				</script>
				<div id="searchDiv" style="width: 90%;">
					<table width="100%">
						<tr>
							<td width="20%">文件名：</td><td width="30%">
							<input name="fileName" />
							</td>
							<td width="20%">类型：</td><td width="30%">
							<input name="attachmentType" />
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
				<div id="tableDiv" style="width: 100%;">
					<table id="fileQueryTable"></table>
				</div>
			</div>
			<%@ include file="../foot.jsp"%>
		</div>
	</body>
</html>
