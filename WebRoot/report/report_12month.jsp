<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>近1年财务报表</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/lib-base.jsp"></script>
	</head>
	<body>
		<%@ include file="left.jsp"%>
		<div id="page">
			<%@ include file="../top.jsp"%>
			<div id="main">
				<script type="text/javascript">
					var chart;
					function buildChart() {
						doGetAjax("<%=request.getContextPath()%>/report/lastoneyear.ajax", '', function(data, textStatus, XMLHttpRequest) {
							newChart(data.data);
						});
					}

					function newChart(datas) {
						var cats = datas.cats;
						var name = datas.name;
						var data = datas.data;
						chart = new Highcharts.Chart({
							credits : {
								enabled : false
							},
							exporting : {
								enabled : false
							},
							chart : {
								renderTo : 'container',
								type : 'column'
							},
							title : {
								text : name
							},
							xAxis : {
								categories : cats
							},
							yAxis : {
								min : 0,
								title : {
									text : '金额（元）'
								}
							},
							legend : {
								layout : 'vertical',
								backgroundColor : '#FFFFFF',
								align : 'left',
								verticalAlign : 'top',
								x : 100,
								y : 70,
								floating : true,
								shadow : true
							},
							tooltip : {
								formatter : function() {
									return this.x + '月 ' + this.series.name + '：' + this.y + '元';
								}
							},
							plotOptions : {
								column : {
									pointPadding : 0.2,
									borderWidth : 0
								}
							},
							series : data
						});

					}


					$(document).ready(function() {
						buildChart();
					});

				</script>
				<div id="container" style="width: 100%; min-height: 400px;"></div>
			</div>
			<%@ include file="../foot.jsp"%>
		</div>
	</body>
</html>
