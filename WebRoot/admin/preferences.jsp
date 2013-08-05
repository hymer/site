<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>系统配置</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/lib-base.jsp"></script>
		<style media="all" type="text/css">
			table {
				border: 1px solid #666;
				border-collapse: collapse;
			}
			th, td {
				text-align: center;
				border: 1px solid #666;
			}
		</style>
		<script type="text/javascript">
			function addItem() {
				var tbody = $("#itemTable tbody");
				var trs = tbody.find("tr");
				var itemCounts = trs.length;

				if(itemCounts > 0) {
					var td = $($(trs[itemCounts-1]).find("td")[0]).text();
					var num = parseInt(td);
					itemCounts = num + 1;
				}

				var tr = $("<tr id='item_tr_" + itemCounts + "'></tr>");
				var td1 = $("<td>" + itemCounts + "</td>");
				var td2 = $("<td><input type='text' size='34' name='key" + itemCounts + "'/></td>");
				var td3 = $("<td><input type='text' size='34' name='value" + itemCounts + "'/></td>");
				var td4 = $("<td><a href='javascript:removeItem(\"item_tr_" + itemCounts + "\");'>删除</a></td>");
				tr.append(td1);
				tr.append(td2);
				tr.append(td3);
				tr.append(td4);
				tbody.append(tr);
			}

			function removeItem(trId) {
				var index = trId.substring(8);
				var tr = $("#" + trId);
				tr.append("<input type='hidden' name='remove" + index + "' value='true'/>");
				tr.hide();
			}
		</script>
	</head>
	<body>
		<%@ include file="left.jsp"%>
		<div id="page">
			<%@ include file="../top.jsp"%>
			<div id="main">
				<input type="button" value="添加配置项" onclick="addItem();" />
				<form action="<%=request.getContextPath()%>/core/preferences.html" method="post">
					<table width="80%" id="itemTable">
						<thead>
							<th style="width: 10%">序号</th>
							<th style="width: 40%">配置项</th>
							<th style="width: 40%">值</th>
							<th style="width: 10%">操作</th>
						</thead>
						<tbody>
							<%
							Map
							<String, String>
								configs = com.hymer.core.Configuration.getConfigMap();
								Iterator
								<String>
									it = configs.keySet().iterator();
									int i=1;
									while(it.hasNext()) {
									String key = it.next();
									String value = configs.get(key);
									%>
									<tr id="item_tr_<%=i%>">
										<td><%=i%></td>
										<td>
										<input type='text' size='34' readonly="readonly" name="key<%=i%>" value='<%=key%>'/>
										</td>
										<td>
										<input type='text' size='34' name="value<%=i%>" value='<%=value%>'/>
										</td>
										<td><a href='javascript:removeItem("item_tr_<%=i%>");'>删除</a></td>
									</tr>
									<%
									i++;
									}
									%>
						</tbody>
					</table>
					<p>
						<input type="submit" value="提交" />
						<input type="reset" value="重置" />
					</p>
				</form>
			</div>
			<%@ include file="../foot.jsp"%>
		</div>
	</body>
</html>
